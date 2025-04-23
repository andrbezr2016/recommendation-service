package com.andrbezr2016.library.recommendation.service;

import com.andrbezr2016.library.recommendation.dto.*;
import com.andrbezr2016.library.recommendation.model.ReadingStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class ReadingServiceTest {

    @Autowired
    ReadingService readingService;
    @MockitoBean
    CatalogLoaderService catalogLoaderService;

    @Test
    void addNotableBook() {
        NotableBookInput notableBookInput = NotableBookInput.builder().bookId(UUID.randomUUID()).status(ReadingStatus.IN_PROGRESS).build();
        BookDto bookDto = BookDto.builder().id(notableBookInput.getBookId()).title("Test Book").build();
        BookFilter bookFilter = BookFilter.builder().id(bookDto.getId()).build();

        doReturn(List.of(bookDto)).when(catalogLoaderService).getBooks(eq(bookFilter));

        NotableBookDto notableBookDto = readingService.addNotableBook(notableBookInput);

        assertEquals(bookDto.getId(), notableBookDto.getBookDto().getId());
        assertEquals(bookDto.getTitle(), notableBookDto.getBookDto().getTitle());
        assertEquals(notableBookInput.getStatus(), notableBookDto.getStatus());
    }

    @Test
    void updateNotableBook() {
        UUID notableBookId = UUID.fromString("a9875c41-b8fe-49df-ad6f-62445f74d5ef");
        NotableBookUpdate notableBookUpdate = NotableBookUpdate.builder().score(4).build();
        BookDto bookDto = BookDto.builder().id(UUID.fromString("4d9113af-7af9-486a-938e-2b53882077d9")).title("Test Book").build();
        BookFilter bookFilter = BookFilter.builder().id(bookDto.getId()).build();

        doReturn(List.of(bookDto)).when(catalogLoaderService).getBooks(eq(bookFilter));

        NotableBookDto notableBookDto = readingService.updateNotableBook(notableBookId, notableBookUpdate);

        assertEquals(notableBookId, notableBookDto.getId());
        assertEquals(bookDto.getId(), notableBookDto.getBookDto().getId());
        assertEquals(bookDto.getTitle(), notableBookDto.getBookDto().getTitle());
        assertEquals(notableBookUpdate.getScore(), notableBookDto.getScore());
    }

    @Test
    void deleteNotableBook() {
        UUID notableBookId = UUID.fromString("ad0ef6df-7d2d-457d-b3d9-15de9dc3e201");
        BookDto bookDto = BookDto.builder().id(UUID.fromString("e881d0a3-e948-4c16-a6a6-16e6e1b3e00c")).title("Test Book").build();
        BookFilter bookFilter = BookFilter.builder().id(bookDto.getId()).build();

        doReturn(List.of(bookDto)).when(catalogLoaderService).getBooks(eq(bookFilter));

        NotableBookDto notableBookDto = readingService.deleteNotableBook(notableBookId);

        assertEquals(notableBookId, notableBookDto.getId());
        assertEquals(bookDto.getId(), notableBookDto.getBookDto().getId());
        assertEquals(bookDto.getTitle(), notableBookDto.getBookDto().getTitle());
    }

    @Test
    void getNotableBook() {
        UUID notableBookId = UUID.fromString("6ee73aa1-4217-4683-9ca7-891e10bc98f3");
        BookDto bookDto = BookDto.builder().id(UUID.fromString("9cf94139-3bc4-4f25-9154-12961f53f46f")).title("Test Book").build();
        BookFilter bookFilter = BookFilter.builder().id(bookDto.getId()).build();

        doReturn(List.of(bookDto)).when(catalogLoaderService).getBooks(eq(bookFilter));

        NotableBookDto notableBookDto = readingService.getNotableBook(notableBookId);

        assertEquals(notableBookId, notableBookDto.getId());
        assertEquals(bookDto.getId(), notableBookDto.getBookDto().getId());
        assertEquals(bookDto.getTitle(), notableBookDto.getBookDto().getTitle());
    }

    @Test
    void getNotableBooks() {
        BookDto bookDto1 = BookDto.builder().id(UUID.fromString("9cf94139-3bc4-4f25-9154-12961f53f46f")).title("Test Book 1").build();
        BookDto bookDto2 = BookDto.builder().id(UUID.fromString("4d9113af-7af9-486a-938e-2b53882077d9")).title("Test Book 2").build();
        BookDto bookDto3 = BookDto.builder().id(UUID.fromString("646d0dec-4f90-48dd-9481-058d161f9c02")).title("Test Book 3").build();
        BookDto bookDto4 = BookDto.builder().id(UUID.fromString("e881d0a3-e948-4c16-a6a6-16e6e1b3e00c")).title("Test Book 4").build();
        doReturn(List.of(bookDto1, bookDto2, bookDto3, bookDto4)).when(catalogLoaderService).getBooksByIds(anyList());

        Collection<NotableBookDto> notableBookDtoCollection = readingService.getNotableBooks();

        assertNotNull(notableBookDtoCollection);
        assertEquals(4, notableBookDtoCollection.stream().filter(notableBookDto -> notableBookDto.getBookDto() != null).toList().size());
    }

    @Test
    void addNote() {
        UUID notableBookId = UUID.fromString("a9875c41-b8fe-49df-ad6f-62445f74d5ef");
        NoteInput noteInput = NoteInput.builder().content("Test content").build();

        NoteDto noteDto = readingService.addNote(notableBookId, noteInput);

        assertEquals(notableBookId, noteDto.getNotableBookId());
        assertEquals(noteInput.getContent(), noteDto.getContent());
    }

    @Test
    void updateNote() {
        UUID noteId = UUID.fromString("8c81ccda-77f6-4677-a762-b539e7280b43");
        NoteUpdate noteUpdate = NoteUpdate.builder().content("Test content").build();

        NoteDto noteDto = readingService.updateNote(noteId, noteUpdate);

        assertEquals(noteId, noteDto.getId());
        assertEquals(noteUpdate.getContent(), noteDto.getContent());
    }

    @Test
    void deleteNote() {
        UUID noteId = UUID.fromString("aa532f27-26a6-4c41-8299-e5ce43ad1985");

        NoteDto noteDto = readingService.deleteNote(noteId);

        assertEquals(noteId, noteDto.getId());
    }

    @Test
    void getBooksToRead() {
        BookDto bookDto1 = BookDto.builder().id(UUID.fromString("9cf94139-3bc4-4f25-9154-12961f53f46f")).title("Test Book 1").build();
        BookDto bookDto2 = BookDto.builder().id(UUID.fromString("4d9113af-7af9-486a-938e-2b53882077d9")).title("Test Book 2").build();
        BookDto bookDto3 = BookDto.builder().id(UUID.fromString("646d0dec-4f90-48dd-9481-058d161f9c02")).title("Test Book 3").build();
        BookDto bookDto4 = BookDto.builder().id(UUID.fromString("e881d0a3-e948-4c16-a6a6-16e6e1b3e00c")).title("Test Book 4").build();
        doReturn(List.of(bookDto1, bookDto2, bookDto3, bookDto4)).when(catalogLoaderService).getBooksExcludingIds(anyList());

        Collection<BookDto> bookDtoCollection = readingService.getBooksToRead();

        assertNotNull(bookDtoCollection);
        assertEquals(4, bookDtoCollection.size());
    }
}