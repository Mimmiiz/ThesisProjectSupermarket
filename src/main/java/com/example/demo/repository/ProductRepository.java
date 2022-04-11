package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    List<Product> findByGtin14(String gtin14);
    List<Product> findByGtin12(String gtin12);
    List<Product> findByProductName(String name);
    void deleteByGtin14(String gtin14);
    void deleteByGtin12(String gtin12);
}
