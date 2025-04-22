package com.andrbezr2016.library.recommendation.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.client.GraphQlClient;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
class CatalogLoaderServiceTest {

    @Autowired
    CatalogLoaderService catalogLoaderService;
    @MockitoBean
    GraphQlClient graphQlClient;

    @Test
    void getBooksExcludingIds() {
    }

    @Test
    void getBooksByIds() {
    }

    @Test
    void getBooks() {
    }
}