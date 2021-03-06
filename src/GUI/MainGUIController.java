/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import IO.ProjectFile;
import Model.Project;
import Model.Student;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView.TreeTableViewSelectionModel;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 *
 * @author Fan Syong Yue
 */
public class MainGUIController implements Initializable {

    ArrayList<Project> projects = new ArrayList<>();
    int currentProjectIndex = 0;
    int currentStudentIndex = 0;
    
    ObservableList<String> genderList = FXCollections.observableArrayList("Male", "Female");
    private double xOffset = 0, yOffset = 0;
    
    @FXML
    private ComboBox genderBox;
    @FXML
    private JFXTextField tfProjectTitle;
    @FXML
    private JFXTextField tfSchool;
    @FXML
    private JFXTextField tfSupervisor;
    @FXML
    private JFXTextField tfAdminNo;
    @FXML
    private JFXTextField tfName;
    @FXML
    private JFXTextField tfCourse;
    @FXML
    private JFXCheckBox chkbxEditMode;
    @FXML
    private JFXButton btnUpdateProject;
    @FXML
    private JFXTreeTableView<ttvProject> ttvProjects;
    @FXML
    private JFXListView<String> lvStudents;

    
    

    public MainGUIController() {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        genderBox.setItems(genderList);
        genderBox.setDisable(true);
        chkbxEditMode.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            // TODO Auto-generated method stub
            if(newValue){
                // checkbox has been ticked.
                tfProjectTitle.setMouseTransparent(false);
                tfSchool.setMouseTransparent(false);
                tfSupervisor.setMouseTransparent(false);
                btnUpdateProject.setDisable(false);
            }else{
                // checkbox has been unticked
                tfProjectTitle.setMouseTransparent(true);
                tfSchool.setMouseTransparent(true);
                tfSupervisor.setMouseTransparent(true);
                btnUpdateProject.setDisable(true);
            }
        });
        
        loadAndDisplayProjectList();
    }
    
    public void loadAndDisplayProjectList(){
        try {
            projects = ProjectFile.readFile("src/DataFile/projects.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setUpTreeTableViewProject();

    }
    
    
    public void displaySelectedProject(){
        tfProjectTitle.setText(projects.get(currentProjectIndex).getProjectTitle());
        tfSchool.setText(projects.get(currentProjectIndex).getSchool());
        tfSupervisor.setText(projects.get(currentProjectIndex).getSupervisorName());
        //setUpTreeTableViewStudent();
        setUpListViewStudent();
        
    }
    
    public void displaySelectedStudent(){
        ArrayList<Student> students = projects.get(currentProjectIndex).getStudents();
        tfAdminNo.setText(students.get(currentStudentIndex).getAdmissionNo());
        tfCourse.setText(students.get(currentStudentIndex).getCourse());
        tfName.setText(students.get(currentStudentIndex).getName());
        genderBox.setValue(students.get(currentStudentIndex).getGender());
        
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
    
    public void addStudent(MouseEvent evt) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("AddStudent.fxml"));
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
    
    public void setUpTreeTableViewProject(){
        JFXTreeTableColumn<ttvProject,String> titleCol = new JFXTreeTableColumn<>("Title");
        titleCol.setPrefWidth(100);
        titleCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ttvProject,String>,ObservableValue<String>>(){
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ttvProject,String> param){
                    return param.getValue().getValue().title;
                }       
        });
        
         JFXTreeTableColumn<ttvProject,String> schoolCol = new JFXTreeTableColumn<>("School");
        schoolCol.setPrefWidth(100);
        schoolCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ttvProject,String>,ObservableValue<String>>(){
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ttvProject,String> param){
                    return param.getValue().getValue().school;
                }       
        });
        
         JFXTreeTableColumn<ttvProject,String> supervisorCol = new JFXTreeTableColumn<>("Supervisor");
        supervisorCol.setPrefWidth(100);
        supervisorCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ttvProject,String>,ObservableValue<String>>(){
                @Override
                public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ttvProject,String> param){
                    return param.getValue().getValue().supervisor;
                }       
        });
        
        ObservableList<ttvProject> projectList = FXCollections.observableArrayList();
        for(Project p :projects){
            projectList.add(new ttvProject(p.getProjectTitle(),p.getSchool(),p.getSupervisorName()));
        }
        
        final TreeItem<ttvProject> root = new RecursiveTreeItem<ttvProject>(projectList,RecursiveTreeObject::getChildren);
        ttvProjects.getColumns().setAll(titleCol,schoolCol,supervisorCol);
        ttvProjects.setRoot(root);
        ttvProjects.setShowRoot(false);
        
        //add listener to the tree table view's row
        ttvProjects.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                ttvProject p = ttvProjects.getSelectionModel().getSelectedItem().getValue();     
                for(int i=0;i<projects.size();i++){
                    if(p.title.getValue().equals(projects.get(i).getProjectTitle())){
                        if(p.school.getValue().equals(projects.get(i).getSchool())){
                            currentProjectIndex = i;
                            displaySelectedProject();
                            break;
                        }
                      
                    }
                }
            
        });

    }
    
    
    public void setUpListViewStudent(){
        ArrayList<Student> students = projects.get(currentProjectIndex).getStudents();
        lvStudents.getItems().clear();
        for(int i=0;i<students.size();i++){
            lvStudents.getItems().add(students.get(i).getName());
        }
        
        lvStudents.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                ArrayList<Student> students = projects.get(currentProjectIndex).getStudents();
                String name = lvStudents.getSelectionModel().getSelectedItem();
                for(int i=0;i<students.size();i++){
                    if(students.get(i).getName().equals(name)){
                        currentStudentIndex = i;
                        displaySelectedStudent();
                        break;
                    }
                    
                 };                
            }
        });
        
    }
    
    
    class ttvProject extends RecursiveTreeObject<ttvProject>{
        StringProperty title;
        StringProperty school;
        StringProperty supervisor;
        
        public ttvProject(String title, String school, String supervisor){
            this.title = new SimpleStringProperty(title);
            this.school = new SimpleStringProperty(school);
            this.supervisor = new SimpleStringProperty(supervisor);           
        }
    }
    
 
    public void actionPerformed() {
        genderBox.setDisable(!chkbxEditMode.isSelected());
    }
    
}
