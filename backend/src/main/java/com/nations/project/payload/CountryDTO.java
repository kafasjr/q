package com.nations.project.payload;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryDTO {
    //private Long countryId;
    private String name;
    private BigDecimal area;
    //private LocalDate nationalDay;
    private String countryCode2;
    //private String countryCode3;
    //private Integer regionId;
}
