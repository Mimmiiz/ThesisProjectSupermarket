package com.example.demo.dto;

import com.example.demo.model.generalsupermarket.Supplier;
import lombok.Data;

@Data
public class SupplierDTO {
    private int id;
    private String brand;
    private String manufacturer;
    private String address;
    private String website;

    public SupplierDTO(Supplier supplier) {
        id = supplier.getId();
        brand = supplier.getBrand();
        manufacturer = supplier.getManufacturer();
        address = supplier.getAddress();
        website = supplier.getWebsite();
    }
}
