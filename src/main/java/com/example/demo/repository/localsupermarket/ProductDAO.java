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

    public ProductDTO getProductByGtin14(String gtin14) {
        Product product = productRepository.findByGtin14(gtin14);
        return convertProductToProductDTO(product);
    }

    public ProductDTO getProductByGtin12(String gtin12) throws Exception {
        Product product = productRepository.findByGtin12(gtin12);
        if(product == null) {
            throw new Exception("Could not find a product with product code " + gtin12);
        }
        return convertProductToProductDTO(product);
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
        if (!floorNumber.isEmpty())
            try {
                floor = floorRepository.findByFloorNumber(floorNumber).get(0);
            } catch(Exception e) {
                throw new Exception("Could not find floor with floor number " + floorNumber);
            }

        if (product.getGtin14() == null && product.getGtin12() == null)
            throw new Exception("No product code has been entered.");

        if(product.getPrice() != null) {
            if (productRepository.updatePrice(product.getGtin14(), product.getGtin12(), product.getPrice()) == 0)
                throw new Exception("Could not find a product with the specified product number.");
        }
        if(product.getReOrderLevel() != null) {
            if (productRepository.updateReOrderLevel(product.getGtin14(), product.getGtin12(), product.getReOrderLevel()) == 0)
                throw new Exception("Could not find a product with the specified product number.");
        }
        if(product.getOrderQuantity() != null) {
            if (productRepository.updateOrderQuantity(product.getGtin14(), product.getGtin12(), product.getOrderQuantity()) == 0)
                throw new Exception("Could not find a product with the specified product number.");
        }
        if(product.getQuantity() != null) {
            if (productRepository.updateQuantity(product.getGtin14(), product.getGtin12(), product.getQuantity()) == 0)
                throw new Exception("Could not find a product with the specified product number.");
        }
        if(product.getLocationX() != null) {
            if (productRepository.updateLocationX(product.getGtin14(), product.getGtin12(), product.getLocationX()) == 0)
                throw new Exception("Could not find a product with the specified product number.");
        }
        if(product. getLocationY() != null) {
            if (productRepository.updateLocationY(product.getGtin14(), product.getGtin12(), product.getLocationY()) == 0)
                throw new Exception("Could not find a product with the specified product number.");
        }
        if(floor != null) {
            if (productRepository.updateFloorId(product.getGtin14(), product.getGtin12(), getFloorId(floor)) == 0)
                throw new Exception("Could not find a product with the specified product number.");
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

    public void deleteProductByGtin14(String gtin14) throws Exception {
        if(productRepository.deleteByGtin14(gtin14) == 0)
            throw new Exception("Could not delete a product with the specified product code: " + gtin14 +
                    ", does not exist in the database.");
    }

    public void deleteProductByGtin12(String gtin12) throws Exception {
        if(productRepository.deleteByGtin12(gtin12) == 0)
            throw new Exception("Could not delete a product with the specified product code: " + gtin12 +
                    ", does not exist in the database.");
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
