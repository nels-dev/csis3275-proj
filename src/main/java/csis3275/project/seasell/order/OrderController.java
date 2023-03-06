package csis3275.project.seasell.order;

import csis3275.project.seasell.order.model.Order;
import csis3275.project.seasell.order.repository.OrderRepository;
import csis3275.project.seasell.order.service.OrderService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client/order")
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestParam int productId) throws IOException {
        orderService.addOrder(productId);
        return ResponseEntity.status(201).build();
    }

}
