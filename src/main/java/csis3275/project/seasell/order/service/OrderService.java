package csis3275.project.seasell.order.service;

import csis3275.project.seasell.account.model.TransactionType;
import csis3275.project.seasell.account.service.BalanceAccountService;
import csis3275.project.seasell.account.service.JournalEntryService;
import csis3275.project.seasell.common.exception.ResourceNotFoundException;
import csis3275.project.seasell.common.service.FileService;
import csis3275.project.seasell.order.model.Order;
import csis3275.project.seasell.order.model.OrderStatus;
import csis3275.project.seasell.order.repository.OrderRepository;
import csis3275.project.seasell.product.model.ProductStatus;
import csis3275.project.seasell.product.service.ProductService;
import csis3275.project.seasell.user.service.CurrentUserService;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
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

    public void addOrder(int productId) throws IOException {
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

    public void updateShipmentReferenceInOrder(int orderId, String shippmentReference) {
        Order order = orderRepository.findById(orderId).orElseThrow(ResourceNotFoundException::new);
        order.setShippmentReference(shippmentReference);
        orderRepository.save(order);
    }

    public Order getOrder(int id) {
        return orderRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public List<Order> getOrderByProductId(int productId) {
        return orderRepository.findAllByProduct_id(productId);
    }

}
