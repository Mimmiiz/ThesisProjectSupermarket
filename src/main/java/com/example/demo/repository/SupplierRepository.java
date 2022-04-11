package com.example.demo.repository;

import com.example.demo.model.Supplier;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface SupplierRepository extends CrudRepository<Supplier, Integer> {

    List<Supplier> findByBrand(String brand);
}
