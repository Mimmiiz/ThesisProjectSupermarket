package com.example.demo.model;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.IOException;

@Data
@Table(name = "supplier")
@AllArgsConstructor
@NoArgsConstructor
@Component
@Entity
public class Supplier {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(length = 100, unique = true, nullable = false)
    String brand;

    @Size(min = 1, max = 255)
    @Column(name = "manufacturer_name")
    String manufacturer;

    @Size(min = 0, max = 255)
    String address;

    @Size(min = 0, max = 255)
    String website;

    public Supplier fromJsonToSupplier(String json) throws JsonGenerationException, JsonMappingException, IOException {
        Supplier supplier;
        try {
            supplier = new ObjectMapper().readValue(json, new TypeReference<Supplier>(){});
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not convert JSON String to Supplier object", e);
        }
        return supplier;
    }
}
