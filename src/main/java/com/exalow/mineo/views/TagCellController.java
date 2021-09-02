package com.exalow.mineo.views;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@Scope("prototype")
@FXMLView(fxml = "/assets/views/TagCell.fxml", css = "/assets/views/css/TagCell.css")
public class TagCellController extends AppController {

    @FXML private Label name;

    private final ApplicationContext context;
    private Consumer<String> onClick;

    @Autowired
    public TagCellController(ApplicationContext context) {
        this.context = context;
    }

    @FXML
    public void onClick() {
        if (onClick != null)
            onClick.accept(name.getText());
    }

    public void setOnClick(Consumer<String> onClick) {
        this.onClick = onClick;
    }

    public void initName(String text) {
        name.setText(text);
    }
}
