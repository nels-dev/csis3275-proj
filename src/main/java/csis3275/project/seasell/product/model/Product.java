package csis3275.project.seasell.product.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
}
