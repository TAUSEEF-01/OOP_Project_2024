package com.example.hellofx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class HelloController {

    @FXML
    private VBox vbox;

    @FXML
    private Label welcomeText;

    @FXML
    public void initialize() {
        for (int i = 0; i < 5; i++) {
            Button button = new Button("Hello!");
            button.setOnAction(e -> onHelloButtonClick());
            vbox.getChildren().add(button);
        }
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Hello, JavaFX!");
    }
}
