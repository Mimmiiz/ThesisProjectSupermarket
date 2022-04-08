package com.example.demo.repository;

import com.example.demo.model.Container;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ContainerDAO {

    @Autowired
    private ContainerRepository containerRepository;

    public Container getContainerById(Integer id) {
        return containerRepository.findById(id).get();
    }

    public void deleteContainer(Integer id) {
        containerRepository.deleteById(id);
    }

    public void saveContainer(Container container) {
        containerRepository.save(container);
    }

    public List<Container> getAllContainers() {
        List<Container> containers = new ArrayList<>();
        containerRepository.findAll().forEach(containers::add);
        return containers;
    }
}
