package com.example.demo.controller;

import com.example.demo.model.Supplier;
import com.example.demo.repository.SupplierDAO;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class SupplierController {

    @Autowired
    private SupplierDAO supplierDAO;

    @GetMapping("/getSupplierById")
    public Supplier getSupplierById(@RequestParam(value = "id") Integer id) {
        return supplierDAO.getSupplierById(id);
    }

    @GetMapping("/getSupplierByBrand")
    public Supplier getSupplierByBrand(@RequestParam(value = "brand") String brand) {
        return supplierDAO.getSupplierByBrand(brand);
    }

    @PutMapping("/updateSupplier")
    public String updateSupplier(@RequestParam(value = "id") Integer id, @RequestBody String json) {
        Supplier supplier = new Supplier();
        try {
            supplier.fromJsonToSupplier(json);
        } catch (Exception e) {
            return "Error, failed to deserialize JSON";
        }
        supplierDAO.saveSupplier(supplier);
        return "Updated supplier successfully";
    }

    @PostMapping("/insertSupplier")
    public String insertSupplier(@RequestBody String json) {
        Supplier supplier = new Supplier();
        /*supplier.setAddress("Matvagen 1");
        supplier.setManufacturer("Mat Brand");
        supplier.setWebsite("www.mat.se");
        supplier.setBrand("Mat");*/
        try {
            supplier.fromJsonToSupplier(json);
        } catch (Exception e) {
            return "Error, failed to deserialize JSON";
        }
        try {
            supplierDAO.saveSupplier(supplier);
        } catch (Exception e) {
            return "Error, failed to insert supplier: " + e.getMessage();
        }
        return "Added supplier successfully";
    }

    @DeleteMapping("/deleteSupplier")
    public String deleteSupplier(@RequestParam(value = "id") Integer id) {
        try {
            supplierDAO.deleteSupplier(id);
        } catch (EmptyResultDataAccessException e) {
            return "No Supplier with id " + id.toString() + " exists.";
        } catch (DataIntegrityViolationException e) {
            return "Error, failed to delete supplier: " + e.getRootCause().getMessage();
        } catch (Exception e) {
            return "Error, failed to delete supplier: " + e.getMessage();
        }
        return "Deleted supplier successfully";
    }
}
