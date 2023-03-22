package csis3275.project.seasell.account.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import csis3275.project.seasell.account.dto.DepositRequestDto;
import csis3275.project.seasell.account.dto.RequestStatusChangeDto;
import csis3275.project.seasell.account.model.RequestStatus;
import csis3275.project.seasell.common.exception.InvalidStateException;
import csis3275.project.seasell.user.repository.UserRepository;
import csis3275.project.seasell.user.service.CurrentUserService;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@Transactional
@SpringBootTest
class DepositRequestServiceTest {

    public static final String TEST_USER = "cliff@test.io";
    @MockBean
    CurrentUserService mockCurrentUser;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DepositRequestService depositRequestService;

    @BeforeEach
    public void setupMock() {
        when(mockCurrentUser.getCurrentUser()).thenReturn(userRepository.findByEmail(TEST_USER).get());
    }

    @Test
    public void getUserDepositRequests() {
        List<DepositRequestDto> result = depositRequestService.getUserDepositRequests(RequestStatus.PENDING);
        assertThat(result).hasSize(1);
        DepositRequestDto dto = result.get(0);
        assertThat(dto.getAmount()).isEqualByComparingTo(BigDecimal.valueOf(100));
        assertEquals(RequestStatus.PENDING, dto.getStatus());
        assertEquals("R-105102401248", dto.getBankReference());
        assertEquals(TEST_USER, dto.getCreatedByEmail());
    }

    @Test
    public void addDepositRequest() {
        DepositRequestDto dto = new DepositRequestDto();
        dto.setBankReference("BR12938912");
        dto.setDepositDate(LocalDate.of(2022, 2, 20));
        dto.setAmount(new BigDecimal(150));
        depositRequestService.addDepositRequest(dto);

        List<DepositRequestDto> result = depositRequestService.getUserDepositRequests(RequestStatus.PENDING);
        assertThat(result).hasSize(2);
        // Due to ordering, the first record must be the one just added
        DepositRequestDto newlyAdded = result.get(0);
        assertThat(newlyAdded.getAmount()).isEqualByComparingTo(BigDecimal.valueOf(150));
        assertEquals("BR12938912", newlyAdded.getBankReference());
        assertEquals(LocalDate.of(2022, 2, 20), newlyAdded.getDepositDate());
        assertEquals(TEST_USER, newlyAdded.getCreatedByEmail());

        // Account available balance is NOT added because the request is still pending
        assertThat(userRepository.findByEmail(TEST_USER).get().getBalanceAccount().getAvailableBalance())
                .isEqualByComparingTo(BigDecimal.valueOf(1000));
    }

    @Test
    public void changeStatusToFulfill() {
        assertThat(depositRequestService.getUserDepositRequests(RequestStatus.FULFILLED)).hasSize(0);
        RequestStatusChangeDto dto = new RequestStatusChangeDto();
        dto.setStatus(RequestStatus.FULFILLED);
        depositRequestService.changeStatus(1, dto);
        assertThat(depositRequestService.getUserDepositRequests(RequestStatus.FULFILLED)).hasSize(1);
        assertThat(depositRequestService.getUserDepositRequests(RequestStatus.PENDING)).hasSize(0);

        // Account available balance is increased
        assertThat(userRepository.findByEmail(TEST_USER).get().getBalanceAccount().getAvailableBalance())
                .isEqualByComparingTo(BigDecimal.valueOf(1100.00));
    }

    @Test
    public void changeStatusToReject() {
        assertThat(depositRequestService.getUserDepositRequests(RequestStatus.REJECTED)).hasSize(0);
        RequestStatusChangeDto dto = new RequestStatusChangeDto();
        dto.setStatus(RequestStatus.REJECTED);
        depositRequestService.changeStatus(1, dto);
        assertThat(depositRequestService.getUserDepositRequests(RequestStatus.REJECTED)).hasSize(1);
        assertThat(depositRequestService.getUserDepositRequests(RequestStatus.PENDING)).hasSize(0);

        // Account available balance is unchanged
        assertThat(userRepository.findByEmail(TEST_USER).get().getBalanceAccount().getAvailableBalance())
                .isEqualByComparingTo(BigDecimal.valueOf(1000.00));
    }

    @Test
    public void changeStatus_invalidStatus() {
        RequestStatusChangeDto change1 = new RequestStatusChangeDto();
        change1.setStatus(RequestStatus.REJECTED);
        depositRequestService.changeStatus(1, change1);
        RequestStatusChangeDto change2 = new RequestStatusChangeDto();
        change2.setStatus(RequestStatus.FULFILLED);
        assertThrows(InvalidStateException.class, () -> depositRequestService.changeStatus(1, change2));
    }

    @Test
    public void getAllWithdrawalRequestsForAdmin() {
        List<DepositRequestDto> result = depositRequestService.getAllDepositRequestsForAdmin(RequestStatus.PENDING);
        assertThat(result).hasSize(2);
    }
}
