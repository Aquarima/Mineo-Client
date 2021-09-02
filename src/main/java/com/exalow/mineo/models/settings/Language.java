package com.exalow.mineo.models.settings;

import java.util.ResourceBundle;

public enum Language {

    ENGLISH("lang/EN_en"),
    FRANCAIS("lang/FR_fr");

    private final String url;

    Language(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public ResourceBundle getResourceBundle() {
        return ResourceBundle.getBundle(url);
    }
}
