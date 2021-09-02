package com.exalow.mineo;

import com.exalow.mineo.views.MainBoardController;
import com.exalow.mineo.util.ViewLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StageListener implements ApplicationListener<Launcher.StageReadyEvent> {

    private final String title;
    private final ApplicationContext context;
    private Stage stage;

    StageListener(@Value("${spring.application.ui.title}") String title, ApplicationContext context) {
        this.title = title;
        this.context = context;
    }

    @Override
    public void onApplicationEvent(Launcher.StageReadyEvent event) {

        StageManager manager = context.getBean(StageManager.class);
        manager.setStage(event.getStage());

        Parent parent = ViewLoader.load(Parent.class, context.getBean(MainBoardController.class));
        manager.switchView(parent, true, true);
        stage = event.getStage();
        stage.getIcons().add(new Image("/assets/images/Logo.png"));
        stage.setTitle(title);
        stage.show();
    }

    public Stage getStage() {
        return stage;
    }
}
