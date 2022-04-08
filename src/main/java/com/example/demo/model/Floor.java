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
@Table(name = "floor")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
@Entity
public class Floor {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int id;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "shape", nullable = false, length = 50)
    String shape;

    @Column(name = "polygon_points")
    String polygonPoints;

    @NotNull
    @Column(name = "center_point", length = 100, nullable = false)
    String centerPoint;

    @NotNull
    @Column(name = "opposite_point", length = 100, nullable = false)
    String oppositePoint;

    @NotNull
    @Column(name = "floor_number", length = 10, unique = true, nullable = false)
    String floorNumber;
}
