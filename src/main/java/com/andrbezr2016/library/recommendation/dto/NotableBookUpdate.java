package com.andrbezr2016.library.recommendation.dto;

import com.andrbezr2016.library.recommendation.model.ReadingStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class NotableBookUpdate {

    @Min(1)
    @Max(5)
    private Integer score;
    private ReadingStatus status;
}
