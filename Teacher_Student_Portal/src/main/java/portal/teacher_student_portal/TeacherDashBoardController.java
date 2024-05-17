package portal.teacher_student_portal;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.*;


public class TeacherDashBoardController implements Initializable{

    @FXML
    private Label teacherNameLabel;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        assignmentNotification.setText("");


        setAssignmentMarkVBox.setVisible(false);
        availableCoursesVbox.setVisible(false);
        Collections.addAll(availableCourses, courses);

        // adding available subjects to the listview of available courses
        availableCourseListView.getItems().addAll(availableCourses);
        courseListView.getItems().addAll(selectedCourses);

        // setting listener for each listview - start
        availableCourseListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                selectedAvailableSubject = availableCourseListView.getSelectionModel().getSelectedItem();
                System.out.println(selectedAvailableSubject);
            }
        });

        courseListView.getSelectionModel().selectedItemProperty().addListener((new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                selectedSubject = courseListView.getSelectionModel().getSelectedItem();
                System.out.println(selectedSubject);
            }
        }));

        assignmentListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                selectedAssignmentSubject = assignmentListView.getSelectionModel().getSelectedItem();
                if(selectedAssignmentSubject != null){
                    setAssignmentMarkVBox.setVisible(true);
                }
                System.out.println(selectedAssignmentSubject);
            }
        });

        examListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                selectedExamSubject = examListView.getSelectionModel().getSelectedItem();
                System.out.println(selectedExamSubject);
            }
        });
        // setting listener for each listview - end

        // Initialize the Timeline for showing messages
        messageTimeline = new Timeline(new KeyFrame(
                Duration.seconds(2),
                event -> assignmentNotification.setVisible(false)
        ));
        messageTimeline.setCycleCount(1);
    }


    public void LoggedIn(String userName, String passWord){
        if(passWord.equals("Hello"))
            teacherNameLabel.setText("Hello: " + userName);
        else
            teacherNameLabel.setText("Incorrect Password");
    }


// Course Management - start <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @FXML
    VBox availableCoursesVbox;

    @FXML
    private ListView<String> courseListView;

    @FXML
    private ListView<String> availableCourseListView;

    // available courses
    private String[] courses = {"DSA", "EEE", "OOP", "MATH", "GED"};
    private List<String> availableCourses = new ArrayList<>();
    private List<String> selectedCourses = new ArrayList<>();
    private String selectedSubject;
    private String selectedAvailableSubject;

    @FXML
    private Button addCourseButton;



    @FXML
    public void handleAddCourse(ActionEvent event) {
        // Logic to add a new course
        System.out.println("Add Course clicked");
        addCourseButton.setVisible(false);
        availableCoursesVbox.setVisible(true);
    }

    @FXML
    public void handleAddAvailableCourse(ActionEvent event){
        if(selectedAvailableSubject != null){
            courseListView.getItems().add(selectedAvailableSubject);

            // adding subject to assignmentView
            assignmentListView.getItems().add(selectedAvailableSubject);
            examListView.getItems().add(selectedAvailableSubject);

            // adding course to Central Course Management System
            Central_Course_MS.addCourse(selectedAvailableSubject, Central_Course_MS.getCourseID(selectedAvailableSubject), LogInPage.teacherName); // checking

            availableCourseListView.getItems().remove(selectedAvailableSubject);
        }
    }

    @FXML
    public void handleEditCourse(ActionEvent event) {
        // Logic to edit an existing course
        System.out.println("Edit Course clicked");
    }

    @FXML
    public void closeAvailableCourse(ActionEvent event){
        addCourseButton.setVisible(true);
        availableCoursesVbox.setVisible(false);
    }


    @FXML
    public void handleDeleteCourse(ActionEvent event) {
        // Logic to delete a course
        System.out.println("Delete Course clicked");

        if(selectedSubject != null){
            String subject = selectedSubject;

            availableCourseListView.getItems().add(subject);

            Central_Course_MS.removeCourse(subject);

            courseListView.getItems().remove(subject);
            assignmentListView.getItems().remove(subject);
            examListView.getItems().remove(subject);
        }
    }


// Course management - end >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


// Assignment Management - start <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @FXML
    VBox setAssignmentMarkVBox;

    @FXML
    Label assignmentNotification;

    @FXML
    TextField assignmentMarkTextField;


    @FXML
    private ListView<String> assignmentListView;

    private String selectedAssignmentSubject;

    private Timeline messageTimeline;

    @FXML
    public void handleAddAssignment(ActionEvent event) {
        // Logic to add a new assignment
        System.out.println("Add Assignment clicked");
        int mark;

        try{
            mark = Integer.parseInt(assignmentMarkTextField.getText());
            assignmentNotification.setText("Added Successfully..!!");
            assignmentNotification.setVisible(true);
            setAssignmentMarkVBox.setVisible(false);

            // Restart the Timeline to hide the label after 1 second
            if (messageTimeline.getStatus() == Timeline.Status.RUNNING) {
                messageTimeline.stop();
            }
            messageTimeline.playFromStart();


            System.out.println(mark); // checking
        }catch (NumberFormatException e){
            assignmentNotification.setText("Invalid Input...!!!");
            assignmentNotification.setVisible(true);

            // Restart the Timeline to hide the label after 1 second
            if (messageTimeline.getStatus() == Timeline.Status.RUNNING) {
                messageTimeline.stop();
            }
            messageTimeline.playFromStart(); // run only once

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void handleViewSubmissions(ActionEvent event) {
        // Logic to view submissions of assignments
        System.out.println("View Submissions clicked");
    }

// Assignment Management - end >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

// Exam Management - start <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @FXML
    private ListView<String> examListView;
    private String selectedExamSubject;

    @FXML
    public void handleScheduleExam(ActionEvent event) {
        // Logic to schedule a new exam
        System.out.println("Schedule Exam clicked");
    }

    @FXML
    public void handleViewExams(ActionEvent event) {
        // Logic to view scheduled exams
        System.out.println("View Exams clicked");
    }
//    ExamManagement examManagement = new ExamManagement();

// Exam Management - start >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}
