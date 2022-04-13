package com.example.demo.repository.generalsupermarket;

import com.example.demo.model.generalsupermarket.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    List<Product> findByGtin14(String gtin14);
    List<Product> findByGtin12(String gtin12);
    List<Product> findByProductName(String name);
    void deleteByGtin14(String gtin14);
    void deleteByGtin12(String gtin12);
}
