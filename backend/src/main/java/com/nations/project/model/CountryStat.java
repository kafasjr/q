package com.nations.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "country_stats")
@IdClass(CountryStatId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryStat {
    @Id
    @Column(name = "country_id")
    private Integer countryId;
    
    @Id
    @Column(name = "year")
    private Integer year;
    
    @Column(name = "population")
    private Integer population;
    
    @Column(name = "gdp", precision = 15, scale = 0)
    private BigDecimal gdp;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", insertable = false, updatable = false)
    private Country country;
}

