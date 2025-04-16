package com.andrbezr2016.library.recommendation.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.client.GraphQlClient;
import org.springframework.graphql.client.HttpSyncGraphQlClient;
import org.springframework.web.client.RestClient;

@Configuration
public class GraphQlConfig {

    @Bean
    public GraphQlClient graphQlClient(@Value("${catalog-service.graphql.url}") String url) {
        RestClient restClient = RestClient.create(url);
        return HttpSyncGraphQlClient.create(restClient);
    }
}
