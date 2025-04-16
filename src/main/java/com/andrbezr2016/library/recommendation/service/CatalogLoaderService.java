package com.andrbezr2016.library.recommendation.service;

import com.andrbezr2016.library.recommendation.dto.BookDto;
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
        return graphQlClient.document("getBooksExcludingIds").variable("ids", ids).retrieveSync("getBooksExcludingIds").toEntity(new ParameterizedTypeReference<>() {
        });
    }

    public Collection<BookDto> getBooksByIds(Collection<UUID> ids) {
        return graphQlClient.document("getBooksByIds").variable("ids", ids).retrieveSync("getBooksByIds").toEntity(new ParameterizedTypeReference<>() {
        });
    }
}
