package com.exalow.mineo;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class StageManager {

    private Stage stage;

    public void setTitle(String title) {
        stage.setTitle(title);
    }

    public void switchView(Parent parent, boolean maximized, boolean resizable) {

        if (parent == null || stage == null)
            return;

        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setMaximized(maximized);
        stage.setResizable(resizable);
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
