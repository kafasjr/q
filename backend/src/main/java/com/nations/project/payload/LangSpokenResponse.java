package com.nations.project.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LangSpokenResponse {
//    private List<CountryLangDTO> content;
    private String country;
    private Set<LanguageDTO> languages;

}
