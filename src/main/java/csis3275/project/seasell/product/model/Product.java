package csis3275.project.seasell.product.model;

import csis3275.project.seasell.order.model.Order;
import csis3275.project.seasell.user.model.AppUser;
import jakarta.persistence.*;
import java.util.List;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String description;

    @Column(name = "product_condition") // condition is a reserved word in mysql
    private String condition;

    @Enumerated(value = EnumType.STRING)
    private ProductStatus status;

    private double price;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private List<ProductImage> images;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private AppUser seller;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    @JsonIgnore
    private List<Order> orders;

}
