package com.exalow.mineo.views;

import com.exalow.mineo.util.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@FXMLView(fxml = "/assets/views/Settings.fxml", css = "/assets/views/css/Settings.css")
public class SettingsController extends AppController {

    @FXML private ScrollPane scrollPane;

    private final ApplicationContext context;

    @Autowired
    public SettingsController(ApplicationContext context) {
        this.context = context;
    }

    @FXML
    public void onAccount() {

    }

    @FXML
    public void onAppearance() {

    }

    @FXML
    public void onSecurity() {

    }

    @FXML
    public void onData() {

    }

    @FXML
    public void onIntegrations() {

    }

    public void initInnerView(AppController controller) {
        scrollPane.setContent(ViewLoader.load(controller));
    }
}
