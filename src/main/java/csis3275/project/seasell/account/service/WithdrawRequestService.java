package csis3275.project.seasell.account.service;

import csis3275.project.seasell.account.dto.RequestStatusChangeDto;
import csis3275.project.seasell.account.dto.WithdrawalRequestDto;
import csis3275.project.seasell.account.model.BalanceAccount;
import csis3275.project.seasell.account.model.RequestStatus;
import csis3275.project.seasell.account.model.TransactionType;
import csis3275.project.seasell.account.model.WithdrawalRequest;
import csis3275.project.seasell.account.repository.WithdrawalRequestRepository;
import csis3275.project.seasell.common.exception.InsufficientBalanceException;
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
public class WithdrawRequestService {

    @Autowired
    WithdrawalRequestRepository repository;
    @Autowired
    CurrentUserService currentUserService;
    @Autowired
    JournalEntryService journalEntryService;

    public List<WithdrawalRequestDto> getUserWithdrawalRequests(RequestStatus status) {
        AppUser currentUser = currentUserService.getCurrentUser();
        return repository.findAllByCreatedByAndStatusOrderByCreatedAtDesc(currentUser, status).stream().map(this::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void addWithdrawalRequest(WithdrawalRequestDto dto) {
        BalanceAccount account = currentUserService.getCurrentUser().getBalanceAccount();
        if (account.getAvailableBalance().compareTo(dto.getAmount()) < 0) {
            throw new InsufficientBalanceException();
        }
        journalEntryService.post(account, dto.getAmount(), TransactionType.WITHDRAWAL_REQUESTED,
                "Account balance withdrawal submitted by client");
        repository.save(toEntity(dto));
    }

    @Transactional
    public void changeStatus(long id, RequestStatusChangeDto dto) {
        WithdrawalRequest request = repository.findById(id).orElseThrow(ResourceNotFoundException::new);
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

    private void fulfill(WithdrawalRequest request) {
        request.setStatus(RequestStatus.FULFILLED);
    }

    private void reject(WithdrawalRequest request, String rejectReason) {
        request.setStatus(RequestStatus.REJECTED);
        request.setRejectReason(rejectReason);
        BalanceAccount account = request.getCreatedBy().getBalanceAccount();
        journalEntryService.post(account, request.getAmount(), TransactionType.WITHDRAWAL_REJECTED,
                "Client withdrawal rejected.");
    }

    public List<WithdrawalRequestDto> getAllWithdrawalRequestsForAdmin(RequestStatus status) {
        return repository.findAllByStatusOrderByCreatedAtDesc(status).stream().map(this::toDto)
                .collect(Collectors.toList());
    }

    private WithdrawalRequest toEntity(WithdrawalRequestDto dto) {
        WithdrawalRequest withdrawalRequest = new WithdrawalRequest();
        withdrawalRequest.setAmount(dto.getAmount());
        withdrawalRequest.setCreatedAt(OffsetDateTime.now());
        withdrawalRequest.setStatus(RequestStatus.PENDING);
        withdrawalRequest.setCreatedBy(currentUserService.getCurrentUser());
        withdrawalRequest.setBankAccountNumber(dto.getBankAccountNumber());
        withdrawalRequest.setBankTransitNumber(dto.getBankTransitNumber());
        withdrawalRequest.setBankInstitutionNumber(dto.getBankInstitutionNumber());
        withdrawalRequest.setBeneficiaryName(dto.getBeneficiaryName());
        return withdrawalRequest;
    }

    private WithdrawalRequestDto toDto(WithdrawalRequest withdrawalRequest) {
        WithdrawalRequestDto dto = new WithdrawalRequestDto();
        dto.setAmount(withdrawalRequest.getAmount());
        dto.setBankAccountNumber(withdrawalRequest.getBankAccountNumber());
        dto.setBankInstitutionNumber(withdrawalRequest.getBankInstitutionNumber());
        dto.setBankTransitNumber(withdrawalRequest.getBankTransitNumber());
        dto.setBeneficiaryName(withdrawalRequest.getBeneficiaryName());
        dto.setCreatedAt(withdrawalRequest.getCreatedAt());
        dto.setId(withdrawalRequest.getId());
        dto.setCreatedByEmail(withdrawalRequest.getCreatedBy().getEmail());
        dto.setCreatedById(withdrawalRequest.getCreatedBy().getId());
        dto.setStatus(withdrawalRequest.getStatus());
        dto.setRejectReason(withdrawalRequest.getRejectReason());
        return dto;

    }
}
