package csis3275.project.seasell.order.service;

import csis3275.project.seasell.account.model.TransactionType;
import csis3275.project.seasell.account.service.BalanceAccountService;
import csis3275.project.seasell.account.service.JournalEntryService;
import csis3275.project.seasell.common.service.FileService;
import csis3275.project.seasell.order.dto.CreateOrderDto;
import csis3275.project.seasell.order.model.Order;
import csis3275.project.seasell.order.model.OrderStatus;
import csis3275.project.seasell.order.repository.OrderRepository;
import csis3275.project.seasell.product.model.ProductStatus;
import csis3275.project.seasell.product.service.ProductService;
import csis3275.project.seasell.user.service.CurrentUserService;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
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
    }

}
