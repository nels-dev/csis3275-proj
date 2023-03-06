package csis3275.project.seasell.order;

import csis3275.project.seasell.account.model.TransactionType;
import csis3275.project.seasell.account.service.BalanceAccountService;
import csis3275.project.seasell.account.service.JournalEntryService;
import csis3275.project.seasell.order.model.Order;
import csis3275.project.seasell.order.repository.OrderRepository;
import csis3275.project.seasell.order.service.OrderService;
import csis3275.project.seasell.product.model.ProductStatus;
import csis3275.project.seasell.product.repository.ProductRepository;
import csis3275.project.seasell.product.service.ProductService;
import java.io.IOException;
import java.math.BigDecimal;
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
    private ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @Autowired
    JournalEntryService journalEntryService;

    @Autowired
    BalanceAccountService balanceAccountService;

    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestParam int productIId) throws IOException {

        productService.updateProductStatus(productIId, ProductStatus.valueOf("ORDERED"));
        journalEntryService.post(balanceAccountService.getAccountData(),
                new BigDecimal(productService.getProductData(productIId).getPrice()),
                TransactionType.valueOf("PURCHASE_CREDIT_HOLD"), "ORDER");
        orderService.addOrder(productIId);
        return ResponseEntity.status(201).build();
    }

}
