package com.hello.graphql.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import graphql.ExecutionResult;

@Controller
@RequestMapping("test")
public class CityController {

    @Autowired
    private CityProvider cityProvider;

    @GetMapping("/city")
    public ResponseEntity<Object> getCities (@RequestBody String query) {
        ExecutionResult execute = cityProvider.execute(query);

        return new ResponseEntity<>(execute, HttpStatus.OK);
    }

}