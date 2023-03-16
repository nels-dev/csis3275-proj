package csis3275.project.seasell.order.dto;

import csis3275.project.seasell.order.model.OrderStatus;
import jakarta.persistence.*;
import java.time.OffsetDateTime;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderDto {
    private int id;

    private String productName;

    private OffsetDateTime orderTime;

    private OrderStatus status;

    private String shipmentReference;

    private String buyerName;

    private String buyerAddress;

    private String buyerEmail;
}
