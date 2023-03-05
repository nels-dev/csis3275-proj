package csis3275.project.seasell.order.service;

import java.io.IOException;
import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import csis3275.project.seasell.account.service.JournalEntryService;
import csis3275.project.seasell.common.service.FileService;
import csis3275.project.seasell.order.model.Order;
import csis3275.project.seasell.order.model.OrderStatus;
import csis3275.project.seasell.product.repository.ProductRepository;
import csis3275.project.seasell.product.service.ProductService;
import csis3275.project.seasell.user.model.AppUser;
import csis3275.project.seasell.user.service.CurrentUserService;

@Service
public class OrderService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    FileService fileService;

    @Autowired
    CurrentUserService currentUserService;
    
    @Autowired
    JournalEntryService journalEntryService;

    @Autowired
    ProductService productService;
    public void addOrder(int id) throws IOException {
    	Order order=new Order();
    	OffsetDateTime time=OffsetDateTime.now();
    	time.getYear();
    	order.setBuyer(currentUserService.getCurrentUser());
    	order.setOrdertime(time);
    	order.setProduct(productService.getProductData(id));
    	order.setStatus(OrderStatus.valueOf("ORDERED"));
    }
    
}
