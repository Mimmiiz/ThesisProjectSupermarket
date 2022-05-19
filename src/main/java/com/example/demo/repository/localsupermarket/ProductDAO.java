package com.example.demo.repository.localsupermarket;

import com.example.demo.dto.ProductDTO;
import com.example.demo.model.generalsupermarket.Supplier;
import com.example.demo.model.localsupermarket.Floor;
import com.example.demo.model.localsupermarket.Product;
import com.example.demo.repository.generalsupermarket.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductDAO {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private FloorRepository floorRepository;

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.getAllProducts();
        return convertListOfProductsToProductDTOs(products);
    }

    public ProductDTO getProductByGtin(String gtin) throws Exception {
        if(gtin.length() == 12) {
            Product product = productRepository.findByGtin12(gtin);
            if (product == null) {
                throw new Exception("Could not find a product with product code " + gtin);
            }
            return convertProductToProductDTO(product);
        }
        else if(gtin.length() == 14) {
            Product product = productRepository.findByGtin14(gtin);
            if (product == null) {
                throw new Exception("Could not find a product with product code " + gtin);
            }
            return convertProductToProductDTO(product);
        }
        else {
            throw new Exception("Invalid GTIN entered.");
        }
    }

    public List<ProductDTO> getProductByName(String name) {
        List<Product> products = productRepository.findByName(name);
        return convertListOfProductsToProductDTOs(products);
    }

    public void insertProduct(Product product, String floorNumber) throws Exception {
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

        Floor floor = null;
        if(floorNumber != null)
            try {
                floor = floorRepository.findByFloorNumber(floorNumber).get(0);
            } catch(Exception e) {
                throw new Exception("Could not find floor with floor number " + floorNumber);
            }

        productRepository.insertProduct(product.getGtin14(), product.getGtin12(), product.getPrice(), product.getReOrderLevel(),
                product.getOrderQuantity(), product.getQuantity(), product.getLocationX(), product.getLocationY(), getFloorId(floor));
    }

    public void updateProduct(Product product, String floorNumber) throws Exception {
        Floor floor = null;
        if(!floorNumber.isEmpty())
            try {
                floor = floorRepository.findByFloorNumber(floorNumber).get(0);
            } catch(Exception e) {
                throw new Exception("Could not find floor with floor number " + floorNumber);
            }

        if (product.getGtin14() == null && product.getGtin12() == null)
            throw new Exception("No product code has been entered.");

        if(productRepository.updateProduct(product.getGtin14(), product.getGtin12(), product.getPrice(), product.getReOrderLevel(),
                product.getOrderQuantity(), product.getQuantity(), product.getLocationX(), product.getLocationY(),
                getFloorId(floor)) == 0) {
            throw new Exception("Could not update product.");
        }
    }

    public void updateProductLocation(Product product) throws Exception {
        if (product.getGtin12() != null) {
            productRepository.updateLocationByGtin12(product.getGtin12(), product.getLocationX(), product.getLocationY());
        }
        else if (product.getGtin14() != null) {
            productRepository.updateLocationByGtin12(product.getGtin14(), product.getLocationX(), product.getLocationY());
        }
        else
            throw new Exception("No product code has been entered.");
    }

    public void deleteProductByGtin(String gtin) throws Exception {
        if(gtin.length() == 12) {
            if(productRepository.deleteByGtin12(gtin) == 0)
                throw new Exception("Could not delete a product with the specified product code: " + gtin +
                        ", does not exist in the database.");
        }
        else if(gtin.length() == 14) {
            if(productRepository.deleteByGtin14(gtin) == 0)
                throw new Exception("Could not delete a product with the specified product code: " + gtin +
                        ", does not exist in the database.");
        }
        else {
            throw new Exception("Invalid GTIN entered.");
        }
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

    private List<ProductDTO> convertListOfProductsToProductDTOs(List<Product> products) {
        List<ProductDTO> productDTOs = new ArrayList<>();
        Supplier supplier = null;
        for (Product product : products) {
            if(product.getSupplierId() != null) {
                supplier = supplierRepository.findById(product.getSupplierId()).get();
            }
            productDTOs.add(new ProductDTO(product, supplier));
            supplier = null;
        }

        return productDTOs;
    }

    private ProductDTO convertProductToProductDTO(Product product) {
        Supplier supplier = null;
        if(product.getSupplierId() != null)
            supplier = supplierRepository.findById(product.getSupplierId()).get();
        return new ProductDTO(product, supplier);
    }
}
