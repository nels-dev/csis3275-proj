package csis3275.project.seasell.product.dto;

import csis3275.project.seasell.product.model.ProductStatus;
import java.util.List;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ProductDto {
    int id;
    String name;
    String description;
    String condition;
    double price;
    ProductStatus status;
    List<String> images;
    Integer activeOrder;
    String sellerName;
    int sellerId;
}
