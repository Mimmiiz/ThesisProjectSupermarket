package com.example.demo.dto;

import com.example.demo.model.generalsupermarket.Supplier;
import com.example.demo.model.localsupermarket.Product;
import lombok.Data;

@Data
public class ProductDTO {
    private int id;
    private String gtin14;
    private String gtin12;
    private String productName;
    private String price;
    private Integer reOrderLevel;
    private Integer orderQuantity;
    private String locationX;
    private String locationY;
    private Integer quantity;
    private FloorDTO floor;
    private SupplierDTO supplier;

    public ProductDTO(Product product, Supplier supplier) {
        id = product.getId();
        gtin14 = product.getGtin14();
        gtin12 = product.getGtin12();
        productName = product.getProductName();
        price = product.getPrice();
        reOrderLevel = product.getReOrderLevel();
        orderQuantity = product.getOrderQuantity();
        locationX = product.getLocationX();
        locationY = product.getLocationY();
        quantity = product.getQuantity();
        if(product.getFloor() != null)
            this.floor = new FloorDTO(product.getFloor());
        if(supplier != null)
            this.supplier = new SupplierDTO(supplier);
    }
}
