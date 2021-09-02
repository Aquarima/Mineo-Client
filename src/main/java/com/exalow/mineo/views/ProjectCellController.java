package com.exalow.mineo.views;

import com.exalow.mineo.models.projects.Project;
import com.exalow.mineo.views.factories.TagCellFactory;
import com.exalow.mineo.views.layout.GridView;
import com.exalow.mineo.views.layout.SimpleGridView;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

@Component
@Scope("prototype")
@FXMLView(fxml = "/assets/views/ProjectCell.fxml", css = "/assets/views/css/ProjectCell.css")
public class ProjectCellController extends AppController implements Initializable {

    @FXML private Parent root;
    @FXML private Label name;
    @FXML private Label description;
    @FXML private GridPane gridPane;
    @FXML private Circle frame1;
    @FXML private Circle frame2;
    @FXML private Circle frame3;

    private final ApplicationContext context;
    private ResourceBundle resources;
    private Project project;
    private GridView<String> gridView;

    @Autowired
    public ProjectCellController(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gridView = new SimpleGridView<>(gridPane, 2, 1);
        gridView.setCellFactory(new TagCellFactory(context));
        this.resources = resources;
    }

    @FXML
    public void onClick() {
        context.getBean(ProjectsController.class)
                .updateDisplayedProject(project);
    }

    public void initProject(Project project) {
        name.setText(Objects.requireNonNullElse(project.getName(), resources.getString("project_cell.label.default_name")));
        description.setText(Objects.requireNonNullElse(project.getDescription(), resources.getString("project_cell.label.default_description")));
        gridView.setItems(FXCollections.observableList(project.getTags()));
        this.project = project;
    }
}
