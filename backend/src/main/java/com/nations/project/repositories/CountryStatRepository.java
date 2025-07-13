package com.nations.project.repositories;

import com.nations.project.model.CountryStat;

import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CountryStatRepository extends JpaRepository<CountryStat,Integer>, JpaSpecificationExecutor<CountryStat> {
    
    
    @EntityGraph(attributePaths = {"country", "country.region", "country.region.continent"})
    @Override
    List<CountryStat> findAll();
}



