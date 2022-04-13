package com.example.demo.repository.generalsupermarket;

import com.example.demo.model.generalsupermarket.Supplier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends CrudRepository<Supplier, Integer> {

    List<Supplier> findByBrand(String brand);
}
