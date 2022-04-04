package com.example.demo.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Table(name = "container")
@AllArgsConstructor
@NoArgsConstructor
@Component
@Entity
public class Container {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int id;

    @NotNull
    @Column(name = "name", unique = true, length = 100)
    String containerName;

    @NotNull
    @Column(name = "center_point", nullable = false, length = 100)
    String containerCenterPoint;

    @NotNull
    @Column(name = "shape", nullable = false, length = 50)
    String containerShape;

    @Column(name = "opposite_point", nullable = false, length = 100)
    String containerOppositePoint;

    @Column(name = "radius", length = 50)
    String containerRadius;

    @Column(name = "polygon_points")
    String containerPolygonPoints;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "floor_id")
    Floor floor;

    public Container fromJsonToContainer(String json) {
        Container container = new Container();
        try {
            container = new ObjectMapper().readValue(json, new TypeReference<Container>(){});
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not convert JSON String to a Container object", e);
        }
        return container;
    }
}
