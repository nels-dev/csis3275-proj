package csis3275.project.seasell.account.repository;

import csis3275.project.seasell.account.model.DepositRequest;
import csis3275.project.seasell.account.model.RequestStatus;
import csis3275.project.seasell.user.model.AppUser;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositRequestRepository extends JpaRepository<DepositRequest, Long> {

    List<DepositRequest> findAllByCreatedByAndStatusOrderByCreatedAtDesc(AppUser createdBy,
            RequestStatus requestStatus);

    List<DepositRequest> findAllByStatusOrderByCreatedAtDesc(RequestStatus status);
}
