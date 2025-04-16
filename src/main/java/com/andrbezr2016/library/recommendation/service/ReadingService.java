package com.andrbezr2016.library.recommendation.service;

import com.andrbezr2016.library.recommendation.dto.BookDto;
import com.andrbezr2016.library.recommendation.dto.NotableBookDto;
import com.andrbezr2016.library.recommendation.entity.NotableBook;
import com.andrbezr2016.library.recommendation.mapper.NotableBookMapper;
import com.andrbezr2016.library.recommendation.repository.NotableBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReadingService {

    private final NotableBookRepository notableBookRepository;
    private final CatalogLoaderService catalogLoaderService;
    private final NotableBookMapper notableBookMapper;

    public Collection<BookDto> getBooksToRead() {
        Collection<UUID> bookIds = notableBookRepository.findAllBookIds();
        if (bookIds == null) {
            bookIds = Collections.emptyList();
        }

        return catalogLoaderService.getBooksExcludingIds(bookIds);
    }

    public Collection<NotableBookDto> getNotableBooks() {
        Collection<NotableBook> notableBooks = notableBookRepository.findAll();
        if (notableBooks.isEmpty()) {
            return Collections.emptyList();
        }

        Collection<UUID> bookIds = notableBooks.stream().map(NotableBook::getBookId).toList();
        Map<UUID, BookDto> bookDtoMap = catalogLoaderService.getBooksByIds(bookIds).stream().collect(Collectors.toUnmodifiableMap(BookDto::getId, Function.identity()));

        Collection<NotableBookDto> notableBookDtos = notableBookMapper.toDtoCollection(notableBooks);
        for (NotableBookDto notableBookDto : notableBookDtos) {
            BookDto bookDto = bookDtoMap.get(notableBookDto.getBookDto().getId());
            notableBookDto.setBookDto(bookDto);
        }
        return notableBookDtos;
    }
}
