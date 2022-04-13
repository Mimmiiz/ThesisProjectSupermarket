package com.example.demo.controller;

import com.example.demo.model.localsupermarket.Container;
import com.example.demo.model.localsupermarket.Floor;
import com.example.demo.repository.localsupermarket.ContainerDAO;
import com.example.demo.repository.localsupermarket.FloorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Transactional("localSupermarketTransactionManager")
@RestController
public class ContainerController {

    @Autowired
    private ContainerDAO containerDAO;

    @Autowired
    private FloorDAO floorDAO;

    @GetMapping("/getContainerById")
    public Container getContainerById(@RequestParam(value = "id") Integer id) {
        return containerDAO.getContainerById(id);
    }

    @GetMapping("/getAllContainers")
    public List<Container> getAllContainers () {
        return containerDAO.getAllContainers();
    }

    @GetMapping("/getContainerByName")
    public Container getContainerByName(@RequestParam(value = "id") String name) {
        return containerDAO.getContainerByName(name);
    }

    @PostMapping("/insertContainer")
    public String insertContainer (@RequestParam(value = "floorNumber") String floorNumber, @RequestBody Container container) {
        Floor floor = floorDAO.getFloorByFloorNumber(floorNumber);
        container.setFloor(floor);
        try {
            containerDAO.saveContainer(container);
        } catch (Exception e) {
            return "Error, failed to insert container: " + e.getMessage();
        }
        return "Successfully inserted container.";
    }

    @DeleteMapping("/deleteContainerById")
    public String deleteContainerById(@RequestParam(value = "id") Integer id) {
        try {
            containerDAO.deleteContainerById(id);
        } catch (EmptyResultDataAccessException e) {
            return "No container with id " + id.toString() + " exists.";
        } catch (DataIntegrityViolationException e) {
            return "Error, failed to delete container: " + e.getRootCause().getMessage();
        } catch (Exception e) {
            return "Error, failed to delete container " + e.getMessage();
        }
        return "Deleted container successfully";
    }

    @DeleteMapping("/deleteContainerByName")
    public String deleteContainerByName(@RequestParam(value = "id") String name) {
        try {
            containerDAO.deleteContainerByName(name);
        } catch (EmptyResultDataAccessException e) {
            return "No container with name " + name.toString() + " exists.";
        } catch (DataIntegrityViolationException e) {
            return "Error, failed to delete container: " + e.getRootCause().getMessage();
        } catch (Exception e) {
            return "Error, failed to delete container " + e.getMessage();
        }
        return "Deleted container successfully";
    }
}
