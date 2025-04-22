package com.andrbezr2016.library.recommendation.service;

import com.andrbezr2016.library.recommendation.dto.BookDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class NotificationServiceTest {

    @Autowired
    NotificationService notificationService;
    @MockitoBean
    ReadingService readingService;

    @Test
    void sendNotification() {
        BookDto bookDto1 = BookDto.builder().id(UUID.randomUUID()).build();
        BookDto bookDto2 = BookDto.builder().id(UUID.randomUUID()).build();
        List<BookDto> bookDtoList = List.of(bookDto1, bookDto2);

        doReturn(bookDtoList).when(readingService).getBooksToRead();

        boolean isOk = notificationService.sendNotification();

        assertTrue(isOk);
    }
}