package com.ismail.creatvt.bookmyshow.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel {
    @ManyToOne
    private Show show;
    @Enumerated(EnumType.ORDINAL)
    private BookingStatus status;
    private Date time;
    private int amount;
    @OneToMany
    private List<Payment> payments;
    @ManyToOne
    private Screen screen;
    @ManyToOne
    private User user;
    @ManyToMany
    private List<ShowSeat> showSeats;
}
