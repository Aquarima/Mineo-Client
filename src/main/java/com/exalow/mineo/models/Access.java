package com.exalow.mineo.models;

import java.util.ResourceBundle;

public class Access {

    public static final Access LOCAL = load("config/Config0");
    public static final Access CLASSIC = load("config/Config1");
    public static final Access PREMIUM = load("config/Config2");

    private final ResourceBundle bundle;

    private Access(final ResourceBundle bundle) {
        this.bundle = bundle;
    }

    private static Access load(String url) {
        return new Access(ResourceBundle.getBundle(url));
    }

    public String getString(String key) {
        return bundle.getString(key);
    }

    public int getInteger(String key) {
        try {
            return Integer.parseInt(getString(key));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public long getLong(String key) {
        return Long.parseLong(getString(key));
    }

    public float getFloat(String key) {
        try {
            return Float.parseFloat(getString(key));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public double getDouble(String key) {
        try {
            return Double.parseDouble(getString(key));
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
