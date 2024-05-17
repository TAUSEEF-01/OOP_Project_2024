package portal.teacher_student_portal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage Stage) throws Exception {
        Central_Course_MS.Initiate();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/LogInPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage.setWidth(900);
        Stage.setHeight(580);
        Stage.setScene(scene);
        Stage.show();
    }
}
