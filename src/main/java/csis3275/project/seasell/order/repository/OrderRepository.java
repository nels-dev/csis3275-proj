package csis3275.project.seasell.order.repository;

import csis3275.project.seasell.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
