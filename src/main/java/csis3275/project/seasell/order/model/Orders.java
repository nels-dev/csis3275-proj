package csis3275.project.seasell.order.model;

import csis3275.project.seasell.product.model.Product;
import csis3275.project.seasell.user.model.AppUser;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.OffsetDateTime;
import lombok.Data;

@Entity
@Data
public class Orders {

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

    private OrderStatus status;
}
