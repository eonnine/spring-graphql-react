package com.hello.graphql.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class CityDataFetcher implements DataFetcher<City> {
  
  @Autowired
  private CityRepository cityRepository;

  @Override
  public City get(DataFetchingEnvironment environment) {
    int id = environment.getArgument("id");
    return cityRepository.findById(id);
  }

}