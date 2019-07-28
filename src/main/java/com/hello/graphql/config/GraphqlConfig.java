package com.hello.graphql.config;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

import java.io.IOException;
import java.net.URL;

import javax.annotation.PostConstruct;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.hello.graphql.sample.AllCitiesDataFetcher;
import com.hello.graphql.sample.CityDataFetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Configuration
public class GraphqlConfig {

  @Autowired private CityDataFetcher cityDataFetcher;
  @Autowired private AllCitiesDataFetcher allCitiesDataFetcher;

  private GraphQL graphQL;

  @Value("classpath:graphql/schema.graphqls") 
  Resource resource;

  @PostConstruct
  public void init() throws IOException {
      URL url = resource.getURL();
      String sdl = Resources.toString(url, Charsets.UTF_8);
      GraphQLSchema graphQLSchema = buildSchema(sdl);
      this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
  }

  private GraphQLSchema buildSchema(String sdl) {
      TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
      RuntimeWiring runtimeWiring = buildWiring();
      SchemaGenerator schemaGenerator = new SchemaGenerator();
      return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
  }

  private RuntimeWiring buildWiring() {
      return RuntimeWiring.newRuntimeWiring()
              .type(
                newTypeWiring("Query")
                .dataFetcher("allCities", allCitiesDataFetcher)
                .dataFetcher("city", cityDataFetcher)
              ).
              build();
  }

  @Bean 
  public GraphQL graphQL() {
      return graphQL;
  }
  
}