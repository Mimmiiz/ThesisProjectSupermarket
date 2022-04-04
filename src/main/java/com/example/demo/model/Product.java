package com.example.demo.model;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
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

    @NotNull
    @Size(min = 1, max = 100)
    @Column(length = 255, nullable = false)
    private String price;

    @Column(name = "re_order_level")
    private int reOrderLevel;

    @Column(name = "order_quantity")
    private int orderQuantity;

    @Size(min = 1, max = 100)
    @Column(length = 100, name = "location_x")
    private String locationX;

    @Size(min = 1, max = 100)
    @Column(length = 100, name = "location_y")
    private String locationY;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    public List<Product> fromJsonToListOfProducts(String json) throws JsonGenerationException, JsonMappingException, IOException {
        List<Product> products = new ArrayList<Product>();
        try {
            products = new ObjectMapper().readValue(json, new TypeReference<List<Product>>(){});
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not convert JSON String to a List of Product objects", e);
        }
        return products;
    }

    /**
     * Converts a JSON String input to a Product object.
     *
     * @param json The JSON string
     * @return Returns the created Product object;
     */
    public Product fromJsonToProduct(String json) {
        Product product = new Product();
        try {
            product = new ObjectMapper().readValue(json, new TypeReference<Product>(){});
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not convert JSON String to a Product object", e);
        }
        return product;
    }
}
