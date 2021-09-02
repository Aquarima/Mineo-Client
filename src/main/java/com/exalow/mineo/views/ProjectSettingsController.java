package com.exalow.mineo.views;

import com.exalow.mineo.models.projects.Project;
import com.exalow.mineo.util.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ResourceBundle;

@Component
@Scope("prototype")
@FXMLView(fxml = "/assets/views/ProjectSettings.fxml", css = "/assets/views/css/ProjectSettings.css")
public class ProjectSettingsController extends AppController {

    @FXML private AnchorPane innerView;

    private final ApplicationContext context;
    private ResourceBundle resources;
    private Project project;

    @Autowired
    public ProjectSettingsController(ApplicationContext context) {
        this.context = context;
    }

    @FXML
    public void onOverview() {
        ProjectOverviewSettingsController controller = context.getBean(ProjectOverviewSettingsController.class);
        initInnerView(ViewLoader.load(controller));
        controller.initProject(project);
    }

    @FXML
    public void onMembers() {

    }

    @FXML
    public void onIntegrations() {

    }

    public void initInnerView(Node node) {
        innerView.getChildren()
                .setAll(node);
    }

    public void initProject(Project project) {
        if (project == null)
            return;
        this.project = project;
    }
}
