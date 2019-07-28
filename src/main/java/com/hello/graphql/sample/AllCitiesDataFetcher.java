package com.hello.graphql.sample;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AllCitiesDataFetcher implements DataFetcher<List<City>> {
  
  @Autowired
  private CityRepository cityRepository;

  @Override
  public List<City> get(DataFetchingEnvironment environment) {
    return cityRepository.findAll();
  }

}