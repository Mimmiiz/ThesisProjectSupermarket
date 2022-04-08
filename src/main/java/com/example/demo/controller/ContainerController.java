package com.example.demo.controller;

import com.example.demo.model.Container;
import com.example.demo.repository.ContainerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContainerController {

    @Autowired
    private ContainerDAO containerDAO;


    @GetMapping("/getContainer")
    public Container getContainer (@RequestParam(value ="id") Integer id) {
        return containerDAO.getContainerById(id);
    }

    @GetMapping("/getAllContainers")
    public List<Container> getAllContainers () {
        return containerDAO.getAllContainers();
    }

    @PostMapping("/insertContainer")
    public String insertContainer (@RequestBody Container container) {
        try {
            containerDAO.saveContainer(container);
        } catch (Exception e) {
            return "Error, failed to insert container: " + e.getMessage();
        }
        return "Successfully inserted container.";
    }

    @DeleteMapping("/deleteContainer")
    public String deleteContainer(@RequestParam(value = "id") Integer id) {
        try {
            containerDAO.deleteContainer(id);
        } catch (EmptyResultDataAccessException e) {
            return "No container with id " + id.toString() + " exists.";
        } catch (DataIntegrityViolationException e) {
            return "Error, failed to delete container: " + e.getRootCause().getMessage();
        } catch (Exception e) {
            return "Error, failed to delete container " + e.getMessage();
        }
        return "Deleted container successfully";
    }
}
