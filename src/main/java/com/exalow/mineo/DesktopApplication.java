package com.exalow.mineo;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class DesktopApplication {

    public static void main(String[] args) {
        Application.launch(Launcher.class, args);
    }
}
