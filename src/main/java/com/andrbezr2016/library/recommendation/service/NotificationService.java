package com.andrbezr2016.library.recommendation.service;

import com.andrbezr2016.library.recommendation.dto.BookDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationService {

    private final ReadingService readingService;

    public void sendNotification() {
        Collection<BookDto> books = readingService.getBooksToRead();
        log.info("You haven't read these books yet: {}", books);
    }
}
