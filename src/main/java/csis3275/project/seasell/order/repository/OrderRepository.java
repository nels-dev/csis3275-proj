package csis3275.project.seasell.order.repository;

import csis3275.project.seasell.order.model.Order;
import csis3275.project.seasell.order.model.OrderStatus;
import csis3275.project.seasell.user.model.AppUser;
import java.util.List;
import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByBuyerOrderByOrdertimeDesc(AppUser appUser);

    Optional<Order> findByProduct_id(int productId);

    List<Order> findByStatusAndProduct_Id(OrderStatus status, int productId);
}
