package csis3275.project.seasell.order.service;

import csis3275.project.seasell.account.model.TransactionType;
import csis3275.project.seasell.account.service.BalanceAccountService;
import csis3275.project.seasell.account.service.JournalEntryService;
import csis3275.project.seasell.common.exception.ResourceNotFoundException;
import csis3275.project.seasell.common.service.FileService;
import csis3275.project.seasell.order.dto.CreateOrderDto;
import csis3275.project.seasell.order.dto.OrderDto;
import csis3275.project.seasell.order.model.Order;
import csis3275.project.seasell.order.model.OrderStatus;
import csis3275.project.seasell.order.repository.OrderRepository;
import csis3275.project.seasell.product.dto.ProductDto;
import csis3275.project.seasell.product.model.Product;
import csis3275.project.seasell.product.model.ProductStatus;
import csis3275.project.seasell.product.service.ProductService;
import csis3275.project.seasell.user.model.AppUser;
import csis3275.project.seasell.user.service.CurrentUserService;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    FileService fileService;

    @Autowired
    CurrentUserService currentUserService;

    @Autowired
    JournalEntryService journalEntryService;

    @Autowired
    ProductService productService;

    @Autowired
    BalanceAccountService balanceAccountService;

    public void addOrder(CreateOrderDto dto) throws IOException {
        int productId = dto.getProductId();
        productService.updateProductStatus(productId, ProductStatus.ORDERED);
        journalEntryService.post(balanceAccountService.getAccountData(),
                new BigDecimal(productService.getProductData(productId).getPrice()),
                TransactionType.PURCHASE_CREDIT_HOLD, "ORDER");

        Order order = new Order();
        OffsetDateTime time = OffsetDateTime.now();
        time.getYear();
        order.setBuyer(currentUserService.getCurrentUser());
        order.setOrdertime(time);
        order.setProduct(productService.getProductData(productId));
        order.setStatus(OrderStatus.ORDERED);
        orderRepository.save(order);
    }

    private OrderDto toOrderDto(Order order){
        return  OrderDto.builder().id(order.getId()).productName(order.getProduct().getName())
                .orderTime(order.getOrdertime()).status(order.getStatus()).buyerName(order.getBuyer().getUsername())
                .shipmentReference("Have not provided")
                .buyerAddress(order.getBuyer().getAddress()).buyerEmail(order.getBuyer().getEmail()).build();
    }

    public List<OrderDto> getOrders(){
        return orderRepository.findByBuyerOrderByOrdertimeDesc((currentUserService.getCurrentUser()))
                .stream().map(this::toOrderDto).collect(Collectors.toList());
//        List<OrderDto> orderDtos = new ArrayList<>();
//        List<Order> orders = orderRepository.findByBuyerOrderByOrdertimeDesc(currentUserService.getCurrentUser());
//        for (int i=0; i<orders.size(); i++){
//            Order order = orders.get(i);
//
//            OrderDto orderDto = new OrderDto();
//            orderDto.setId(order.getId());
//            orderDto.setOrderTime(order.getOrdertime());
//            orderDto.setStatus(order.getStatus());
//            orderDto.setProductName(order.getProduct().getName());
//            orderDto.setShipmentReference("");
//            orderDtos.add(orderDto);
//        }
//        return orderDtos;
    }

    public OrderDto getBuyerInfoById(int id) {
        Order order = orderRepository.findByProduct_id(id).orElseThrow(ResourceNotFoundException::new);
        return toOrderDto(order);
//                OrderDto.builder().id(order.getId()).productName(order.getProduct().getName())
//                        .orderTime(order.getOrdertime()).status(order.getStatus()).buyerName(order.getBuyer().getUsername())
//                        .buyerAddress(order.getBuyer().getAddress()).buyerEmail(order.getBuyer().getEmail()).build();
    }}
