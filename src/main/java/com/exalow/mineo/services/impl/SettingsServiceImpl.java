package com.exalow.mineo.services.impl;

import com.exalow.mineo.models.settings.SimpleSettings;
import com.exalow.mineo.repositories.SettingsRepository;
import com.exalow.mineo.services.SettingsService;
import org.springframework.stereotype.Service;

@Service
public class SettingsServiceImpl implements SettingsService {

    private final SettingsRepository repository;

    SettingsServiceImpl(SettingsRepository repository){
        this.repository = repository;
    }

    @Override
    public SimpleSettings findSettingsById(Long id) {
        return repository.getById(id);
    }
}
