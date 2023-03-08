package csis3275.project.seasell.account.service;

import csis3275.project.seasell.account.dto.DepositRequestDto;
import csis3275.project.seasell.account.dto.RequestStatusChangeDto;
import csis3275.project.seasell.account.model.BalanceAccount;
import csis3275.project.seasell.account.model.DepositRequest;
import csis3275.project.seasell.account.model.RequestStatus;
import csis3275.project.seasell.account.model.TransactionType;
import csis3275.project.seasell.account.repository.DepositRequestRepository;
import csis3275.project.seasell.common.exception.InvalidStateException;
import csis3275.project.seasell.common.exception.ResourceNotFoundException;
import csis3275.project.seasell.user.model.AppUser;
import csis3275.project.seasell.user.service.CurrentUserService;
import jakarta.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepositRequestService {

    @Autowired
    DepositRequestRepository repository;
    @Autowired
    CurrentUserService currentUserService;
    @Autowired
    JournalEntryService journalEntryService;

    public List<DepositRequestDto> getUserDepositRequests(RequestStatus status) {
        AppUser currentUser = currentUserService.getCurrentUser();
        return repository.findAllByCreatedByAndStatusOrderByCreatedAtDesc(currentUser, status).stream().map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<DepositRequestDto> getAllDepositRequestsForAdmin(RequestStatus status) {
        return repository.findAllByStatusOrderByCreatedAtDesc(status).stream().map(this::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void changeStatus(long id, RequestStatusChangeDto dto) {
        DepositRequest request = repository.findById(id).orElseThrow(ResourceNotFoundException::new);
        // Check current status
        if (request.getStatus() != RequestStatus.PENDING) {
            throw new InvalidStateException();
        }

        if (dto.getStatus() == RequestStatus.REJECTED) {
            reject(request, dto.getRejectReason());
        } else if (dto.getStatus() == RequestStatus.FULFILLED) {
            fulfill(request);
        }
        repository.save(request);
    }

    private void fulfill(DepositRequest request) {
        request.setStatus(RequestStatus.FULFILLED);
        BalanceAccount account = request.getCreatedBy().getBalanceAccount();
        journalEntryService.post(account, request.getAmount(), TransactionType.DEPOSIT_FULFILLED, "Client deposit");
    }

    private void reject(DepositRequest request, String rejectReason) {
        request.setStatus(RequestStatus.REJECTED);
        request.setRejectReason(rejectReason);
    }

    public void addDepositRequest(DepositRequestDto dto) {
        repository.save(toEntity(dto));
    }

    private DepositRequest toEntity(DepositRequestDto dto) {

        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setDepositDate(dto.getDepositDate());
        depositRequest.setCreatedBy(currentUserService.getCurrentUser());
        depositRequest.setCreatedAt(OffsetDateTime.now());
        depositRequest.setBankReference(dto.getBankReference());
        depositRequest.setAmount(dto.getAmount());
        depositRequest.setStatus(RequestStatus.PENDING);
        return depositRequest;
    }

    private DepositRequestDto toDto(DepositRequest depositRequest) {
        DepositRequestDto dto = new DepositRequestDto();
        dto.setAmount(depositRequest.getAmount());
        dto.setDepositDate(depositRequest.getDepositDate());
        dto.setBankReference(depositRequest.getBankReference());
        dto.setCreatedAt(depositRequest.getCreatedAt());
        dto.setId(depositRequest.getId());
        dto.setCreatedByEmail(depositRequest.getCreatedBy().getEmail());
        dto.setCreatedById(depositRequest.getCreatedBy().getId());
        dto.setStatus(depositRequest.getStatus());
        dto.setRejectReason(depositRequest.getRejectReason());
        return dto;

    }
}
