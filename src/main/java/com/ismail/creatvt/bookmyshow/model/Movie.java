package com.ismail.creatvt.bookmyshow.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Movie extends BaseModel {
    private String name;
    private String genre;
}
