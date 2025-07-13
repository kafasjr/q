package com.nations.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "countries")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Integer countryId;

    @NotBlank
    @Size(min = 2, max = 50)
    private String name;

    @NotNull
    private BigDecimal area;

    @Column(name = "national_day")
    private LocalDate nationalDay;

    @Column(name = "country_code2", length = 2, unique = true, nullable = false)
    @NotBlank
    @Size(min = 2, max = 2)
    private String countryCode2;

    @Column(name = "country_code3", length = 3, unique = true, nullable = false)
    @NotBlank
    @Size(min = 3, max = 3)
    private String countryCode3;

    @Column(name = "region_id", nullable = false)
    private Integer regionId;
    
    
    @ManyToMany
    @JoinTable(
        name = "country_languages",
        joinColumns = @JoinColumn(name = "country_id"),
        inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    private Set<Language> languages = new HashSet<>();


    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<CountryStat> stats;
    
    @ManyToOne    
    @JoinColumn(name = "region_id", insertable = false, updatable = false)
    private Region region;
    
    
}
