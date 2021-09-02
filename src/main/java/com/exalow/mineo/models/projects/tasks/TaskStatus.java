package com.exalow.mineo.models.projects.tasks;

import java.util.Arrays;

public enum TaskStatus {

    UNKNOWN(-1, "#7F8C8D"),

    WAITING(0, ""),

    ACTIVE(1, ""),

    COMPLETED(2, ""),

    ARCHIVED(3, "");

    private final int key;
    private final String color;

    TaskStatus(int key, String color) {
        this.key = key;
        this.color = color;
    }

    public static TaskStatus fromKey(int key) {
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
