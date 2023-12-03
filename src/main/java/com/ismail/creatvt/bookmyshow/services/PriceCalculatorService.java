package com.ismail.creatvt.bookmyshow.services;

import com.ismail.creatvt.bookmyshow.model.Show;
import com.ismail.creatvt.bookmyshow.model.ShowSeat;
import com.ismail.creatvt.bookmyshow.model.ShowSeatType;
import com.ismail.creatvt.bookmyshow.repos.ShowSeatTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculatorService {

    private final ShowSeatTypeRepository showSeatTypeRepository;

    @Autowired
    public PriceCalculatorService(ShowSeatTypeRepository showSeatTypeRepository) {
        this.showSeatTypeRepository = showSeatTypeRepository;
    }

    public int calculatePrice(List<ShowSeat> showSeats, Show show) {
        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findByShow(show);

        int totalAmount = 0;
        for(ShowSeatType showSeatType: showSeatTypes) {
            for(ShowSeat showSeat: showSeats) {
                if(showSeatType.getSeatType().equals(showSeat.getSeat().getSeatType())) {
                    totalAmount += showSeatType.getPrice();
                }
            }
        }
        return totalAmount;
    }
}
