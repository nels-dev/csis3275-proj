package csis3275.project.seasell.product.service;

import csis3275.project.seasell.common.exception.ResourceNotFoundException;
import csis3275.project.seasell.common.service.FileService;
import csis3275.project.seasell.product.dto.ProductDto;
import csis3275.project.seasell.product.model.Product;
import csis3275.project.seasell.product.model.ProductImage;
import csis3275.project.seasell.product.model.ProductStatus;
import csis3275.project.seasell.product.repository.ProductRepository;
import csis3275.project.seasell.user.service.CurrentUserService;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    FileService fileService;

    @Autowired
    CurrentUserService currentUserService;

    public void addProduct(MultipartFile file, String productName) throws IOException {
        // TODO: save product details and images in service
        String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        String fileName = fileService.store(file.getBytes(), ext);
        log.info("Saved file name: " + fileName);
        log.info("Product name: " + productName);
    }

    public List<ProductDto> getProducts() {
        return productRepository.findAllByStatus(ProductStatus.LISTED).stream().map(this::toProductDto)
                .collect(Collectors.toList());
    }

    public List<ProductDto> getProductsExceptCurrentUser() {
        return productRepository.findAllByStatusAndSellerNot(ProductStatus.LISTED, currentUserService.getCurrentUser())
                .stream().map(this::toProductDto).collect(Collectors.toList());
    }

    public ProductDto getProduct(int id) {
        return toProductDto(productRepository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    private ProductDto toProductDto(Product product) {
        return ProductDto.builder().name(product.getName()).condition(product.getCondition()).price(product.getPrice())
                .id(product.getId()).description(product.getDescription()).status(product.getStatus())
                .images(product.getImages().stream().map(ProductImage::getPath).collect(Collectors.toList())).build();
    }

    public List<ProductDto> getUserProducts() {
        List<Product> myProducts = productRepository.findAllBySeller(currentUserService.getCurrentUser());
        return myProducts.stream().map(this::toProductDto).collect(Collectors.toList());
    }

    public List<ProductDto> getUserProductsByStatus(String status) {
        List<Product> myProducts = productRepository.findAllBySellerAndStatus(currentUserService.getCurrentUser(),
                ProductStatus.valueOf(status));
        return myProducts.stream().map(this::toProductDto).collect(Collectors.toList());
    }

}
