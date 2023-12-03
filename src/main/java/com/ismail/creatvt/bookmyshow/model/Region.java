package com.ismail.creatvt.bookmyshow.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
public class Region extends BaseModel {
    private String name;
    @OneToMany
    private List<Theatre> theatres;
}
