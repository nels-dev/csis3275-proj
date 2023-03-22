package csis3275.project.seasell.account.service;

import csis3275.project.seasell.account.dto.BalanceAccountDto;
import csis3275.project.seasell.account.model.BalanceAccount;
import csis3275.project.seasell.account.repository.BalanceAccountRepository;
import csis3275.project.seasell.common.exception.ResourceNotFoundException;
import csis3275.project.seasell.user.model.AppUser;
import csis3275.project.seasell.user.service.CurrentUserService;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BalanceAccountService {
    @Autowired
    CurrentUserService currentUserService;
    @Autowired
    BalanceAccountRepository repository;

    public BalanceAccountDto getAccount() {

        return repository.findByUser(currentUserService.getCurrentUser()).map(this::toDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    public void createAccountForNewUser(AppUser user) {
        BalanceAccount balanceAccount = new BalanceAccount();
        balanceAccount.setUser(user);
        balanceAccount.setHeldBalance(BigDecimal.ZERO);
        balanceAccount.setAvailableBalance(BigDecimal.ZERO);
        balanceAccount.setLastUpdate(OffsetDateTime.now());
        repository.save(balanceAccount);
    }

    private BalanceAccountDto toDto(BalanceAccount account) {
        return BalanceAccountDto.builder().availableBalance(account.getAvailableBalance())
                .heldBalance(account.getHeldBalance()).lastUpdate(account.getLastUpdate()).id(account.getId()).build();
    }
}
