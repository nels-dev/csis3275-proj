package csis3275.project.seasell.order.dto;

import csis3275.project.seasell.order.model.OrderStatus;
import lombok.Data;

@Data
public class OrderStatusUpdateDto {
    OrderStatus status;
    private String shipmentReference;
}
