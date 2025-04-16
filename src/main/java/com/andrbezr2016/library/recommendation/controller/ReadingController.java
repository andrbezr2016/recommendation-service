package com.andrbezr2016.library.recommendation.controller;

import com.andrbezr2016.library.recommendation.dto.BookDto;
import com.andrbezr2016.library.recommendation.dto.NotableBookDto;
import com.andrbezr2016.library.recommendation.service.ReadingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RequiredArgsConstructor
@RestController
public class ReadingController {

    private final ReadingService readingService;

    public Collection<BookDto> getBooksToRead() {
        return readingService.getBooksToRead();
    }

    public Collection<NotableBookDto> getNotableBooks() {
        return readingService.getNotableBooks();
    }
}
