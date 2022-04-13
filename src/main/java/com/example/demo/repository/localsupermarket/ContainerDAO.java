package com.example.demo.repository.localsupermarket;

import com.example.demo.model.localsupermarket.Container;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContainerDAO {

    @Autowired
    private ContainerRepository containerRepository;

    public Container getContainerById(Integer id) {
        return containerRepository.findById(id).get();
    }

    public void deleteContainerById(Integer id) {
        containerRepository.deleteById(id);
    }

    public Container getContainerByName(String name) {
        return containerRepository.findByName(name).get(0);
    }

    public void deleteContainerByName(String name) {
        containerRepository.deleteByName(name);
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
