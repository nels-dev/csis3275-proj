package csis3275.project.seasell.order.model;

import csis3275.project.seasell.product.model.Product;
import csis3275.project.seasell.user.model.AppUser;
import jakarta.persistence.*;
import java.time.OffsetDateTime;
import lombok.Data;

@Entity
@Data
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private AppUser buyer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private OffsetDateTime ordertime;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    private String shippmentReference;
}
