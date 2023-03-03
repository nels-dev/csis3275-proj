package csis3275.project.seasell.account.repository;

import csis3275.project.seasell.account.model.RequestStatus;
import csis3275.project.seasell.account.model.WithdrawalRequest;
import csis3275.project.seasell.user.model.AppUser;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawalRequestRepository extends JpaRepository<WithdrawalRequest, Long> {

    List<WithdrawalRequest> findAllByCreatedByAndStatusOrderByCreatedAtDesc(AppUser createdBy,
            RequestStatus requestStatus);

    List<WithdrawalRequest> findAllByStatusOrderByCreatedAtDesc(RequestStatus status);
}
