package com.hello.graphql.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import graphql.ExecutionResult;
import graphql.GraphQL;

@Service
public class CityProvider implements ICityProvider {

    @Autowired GraphQL graphQL;

    public ExecutionResult execute (String query) {
        return graphQL.execute(query);
    }

}