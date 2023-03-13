package csis3275.project.seasell.order;

import csis3275.project.seasell.order.dto.CreateOrderDto;
import csis3275.project.seasell.order.model.Order;
import csis3275.project.seasell.order.repository.OrderRepository;
import csis3275.project.seasell.order.service.OrderService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client/order")
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderDto dto) throws IOException {
        orderService.addOrder(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
