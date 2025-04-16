package com.andrbezr2016.library.recommendation.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class NoteDto {

    private UUID id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
