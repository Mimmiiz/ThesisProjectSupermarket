package com.example.demo.dto;

import com.example.demo.model.localsupermarket.Floor;
import lombok.Data;

@Data
public class FloorDTO {
    int id;
    private String shape;
    private String polygonPoints;
    private String centerPoint;
    private String oppositePoint;
    private String floorNumber;

    public FloorDTO(Floor floor) {
        id = floor.getId();
        shape = floor.getShape();
        polygonPoints = floor.getPolygonPoints();
        centerPoint = floor.getCenterPoint();
        oppositePoint = floor.getOppositePoint();
        floorNumber = floor.getFloorNumber();
    }
}
