package csis3275.project.seasell.product;

import csis3275.project.seasell.product.dto.ProductDto;
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

    @GetMapping("/products")
    public List<ProductDto> getUserProducts() {
        return productService.getUserProducts();
    }

    @GetMapping("/products/{status}")
    public List<ProductDto> getUserProductsByStatus(@PathVariable("status") String status) {
        return productService.getUserProductsByStatus(status);
    }

    @PutMapping("/products/{id}/status")
    public ResponseEntity<?> updateProductStatusToOrdered(@RequestParam ProductStatus status,
            @PathVariable("id") int id) {
        productService.updateProductStatus(id, status);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody ProductDto updatedProduct) {
        productService.updateProduct(id, updatedProduct);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/products")
    public ResponseEntity<?> saveProduct(@RequestParam("name") String name,
            @RequestParam("description") String description, @RequestParam("condition") String condition,
            @RequestParam("price") Double price, @RequestParam("images") MultipartFile file) throws IOException {
        productService.addProduct(name, description, price, condition, file);
        return ResponseEntity.status(201).build();
    }
}
