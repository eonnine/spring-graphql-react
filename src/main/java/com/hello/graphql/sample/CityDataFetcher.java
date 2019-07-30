package com.hello.graphql.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;

@Component
public class CityDataFetcher {
  
  @Autowired
  private CityRepository cityRepository;

  public DataFetcher getCities () {
    return environment -> {
      return cityRepository.findAll();
    };
  }

  public DataFetcher getCity () {
    return environment -> {
      int id = environment.getArgument("id");
      return cityRepository.findById(id);
    };
  }

}