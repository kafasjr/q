package com.nations.project.service;

import com.nations.project.model.Continent;
import com.nations.project.model.Country;
import com.nations.project.model.CountryStat;
import com.nations.project.model.Region;
import com.nations.project.payload.CountryDTO;
import com.nations.project.payload.CountryResponse;
import com.nations.project.payload.CountryStatsAndRegInfoDTO;
import com.nations.project.payload.CountryStatsAndRegInfoResponse;
import com.nations.project.payload.CountryStatsDTO;
import com.nations.project.payload.CountryStatsResponse;
import com.nations.project.payload.LangSpokenResponse;
import com.nations.project.payload.LanguageDTO;
import com.nations.project.repositories.CountryRepository;
import com.nations.project.repositories.CountryStatRepository;
import com.nations.project.repositories.specification.CountryStatFilter;
import org.springframework.data.jpa.domain.Specification;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService{

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CountryStatRepository countryStatRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CountryResponse getAllCountries(Integer pageNumber, Integer pageSize) {
        Pageable pageDetails = PageRequest.of(pageNumber, pageSize);
        Page<Country> countryPage = countryRepository.findAll(pageDetails);

        List<Country> countries = countryPage.getContent();
        
        List<CountryDTO> countryDTOS = countries.stream()
                .map(country -> modelMapper.map(country, CountryDTO.class))
                .toList();

        CountryResponse countryResponse = new CountryResponse();
        countryResponse.setContent(countryDTOS);
        countryResponse.setPageNumber(countryPage.getNumber());
        countryResponse.setPageSize(countryPage.getSize());
        countryResponse.setTotalElements(countryPage.getTotalElements());
        countryResponse.setTotalpages(countryPage.getTotalPages());
        countryResponse.setLastPage(countryPage.isLast());
        return countryResponse;
    }

    @Override
    public LangSpokenResponse  getLanguagesByCountryName(String countryName) {
        Country country = countryRepository.findByName(countryName);
            
        Set<LanguageDTO> languageDTOs = country.getLanguages()
            .stream()
            .map(language -> {
                LanguageDTO dto = new LanguageDTO();
                dto.setId(language.getLanguageId());
                dto.setLanguageName(language.getLanguageName());
                return dto;
            })
            .collect(Collectors.toSet());

        LangSpokenResponse response = new LangSpokenResponse();
        response.setCountry(countryName);
        response.setLanguages(languageDTOs);

        return response;
    }


    public CountryStatsResponse getAllCountriesWithStats(Integer pageNumber, Integer pageSize) {
    
        Pageable pageDetails = PageRequest.of(pageNumber, pageSize);
        Page<Country> countryPage = countryRepository.findAll(pageDetails);
        List<Country> countries = countryPage.getContent();    
        List<CountryStatsDTO> countryStatsDTOs = countries.stream()
        .map(country -> {
            if (country.getStats() == null || country.getStats().isEmpty()) {
                return null; 
            }

            CountryStat maxRatioStat = country.getStats().stream()
                .max((stat1, stat2) -> {
                    double ratio1 = (double) stat1.getPopulation() / stat1.getGdp().doubleValue();
                    double ratio2 = (double) stat2.getPopulation() / stat2.getGdp().doubleValue();
                    return Double.compare(ratio1, ratio2);
                })
                .orElseThrow(() -> new IllegalStateException("No stats found for country: " + country.getName()));

            return new CountryStatsDTO(
                country.getName(),
                country.getCountryCode3(),
                maxRatioStat.getYear(),
                maxRatioStat.getPopulation(),
                maxRatioStat.getGdp()
            );
        })
        .filter(Objects::nonNull) 
        .collect(Collectors.toList());

        CountryStatsResponse countryStatsResponse = new CountryStatsResponse();
        countryStatsResponse.setContent(countryStatsDTOs);
        countryStatsResponse.setPageNumber(countryPage.getNumber());
        countryStatsResponse.setPageSize(countryPage.getSize());
        countryStatsResponse.setTotalElements(countryPage.getTotalElements());
        countryStatsResponse.setTotalpages(countryPage.getTotalPages());
        countryStatsResponse.setLastPage(countryPage.isLast());
        return countryStatsResponse;
    }

    public CountryStatsAndRegInfoResponse findAllWithStatsAndRegInfo(
        Integer pageNumber, Integer pageSize, String regionName,
        Integer yearFrom, Integer yearTo) {


        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Specification<CountryStat> spec = CountryStatFilter.withFilters(regionName, yearFrom, yearTo);

        Page<CountryStat> page = countryStatRepository.findAll(spec, pageable);


        List<CountryStatsAndRegInfoDTO> dtos = page.getContent().stream()
            .map(stat -> {
                Country country = stat.getCountry();
                String regName = country.getRegion() != null ? country.getRegion().getName() : null;
                String contName = country.getRegion() != null && country.getRegion().getContinent() != null
                    ? country.getRegion().getContinent().getName()
                    : null;

                return new CountryStatsAndRegInfoDTO(
                    country.getName(),
                    country.getCountryCode3(),
                    stat.getYear(),
                    stat.getPopulation(),
                    stat.getGdp(),
                    regName,
                    contName
                );
            })
            .collect(Collectors.toList());

        return new CountryStatsAndRegInfoResponse(
            dtos,
            page.getNumber(),
            page.getSize(),
            page.getTotalElements(),
            page.getTotalPages(),
            page.isLast()
        );
    }


}
