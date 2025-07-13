package com.nations.project.controller;

import com.nations.project.config.AppConstants;
import com.nations.project.payload.CountryResponse;
import com.nations.project.payload.CountryStatsAndRegInfoResponse;
import com.nations.project.payload.CountryStatsResponse;
import com.nations.project.payload.LangSpokenResponse;
import com.nations.project.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CountryController {

        @Autowired
        private CountryService countryService;



        @GetMapping("/countries")
        public ResponseEntity<CountryResponse> getAllCountries(
                @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize) {
        CountryResponse countryResponse = countryService.getAllCountries(pageNumber, pageSize);
        return new ResponseEntity<>(countryResponse, HttpStatus.OK);
        }
        @GetMapping("/languages")
        public ResponseEntity<LangSpokenResponse> getAllLanguages(
                @RequestParam(name = "country", defaultValue = AppConstants.PAGE_NUMBER, required = false) String countryName,
                @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize) {

                LangSpokenResponse langSpokenResponse = countryService.getLanguagesByCountryName(countryName);
                return new ResponseEntity<>(langSpokenResponse, HttpStatus.OK);
        }

        @GetMapping("/stats")
        public ResponseEntity<CountryStatsResponse> getStats(
                @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize) {
                
                CountryStatsResponse countryStatsResponse = countryService.getAllCountriesWithStats(pageNumber, pageSize);
                return new ResponseEntity<>(countryStatsResponse, HttpStatus.OK);
                }
        @GetMapping("/statswithinfo")
        public ResponseEntity<CountryStatsAndRegInfoResponse> getStats2(
                @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize, 
                @RequestParam(name = "regionName", required = false) String regionName,
                @RequestParam(name = "yearFrom", required = false) Integer yearFrom,
                @RequestParam(name = "yearTo", required = false) Integer yearTo){
                        CountryStatsAndRegInfoResponse countryStatsResponse = countryService.findAllWithStatsAndRegInfo(pageNumber, pageSize, regionName, yearFrom, yearTo);
                        return new ResponseEntity<>(countryStatsResponse, HttpStatus.OK);
                }
}
