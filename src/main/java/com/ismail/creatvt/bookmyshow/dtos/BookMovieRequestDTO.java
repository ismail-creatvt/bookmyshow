package com.ismail.creatvt.bookmyshow.dtos;

import lombok.Data;

import java.util.List;

@Data
public class BookMovieRequestDTO {
    private long userId;
    private long showId;
    private List<Long> seatIds;
}
