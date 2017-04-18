package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("Hello World");


        // Load person overview.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        Parent root = (Parent) loader.load();
        primaryStage.setScene(new Scene(root, 800, 600));
        // Set person overview into the center of root layout.
        Controller crtl = loader.getController();
        crtl.mainApp = primaryStage;
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
