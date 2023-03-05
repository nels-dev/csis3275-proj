package csis3275.project.seasell.order.repository;

import csis3275.project.seasell.order.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {

}
