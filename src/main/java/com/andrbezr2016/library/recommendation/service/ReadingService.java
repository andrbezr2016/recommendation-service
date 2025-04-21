package com.andrbezr2016.library.recommendation.service;

import com.andrbezr2016.library.recommendation.dto.*;
import com.andrbezr2016.library.recommendation.entity.NotableBook;
import com.andrbezr2016.library.recommendation.entity.Note;
import com.andrbezr2016.library.recommendation.mapper.NotableBookMapper;
import com.andrbezr2016.library.recommendation.mapper.NoteMapper;
import com.andrbezr2016.library.recommendation.repository.NotableBookRepository;
import com.andrbezr2016.library.recommendation.repository.NoteRepository;
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
    private final NoteRepository noteRepository;
    private final CatalogLoaderService catalogLoaderService;
    private final NotableBookMapper notableBookMapper;
    private final NoteMapper noteMapper;

    public NotableBookDto addNotableBook(NotableBookInput notableBookInput) {
        NotableBook notableBook = notableBookMapper.toEntity(notableBookInput);
        Collection<BookDto> bookDtos = catalogLoaderService.getBooks(BookFilter.builder().id(notableBook.getBookId()).build());
        if (bookDtos.isEmpty()) {
            return null;
        }
        notableBook = notableBookRepository.save(notableBook);
        NotableBookDto notableBookDto = notableBookMapper.toDto(notableBook);
        notableBookDto.setBookDto(bookDtos.iterator().next());
        return notableBookDto;
    }

    public NotableBookDto updateNotableBook(UUID notableBookId, NotableBookUpdate notableBookUpdate) {
        NotableBook notableBook = notableBookRepository.findById(notableBookId).orElse(null);
        if (notableBook != null) {
            if (notableBookUpdate.getScore() != null) {
                notableBook.setScore(notableBookUpdate.getScore());
            }
            if (notableBookUpdate.getStatus() != null) {
                notableBook.setStatus(notableBookUpdate.getStatus());
            }
            notableBook = notableBookRepository.save(notableBook);
            NotableBookDto notableBookDto = notableBookMapper.toDto(notableBook);
            Collection<BookDto> bookDtos = catalogLoaderService.getBooks(BookFilter.builder().id(notableBook.getBookId()).build());
            if (!bookDtos.isEmpty()) {
                notableBookDto.setBookDto(bookDtos.iterator().next());
            }
            return notableBookDto;
        }
        return null;
    }

    public NotableBookDto deleteNotableBook(UUID notableBookId) {
        NotableBook notableBook = notableBookRepository.findById(notableBookId).orElse(null);
        if (notableBook != null) {
            notableBookRepository.delete(notableBook);
            NotableBookDto notableBookDto = notableBookMapper.toDto(notableBook);
            Collection<BookDto> bookDtos = catalogLoaderService.getBooks(BookFilter.builder().id(notableBook.getBookId()).build());
            if (!bookDtos.isEmpty()) {
                notableBookDto.setBookDto(bookDtos.iterator().next());
            }
            return notableBookDto;
        }
        return null;
    }

    public NotableBookDto getNotableBook(UUID notableBookId) {
        NotableBook notableBook = notableBookRepository.findById(notableBookId).orElse(null);
        if (notableBook != null) {
            NotableBookDto notableBookDto = notableBookMapper.toDto(notableBook);
            Collection<BookDto> bookDtos = catalogLoaderService.getBooks(BookFilter.builder().id(notableBook.getBookId()).build());
            if (!bookDtos.isEmpty()) {
                notableBookDto.setBookDto(bookDtos.iterator().next());
            }
            return notableBookDto;
        }
        return null;
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

    public NoteDto addNote(UUID notableBookId, NoteInput noteInput) {
        NotableBook notableBook = notableBookRepository.findById(notableBookId).orElse(null);
        if (notableBook != null) {
            Note note = noteMapper.toEntity(noteInput);
            note = noteRepository.save(note);
            return noteMapper.toDto(note);
        }
        return null;
    }

    public NoteDto updateNote(UUID noteId, NoteUpdate noteUpdate) {
        Note note = noteRepository.findById(noteId).orElse(null);
        if (note != null) {
            if (noteUpdate.getContent() != null) {
                note.setContent(noteUpdate.getContent());
            }
            note = noteRepository.save(note);
            return noteMapper.toDto(note);
        }
        return null;
    }

    public NoteDto deleteNote(UUID noteId) {
        Note note = noteRepository.findById(noteId).orElse(null);
        if (note != null) {
            noteRepository.delete(note);
            return noteMapper.toDto(note);
        }
        return null;
    }

    public Collection<BookDto> getBooksToRead() {
        Collection<UUID> bookIds = notableBookRepository.findAllBookIds();
        if (bookIds == null) {
            bookIds = Collections.emptyList();
        }
        return catalogLoaderService.getBooksExcludingIds(bookIds);
    }
}
