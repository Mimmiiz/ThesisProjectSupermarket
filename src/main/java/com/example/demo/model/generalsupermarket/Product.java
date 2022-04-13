package com.example.demo.model.generalsupermarket;

import com.example.demo.model.generalsupermarket.Supplier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
@Builder
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "grp_id")
    private int id;

    @NotNull
    @Size(min = 14, max = 14)
    @Column(unique = true, nullable = false)
    private String gtin14;

    @NotNull
    @Size(min = 12, max = 12)
    @Column(unique = true, nullable = false)
    private String gtin12;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(length = 100, nullable = false)
    private String brand;

    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "product_name", length = 255, nullable = false)
    private String productName;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
}