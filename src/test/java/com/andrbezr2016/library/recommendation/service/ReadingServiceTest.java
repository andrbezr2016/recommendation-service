package com.andrbezr2016.library.recommendation.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
class ReadingServiceTest {

    @Autowired
    ReadingService readingService;
    @MockitoBean
    CatalogLoaderService catalogLoaderService;

    @Test
    void addNotableBook() {
    }

    @Test
    void updateNotableBook() {
    }

    @Test
    void deleteNotableBook() {
    }

    @Test
    void getNotableBook() {
    }

    @Test
    void getNotableBooks() {
    }

    @Test
    void addNote() {
    }

    @Test
    void updateNote() {
    }

    @Test
    void deleteNote() {
    }

    @Test
    void getBooksToRead() {
    }
}