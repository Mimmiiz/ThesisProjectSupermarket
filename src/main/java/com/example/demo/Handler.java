package com.example.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.example.demo.controller.ProductController;
import com.example.demo.controller.SupplierController;
import com.example.demo.model.Product;
import com.example.demo.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class Handler implements RequestHandler<String, String> {


    @Autowired
    private ProductController controller;
    @Autowired
    private SupplierController supplierController;

    @Override
    public String handleRequest(String input, Context context) {
        //return supplierController.insertSupplier(input);
        return "123";
    }
}
