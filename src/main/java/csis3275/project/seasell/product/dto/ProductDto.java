package csis3275.project.seasell.product.dto;

import java.util.List;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ProductDto {

    String name;
    String description;
    String condition;
    List<String> images;
}
