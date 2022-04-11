package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAO {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);

        return products;
    }

    public Product getProductByGtin14(String gtin14) {
        return productRepository.findByGtin14(gtin14).get(0);
    }

    public Product getProductByGtin12(String gtin12) {
        return productRepository.findByGtin12(gtin12).get(0);
    }

    public Product getProductByName(String name) {
        return productRepository.findByProductName(name).get(0);
    }

    public Product getProductById(Integer id) {
        return this.productRepository.findById(id).get();
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    public void insertProducts(List<Product> products) {
        productRepository.saveAll(products);
    }
}
