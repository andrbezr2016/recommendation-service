package com.andrbezr2016.library.recommendation.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class NoteInput {

    @NotNull
    private String content;
}
