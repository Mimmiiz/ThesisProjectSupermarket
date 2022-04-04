package com.example.demo.repository;

import com.example.demo.model.Floor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FloorDAO {

    @Autowired
    private FloorRepository floorRepository;

    public Floor getFloorById(Integer id) {
        return floorRepository.findById(id).get();
    }

    public List<Floor> getAllFloors() {
        List<Floor> floors = new ArrayList<>();
        floorRepository.findAll().forEach(floors::add);
        return floors;
    }

    public void deleteFloor(Integer id) {
        floorRepository.deleteById(id);
    }

    public void saveFloor(Floor floor) {
        floorRepository.save(floor);
    }
}
