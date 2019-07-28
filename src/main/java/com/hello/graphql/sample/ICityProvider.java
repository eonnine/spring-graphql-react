package com.hello.graphql.sample;

import graphql.ExecutionResult;

public interface ICityProvider {
  ExecutionResult execute(String query);
}