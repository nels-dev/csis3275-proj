package csis3275.project.seasell.account;

import csis3275.project.seasell.account.dto.BalanceAccountDto;
import csis3275.project.seasell.account.service.BalanceAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client/account")
public class AccountController {

    @Autowired
    BalanceAccountService balanceAccountService;

    @GetMapping
    public BalanceAccountDto getAccount() {
        return balanceAccountService.getAccount();
    }
}
