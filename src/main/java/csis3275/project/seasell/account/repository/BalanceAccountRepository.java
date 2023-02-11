package csis3275.project.seasell.account.repository;

import csis3275.project.seasell.account.model.BalanceAccount;
import csis3275.project.seasell.user.model.AppUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceAccountRepository extends JpaRepository<BalanceAccount, Integer> {

    Optional<BalanceAccount> findByUser(AppUser user);
}
