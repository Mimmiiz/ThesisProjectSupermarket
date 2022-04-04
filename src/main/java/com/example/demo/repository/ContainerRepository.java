package com.example.demo.repository;

import com.example.demo.model.Container;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContainerRepository extends CrudRepository<Container, Integer> {
}