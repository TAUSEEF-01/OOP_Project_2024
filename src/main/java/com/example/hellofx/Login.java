package com.example.hellofx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.StageStyle;
import javafx.scene.Node;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Login implements Initializable {

    @FXML
    private Button cancelButton;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private TextField enterUsernameField;

    @FXML
    private PasswordField enterPasswordField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void loginButtonOnAction(ActionEvent event) {

        if(!enterUsernameField.getText().isEmpty() && !enterPasswordField.getText().isEmpty()) {
            validateLogin(event);
        } else {
            loginMessageLabel.setText("Please enter your username and password");
        }
    }


    public void validateLogin(ActionEvent event){

        databaseconnection connectNow = new databaseconnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM project.logintable WHERE username = '" + enterUsernameField.getText() + "' AND password = '" + enterPasswordField.getText() + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()) {
                if(queryResult.getInt(1) == 1) {
                    loginMessageLabel.setText("You have successfully logged in!");
                    /*Parent root2 = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
                    Stage registerStage = new Stage();
//            registerStage.initStyle(StageStyle.UNDECORATED);
                    registerStage.setScene(new Scene(root2, 900, 580));
                    registerStage.setResizable(false);
                    registerStage.show();*/
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
                    Parent root = fxmlLoader.load();

                    // Get the scene from the current event source
                    Scene currentScene = ((Node) event.getSource()).getScene();

                    // Set the new root in the current scene
                    currentScene.setRoot(root);



                } else {
                    loginMessageLabel.setText("Invalid login!");
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }


    public void cancelButtonOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

}