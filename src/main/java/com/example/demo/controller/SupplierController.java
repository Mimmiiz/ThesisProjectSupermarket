package com.example.demo.controller;

import com.example.demo.model.generalsupermarket.Supplier;
import com.example.demo.repository.generalsupermarket.SupplierDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;


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
    public String updateSupplier(@RequestBody Supplier supplier) {
        supplierDAO.saveSupplier(supplier);
        return "Updated supplier successfully";
    }

    @PostMapping("/insertSupplier")
    public String insertSupplier(@RequestBody Supplier supplier) {
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
