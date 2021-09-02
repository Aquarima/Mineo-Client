package com.exalow.mineo.views;

import com.exalow.mineo.models.projects.Project;
import com.exalow.mineo.views.factories.TagCellFactory;
import com.exalow.mineo.views.layout.GridView;
import com.exalow.mineo.views.layout.SimpleGridView;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@Scope("prototype")
@FXMLView(fxml = "/assets/views/ProjectOverviewSettings.fxml", css = "/assets/views/css/ProjectOverviewSettings.css")
public class ProjectOverviewSettingsController extends AppController implements Initializable {

    @FXML private TextField name;
    @FXML private TextArea description;
    @FXML private TextField tag;
    @FXML private GridPane gridPane;

    private final ApplicationContext context;
    private GridView<String> gridView;
    private Project project;

    @Autowired
    public ProjectOverviewSettingsController(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gridView = new SimpleGridView<>(gridPane, 1, 3);
        gridView.setCellFactory(new TagCellFactory(context, gridView::removeItem));
    }

    @FXML
    public void onAddTag() {
        gridView.addItem(tag.getText());
    }

    public void initProject(Project project) {

        if (project == null)
            return;

        name.setText(project.getName());
        description.setText(project.getDescription());
        gridView.setItems(FXCollections.observableArrayList(project.getTags()));
        this.project = project;
    }
}
