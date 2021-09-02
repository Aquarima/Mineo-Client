package com.exalow.mineo.services;

import com.exalow.mineo.models.settings.SimpleSettings;

public interface SettingsService {

    SimpleSettings findSettingsById(Long id);
}
