package utils;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ejecutar extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = (Pane) FXMLLoader.load(getClass().getResource("/Vista/Menu.fxml"));
        primaryStage.setTitle("Informe de parqueo UMG");
        Scene scene = new Scene(root,600,600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
