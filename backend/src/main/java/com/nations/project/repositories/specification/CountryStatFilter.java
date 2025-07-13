package com.nations.project.repositories.specification;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.nations.project.model.Country;
import com.nations.project.model.CountryStat;
import com.nations.project.model.Region;

import jakarta.persistence.criteria.Fetch;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;


public class CountryStatFilter {

    public static Specification<CountryStat> withFilters(
            String regionName, Integer yearFrom, Integer yearTo) {

        return (root, query, cb) -> {
            query.distinct(true);

            // Use proper typing for joins (only for filtering)
            Join<CountryStat, Country> country = root.join("country", JoinType.INNER);
            Join<Country, Region> region = country.join("region", JoinType.LEFT);

            List<Predicate> predicates = new ArrayList<>();

            if (regionName != null && !regionName.trim().isEmpty()) {
                predicates.add(cb.equal(region.get("name"), regionName));
            }

            if (yearFrom != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("year"), yearFrom));
            }

            if (yearTo != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("year"), yearTo));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
