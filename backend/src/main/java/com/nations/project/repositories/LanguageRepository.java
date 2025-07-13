package com.nations.project.repositories;

import com.nations.project.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository  extends JpaRepository<Language,Long> {
    Language findBylanguageName(String languageName);
}

