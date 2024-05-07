package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.net.URL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.net.URL;

public class LoginController implements Initializable {

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
            validateLogin();
        } else {
            loginMessageLabel.setText("Please enter your username and password");
        }
    }

    public void validateLogin(){

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM user_account WHERE username = '" + enterUsernameField.getText() + "' AND password = '" + enterPasswordField.getText() + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()) {
                if(queryResult.getInt(1) == 1) {
                    loginMessageLabel.setText("You have successfully logged in!");
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
