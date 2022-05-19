package com.example.demo.repository.localsupermarket;

import com.example.demo.model.localsupermarket.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    @Query(value = "SELECT * FROM product as tb1 JOIN supermarket.product as tb2 ON tb1.gtin14 = tb2.gtin14 WHERE tb1.gtin14 = ?1",
            nativeQuery = true)
    Product findByGtin14(String gtin14);

    @Query(value = "SELECT * FROM product as tb1 JOIN supermarket.product as tb2 ON tb1.gtin12 = tb2.gtin12 WHERE tb1.gtin12 = ?1",
            nativeQuery = true)
    Product findByGtin12(String gtin12);

    @Query(value = "SELECT * FROM supermarket.product as tb1 JOIN product as tb2 ON tb1.gtin14 = tb2.gtin14 WHERE tb1.product_name = ?1",
            nativeQuery = true)
    List<Product> findByName(String name);

    @Query(value = "SELECT * FROM product as tb1 JOIN supermarket.product as tb2 ON tb1.gtin14 = tb2.gtin14", nativeQuery = true)
    List<Product> getAllProducts();

    @Modifying
    @Query(value = "INSERT INTO product (gtin14, gtin12, price, re_order_level, order_quantity, quantity, location_x, location_y, floor_id)" +
                    "values (:gtin14, :gtin12, :price, :reOrderLevel, :orderQuantity, :quantity, :locationX, :locationY, :floorId)",
            nativeQuery = true)
    void insertProduct(@Param("gtin14") String gtin14, @Param("gtin12") String gtin12, @Param("price") String price,
                       @Param("reOrderLevel") Integer reOrderLevel, @Param("orderQuantity") Integer orderQuantity, @Param("quantity") Integer quantity,
                       @Param("locationX") String locationX, @Param("locationY") String locationY, @Param("floorId") Integer floorId);

    @Query(value = "SELECT gtin12 FROM supermarket.product WHERE gtin14 = ?1", nativeQuery = true)
    String findGtin12(String gtin14);

    @Query(value = "SELECT gtin14 FROM supermarket.product WHERE gtin12 = ?1", nativeQuery = true)
    String findGtin14(String gtin12);

    @Modifying
    @Query(value = "UPDATE product SET location_x = :locationX, location_y = :locationY WHERE gtin14 = :gtin14", nativeQuery = true)
    int updateLocationByGtin14(@Param("gtin14") String gtin14, @Param("locationX") String locationX, @Param("locationY") String locationY);

    @Modifying
    @Query(value = "UPDATE product SET location_x = :locationX, location_y = :locationY WHERE gtin12 = :gtin12", nativeQuery = true)
    int updateLocationByGtin12(@Param("gtin12") String gtin14, @Param("locationX") String locationX, @Param("locationY") String locationY);


    @Modifying
    @Query(value = "UPDATE product SET price = :price, re_order_level = :reOrderLevel, " +
            "order_quantity = :orderQuantity, quantity = :quantity, location_x = :locationX, location_y = :locationY, " +
            "floor_id = :floorId WHERE gtin14 = :gtin14 OR gtin12 = :gtin12", nativeQuery = true)
    int updateProduct(@Param("gtin14") String gtin14, @Param("gtin12") String gtin12, @Param("price") String price, @Param("reOrderLevel") Integer reOrderLevel,
                      @Param("orderQuantity") Integer orderQuantity, @Param("quantity") Integer quantity, @Param("locationX") String locationX,
                      @Param("locationY") String locationY, @Param("floorId") Integer floorId);

    @Modifying
    @Query(value = "DELETE FROM product WHERE gtin14 = ?1", nativeQuery = true)
    int deleteByGtin14(String gtin14);

    @Modifying
    @Query(value = "DELETE FROM product WHERE gtin12 = ?1", nativeQuery = true)
    int deleteByGtin12(String gtin12);
}
