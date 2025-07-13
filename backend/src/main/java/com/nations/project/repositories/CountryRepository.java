package com.nations.project.repositories;

import com.nations.project.model.Country;
import com.nations.project.model.CountryStat;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CountryRepository extends JpaRepository<Country,Integer> {
    Country findByName(String name);
    
    
    List<Country> findAll();

}

