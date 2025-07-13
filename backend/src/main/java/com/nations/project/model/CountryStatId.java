package com.nations.project.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryStatId implements Serializable {

    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "year")
    private Integer year;
    
}