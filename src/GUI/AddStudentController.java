/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Fan Syong Yue
 */
public class AddStudentController implements Initializable {

    @FXML
    private ImageView closeAddBtn;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void closeAddWindow(MouseEvent evt) {
        Stage stage = (Stage) closeAddBtn.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
