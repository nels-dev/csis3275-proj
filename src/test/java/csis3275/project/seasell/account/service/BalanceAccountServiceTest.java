package csis3275.project.seasell.account.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import csis3275.project.seasell.account.dto.BalanceAccountDto;
import csis3275.project.seasell.account.model.BalanceAccount;
import csis3275.project.seasell.account.repository.BalanceAccountRepository;
import csis3275.project.seasell.user.model.AppUser;
import csis3275.project.seasell.user.repository.UserRepository;
import csis3275.project.seasell.user.service.CurrentUserService;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@Transactional
@SpringBootTest
class BalanceAccountServiceTest {

    public static final String TEST_USER = "cliff@test.io";
    @MockBean
    CurrentUserService mockCurrentUser;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BalanceAccountRepository repository;
    @Autowired
    BalanceAccountService balanceAccountService;

    @BeforeEach
    public void setupMock() {
        when(mockCurrentUser.getCurrentUser()).thenReturn(userRepository.findByEmail(TEST_USER).get());
    }

    @Test
    void getAccount() {
        BalanceAccountDto account = balanceAccountService.getAccount();
        assertThat(account.getAvailableBalance()).isEqualByComparingTo(BigDecimal.valueOf(1000));
        assertThat(account.getHeldBalance()).isEqualByComparingTo(BigDecimal.valueOf(20));
    }

    @Test
    void createAccountForNewUser() {
        AppUser newUser = new AppUser();
        newUser.setEmail("new-account@test");
        newUser = userRepository.save(newUser);
        balanceAccountService.createAccountForNewUser(newUser);

        BalanceAccount newAccount = repository.findByUser(newUser).orElseThrow();
        assertThat(newAccount.getAvailableBalance()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(newAccount.getHeldBalance()).isEqualByComparingTo(BigDecimal.ZERO);
        assertNotNull(newAccount.getLastUpdate());
    }
}