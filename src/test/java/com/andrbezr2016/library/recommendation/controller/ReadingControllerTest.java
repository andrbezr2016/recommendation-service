package com.andrbezr2016.library.recommendation.controller;

import com.andrbezr2016.library.recommendation.dto.*;
import com.andrbezr2016.library.recommendation.model.ReadingStatus;
import com.andrbezr2016.library.recommendation.service.ReadingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ReadingControllerTest {

    @Autowired
    MockMvc tester;
    @Autowired
    ObjectMapper objectMapper;
    @MockitoBean
    ReadingService readingService;

    @Test
    void addNotableBook() throws Exception {
        UUID bookId = UUID.randomUUID();
        NotableBookInput notableBookInput = NotableBookInput.builder().bookId(bookId).status(ReadingStatus.IN_PROGRESS).build();
        NotableBookDto notableBookDto = NotableBookDto.builder().bookDto(BookDto.builder().id(bookId).build()).build();

        doReturn(notableBookDto).when(readingService).addNotableBook(eq(notableBookInput));

        tester.perform(post("/reading/notableBooks/add").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(notableBookInput)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(notableBookDto)));
    }

    @Test
    void updateNotableBook() throws Exception {
        UUID notableBookId = UUID.randomUUID();
        NotableBookUpdate notableBookUpdate = NotableBookUpdate.builder().score(100).build();
        NotableBookDto notableBookDto = NotableBookDto.builder().id(notableBookId).score(100).build();

        doReturn(notableBookDto).when(readingService).updateNotableBook(eq(notableBookId), eq(notableBookUpdate));

        tester.perform(patch("/reading/notableBooks/{notableBookId}/update", notableBookId).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(notableBookUpdate)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(notableBookDto)));
    }

    @Test
    void deleteNotableBook() throws Exception {
        UUID notableBookId = UUID.randomUUID();
        NotableBookDto notableBookDto = NotableBookDto.builder().id(notableBookId).build();

        doReturn(notableBookDto).when(readingService).deleteNotableBook(eq(notableBookId));

        tester.perform(delete("/reading/notableBooks/{notableBookId}/delete", notableBookId))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(notableBookDto)));
    }

    @Test
    void getNotableBook() throws Exception {
        UUID notableBookId = UUID.randomUUID();
        NotableBookDto notableBookDto = NotableBookDto.builder().id(notableBookId).build();

        doReturn(notableBookDto).when(readingService).getNotableBook(eq(notableBookId));

        tester.perform(get("/reading/notableBooks/{notableBookId}", notableBookId))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(notableBookDto)));
    }

    @Test
    void getNotableBooks() throws Exception {
        NotableBookDto notableBookDto1 = NotableBookDto.builder().id(UUID.randomUUID()).build();
        NotableBookDto notableBookDto2 = NotableBookDto.builder().id(UUID.randomUUID()).build();
        List<NotableBookDto> notableBookDtoList = List.of(notableBookDto1, notableBookDto2);

        doReturn(notableBookDtoList).when(readingService).getNotableBooks();

        tester.perform(get("/reading/notableBooks/all"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(notableBookDtoList)));
    }

    @Test
    void addNote() throws Exception {
        UUID notableBookId = UUID.randomUUID();
        String content = "Test note content!";
        NoteInput noteInput = NoteInput.builder().content(content).build();
        NoteDto noteDto = NoteDto.builder().content(content).build();

        doReturn(noteDto).when(readingService).addNote(eq(notableBookId), eq(noteInput));

        tester.perform(post("/reading/notableBooks/{notableBookId}/notes/add", notableBookId).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(noteInput)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(noteDto)));
    }

    @Test
    void updateNote() throws Exception {
        UUID noteId = UUID.randomUUID();
        String content = "Test note content!";
        NoteUpdate noteUpdate = NoteUpdate.builder().content(content).build();
        NoteDto noteDto = NoteDto.builder().id(noteId).content(content).build();

        doReturn(noteDto).when(readingService).updateNote(eq(noteId), eq(noteUpdate));

        tester.perform(patch("/reading/notableBooks/notes/{noteId}/update", noteId).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(noteUpdate)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(noteDto)));
    }

    @Test
    void deleteNote() throws Exception {
        UUID noteId = UUID.randomUUID();
        String content = "Test note content!";
        NoteDto noteDto = NoteDto.builder().id(noteId).content(content).build();

        doReturn(noteDto).when(readingService).deleteNote(eq(noteId));

        tester.perform(delete("/reading/notableBooks/notes/{noteId}/delete", noteId))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(noteDto)));
    }

    @Test
    void getBooksToRead() throws Exception {
        BookDto bookDto1 = BookDto.builder().id(UUID.randomUUID()).build();
        BookDto bookDto2 = BookDto.builder().id(UUID.randomUUID()).build();
        List<BookDto> bookDtoList = List.of(bookDto1, bookDto2);

        doReturn(bookDtoList).when(readingService).getBooksToRead();

        tester.perform(get("/reading/books/getBooksToRead"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(bookDtoList)));
    }
}