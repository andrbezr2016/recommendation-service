package com.andrbezr2016.library.recommendation.mapper;

import com.andrbezr2016.library.recommendation.dto.NotableBookDto;
import com.andrbezr2016.library.recommendation.dto.NotableBookInput;
import com.andrbezr2016.library.recommendation.dto.NotableBookUpdate;
import com.andrbezr2016.library.recommendation.entity.NotableBook;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring", uses = NoteMapper.class)
public interface NotableBookMapper {

    @Mapping(target = "bookDto.id", source = "notableBook.bookId")
    NotableBookDto toDto(NotableBook notableBook);

    Collection<NotableBookDto> toDtoCollection(Collection<NotableBook> notableBooks);

    @Mapping(target = "bookId", source = "notableBookDto.bookDto.id")
    NotableBook toEntity(NotableBookDto notableBookDto);

    NotableBook toEntity(NotableBookInput notableBookInput);

    NotableBook toEntity(NotableBookUpdate notableBookUpdate);
}
