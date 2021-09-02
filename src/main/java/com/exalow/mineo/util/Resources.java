package com.exalow.mineo.util;

import java.io.InputStream;
import java.net.URL;

public class Resources {

    public static URL getResource(String name) {
        return Resources.class.getResource(name);
    }

    public static InputStream getResourceAsStream(String name) {
        return Resources.class.getResourceAsStream(name);
    }
}
