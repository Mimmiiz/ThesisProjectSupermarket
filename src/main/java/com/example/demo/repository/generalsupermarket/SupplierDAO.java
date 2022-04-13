package com.example.demo.repository.generalsupermarket;

import com.example.demo.model.generalsupermarket.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierDAO {

    @Autowired
    private SupplierRepository supplierRepository;

    public Supplier getSupplierById(Integer id) {
        return supplierRepository.findById(id).get();
    }

    public Supplier getSupplierByBrand(String brand) {
        return supplierRepository.findByBrand(brand).get(0);
    }

    public void saveSupplier(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    public void deleteSupplier(Integer id) {
        supplierRepository.deleteById(id);
    }
}
