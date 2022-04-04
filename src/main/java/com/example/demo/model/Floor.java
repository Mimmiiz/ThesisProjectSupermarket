package com.example.demo.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Table(name = "floor")
@AllArgsConstructor
@NoArgsConstructor
@Component
@Entity
public class Floor {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int id;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "shape", nullable = false, length = 50)
    String floorShape;

    @Column(name = "polygon_points")
    String floorPolygonPoints;

    @NotNull
    @Column(name = "center_point", length = 100, nullable = false)
    String floorCenterPoint;

    @NotNull
    @Column(name = "opposite_point", length = 100, nullable = false)
    String floorOppositePoint;

    @NotNull
    @Column(name = "floor_number", length = 10, unique = true, nullable = false)
    String floorNumber;

    public Floor fromJsonToFloor(String json) {
        Floor floor = new Floor();
        try {
            floor = new ObjectMapper().readValue(json, new TypeReference<Floor>(){});
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not convert JSON String to a Floor object", e);
        }
        return floor;
    }
}
