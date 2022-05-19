package com.example.demo.controller;

import com.example.demo.dto.ProductDTO;
import com.example.demo.model.localsupermarket.Product;
import com.example.demo.repository.localsupermarket.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Transactional("localSupermarketTransactionManager")
@RestController
public class ProductController {

    @Autowired
    private ProductDAO productDAO;

    @GetMapping("/getProductByGtin")
    public ProductDTO getProductByGtin(@RequestParam(value = "id") String gtin) throws Exception {
        return productDAO.getProductByGtin(gtin);
    }

    @GetMapping("/getProductByName")
    public List<ProductDTO> getProductByName(@RequestParam(value = "name") String name) {
        return productDAO.getProductByName(name);
    }

    @GetMapping("/getAllProducts")
    public List<ProductDTO> getAllProducts() {
        return productDAO.getAllProducts();
    }

    @PutMapping("/updateProduct")
    public String updateProduct(@RequestParam(value = "floorNumber") String floorNumber, @RequestBody Product product) {
        try {
            productDAO.updateProduct(product, floorNumber);
        } catch (Exception e) {
            return "Error, failed to update product: " + e.getMessage();
        }
        return "Successfully updated product";
    }

    @PutMapping("/updateProductLocation")
    public String updateProductLocation(@RequestBody Product product) {
        try {
            productDAO.updateProductLocation(product);
        } catch (Exception e) {
            return "Error, failed to update product: " + e.getMessage();
        }
        return "Successfully updated product";
    }

    @DeleteMapping("/deleteProductByGtin")
    public String deleteProductByGtin(@RequestParam(value = "id") String gtin) {
        try {
            productDAO.deleteProductByGtin(gtin);
        } catch (EmptyResultDataAccessException e) {
            return "No product with product number " + gtin + " exists.";
        } catch (DataIntegrityViolationException e) {
            return "Error, failed to delete product: " + e.getRootCause().getMessage();
        } catch (Exception e) {
            return "Error, failed to delete product: " + e.getMessage();
        }
        return "Deleted product successfully";
    }

    @PostMapping("/insertProduct")
    public String insertProduct(@RequestParam(value = "floorNumber") String floorNumber, @RequestBody Product product) {
        try {
            productDAO.insertProduct(product, floorNumber);
        } catch (Exception e) {
            return "Error, failed to insert product: " + e.getMessage();
        }
        return "Successfully inserted product";
    }

    @PostMapping("/insertProducts")
    public String insertProducts(@RequestBody List<Product> products) {
        try {
            productDAO.insertProducts(products);
        } catch (DataIntegrityViolationException e) {
            return "Error, failed to insert products: " + e.getRootCause().getMessage();
        } catch (Exception e) {
            return "Error, failed to insert products: " + e.getMessage();
        }
        return "Successfully inserted products.";
    }
}
