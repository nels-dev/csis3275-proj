package csis3275.project.seasell.account.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import csis3275.project.seasell.account.dto.WithdrawalRequestDto;
import csis3275.project.seasell.account.model.RequestStatus;
import csis3275.project.seasell.user.repository.UserRepository;
import csis3275.project.seasell.user.service.CurrentUserService;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@Transactional
@SpringBootTest
class WithdrawRequestServiceTest {

    public static final String TEST_USER = "cliff@test.io";
    @MockBean
    CurrentUserService mockCurrentUser;
    @Autowired
    UserRepository userRepository;
    @Autowired
    WithdrawRequestService withdrawRequestService;

    @BeforeEach
    public void setupMock() {
        when(mockCurrentUser.getCurrentUser()).thenReturn(userRepository.findByEmail(TEST_USER).get());
    }

    @Test
    public void getUserWithdrawalRequests() {
        List<WithdrawalRequestDto> result = withdrawRequestService.getUserWithdrawalRequests(RequestStatus.PENDING);
        assertThat(result).hasSize(1);
        WithdrawalRequestDto dto = result.get(0);
        assertThat(dto.getAmount()).isEqualByComparingTo(BigDecimal.valueOf(100));
        assertEquals(RequestStatus.PENDING, dto.getStatus());
        assertEquals("82850129", dto.getBankAccountNumber());
        assertEquals("98765", dto.getBankTransitNumber());
        assertEquals("001", dto.getBankInstitutionNumber());
        assertEquals(TEST_USER, dto.getCreatedByEmail());
    }

    @Test
    public void addWithdrawalRequest() {
        WithdrawalRequestDto dto = new WithdrawalRequestDto();
        dto.setBankTransitNumber("55555");
        dto.setBankAccountNumber("10418372");
        dto.setBankInstitutionNumber("005");
        dto.setAmount(new BigDecimal(150));
        withdrawRequestService.addWithdrawalRequest(dto);

        List<WithdrawalRequestDto> result = withdrawRequestService.getUserWithdrawalRequests(RequestStatus.PENDING);
        assertThat(result).hasSize(2);
        // Due to ordering, the first record must be the one just added
        WithdrawalRequestDto newlyAdded = result.get(0);
        assertThat(newlyAdded.getAmount()).isEqualByComparingTo(BigDecimal.valueOf(150));
        assertEquals("10418372", newlyAdded.getBankAccountNumber());
        assertEquals("55555", newlyAdded.getBankTransitNumber());
        assertEquals("005", newlyAdded.getBankInstitutionNumber());
        assertEquals(TEST_USER, newlyAdded.getCreatedByEmail());

        // Account available balance is deducted
        assertThat(userRepository.findByEmail(TEST_USER).get().getBalanceAccount().getAvailableBalance())
                .isEqualByComparingTo(BigDecimal.valueOf(850));
    }
}
