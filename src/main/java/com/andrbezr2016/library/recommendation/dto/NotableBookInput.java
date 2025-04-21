package com.andrbezr2016.library.recommendation.dto;

import com.andrbezr2016.library.recommendation.model.ReadingStatus;
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

    private UUID bookId;
    private Integer score;
    private ReadingStatus status;
}
