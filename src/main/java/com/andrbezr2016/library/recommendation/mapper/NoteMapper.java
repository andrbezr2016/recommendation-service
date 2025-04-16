package com.andrbezr2016.library.recommendation.mapper;

import com.andrbezr2016.library.recommendation.dto.NoteDto;
import com.andrbezr2016.library.recommendation.entity.Note;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoteMapper {

    NoteDto toDto(Note note);

    Note toEntity(NoteDto noteDto);
}
