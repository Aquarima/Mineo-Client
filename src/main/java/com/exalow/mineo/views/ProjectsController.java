package com.exalow.mineo.views;

import com.exalow.mineo.models.projects.Project;
import com.exalow.mineo.models.projects.SimpleProject;
import com.exalow.mineo.services.impl.ProjectServiceImpl;
import com.exalow.mineo.util.ViewLoader;
import com.exalow.mineo.views.factories.ProjectCellFactory;
import com.exalow.mineo.views.layout.GridView;
import com.exalow.mineo.views.layout.SimpleGridView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
@FXMLView(fxml = "/assets/views/Projects.fxml", css = "/assets/views/css/Projects.css")
public class ProjectsController extends AppController implements Initializable {

    @FXML private AnchorPane innerView;
    @FXML private TextField filter;
    @FXML private GridPane gridPane;

    private final ApplicationContext context;
    private GridView<SimpleProject> gridView;

    private ProjectBoardController projectBoard;

    @Autowired
    public ProjectsController(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<SimpleProject> projects = context.getBean(ProjectServiceImpl.class)
                .findProjects();

        ObservableList<SimpleProject> observableList = FXCollections.observableList(projects);
        gridView = new SimpleGridView<>(gridPane, observableList, 1, 100);
        gridView.setCellFactory(new ProjectCellFactory(context));
        gridView.nextPage();

        this.projectBoard = context.getBean(ProjectBoardController.class);
    }

    @FXML
    public void onNameFilterUpdate() {
        gridView.showOnly(project -> {
            String name = project.getName();
            return name.startsWith(filter.getText());
        });
    }

    @FXML
    public void onCreate() {
        context.getBean(CreateProjectController.class)
                .show();
    }

    public void updateDisplayedProject(Project project) {

        List<Node> child = innerView.getChildren();

        if (child.isEmpty()) {
            child.add(ViewLoader.load(projectBoard));
        }

        projectBoard.initProject(project);
        projectBoard.onTasks();
    }
}
