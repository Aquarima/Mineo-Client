package com.exalow.mineo.views;

import com.exalow.mineo.StageManager;
import com.exalow.mineo.util.ViewLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.*;

@Component
@FXMLView(fxml = "/assets/views/MainBoard.fxml", css = "/assets/views/css/MainBoard.css")
public class MainBoardController extends AppController implements Initializable {

    @FXML private Label sectionName;
    @FXML private Rectangle avatarFrame;
    @FXML private Label username;
    @FXML private ImageView nextSectionIcon;
    @FXML private ImageView previousSectionIcon;
    @FXML private ImageView notificationsIcon;
    @FXML private ImageView dashboardIcon;
    @FXML private ImageView projectsIcon;
    @FXML private ImageView settingsIcon;
    @FXML private ImageView helpIcon;
    @FXML private ImageView logoutIcon;
    @FXML private AnchorPane section;

    private final ApplicationContext context;
    private final StageManager stageManager;
    private ResourceBundle resources;
    private final List<Map.Entry<String, Runnable>> sections;
    private int sectionIndex;

    @Autowired
    public MainBoardController(ApplicationContext context, StageManager stageManager) {
        this.context = context;
        this.stageManager = stageManager;
        this.sections = new ArrayList<>();
        this.sectionIndex = 0;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        sections.add(new AbstractMap.SimpleEntry<>("Dashboard", this::onDashboard));
        sections.add(new AbstractMap.SimpleEntry<>("Projects", this::onProjects));
        sections.add(new AbstractMap.SimpleEntry<>("Settings", this::onSettings));

        Image placeholder = new Image("/assets/images/MAINBOARD_profile_picture_placeholder.png");
        avatarFrame.setFill(new ImagePattern(placeholder));
        this.resources = resources;
        this.onDashboard();
    }

    @FXML
    public void onDashboardMouseEntered() {
        dashboardIcon.setImage(new Image("/assets/images/MAINBOARD_dashboard_hover.png"));
    }

    @FXML
    public void onDashboardMouseExited() {
        dashboardIcon.setImage(new Image("/assets/images/MAINBOARD_dashboard.png"));
    }

    @FXML
    public void onDashboard() {
        sectionName.setText(resources.getString("main_board.label.section.dashboard"));
        stageManager.setTitle(resources.getString("stage.title.dashboard"));
        this.sectionIndex = 0;
    }

    @FXML
    public void onProjectsMouseEntered() {
        projectsIcon.setImage(new Image("/assets/images/MAINBOARD_projects_hover.png"));
    }

    @FXML
    public void onProjectsMouseExited() {
        projectsIcon.setImage(new Image("/assets/images/MAINBOARD_projects.png"));
    }

    @FXML
    public void onProjects() {
        sectionName.setText(resources.getString("main_board.label.section.projects"));
        stageManager.setTitle(resources.getString("stage.title.projects"));
        initInnerView(ProjectsController.class);
        this.sectionIndex = 1;
    }

    @FXML
    public void onSettingsMouseEntered() {
        settingsIcon.setImage(new Image("/assets/images/MAINBOARD_settings_hover.png"));
    }

    @FXML
    public void onSettingsMouseExited() {
        settingsIcon.setImage(new Image("/assets/images/MAINBOARD_settings.png"));
    }

    @FXML
    public void onSettings() {
        sectionName.setText(resources.getString("main_board.label.section.settings"));
        stageManager.setTitle(resources.getString("stage.title.settings"));
        initInnerView(SettingsController.class);
        this.sectionIndex = 2;
    }

    @FXML
    public void onAccount() {
        sectionName.setText(resources.getString("main_board.label.section.account"));
        stageManager.setTitle(resources.getString("stage.title.account"));
    }

    @FXML
    public void onHelpMouseEntered() {
        helpIcon.setImage(new Image("/assets/images/MAINBOARD_help_hover.png"));
    }

    @FXML
    public void onHelpMouseExited() {
        helpIcon.setImage(new Image("/assets/images/MAINBOARD_help.png"));
    }

    @FXML
    public void onHelp() {

    }

    @FXML
    public void onLogoutMouseEntered() {
        logoutIcon.setImage(new Image("/assets/images/MAINBOARD_logout_hover.png"));
    }

    @FXML
    public void onLogoutMouseExited() {
        logoutIcon.setImage(new Image("/assets/images/MAINBOARD_logout.png"));
    }

    @FXML
    public void onLogout() {
        stageManager.setTitle(resources.getString("stage.title.welcome"));
    }

    @FXML
    public void onNextSectionMouseEntered() {
        nextSectionIcon.setImage(new Image("/assets/images/MAINBOARD_next_section_hover.png"));
    }

    @FXML
    public void onNextSectionMouseExited() {
        nextSectionIcon.setImage(new Image("/assets/images/MAINBOARD_next_section.png"));
    }

    @FXML
    public void onNextSection() {

        int newIndex = sectionIndex - 1;

        if (newIndex < 0) {
            return;
        }

        sections.get(newIndex)
                .getValue()
                .run();

        this.sectionIndex = newIndex;
    }

    @FXML
    public void onPreviousSectionMouseEntered() {
        previousSectionIcon.setImage(new Image("/assets/images/MAINBOARD_previous_section_hover.png"));
    }

    @FXML
    public void onPreviousSectionMouseExited() {
        previousSectionIcon.setImage(new Image("/assets/images/MAINBOARD_previous_section.png"));
    }

    @FXML
    public void onPreviousSection() {

        int maxIndex = (sections.size() - 1);
        int newIndex = sectionIndex + 1;

        if (newIndex > maxIndex) {
            return;
        }

        sections.get(newIndex)
                .getValue()
                .run();

        this.sectionIndex = newIndex;
    }

    @FXML
    public void onNotificationsMouseEntered() {
        notificationsIcon.setImage(new Image("/assets/images/MAINBOARD_notifications_hover.png"));
    }

    @FXML
    public void onNotificationsMouseExited() {
        notificationsIcon.setImage(new Image("/assets/images/MAINBOARD_notifications.png"));
    }

    @FXML
    public void onNotifications() {

    }

    @FXML
    public void onNews() {

    }

    private void initInnerView(Class<? extends AppController> type) {
        section.getChildren()
                .addAll(ViewLoader.load(context.getBean(type)));
    }
}
