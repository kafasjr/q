package com.nations.project.service;

import com.nations.project.payload.CountryResponse;
import com.nations.project.payload.CountryStatsAndRegInfoDTO;
import com.nations.project.payload.CountryStatsAndRegInfoResponse;
import com.nations.project.payload.CountryStatsResponse;
import com.nations.project.payload.LangSpokenResponse;

public interface CountryService {
    CountryResponse getAllCountries(Integer pageNumber, Integer pageSize);
    LangSpokenResponse getLanguagesByCountryName(String countryName);
    
    CountryStatsResponse getAllCountriesWithStats(Integer pageNumber, Integer pageSize);
    CountryStatsAndRegInfoResponse findAllWithStatsAndRegInfo(Integer pageNumber, Integer pageSize,  String regionName, Integer yearFrom, Integer yearTo);

    

}
