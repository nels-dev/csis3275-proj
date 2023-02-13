package csis3275.project.seasell.product;

import csis3275.project.seasell.product.dto.ProductDto;
import csis3275.project.seasell.product.service.ProductService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}
