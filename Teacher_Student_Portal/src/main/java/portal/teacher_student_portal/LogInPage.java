package portal.teacher_student_portal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInPage {

    @FXML
    TextField UserNameText;
    @FXML
    TextField PassswordText;

    public static String teacherName;


    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    public void LogIn(ActionEvent event) throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource("FXML/TeacherDashboard.fxml"));
        Parent parent = fxmlLoader.load();

        TeacherDashBoardController teacherHomePage = fxmlLoader.getController();
        teacherHomePage.LoggedIn(UserNameText.getText(), PassswordText.getText());

        teacherName = UserNameText.getText();


        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }


}
