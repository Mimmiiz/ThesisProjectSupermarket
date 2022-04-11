package com.example.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.example.demo.controller.ProductController;
import com.example.demo.controller.SupplierController;
import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;

public class Handler implements RequestHandler<Integer, Product> {

    @Autowired
    private ProductController controller;
    @Autowired
    private SupplierController supplierController;

    @Override
    public Product handleRequest(Integer input, Context context) {
        return controller.getProductById(input);
    }
}
