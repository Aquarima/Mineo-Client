package com.exalow.mineo.models.projects.tasks;

import java.util.Arrays;

public enum TaskPriority {

    UNKNOWN(-1, "#7F8C8D"),
    LOW(0, "#3498DB"),
    NORMAL(1, "#27AE60"),
    MEDIUM(2, "#E67E22"),
    HIGH(3, "#E74C3C");

    private final int key;
    private final String color;

    TaskPriority(int key, String color) {
        this.key = key;
        this.color = color;
    }

    public static TaskPriority fromKey(int key) {
        return Arrays.stream(values())
                .filter(status -> status.key == key)
                .findAny()
                .orElse(UNKNOWN);
    }

    public int getKey() {
        return key;
    }

    public String getColor() {
        return color;
    }
}
