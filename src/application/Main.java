package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

   @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/fxml/Login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.getIcons().add(new Image("/view/icons/icons8-library-100.png"));
        primaryStage.setScene(new Scene(root, 850, 550));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
