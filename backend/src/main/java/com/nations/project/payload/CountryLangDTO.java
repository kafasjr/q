package com.nations.project.payload;


import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryLangDTO {
    private String country;
    private Set<String> languages;

}
