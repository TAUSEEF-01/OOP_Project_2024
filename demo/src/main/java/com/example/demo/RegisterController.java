package com.example.demo;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.net.URL;


public class RegisterController implements Initializable {

    @FXML
    private Button cancelButton;

    @FXML
    private Button signupScreenSignupButton;

    @FXML
    private Label registrationMessageLabel;

    @FXML
    private PasswordField setPasswordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private TextField firstnameTextField;

    @FXML
    private TextField lastnameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField regstrationTextField;

    @FXML
    private TextField rollTextField;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void registerButtonOnAction(ActionEvent event) {
        if(!setPasswordField.getText().isEmpty() && !setPasswordField.getText().isEmpty() && setPasswordField.getText().equals(confirmPasswordField.getText())) {
            registerUser();
//            registrationMessageLabel.setText("");
        } else {
            registrationMessageLabel.setText("Passwords do not match!");
        }
    }


    public void registerUser() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String firstname = firstnameTextField.getText();
        String lastname = lastnameTextField.getText();
        String email = emailTextField.getText();
        String roll = rollTextField.getText();
        String username = regstrationTextField.getText();
        String password = setPasswordField.getText();

        String insertFields = "INSERT INTO user_account (last_name, first_name, username, password) VALUES ('";
        String insertValues = firstname + "','" + lastname + "','" + username + "','" + password + "')";
        String insertToRegister = insertFields + insertValues;

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);

            registrationMessageLabel.setText("User has been registered successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }


    public void redirectLoginPage() {
        try {
            Parent root2 = FXMLLoader.load(getClass().getResource("login.fxml"));
            Stage loginStage = new Stage();
//            loginStage.initStyle(StageStyle.UNDECORATED);
            loginStage.setScene(new Scene(root2, 900, 580));
            loginStage.setResizable(false);
            loginStage.show();

        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }


    public void loginUpButtonOnAction(ActionEvent actionEvent) {
        redirectLoginPage();

        Stage stage = (Stage) signupScreenSignupButton.getScene().getWindow();
        stage.close();
    }


    public void cancelButtonOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }
}
