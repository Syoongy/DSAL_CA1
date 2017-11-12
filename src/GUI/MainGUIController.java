/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Fan Syong Yue
 */
public class MainGUIController implements Initializable {

    ObservableList<String> genderList = FXCollections.observableArrayList("Male", "Female");
    private double xOffset = 0, yOffset = 0;
    
    @FXML
    private ComboBox genderBox;

    public MainGUIController() {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        genderBox.setItems(genderList);
    }

    public void closeApp(MouseEvent evt) {
        Platform.exit();
    }

    public void addProject(MouseEvent evt) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("AddProject.fxml"));
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        root.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
        Scene scene = new Scene(root);
        //Platform.setImplicitExit(false);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

}
