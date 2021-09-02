package com.exalow.mineo.models.settings;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Settings")
public class SimpleSettings implements Settings {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private final Long id = 1L;

    @Column(name = "language", nullable = false)
    private Language language;

    @Override
    public Language getLanguage() {
        return (language == null) ? Language.ENGLISH : language;
    }

    @Override
    public void setLanguage(Language language) {
        this.language = language;
    }
}
