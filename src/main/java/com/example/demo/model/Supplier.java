package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Table(name = "supplier")
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
}
