package com.exalow.mineo.views;

import com.exalow.mineo.models.projects.Project;
import com.exalow.mineo.util.ViewLoader;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

@Component
@FXMLView(fxml = "/assets/views/ProjectBoard.fxml", css = "/assets/views/css/ProjectBoard.css")
public class ProjectBoardController extends AppController implements Initializable {

    @FXML private AnchorPane innerView;
    @FXML private HBox sections;
    @FXML private Label projectName;
    @FXML private Label projectDescription;
    @FXML private Button tasksButton;

    private final ApplicationContext context;
    private ResourceBundle resources;
    private Project project;

    @Autowired
    public ProjectBoardController(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addSelectedEffect(tasksButton);
        this.resources = resources;
    }

    @FXML
    public void onShare() {

    }

    @FXML
    public void onTasks() {
        ProjectTasksController controller = context.getBean(ProjectTasksController.class);
        initInnerView(ViewLoader.load(controller));
        controller.initProject(project);
    }

    @FXML
    public void onSettings() {
        ProjectSettingsController controller = context.getBean(ProjectSettingsController.class);
        initInnerView(ViewLoader.load(controller));
        controller.initProject(project);
    }

    @FXML
    public void onSectionMouseEntered(MouseEvent event) {

        Button button = (Button) event.getSource();

        String text = button.getText();

        Rectangle rectangle = (Rectangle) button.getGraphic();
        rectangle.setWidth(text.length() * 5.5);
        rectangle.setStyle("-fx-fill: #6850EE");
        button.setGraphic(rectangle);
    }

    @FXML
    public void onSectionMouseExited(MouseEvent event) {

        Button button = (Button) event.getSource();

        Rectangle rectangle = (Rectangle) button.getGraphic();
        rectangle.setStyle("-fx-fill: transparent");
        button.setGraphic(rectangle);
    }

    @FXML
    public void onSectionMouseClicked(MouseEvent event) {

        Button button = (Button) event.getSource();

        for (Node node : sections.getChildren()) {
            removeSelectedEffect((Button) node);
            node.addEventHandler(MouseEvent.MOUSE_ENTERED, this::onSectionMouseEntered);
            node.addEventHandler(MouseEvent.MOUSE_EXITED, this::onSectionMouseExited);
        }

        addSelectedEffect(button);
        button.removeEventHandler(MouseEvent.MOUSE_ENTERED, button.getOnMouseEntered());
        button.removeEventHandler(MouseEvent.MOUSE_EXITED, button.getOnMouseExited());
    }

    public void initProject(Project project) {

        if (project == null) {
            return;
        }

        projectName.setText(Objects.requireNonNullElse(project.getName(), resources.getString("project_board.label.default_name")));
        projectDescription.setText(Objects.requireNonNullElse(project.getDescription(), resources.getString("project_board.label.default_description")));
        this.project = project;
    }

    public void addSelectedEffect(Button button) {

        String text = button.getText();

        Rectangle rectangle = (Rectangle) button.getGraphic();
        rectangle.setWidth(text.length() * 5.5);
        rectangle.setStyle("-fx-fill: #6850EE");

        button.setStyle("-fx-text-fill: #1A2D68");
        button.setGraphic(rectangle);
    }

    public void removeSelectedEffect(Button button) {
        Rectangle rectangle = (Rectangle) button.getGraphic();
        rectangle.setStyle("-fx-fill: transparent");
        button.setStyle("-fx-text-fill: #919CA3");
    }

    public void initInnerView(Node node) {
        innerView.getChildren()
                .setAll(node);
    }
}
