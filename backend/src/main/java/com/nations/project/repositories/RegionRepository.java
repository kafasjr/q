package com.nations.project.repositories;

import com.nations.project.model.Continent;
import com.nations.project.model.Country;
import com.nations.project.model.Region;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region,Integer> {
    List<Region> findAll();
}
