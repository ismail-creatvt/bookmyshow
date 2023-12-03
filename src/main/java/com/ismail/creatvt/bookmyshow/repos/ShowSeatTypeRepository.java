package com.ismail.creatvt.bookmyshow.repos;

import com.ismail.creatvt.bookmyshow.model.Show;
import com.ismail.creatvt.bookmyshow.model.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Long> {

    List<ShowSeatType> findByShow(Show show);

}
