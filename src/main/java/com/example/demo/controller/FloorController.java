package com.example.demo.controller;

import com.example.demo.model.Floor;
import com.example.demo.repository.FloorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FloorController {

    @Autowired
    private FloorDAO floorDAO;

    @GetMapping("/getFloor")
    public Floor getFloor(@RequestParam(value = "id") Integer id) {
        return floorDAO.getFloorById(id);
    }

    @GetMapping("/getAllFloors")
    public List<Floor> getAllFloors() {
        return floorDAO.getAllFloors();
    }

    @DeleteMapping("/deleteFloor")
    public String deleteFloor(@RequestParam(value = "id") Integer id) {
        try {
            floorDAO.deleteFloor(id);
        } catch (EmptyResultDataAccessException e) {
            return "No floor with id " + id.toString() + " exists.";
        } catch (DataIntegrityViolationException e) {
            return "Error, failed to delete floor: " + e.getRootCause().getMessage();
        } catch (Exception e) {
            return "Error, failed to delete floor " + e.getMessage();
        }
        return "Deleted floor successfully";
    }
}
