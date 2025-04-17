package com.andrbezr2016.library.recommendation.dto;

import com.andrbezr2016.library.recommendation.model.ReadingStatus;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class NotableBookInput {

    private BookDto bookDto;
    private Integer score;
    private ReadingStatus status;
}
