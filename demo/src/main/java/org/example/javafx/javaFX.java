package org.example.javafx;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;


public class javaFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, Color.BLACK);

        File imageFile = new File("D:\\Downloads\\DUCSE Documents\\2nd year\\OOP\\javaFX\\javaFX_3\\javaFX\\src\\main\\java\\org\\example\\javafx\\batman.jpg");
        System.out.println(imageFile.getAbsolutePath());
        if (imageFile.exists()) {
            ImageView imageView = new ImageView();
            Image image = new Image(imageFile.toURI().toString());
            stage.getIcons().add(image);
        } else {
            System.out.println("No image found!!");
        }

        stage.setTitle("TheBest_01");
        stage.setWidth(420);
        stage.setHeight(420);
        stage.setResizable(false);

//        stage.setX(420);
//        stage.setY(420);

//        stage.setFullScreen(true);
//        stage.setFullScreenExitHint("You can't escape unless you press q");
//        stage.setFullScreenExitKeyCombination(KeyCombination.keyCombination("q"));


        stage.setScene(scene);
        stage.show();
    }


//    for viewing image!!--->
//    File imageFile = new File("mountains001.jpg");
//      System.out.println(imageFile.getAbsolutePath());
//      if (imageFile.exists()) {
//        ImageView imageView = new ImageView();
//        Image image = new Image(imageFile.toURI().toString());
//        imageView.setImage(image);
//        root.getChildren().add(imageView);
//    }
}
