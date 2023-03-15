package csis3275.project.seasell.order.dto;

import csis3275.project.seasell.order.model.OrderStatus;
import csis3275.project.seasell.product.model.Product;
import csis3275.project.seasell.user.model.AppUser;
import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class OrderDto {
    private int id;

    private String productName;

    private OffsetDateTime orderTime;

    private OrderStatus status;

    private String shipmentReference;
}
