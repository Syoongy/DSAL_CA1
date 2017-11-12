/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Fan Syong Yue
 */
public class AddProjectController implements Initializable {

    @FXML
    private JFXHamburger menuBurger;

    @FXML
    private JFXDrawer menuDrawer;

    @FXML
    private ImageView closeAddBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            VBox menuBox = FXMLLoader.load(getClass().getResource("DrawerContent.fxml"));
            menuDrawer.setSidePane(menuBox);

            menuBurger.setOnMouseClicked(e -> {
                if (menuDrawer.isHidden() || menuDrawer.isHiding()) {
                    menuDrawer.open();
                } else {
                    menuDrawer.close();
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(AddProjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeAddWindow(MouseEvent evt) {
        Stage stage = (Stage) closeAddBtn.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

}
