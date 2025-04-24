package com.andrbezr2016.library.recommendation.service;

import com.andrbezr2016.library.recommendation.dto.BookDto;
import com.andrbezr2016.library.recommendation.dto.BookFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.graphql.client.GraphQlClient;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CatalogLoaderService {

    private final GraphQlClient graphQlClient;

    public Collection<BookDto> getBooksExcludingIds(Collection<UUID> ids) {
        return graphQlClient.documentName("getBooksExcludingIds").variable("ids", ids).retrieveSync("getBooksExcludingIds").toEntity(new ParameterizedTypeReference<>() {
        });
    }

    public Collection<BookDto> getBooksByIds(Collection<UUID> ids) {
        return graphQlClient.documentName("getBooksByIds").variable("ids", ids).retrieveSync("getBooksByIds").toEntity(new ParameterizedTypeReference<>() {
        });
    }

    public Collection<BookDto> getBooks(BookFilter bookFilter) {
        return graphQlClient.documentName("getBooks").variable("bookFilter", bookFilter).retrieveSync("getBooks").toEntity(new ParameterizedTypeReference<>() {
        });
    }
}
