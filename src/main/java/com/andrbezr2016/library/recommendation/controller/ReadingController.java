package com.andrbezr2016.library.recommendation.controller;

import com.andrbezr2016.library.recommendation.dto.*;
import com.andrbezr2016.library.recommendation.service.ReadingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/reading")
public class ReadingController {

    private final ReadingService readingService;

    @PostMapping("/notableBooks/add")
    public NotableBookDto addNotableBook(@RequestBody NotableBookInput notableBookInput) {
        log.info("Add notable book: {}", notableBookInput);
        return readingService.addNotableBook(notableBookInput);
    }

    @PatchMapping("/notableBooks/{id}/update")
    public NotableBookDto updateNotableBook(@PathVariable("notableBookId") UUID notableBookId, @RequestBody NotableBookUpdate notableBookUpdate) {
        log.info("Update notable book {} by id: {}", notableBookUpdate, notableBookId);
        return readingService.updateNotableBook(notableBookId, notableBookUpdate);
    }

    @DeleteMapping("/notableBooks/{id}/delete")
    public NotableBookDto deleteNotableBook(@PathVariable("notableBookId") UUID notableBookId) {
        log.info("Delete notable book by id: {}", notableBookId);
        return readingService.deleteNotableBook(notableBookId);
    }

    @GetMapping("/notableBooks/{id}")
    public NotableBookDto getNotableBook(@PathVariable("notableBookId") UUID notableBookId) {
        log.info("Get notable book by id: {}", notableBookId);
        return readingService.getNotableBook(notableBookId);
    }

    @GetMapping("/notableBooks/all")
    public Collection<NotableBookDto> getNotableBooks() {
        log.info("Get notable books");
        return readingService.getNotableBooks();
    }

    @PostMapping("/notableBooks/{id}/notes/add")
    public NoteDto addNote(@PathVariable("notableBookId") UUID notableBookId, @RequestBody NoteInput noteInput) {
        log.info("Add note {} to notable book with id: {}", noteInput, notableBookId);
        return readingService.addNote(notableBookId, noteInput);
    }

    @PatchMapping("/notableBooks/notes/{id}/update")
    public NoteDto updateNote(@PathVariable("noteId") UUID noteId, @RequestBody NoteUpdate noteUpdate) {
        log.info("Update note {} by id: {}", noteUpdate, noteId);
        return readingService.updateNote(noteId, noteUpdate);
    }

    @DeleteMapping("/notableBooks/notes/{id}/delete")
    public NoteDto deleteNote(@PathVariable("noteId") UUID noteId) {
        log.info("Delete note by id: {}", noteId);
        return readingService.deleteNote(noteId);
    }

    @GetMapping("/books/getBooksToRead")
    public Collection<BookDto> getBooksToRead() {
        log.info("Get books to read");
        return readingService.getBooksToRead();
    }
}
