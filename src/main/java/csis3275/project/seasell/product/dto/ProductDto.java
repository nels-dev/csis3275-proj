package csis3275.project.seasell.product.dto;

import java.util.List;

import csis3275.project.seasell.product.model.ProductStatus;
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

}
