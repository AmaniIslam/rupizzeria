package main.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class PizzaMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/demo/PizzaMainMenu-view.fxml"));
        
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Pizza Orders");

        primaryStage.setOnCloseRequest(event -> {
            primaryStage.close();
        });

        primaryStage.show();}

    public static void main(String[] args) {
        
        launch(args);
    }
}
