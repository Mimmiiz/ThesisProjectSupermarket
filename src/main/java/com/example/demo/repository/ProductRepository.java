package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    List<Product> findByGtin14(String gtin14);
    List<Product> findByGtin12(String gtin12);
    List<Product> findByProductName(String name);
}
