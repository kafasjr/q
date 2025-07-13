package com.nations.project.payload;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryStatsDTO {
    private String name;
    private String countryCode3;
    private Integer year;
    private Integer population;
    private BigDecimal gdp;


}
