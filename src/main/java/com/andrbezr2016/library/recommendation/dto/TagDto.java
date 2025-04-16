package com.andrbezr2016.library.recommendation.dto;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class TagDto {

    private UUID id;
    private String name;
}
