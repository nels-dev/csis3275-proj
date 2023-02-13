package csis3275.project.seasell.product.dto;

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
    List<String> images;

}
