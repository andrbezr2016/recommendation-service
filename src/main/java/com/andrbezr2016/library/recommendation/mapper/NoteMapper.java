package com.andrbezr2016.library.recommendation.mapper;

import com.andrbezr2016.library.recommendation.dto.NoteDto;
import com.andrbezr2016.library.recommendation.dto.NoteInput;
import com.andrbezr2016.library.recommendation.dto.NoteUpdate;
import com.andrbezr2016.library.recommendation.entity.Note;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoteMapper {

    NoteDto toDto(Note note);

    Note toEntity(NoteDto noteDto);

    Note toEntity(NoteInput noteInput);

    Note toEntity(NoteUpdate noteUpdate);
}
