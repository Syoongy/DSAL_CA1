/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Enum.Gender;
import IO.IOHelper;
import Model.Event;
import Model.EventCollection;
import Model.Project;
import Model.Student;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.LinkList;
import utils.Queue;

/**
 *
 * @author Fan Syong Yue
 */
public class MainGUIController implements Initializable {

    IOHelper projectFile = new IOHelper();
    EventCollection eventCollection = new EventCollection();
    protected List<Project> projects = new ArrayList<>();
    protected int currentProjectIndex = 0;
    protected int currentStudentIndex = 0;
    protected int currentEventIndex = 0;

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
    private JFXTextField tfSaveFileName;
    @FXML
    private JFXCheckBox chkbxEditMode;
    @FXML
    private JFXButton btnUpdateProject;
    @FXML
    private JFXButton btnAddStudent;
    @FXML
    private JFXButton btnRemoveStudent;
    @FXML
    private JFXButton btnUpdateStudentInfo;
    @FXML
    private JFXButton btnRemoveProjectFromEvent;
    @FXML
    private JFXButton btnAddProjectToEvent;
    @FXML
    private JFXButton btnCreateEvent;
    @FXML
    private JFXTextField tfEventTitle;
    @FXML
    private JFXTextArea taEventDisplay;
    @FXML
    private JFXListView<String> lvEvents;
    @FXML
    private JFXTreeTableView<ttvProject> ttvProjects;
    @FXML
    private JFXListView<String> lvStudents;
    @FXML
    private StackPane dialogStackPane;
    @FXML
    private ToggleGroup fileTypeToggleGroup;

    public MainGUIController() {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        genderBox.setItems(genderList);
        try {
            //EventCollection e = projectFile.readEventCollection("src/DataFile/EventCollection");
            //projectFile.serializedEventCollection(e);
            eventCollection = projectFile.deserializedEventCollection("src/DataFile/eventCollection.ser");
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex);
        }

        //listener for edit mode checkbox
        chkbxEditMode.selectedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (newValue) {
                // checkbox has been ticked.
                tfProjectTitle.setMouseTransparent(false);
                tfSchool.setMouseTransparent(false);
                tfSupervisor.setMouseTransparent(false);
                tfAdminNo.setMouseTransparent(false);
                tfName.setMouseTransparent(false);
                tfCourse.setMouseTransparent(false);
                btnUpdateProject.setDisable(false);
                genderBox.setMouseTransparent(false);
                btnAddStudent.setDisable(false);
            } else {
                // checkbox has been unticked
                tfProjectTitle.setMouseTransparent(true);
                tfSchool.setMouseTransparent(true);
                tfSupervisor.setMouseTransparent(true);
                tfSupervisor.setMouseTransparent(true);
                tfAdminNo.setMouseTransparent(true);
                tfName.setMouseTransparent(true);
                btnUpdateProject.setDisable(true);
                genderBox.setMouseTransparent(true);
                btnAddStudent.setDisable(true);
            }
        });

        loadAndDisplayProjectList();
        displayEventList();
        setUpListViewEvents();
    }

    public void saveProjectToFile(MouseEvent evt) {
        RadioButton fileType = (RadioButton) fileTypeToggleGroup.getSelectedToggle();
        String fileName = "src/DataFile/" + tfSaveFileName.getText() + "." + fileType.getText();
        projectFile.writeOneProjectToFile(projects.get(currentProjectIndex), fileName, fileType.getText());
        tfSaveFileName.clear();

        //Creates dialog to inform user file has been saved
        JFXDialogLayout content = new JFXDialogLayout();
        Label dialogHeader = new Label();
        dialogHeader.setText("Saved");
        dialogHeader.setStyle("-fx-text-fill: #eda678; -fx-font-size: 28;");
        content.setHeading(dialogHeader);
        content.setBody(new Text("This project has been successfully saved!"));
        JFXDialog dialog = new JFXDialog(dialogStackPane, content, JFXDialog.DialogTransition.CENTER, false);
        JFXButton btnCloseDialog = new JFXButton("Okay");
        btnCloseDialog.getStylesheets().add(getClass().getResource("design.css").toExternalForm());
        btnCloseDialog.getStyleClass().add("closeDialog");
        btnCloseDialog.setOnMouseClicked((MouseEvent event) -> {
            dialog.close();
            dialogStackPane.setMouseTransparent(true);
        });
        content.setActions(btnCloseDialog);
        dialog.show();
        dialogStackPane.setMouseTransparent(false);
    }

    //Updates the current project the user is at
    public void updateProject(MouseEvent evt) {
        projects.get(currentProjectIndex).setProjectTitle(tfProjectTitle.getText());
        projects.get(currentProjectIndex).setSchool(tfSchool.getText());
        projects.get(currentProjectIndex).setSupervisorName(tfSupervisor.getText());
        projectFile.saveToProjectListFile(projects, "src/DataFile/projects.txt");

        //Creates dialog to inform user file has been updated
        JFXDialogLayout content = new JFXDialogLayout();
        Label dialogHeader = new Label();
        dialogHeader.setText("Updated");
        dialogHeader.setStyle("-fx-text-fill: #eda678; -fx-font-size: 28;");
        content.setHeading(dialogHeader);
        content.setBody(new Text("This project has been successfully updated!"));
        JFXDialog dialog = new JFXDialog(dialogStackPane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton btnCloseDialog = new JFXButton("Okay");
        btnCloseDialog.getStylesheets().add(getClass().getResource("design.css").toExternalForm());
        btnCloseDialog.getStyleClass().add("closeDialog");
        btnCloseDialog.setOnMouseClicked((MouseEvent event) -> {
            dialog.close();
            dialogStackPane.setMouseTransparent(true);
        });
        content.setActions(btnCloseDialog);
        dialog.show();
        dialogStackPane.setMouseTransparent(false);
    }

    //Serialises event collection
    public void saveEventCollection() {
        try {
            projectFile.serializedEventCollection(eventCollection);
        } catch (IOException ex) {
            Logger.getLogger(MainGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Creates dialog to inform user file has been serialised
        JFXDialogLayout content = new JFXDialogLayout();
        Label dialogHeader = new Label();
        dialogHeader.setText("Updated");
        dialogHeader.setStyle("-fx-text-fill: #eda678; -fx-font-size: 28;");
        content.setHeading(dialogHeader);
        content.setBody(new Text("The events have been successfully serialised and saved!"));
        JFXDialog dialog = new JFXDialog(dialogStackPane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton btnCloseDialog = new JFXButton("Okay");
        btnCloseDialog.getStylesheets().add(getClass().getResource("design.css").toExternalForm());
        btnCloseDialog.getStyleClass().add("closeDialog");
        btnCloseDialog.setOnMouseClicked((MouseEvent event) -> {
            dialog.close();
            dialogStackPane.setMouseTransparent(true);
        });
        content.setActions(btnCloseDialog);
        dialog.show();
        dialogStackPane.setMouseTransparent(false);
    }

    //Updates student list for selected project
    public void updateProjectStudent(MouseEvent evt) {
        List<Student> students = projects.get(currentProjectIndex).getStudents();
        students.get(currentStudentIndex).setAdmissionNo(tfAdminNo.getText());
        students.get(currentStudentIndex).setName(tfName.getText());
        students.get(currentStudentIndex).setCourse(tfCourse.getText());
        if ("Male".equals(genderBox.getSelectionModel().getSelectedItem().toString())) {
            students.get(currentStudentIndex).setGender(Gender.MALE);
        } else {
            students.get(currentStudentIndex).setGender(Gender.FEMALE);
        }
        projects.get(currentProjectIndex).setStudents(students);

        //Creates dialog to inform user student has been updated
        JFXDialogLayout content = new JFXDialogLayout();
        Label dialogHeader = new Label();
        dialogHeader.setText("Updated");
        dialogHeader.setStyle("-fx-text-fill: #eda678; -fx-font-size: 28;");
        content.setHeading(dialogHeader);
        content.setBody(new Text("This student has been successfully updated! \n"
                + "Please update the whole file to apply changes!"));
        JFXDialog dialog = new JFXDialog(dialogStackPane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton btnCloseDialog = new JFXButton("Okay");
        btnCloseDialog.getStylesheets().add(getClass().getResource("design.css").toExternalForm());
        btnCloseDialog.getStyleClass().add("closeDialog");
        btnCloseDialog.setOnMouseClicked((MouseEvent event) -> {
            dialog.close();
            dialogStackPane.setMouseTransparent(true);
            setUpListViewStudent();
        });
        content.setActions(btnCloseDialog);
        dialog.show();
        dialogStackPane.setMouseTransparent(false);
    }

    public void addProjectStudent(MouseEvent evt) {
        String adminNo = tfAdminNo.getText();
        String name = tfName.getText();
        String course = tfCourse.getText();
        Gender sGender;
        if ("Male".equals(genderBox.getSelectionModel().getSelectedItem().toString())) {
            sGender = Gender.MALE;
        } else {
            sGender = Gender.FEMALE;
        }
        Student newStudent = new Student(adminNo, name, course, sGender);

        List<Student> students = projects.get(currentProjectIndex).getStudents();
        students.add(newStudent);

        projects.get(currentProjectIndex).setStudents(students);

        //Creates dialog to inform user student has been added
        JFXDialogLayout content = new JFXDialogLayout();
        Label dialogHeader = new Label();
        dialogHeader.setText("Updated");
        dialogHeader.setStyle("-fx-text-fill: #eda678; -fx-font-size: 28;");
        content.setHeading(dialogHeader);
        content.setBody(new Text("This student has been successfully added! \n"
                + "Please update the whole file to apply changes!"));
        JFXDialog dialog = new JFXDialog(dialogStackPane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton btnCloseDialog = new JFXButton("Okay");
        btnCloseDialog.getStylesheets().add(getClass().getResource("design.css").toExternalForm());
        btnCloseDialog.getStyleClass().add("closeDialog");
        btnCloseDialog.setOnMouseClicked((MouseEvent event) -> {
            dialog.close();
            dialogStackPane.setMouseTransparent(true);
            setUpListViewStudent();
        });
        content.setActions(btnCloseDialog);
        dialog.show();
        dialogStackPane.setMouseTransparent(false);
    }

    public void removeProjectStudent(MouseEvent evt) {
        List<Student> students = projects.get(currentProjectIndex).getStudents();
        students.remove(currentStudentIndex);
        projects.get(currentProjectIndex).setStudents(students);

        //Creates dialog to inform user student has been removed
        JFXDialogLayout content = new JFXDialogLayout();
        Label dialogHeader = new Label();
        dialogHeader.setText("Updated");
        dialogHeader.setStyle("-fx-text-fill: #eda678; -fx-font-size: 28;");
        content.setHeading(dialogHeader);
        content.setBody(new Text("This student has been successfully removed! \n"
                + "Please update the whole file to apply changes!"));
        JFXDialog dialog = new JFXDialog(dialogStackPane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton btnCloseDialog = new JFXButton("Okay");
        btnCloseDialog.getStylesheets().add(getClass().getResource("design.css").toExternalForm());
        btnCloseDialog.getStyleClass().add("closeDialog");
        btnCloseDialog.setOnMouseClicked((MouseEvent event) -> {
            dialog.close();
            dialogStackPane.setMouseTransparent(true);
            setUpListViewStudent();
        });
        content.setActions(btnCloseDialog);
        dialog.show();
        dialogStackPane.setMouseTransparent(false);
    }

    public void createEvent() {
        Event newEvent = new Event();
        newEvent.setEventTitle(tfEventTitle.getText());
        eventCollection.addEvent(newEvent);

        //Creates dialog to inform user event has been created
        JFXDialogLayout content = new JFXDialogLayout();
        Label dialogHeader = new Label();
        dialogHeader.setText("Updated");
        dialogHeader.setStyle("-fx-text-fill: #eda678; -fx-font-size: 28;");
        content.setHeading(dialogHeader);
        content.setBody(new Text("This event has been successfully created! \n"
                + "Please update the whole file to apply changes!"));
        JFXDialog dialog = new JFXDialog(dialogStackPane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton btnCloseDialog = new JFXButton("Okay");
        btnCloseDialog.getStylesheets().add(getClass().getResource("design.css").toExternalForm());
        btnCloseDialog.getStyleClass().add("closeDialog");
        btnCloseDialog.setOnMouseClicked((MouseEvent event) -> {
            dialog.close();
            dialogStackPane.setMouseTransparent(true);
            setUpListViewEvents();
            displayEventList();
        });
        content.setActions(btnCloseDialog);
        dialog.show();
        dialogStackPane.setMouseTransparent(false);
    }

    public void addProjectToEvent() {
        Event thisEvent = eventCollection.getEvent(currentEventIndex);
        Project thisProject = projects.get(currentProjectIndex);
        thisEvent.addProject(thisProject);

        //Creates dialog to inform user project has been added to event
        JFXDialogLayout content = new JFXDialogLayout();
        Label dialogHeader = new Label();
        dialogHeader.setText("Updated");
        dialogHeader.setStyle("-fx-text-fill: #eda678; -fx-font-size: 28;");
        content.setHeading(dialogHeader);
        content.setBody(new Text("This event has been successfully created! \n"
                + "Please update the whole file to apply changes!"));
        JFXDialog dialog = new JFXDialog(dialogStackPane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton btnCloseDialog = new JFXButton("Okay");
        btnCloseDialog.getStylesheets().add(getClass().getResource("design.css").toExternalForm());
        btnCloseDialog.getStyleClass().add("closeDialog");
        btnCloseDialog.setOnMouseClicked((MouseEvent event) -> {
            dialog.close();
            dialogStackPane.setMouseTransparent(true);
            setUpListViewEvents();
            displayEventList();
        });
        content.setActions(btnCloseDialog);
        dialog.show();
        dialogStackPane.setMouseTransparent(false);
    }

    public void removeProjectFromEvent() {
        Event thisEvent = eventCollection.getEvent(currentEventIndex);
        Project thisProject = projects.get(currentProjectIndex);
        if (thisEvent.hasProject(thisProject)) {
            LinkList pList = thisEvent.getProjectList();
            for (int i = 0; i < pList.getNoOfElement(); i++) {
                if (pList.get(i) == thisProject) {
                    pList.remove(i);
                }
            }
            //Creates dialog to inform user project has been removed from event
            JFXDialogLayout content = new JFXDialogLayout();
            Label dialogHeader = new Label();
            dialogHeader.setText("Updated");
            dialogHeader.setStyle("-fx-text-fill: #eda678; -fx-font-size: 28;");
            content.setHeading(dialogHeader);
            content.setBody(new Text("This project has been successfully removed! \n"
                    + "Please update the whole file to apply changes!"));
            JFXDialog dialog = new JFXDialog(dialogStackPane, content, JFXDialog.DialogTransition.CENTER);
            JFXButton btnCloseDialog = new JFXButton("Okay");
            btnCloseDialog.getStylesheets().add(getClass().getResource("design.css").toExternalForm());
            btnCloseDialog.getStyleClass().add("closeDialog");
            btnCloseDialog.setOnMouseClicked((MouseEvent event) -> {
                dialog.close();
                dialogStackPane.setMouseTransparent(true);
                setUpListViewEvents();
                displayEventList();
            });
            content.setActions(btnCloseDialog);
            dialog.show();
            dialogStackPane.setMouseTransparent(false);
        } else {
            //Creates dialog to inform user that the event does not contain this project 
            JFXDialogLayout content = new JFXDialogLayout();
            Label dialogHeader = new Label();
            dialogHeader.setText("Updated");
            dialogHeader.setStyle("-fx-text-fill: #eda678; -fx-font-size: 28;");
            content.setHeading(dialogHeader);
            content.setBody(new Text("This event does not contain that event! \n"));
            JFXDialog dialog = new JFXDialog(dialogStackPane, content, JFXDialog.DialogTransition.CENTER);
            JFXButton btnCloseDialog = new JFXButton("Okay");
            btnCloseDialog.getStylesheets().add(getClass().getResource("design.css").toExternalForm());
            btnCloseDialog.getStyleClass().add("closeDialog");
            btnCloseDialog.setOnMouseClicked((MouseEvent event) -> {
                dialog.close();
                dialogStackPane.setMouseTransparent(true);
                setUpListViewEvents();
                displayEventList();
            });
            content.setActions(btnCloseDialog);
            dialog.show();
            dialogStackPane.setMouseTransparent(false);
        }
    }

    public void loadAndDisplayProjectList() {
        try {
            projects = projectFile.readProjectListFile("src/DataFile/projects.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setUpTreeTableViewProject();

    }

    public void displayEventList() {
        String eventTitle = "";
        for (int i = 0; i < eventCollection.getNoOfEvents(); i++) {
            Event e = eventCollection.getEvent(i);
            String projectDesc = "";
            for (int w = 0; w < e.getNumberOfProject(); w++) {
                Project p = e.getProject(w);
                projectDesc += "\t" + (w + 1) + ": " + String.format("%-25s", p.getProjectTitle()) + String.format("%-25s", p.getSchool()) + String.format("%-25s", p.getSupervisorName()) + "\n";
            }
            projectDesc += "\n";
            eventTitle += "Event " + (i + 1) + "\t" + e.getEventTitle() + "\n" + projectDesc;
        }
        taEventDisplay.setText(eventTitle);
    }

    public void displaySelectedEvent() {
        tfEventTitle.setText(eventCollection.getEvent(currentEventIndex).getEventTitle());
    }

    public void displaySelectedProject() {
        tfProjectTitle.setText(projects.get(currentProjectIndex).getProjectTitle());
        tfSchool.setText(projects.get(currentProjectIndex).getSchool());
        tfSupervisor.setText(projects.get(currentProjectIndex).getSupervisorName());
        //setUpTreeTableViewStudent();
        setUpListViewStudent();

    }

    public void displaySelectedStudent() {
        List<Student> students = projects.get(currentProjectIndex).getStudents();
        tfAdminNo.setText(students.get(currentStudentIndex).getAdmissionNo());
        tfCourse.setText(students.get(currentStudentIndex).getCourse());
        tfName.setText(students.get(currentStudentIndex).getName());
        if (students.get(currentStudentIndex).getGender() == Gender.MALE) {
            genderBox.getSelectionModel().select("Male");
        } else {
            genderBox.getSelectionModel().select("Female");
        }

    }

    public void closeApp(MouseEvent evt) {
        Platform.exit();
    }

    /*
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
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }
     */
    public void setUpTreeTableViewProject() {
        //Set up title column
        JFXTreeTableColumn<ttvProject, String> titleCol = new JFXTreeTableColumn<>("Title");
        titleCol.setPrefWidth(97);
        titleCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<ttvProject, String> param) -> param.getValue().getValue().title);
        //Set up school column
        JFXTreeTableColumn<ttvProject, String> schoolCol = new JFXTreeTableColumn<>("School");
        schoolCol.setPrefWidth(97);
        schoolCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<ttvProject, String> param) -> param.getValue().getValue().school);

        //Set up supervisor column
        JFXTreeTableColumn<ttvProject, String> supervisorCol = new JFXTreeTableColumn<>("Supervisor");
        supervisorCol.setPrefWidth(98);
        supervisorCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<ttvProject, String> param) -> param.getValue().getValue().supervisor);

        //Set up projects list
        ObservableList<ttvProject> projectList = FXCollections.observableArrayList();
        for (Project p : projects) {
            projectList.add(new ttvProject(p.getProjectTitle(), p.getSchool(), p.getSupervisorName()));
        }

        //Set columns within tree table view
        final TreeItem<ttvProject> root = new RecursiveTreeItem<>(projectList, RecursiveTreeObject::getChildren);
        ttvProjects.getColumns().setAll(titleCol, schoolCol, supervisorCol);
        ttvProjects.setRoot(root);
        ttvProjects.setShowRoot(false);

        //add listener to the tree table view's row
        ttvProjects.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            ttvProject p = ttvProjects.getSelectionModel().getSelectedItem().getValue();
            for (int i = 0; i < projects.size(); i++) {
                if (p.title.getValue().equals(projects.get(i).getProjectTitle())) {
                    if (p.school.getValue().equals(projects.get(i).getSchool())) {
                        currentProjectIndex = i;
                        displaySelectedProject();
                        break;
                    }

                }
            }

        });

    }

    public void setUpListViewStudent() {
        List<Student> students = projects.get(currentProjectIndex).getStudents();
        lvStudents.getItems().clear();
        for (int i = 0; i < students.size(); i++) {
            lvStudents.getItems().add(students.get(i).getName());
        }

        lvStudents.setOnMouseClicked((MouseEvent event) -> {
            List<Student> students1 = projects.get(currentProjectIndex).getStudents();
            String name = lvStudents.getSelectionModel().getSelectedItem();
            for (int i = 0; i < students1.size(); i++) {
                if (students1.get(i).getName().equals(name)) {
                    currentStudentIndex = i;
                    displaySelectedStudent();
                    break;
                }
            }
            btnUpdateStudentInfo.setDisable(false);
            btnRemoveStudent.setDisable(false);
        });

    }

    public void setUpListViewEvents() {
        lvEvents.getItems().clear();
        for (int i = 0; i < eventCollection.getNoOfEvents(); i++) {
            lvEvents.getItems().add(eventCollection.getEvent(i).getEventTitle());
        }
        /*
        ObservableList<String> rawData = FXCollections.observableArrayList(lvEvents.getItems());
        FilteredList<String> filteredList = new FilteredList<>(rawData, data -> true);
        tfEventTitle.textProperty().addListener(((observable, oldValue, newValue) -> {
            filteredList.setPredicate(data -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                if (data.contains(newValue)) {
                    return true; // Filter matches
                }

                return false; // Does not match
            });
            lvEvents.setItems(filteredList);
        }));
         */

        lvEvents.setOnMouseClicked((MouseEvent event) -> {
            String eventTitle = lvEvents.getSelectionModel().getSelectedItem();
            for (int i = 0; i < eventCollection.getNoOfEvents(); i++) {
                if (eventCollection.getEvent(i).getEventTitle().equals(eventTitle)) {
                    currentEventIndex = i;
                    displaySelectedEvent();
                    break;
                }
            }
            btnAddProjectToEvent.setDisable(false);
            btnRemoveProjectFromEvent.setDisable(false);
        });
    }

    class ttvProject extends RecursiveTreeObject<ttvProject> {

        StringProperty title;
        StringProperty school;
        StringProperty supervisor;

        public ttvProject(String title, String school, String supervisor) {
            this.title = new SimpleStringProperty(title);
            this.school = new SimpleStringProperty(school);
            this.supervisor = new SimpleStringProperty(supervisor);
        }
    }

    public void sortEventListByNumberOfProjectAsc() {
        //Radix sort

        // Step 1: Create 10 Queues
        Queue[] queues = new Queue[10];

        for (int i = 0; i < 10; i++) {
            queues[i] = new Queue();
        }
        LinkList eventList = eventCollection.getEventList();
        // Step 2: Process each digit in the number
        for (int u = 0; u < 1; u++) {
            int divisor = (int) Math.pow(10, u);
            for (int i = 0; i < eventList.getNoOfElement(); i++) {
                Event e = (Event) eventList.get(i);
                int qNo = (e.getNumberOfProject() / divisor) % 10;
                queues[qNo].enqueue(e);
            }
            eventList.removeAll();
            for (int k = 0; k < 10; k++) {
                while (!queues[k].isEmpty()) {
                    eventList.addLast((Event) queues[k].dequeue());
                }
            }
        }
        eventCollection.setEventList(eventList);
        displayEventList();
    }

    public void sortEventListByNumberOfProjectDesc() {
        //Radix sort
        // Step 1: Create 10 Queues
        Queue[] queues = new Queue[10];

        for (int i = 0; i < 10; i++) {
            queues[i] = new Queue();
        }
        LinkList eventList = eventCollection.getEventList();
        // Step 2: Process each digit in the number
        for (int u = 0; u < 1; u++) {
            int divisor = (int) Math.pow(10, u);
            for (int i = 0; i < eventList.getNoOfElement(); i++) {
                Event e = (Event) eventList.get(i);
                int qNo = (e.getNumberOfProject() / divisor) % 10;
                queues[qNo].enqueue(e);
            }
            eventList.removeAll();
            for (int k = 0; k < 10; k++) {
                while (!queues[k].isEmpty()) {
                    eventList.add(0, (Event) queues[k].dequeue());
                }
            }
        }
        eventCollection.setEventList(eventList);
        displayEventList();
    }

}
