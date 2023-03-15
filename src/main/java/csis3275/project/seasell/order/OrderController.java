package csis3275.project.seasell.order;

import csis3275.project.seasell.order.dto.CreateOrderDto;
import csis3275.project.seasell.order.dto.OrderDto;
import csis3275.project.seasell.order.model.Order;
import csis3275.project.seasell.order.repository.OrderRepository;
import csis3275.project.seasell.order.service.OrderService;
import csis3275.project.seasell.product.model.ProductStatus;
import csis3275.project.seasell.product.service.ProductService;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderDto dto) throws IOException {
        orderService.addOrder(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping
    public ResponseEntity<Order> UpdateOrder(@RequestParam int orderId, @RequestParam String shippmentReference,
            @RequestParam ProductStatus status) throws IOException {
        orderService.updateShipmentReferenceInOrder(orderId, shippmentReference);
        productService.updateProductStatus(orderService.getOrder(orderId).getProduct().getId(), status);
        return ResponseEntity.status(201).build();
    }

    // should be like that
    @GetMapping
    public List<OrderDto> getOrders() {
        return orderService.getOrders();
    }
}
