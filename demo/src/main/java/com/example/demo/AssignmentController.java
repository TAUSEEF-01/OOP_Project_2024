package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
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

    public void returnHome(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/studentHomePage.fxml"));
        Parent root = fxmlLoader.load();

        ((Node) event.getSource()).getScene().setRoot(root);
    }

}
