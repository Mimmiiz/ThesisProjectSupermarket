package com.example.demo.controller;
import com.example.demo.model.generalsupermarket.Product;
import com.example.demo.repository.generalsupermarket.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Transactional("generalSupermarketTransactionManager")
@RestController
public class ProductController {

    @Autowired
    private ProductDAO productDAO;

    @GetMapping("/getProductById")
    public Product getProductById(@RequestParam(value = "id") Integer id) {
        return productDAO.getProductById(id);
    }

    @GetMapping("/getProductByGtin14")
    public Product getProductByGtin14(@RequestParam(value = "id") String gtin14) {
        return productDAO.getProductByGtin14(gtin14);
    }

    @GetMapping("/getProductByGtin12")
    public Product getProductByGtin12(@RequestParam(value = "id") String gtin12) {
        return productDAO.getProductByGtin12(gtin12);
    }

    @GetMapping("/getProductByName")
    public Product getProductByName(@RequestParam(value = "name") String name) {
        return productDAO.getProductByName(name);
    }

    @GetMapping("/getAllProducts")
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    @PutMapping("/updateProduct")
    public String updateProduct(@RequestBody Product product) {
        try {
            productDAO.saveProduct(product);
        } catch (Exception e) {
            return "Error, failed to update product.";
        }
        return "Successfully updated product";
    }

    @DeleteMapping("/deleteProductById")
    public String deleteProductById(@RequestParam(value = "id") Integer id) {
        try {
            productDAO.deleteProductById(id);
        } catch (EmptyResultDataAccessException e) {
            return "No product with id " + id.toString() + " exists.";
        } catch (DataIntegrityViolationException e) {
            return "Error, failed to delete product: " + e.getRootCause().getMessage();
        } catch (Exception e) {
            return "Error, failed to delete product: " + e.getMessage();
        }
        return "Deleted product successfully";
    }

    @DeleteMapping("/deleteProductByGtin14")
    public String deleteProductByGtin14(@RequestParam(value = "id") String gtin14) {
        try {
            productDAO.deleteProductByGtin14(gtin14);
        } catch (EmptyResultDataAccessException e) {
            return "No product with product number " + gtin14 + " exists.";
        } catch (DataIntegrityViolationException e) {
            return "Error, failed to delete product: " + e.getRootCause().getMessage();
        } catch (Exception e) {
            return "Error, failed to delete product: " + e.getMessage();
        }
        return "Deleted product successfully";
    }

    @DeleteMapping("/deleteProductByGtin12")
    public String deleteProductByGtin12(@RequestParam(value = "id") String gtin12) {
        try {
            productDAO.deleteProductByGtin12(gtin12);
        } catch (EmptyResultDataAccessException e) {
            return "No product with product number " + gtin12 + " exists.";
        } catch (DataIntegrityViolationException e) {
            return "Error, failed to delete product: " + e.getRootCause().getMessage();
        } catch (Exception e) {
            return "Error, failed to delete product: " + e.getMessage();
        }
        return "Deleted product successfully";
    }

    @PostMapping("/insertProduct")
    public String insertProduct(@RequestBody Product product) {
        try {
            productDAO.saveProduct(product);
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
