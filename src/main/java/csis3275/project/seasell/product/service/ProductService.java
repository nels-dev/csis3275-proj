package csis3275.project.seasell.product.service;

import csis3275.project.seasell.common.exception.ResourceNotFoundException;
import csis3275.project.seasell.common.service.FileService;
import csis3275.project.seasell.order.model.Order;
import csis3275.project.seasell.order.model.OrderStatus;
import csis3275.project.seasell.product.dto.ProductDto;
import csis3275.project.seasell.product.model.Product;
import csis3275.project.seasell.product.model.ProductImage;
import csis3275.project.seasell.product.model.ProductStatus;
import csis3275.project.seasell.product.repository.ProductRepository;
import csis3275.project.seasell.user.model.AppUser;
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

    public Product updateProductStatus(int id, ProductStatus newStatus) {
        Product product = productRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        product.setStatus(newStatus);
        return productRepository.save(product);
    }

    public void updateProduct(int id, ProductDto updatedProduct) {
        Product product = productRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
        product.setCondition(updatedProduct.getCondition());
        // product.setImages(updatedProduct.getImages());
        productRepository.save(product);
    }

    // public void addProduct(MultipartFile file, String productName) throws IOException {
    public void addProduct(String name, String description, Double price, String condition, MultipartFile image)
            throws IOException {
        // TODO: save product details and images in service
        AppUser seller = currentUserService.getCurrentUser();
        Product prod = new Product();

        prod.setName(name);
        prod.setDescription(description);
        prod.setPrice(price);
        prod.setCondition(condition);
        prod.setStatus(ProductStatus.LISTED);
        prod.setSeller(seller);
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
                .images(getImageFilePaths(product)).activeOrder(getActiveOrderId(product)).build();
    }

    private Integer getActiveOrderId(Product product) {
        return product.getOrders().stream().filter(order -> order.getStatus() != OrderStatus.CANCELLED).findFirst()
                .map(Order::getId).orElse(null);
    }

    private List<String> getImageFilePaths(Product product) {
        return product.getImages().stream().map(ProductImage::getPath).collect(Collectors.toList());
    }

    public List<ProductDto> getUserProductsByStatus(ProductStatus status) {
        List<Product> myProducts;
        if (status == null) {
            myProducts = productRepository.findAllBySeller(currentUserService.getCurrentUser());
        } else {
            myProducts = productRepository.findAllBySellerAndStatus(currentUserService.getCurrentUser(), status);
        }
        return myProducts.stream().map(this::toProductDto).collect(Collectors.toList());
    }
}
