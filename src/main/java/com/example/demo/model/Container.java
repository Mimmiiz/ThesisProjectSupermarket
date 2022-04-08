package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Table(name = "container")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
@Entity
public class Container {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int id;

    @NotNull
    @Column(name = "name", unique = true, length = 100)
    String name;

    @NotNull
    @Column(name = "center_point", nullable = false, length = 100)
    String centerPoint;

    @NotNull
    @Column(name = "shape", nullable = false, length = 50)
    String shape;

    @Column(name = "opposite_point", nullable = false, length = 100)
    String oppositePoint;

    @Column(name = "radius", length = 50)
    String containerRadius;

    @Column(name = "polygon_points")
    String polygonPoints;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "floor_id")
    Floor floor;
}
