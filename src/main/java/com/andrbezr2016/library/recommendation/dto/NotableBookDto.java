package com.andrbezr2016.library.recommendation.dto;

import com.andrbezr2016.library.recommendation.model.ReadingStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class NotableBookDto {

    private UUID id;
    private BookDto bookDto;
    private Integer score;
    private ReadingStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<NoteDto> notes;
}
