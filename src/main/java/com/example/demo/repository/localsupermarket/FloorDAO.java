package com.example.demo.repository.localsupermarket;

import com.example.demo.model.localsupermarket.Floor;
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

    public Floor getFloorByFloorNumber(String floorNumber) {
        return floorRepository.findByFloorNumber(floorNumber).get(0);
    }

    public List<Floor> getAllFloors() {
        List<Floor> floors = new ArrayList<>();
        floorRepository.findAll().forEach(floors::add);
        return floors;
    }

    public void deleteFloorById(Integer id) {
        floorRepository.deleteById(id);
    }

    public void deleteFloorByFloorNumber(String floorNumber) {
        floorRepository.deleteByFloorNumber(floorNumber);
    }

    public void saveFloor(Floor floor) throws Exception {
        String floorNumber = floor.getFloorNumber();
        List<Floor> floors = new ArrayList<>();
        if(floorNumber == null)
            throw new Exception("Floor number is missing.");
        if(!floorRepository.findByFloorNumber(floorNumber).isEmpty())
            throw new Exception("Floor with floor number " + floorNumber + " already exists.");
        else
            floorRepository.save(floor);
    }
}
