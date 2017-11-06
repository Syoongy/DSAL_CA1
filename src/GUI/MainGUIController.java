/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 *
 * @author Fan Syong Yue
 */
public class MainGUIController implements Initializable {

    ObservableList<String> genderList = FXCollections.observableArrayList("Male", "Female");

    @FXML
    private ComboBox genderBox;

    public MainGUIController() {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        genderBox.setItems(genderList);
    }

}
