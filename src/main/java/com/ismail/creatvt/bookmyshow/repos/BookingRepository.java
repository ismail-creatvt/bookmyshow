package com.ismail.creatvt.bookmyshow.repos;

import com.ismail.creatvt.bookmyshow.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking save(Booking entity);
}
