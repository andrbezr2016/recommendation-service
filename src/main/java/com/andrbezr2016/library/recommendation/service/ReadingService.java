package com.andrbezr2016.library.recommendation.service;

import com.andrbezr2016.library.recommendation.dto.*;
import com.andrbezr2016.library.recommendation.entity.NotableBook;
import com.andrbezr2016.library.recommendation.mapper.NotableBookMapper;
import com.andrbezr2016.library.recommendation.repository.NotableBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
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

    public NotableBookDto addNotableBook(NotableBookInput notableBookInput) {
        return null;
    }

    public NotableBookDto updateNotableBook(UUID notableBookId, NotableBookUpdate notableBookUpdate) {
        return null;
    }

    public NotableBookDto deleteNotableBook(UUID notableBookId) {
        return null;
    }

    public Collection<NotableBookDto> getNotableBook(UUID notableBookId) {
        return null;
    }

    @Cacheable("notableBooks")
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

    public NoteDto addNote(UUID notableBookId, NoteInput noteInput) {
        return null;
    }

    public NoteDto updateNote(UUID noteId, NoteUpdate noteUpdate) {
        return null;
    }

    public NoteDto deleteNote(UUID noteId) {
        return null;
    }

    @Cacheable("booksToRead")
    public Collection<BookDto> getBooksToRead() {
        Collection<UUID> bookIds = notableBookRepository.findAllBookIds();
        if (bookIds == null) {
            bookIds = Collections.emptyList();
        }

        return catalogLoaderService.getBooksExcludingIds(bookIds);
    }
}
