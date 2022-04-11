package com.example.demo.controller;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductDAO productDAO;

    @GetMapping("/getProductById")
    public Product getProductById(@RequestParam(value = "id") Integer id) {
        //Product product = new Product(1, "", "", "", "", "", 2, 1, "","", 10, null);
        return productDAO.getProductById(id);
    }

    @GetMapping("/getProductByGtin14")
    public Product getProductByGtin14(@RequestParam(value = "id") String id) {
        return productDAO.getProductByGtin14(id);
    }

    @GetMapping("/getProductByGtin12")
    public Product getProductByGtin12(@RequestParam(value = "id") String id) {
        return productDAO.getProductByGtin12(id);
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
    public String updateProduct(Integer id, @RequestBody Product product) {
        try {
            productDAO.saveProduct(product);
        } catch (Exception e) {
            return "Error, failed to update product.";
        }
        return "Successfully updated product";
    }

    @DeleteMapping("/deleteProduct")
    public String deleteProduct(@RequestParam(value = "id") Integer id) {
        try {
            productDAO.deleteProduct(id);
        } catch (EmptyResultDataAccessException e) {
            return "No product with id " + id.toString() + " exists.";
        } catch (DataIntegrityViolationException e) {
            return "Error, failed to delete product: " + e.getRootCause().getMessage();
        } catch (Exception e) {
            return "Error, failed to delete product " + e.getMessage();
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
