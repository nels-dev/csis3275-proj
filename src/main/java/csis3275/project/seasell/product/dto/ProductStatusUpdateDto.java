package csis3275.project.seasell.product.dto;

import csis3275.project.seasell.product.model.ProductStatus;
import lombok.Data;

@Data
public class ProductStatusUpdateDto {
    ProductStatus status;
}
