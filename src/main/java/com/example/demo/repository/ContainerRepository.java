package com.example.demo.repository;

import com.example.demo.model.Container;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContainerRepository extends CrudRepository<Container, Integer> {

    void deleteByName(String name);
    List<Container> findByName(String name);
}
