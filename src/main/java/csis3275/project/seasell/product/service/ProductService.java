package csis3275.project.seasell.product.service;

import csis3275.project.seasell.common.exception.ResourceNotFoundException;
import csis3275.project.seasell.common.service.FileService;
import csis3275.project.seasell.product.dto.ProductDto;
import csis3275.project.seasell.product.model.Product;
import csis3275.project.seasell.product.model.ProductImage;
import csis3275.project.seasell.product.model.ProductStatus;
import csis3275.project.seasell.product.repository.ProductRepository;
import csis3275.project.seasell.user.model.AppUser;
import csis3275.project.seasell.user.service.CurrentUserService;
import java.io.IOException;
import java.util.ArrayList;
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

    public Product updateProductStatus(int id, ProductStatus newStatus) {
        Product product = productRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        product.setStatus(newStatus);
        return productRepository.save(product);
    }

    public void addProduct(String name, String description, Double price, String condition, MultipartFile image ) throws IOException {
        // TODO: save product details and images in service
        AppUser seller = currentUserService.getCurrentUser();
        Product prod = new Product();

        prod.setName(name);
        prod.setDescription(description);
        prod.setPrice(price);
        prod.setCondition(condition);
        prod.setStatus(ProductStatus.LISTED);
        prod.setSeller(seller);
//        List<ProductImage> productImages = new ArrayList<>();


        String ext = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf(".") + 1);
        String fileName = fileService.store(image.getBytes(), ext);
        ProductImage productImage = new ProductImage();
        productImage.setPath(fileName);
        productImage.setProduct(prod);
        prod.setImages(List.of(productImage));
        productRepository.save(prod);

        log.info("Saved file name: " + fileName);
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

    public Product getProductData(int id) {
        return productRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
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
