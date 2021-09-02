package com.exalow.mineo.repositories;

import com.exalow.mineo.models.settings.SimpleSettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingsRepository extends JpaRepository<SimpleSettings, Long> {
}
