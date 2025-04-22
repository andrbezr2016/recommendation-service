package com.andrbezr2016.library.recommendation.dto;

import com.andrbezr2016.library.recommendation.model.ReadingStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class NotableBookInput {

    @NotNull
    private UUID bookId;
    private Integer score;
    @NotNull
    private ReadingStatus status;
}
