package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.List;

public class AssignmentController {


    @FXML
    private ListView<String> assignmentListView;

    @FXML
    public void fillList(String assignment){
        System.out.println("Asssssssssss " + assignment); // checking
        assignmentListView.getItems().add(assignment);

        System.out.println("Assignment List - " + assignmentListView.getItems()); // checking
    }

    @FXML
    public void fillList(List<String> assignment){
        System.out.println("Asssssssssss " + assignment); // checking
        assignmentListView.getItems().addAll(assignment);

        System.out.println("Assignment List - " + assignmentListView.getItems()); // checking
    }

    public void homeOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("student_mainPage.fxml"));
        Parent root = fxmlLoader.load();

        // Get the scene from the current event source
        Scene currentScene = ((Node) event.getSource()).getScene();

        // Set the new root in the current scene
        currentScene.setRoot(root);
    }

}
