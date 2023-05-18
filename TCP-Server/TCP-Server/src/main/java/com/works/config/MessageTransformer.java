package com.works.config;

import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.stereotype.Component;

@Component
public class MessageTransformer implements GenericTransformer<String, String> {

    @Override
    public String transform(String source) {
        return "Hello " + source;
    }

}
