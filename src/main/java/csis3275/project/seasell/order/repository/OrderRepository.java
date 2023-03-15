package csis3275.project.seasell.order.repository;

import csis3275.project.seasell.order.model.Order;
import java.util.List;
import csis3275.project.seasell.user.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByProduct_id(int productid);

//should be like that
    List<Order> findByBuyerOrderByOrdertimeDesc(AppUser appUser);
}
