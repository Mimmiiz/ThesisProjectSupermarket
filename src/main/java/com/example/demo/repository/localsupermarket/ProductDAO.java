package com.example.demo.repository.localsupermarket;

import com.example.demo.model.localsupermarket.Floor;
import com.example.demo.model.localsupermarket.Product;
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
        productRepository.getAllProducts().forEach(products::add);
        return products;
    }

    public Product getProductByGtin14(String gtin14) {
        return productRepository.findByGtin14(gtin14);
    }

    public Product getProductByGtin12(String gtin12) {
        return productRepository.findByGtin12(gtin12);
    }

    public void insertProduct(Product product) throws Exception {
        String gtin14 = product.getGtin14();
        String gtin12 = product.getGtin12();
        if(gtin14 == null && gtin12 == null) {
            throw new Exception("No product code has been entered.");
        }
        else if(gtin14 != null)
            product.setGtin12(productRepository.findGtin12(gtin14));
        else
            product.setGtin14(productRepository.findGtin14(gtin12));

        if(product.getGtin12() == null || product.getGtin14() == null) {
            throw new Exception("Could not find a product with the specified product code.");
        }

        productRepository.insertProduct(product.getGtin14(), product.getGtin12(), product.getPrice(), product.getReOrderLevel(), product.getOrderQuantity(),
                product.getQuantity(), product.getLocationX(), product.getLocationY(), getFloorId(product.getFloor()));
    }

    public void updateProduct(Product product) {
        productRepository.updateProduct(product.getGtin14(), product.getGtin12(), product.getPrice(), product.getReOrderLevel(), product.getOrderQuantity(),
                product.getQuantity(), product.getLocationX(), product.getLocationY(), getFloorId(product.getFloor()));
    }

    public void deleteProductByGtin14(String gtin14) {
        productRepository.deleteByGtin14(gtin14);
    }

    public void deleteProductByGtin12(String gtin12) {
        productRepository.deleteByGtin12(gtin12);
    }

    public void insertProducts(List<Product> products) throws Exception {
        for (Product product : products) {
            String gtin14 = product.getGtin14();
            String gtin12 = product.getGtin12();
            if(gtin14 == null && gtin12 == null) {
                throw new Exception("No product code has been entered.");
            }
            else if(gtin14 != null)
                product.setGtin12(productRepository.findGtin12(gtin14));
            else
                product.setGtin14(productRepository.findGtin14(gtin12));

            if(product.getGtin12() == null || product.getGtin14() == null) {
                throw new Exception("Could not find a product with the specified product code.");
            }

            productRepository.insertProduct(product.getGtin14(), product.getGtin12(), product.getPrice(), product.getReOrderLevel(),
                    product.getOrderQuantity(), product.getQuantity(), product.getLocationX(), product.getLocationY(), getFloorId(product.getFloor()));
        }
    }

    private Integer getFloorId(Floor floor) {
        if(floor == null)
            return null;
        else
            return floor.getId();
    }
}
