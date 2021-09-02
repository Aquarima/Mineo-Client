package com.exalow.mineo.views;

import com.exalow.mineo.StageListener;
import com.exalow.mineo.util.ViewLoader;
import com.exalow.mineo.views.factories.TagCellFactory;
import com.exalow.mineo.views.layout.GridView;
import com.exalow.mineo.views.layout.SimpleGridView;
import com.jfoenix.controls.JFXProgressBar;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
@Scope("prototype")
@FXMLView(fxml = "/assets/views/CreateProject.fxml", css = "/assets/views/css/CreateProject.css")
public class CreateProjectController extends AppController implements Initializable {

    @FXML private Label progress;
    @FXML private JFXProgressBar progressBar;
    @FXML private TextArea description;
    @FXML private GridPane gridPane;
    @FXML private TextField tag;
    @FXML private AnchorPane layer0;

    private final ApplicationContext context;
    private final List<Node> layers;
    private GridView<String> gridView;
    private int layerIndex;
    private Stage stage;

    @Autowired
    public CreateProjectController(ApplicationContext context) {
        this.context = context;
        this.layers = new ArrayList<>();
        this.layerIndex = 0;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        layers.add(layer0);
        gridView = new SimpleGridView<>(gridPane, 3, 1);
        gridView.setCellFactory(new TagCellFactory(context, gridView::removeItem));
    }

    @FXML
    public void onTagKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            onAddTag();
        }
    }

    @FXML
    public void onAddTag() {
        gridView.addItem(tag.getText());
    }

    @FXML
    public void onBack() {

        int newIndex = layerIndex - 1;

        if (newIndex < 0) {
            return;
        }

        double value = (double) newIndex / (double) (layers.size() - 1);

        progressBar.setProgress(value);
        progress.setText(Math.round(value * 100) + "%");

        layers.get(newIndex)
                .toFront();

        this.layerIndex = newIndex;
    }

    @FXML
    public void onNext() {

        int maxIndex = (layers.size() - 1);
        int newIndex = layerIndex + 1;

        if (newIndex > maxIndex) {
            return;
        }

        double value = (double) newIndex / (double) (layers.size() - 1);;

        progressBar.setProgress(value);
        progress.setText(Math.round(value * 100) + "%");

        layers.get(newIndex)
                .toFront();

        if (value == 1) {
            this.onCreate();
        }

        this.layerIndex = newIndex;
    }

    @FXML
    public void onCancel() {
        stage.close();
    }

    @FXML
    public void onCreate() {

        List<String> tags = gridView.getItems();
    }

    public void show() {

        Stage main = context.getBean(StageListener.class)
                .getStage();

        stage = new Stage();
        stage.setScene(new Scene(ViewLoader.load(this)));
        stage.initOwner(main);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.getIcons().add(new Image("/assets/images/Logo.png"));
        stage.setTitle("New Project");
        stage.setResizable(false);
        stage.show();
    }
}
