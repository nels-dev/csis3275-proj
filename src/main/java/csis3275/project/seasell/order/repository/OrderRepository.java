package csis3275.project.seasell.order.repository;

import csis3275.project.seasell.order.model.Order;
import csis3275.project.seasell.user.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByBuyerOrderByOrdertimeDesc(AppUser appUser);
    Optional<Order> findByProduct_id(int productId);
}
