package com.example.demo.model.localsupermarket;

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
    private int id;

    @NotNull
    @Column(name = "name", unique = true, length = 100)
    private String name;

    @NotNull
    @Column(name = "center_point", nullable = false, length = 100)
    private String centerPoint;

    @NotNull
    @Column(name = "shape", nullable = false, length = 50)
    private String shape;

    @Column(name = "opposite_point", nullable = false, length = 100)
    private String oppositePoint;

    @Column(name = "radius", length = 50)
    private String containerRadius;

    @Column(name = "polygon_points")
    private String polygonPoints;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "floor_id")
    private Floor floor;
}
