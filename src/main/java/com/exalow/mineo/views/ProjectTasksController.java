package com.exalow.mineo.views;

import com.exalow.mineo.models.projects.Project;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FXMLView(fxml = "/assets/views/ProjectTasks.fxml", css = "/assets/views/css/ProjectTasks.css")
public class ProjectTasksController extends AppController implements Initializable {

    private final ApplicationContext context;
    private Project project;

    @Autowired
    public ProjectTasksController(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void onMonth() {

    }

    @FXML
    public void onWeek() {

    }

    @FXML
    public void onDay() {

    }

    public void initProject(Project project) {
        if (project == null)
            return;
        this.project = project;
    }
}
