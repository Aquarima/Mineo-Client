package com.exalow.mineo.views.factories;

import com.exalow.mineo.models.projects.SimpleProject;
import com.exalow.mineo.util.ViewLoader;
import com.exalow.mineo.views.ProjectCellController;
import com.exalow.mineo.views.layout.GridCellFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import org.springframework.context.ApplicationContext;

public class ProjectCellFactory implements GridCellFactory<SimpleProject> {

    private final ApplicationContext context;

    public ProjectCellFactory(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public Node newCell(SimpleProject item) {
        ProjectCellController controller = context.getBean(ProjectCellController.class);
        Parent parent = ViewLoader.load(controller);
        controller.initProject(item);
        return parent;
    }
}
