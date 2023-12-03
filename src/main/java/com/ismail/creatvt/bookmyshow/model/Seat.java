package com.ismail.creatvt.bookmyshow.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Seat extends BaseModel{
    private int rowVal;
    private int colVal;
    @ManyToOne
    private Screen screen;
    private int number;
    @ManyToOne
    private SeatType seatType;
}
