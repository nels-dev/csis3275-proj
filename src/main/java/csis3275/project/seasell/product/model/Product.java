package csis3275.project.seasell.product.model;

import csis3275.project.seasell.user.model.AppUser;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

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

    @OneToMany
    @JoinColumn(name = "product_id")
    private List<ProductImage> images;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser seller;


}
