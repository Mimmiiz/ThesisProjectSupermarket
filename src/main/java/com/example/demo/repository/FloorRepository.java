package com.example.demo.repository;

import com.example.demo.model.Floor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FloorRepository extends CrudRepository<Floor, Integer> {

    List<Floor> findByFloorNumber(String floorNumber);
    void deleteByFloorNumber(String floorNumber);
}
