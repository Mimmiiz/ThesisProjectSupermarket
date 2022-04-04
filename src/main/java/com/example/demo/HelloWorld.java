package com.example.demo;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.Context;

/**
 * This class tests that a Lambda function can be invoked.
 */
public class HelloWorld implements RequestHandler<String, Object> {

    @Override
    public Object handleRequest(String s, Context context) {
        context.getLogger().log("Input: " + s);
        return "Lambda function has been invoked..." + s;
    }
}
