package com.example.demo.repository.generalsupermarket;

import com.example.demo.model.generalsupermarket.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
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

    public void deleteProductById(Integer id) {
        productRepository.deleteById(id);
    }

    public void deleteProductByGtin14(String gtin14) {
        productRepository.deleteByGtin14(gtin14);
    }

    public void deleteProductByGtin12(String gtin12) {
        productRepository.deleteByGtin12(gtin12);
    }

    public void insertProducts(List<Product> products) {
        productRepository.saveAll(products);
    }
}
