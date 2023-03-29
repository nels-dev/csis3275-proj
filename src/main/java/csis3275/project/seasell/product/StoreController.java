package csis3275.project.seasell.product;

import csis3275.project.seasell.order.dto.OrderDto;
import csis3275.project.seasell.order.model.OrderStatus;
import csis3275.project.seasell.order.service.OrderService;
import csis3275.project.seasell.product.dto.ProductDto;
import csis3275.project.seasell.product.dto.ProductStatusUpdateDto;
import csis3275.project.seasell.product.model.ProductStatus;
import csis3275.project.seasell.product.service.ProductService;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/client/store")
@Slf4j
public class StoreController {

    @Autowired
    ProductService productService;
    @Autowired
    OrderService orderService;

    @GetMapping("/products")
    public List<ProductDto> getUserProductsByStatus(
            @RequestParam(value = "status", required = false) ProductStatus status) {
        return productService.getUserProductsByStatus(status);
    }

    @PutMapping("/products/{id}/status")
    public ResponseEntity<?> updateProductStatusToOrdered(@RequestBody ProductStatusUpdateDto dto,
            @PathVariable("id") int id) {
        productService.updateProductStatus(id, dto.getStatus());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody ProductDto updatedProduct) {
        productService.updateProduct(id, updatedProduct);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/products")
    public ResponseEntity<?> saveProduct(@RequestParam("name") String name,
            @RequestParam("description") String description, @RequestParam("condition") String condition,
            @RequestParam("price") Double price, @RequestParam("images") MultipartFile file) throws IOException {
        productService.addProduct(name, description, price, condition, file);
        return ResponseEntity.status(201).build();
    }

    /**
     * Retrieve a specific order given product id and status. Only one single order will be returned
     *
     * @param productId
     * @param status
     *
     * @return
     */
    @GetMapping("/order")
    public OrderDto getOrdersByProduct(@RequestParam int productId, @RequestParam OrderStatus status) {
        return orderService.findByStatusAndProduct_Id(status, productId);
    }

}
