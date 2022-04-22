package com.example.demo.controller;

import com.example.demo.model.localsupermarket.Floor;
import com.example.demo.repository.localsupermarket.FloorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Transactional("localSupermarketTransactionManager")
@RestController
public class FloorController {

    @Autowired
    private FloorDAO floorDAO;

    @GetMapping("/getFloor")
    public Floor getFloor(@RequestParam(value = "id") Integer id) {
        return floorDAO.getFloorById(id);
    }

    @GetMapping("/getFloorByFloorNumber")
    public Floor getFloor(@RequestParam(value = "id") String floorNumber) {
        return floorDAO.getFloorByFloorNumber(floorNumber);
    }

    @GetMapping("/getAllFloors")
    public List<Floor> getAllFloors() {
        return floorDAO.getAllFloors();
    }

    @DeleteMapping("/deleteFloorById")
    public String deleteFloor(@RequestParam(value = "id") Integer id) {
        try {
            floorDAO.deleteFloorById(id);
        } catch (EmptyResultDataAccessException e) {
            return "No floor with id " + id.toString() + " exists.";
        } catch (DataIntegrityViolationException e) {
            return "Error, failed to delete floor: " + e.getRootCause().getMessage();
        } catch (Exception e) {
            return "Error, failed to delete floor " + e.getMessage();
        }
        return "Deleted floor successfully";
    }

    @DeleteMapping("/deleteFloorByFloorNumber")
    public String deleteFloorByFloorNumber(@RequestParam(value = "id") String floorNumber) {
        try {
            floorDAO.deleteFloorByFloorNumber(floorNumber);
        } catch (EmptyResultDataAccessException e) {
            return "No floor with floor number " + floorNumber.toString() + " exists.";
        } catch (DataIntegrityViolationException e) {
            return "Error, failed to delete floor: " + e.getRootCause().getMessage();
        } catch (Exception e) {
            return "Error, failed to delete floor " + e.getMessage();
        }
        return "Deleted floor successfully";
    }

    @PostMapping("/insertFloor")
    public String insertFloor(@RequestBody Floor floor) {
        try {
            floorDAO.saveFloor(floor);
        } catch (DataIntegrityViolationException e) {
            return "Error, failed to insert floor: " + e.getRootCause().getMessage();
        } catch (Exception e) {
            return "Error, failed to insert floor: " + e.getMessage();
        }
        return "Successfully inserted floor.";
    }
}
