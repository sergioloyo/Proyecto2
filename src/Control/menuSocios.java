package Control;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class menuSocios {


    public void vehiculoIngresados(ActionEvent actionEvent) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vista/vehiculoIngresado.fxml"));
        Pane root = (Pane) fxmlLoader.load();
        Stage stage =  new Stage();
        stage.setTitle("Vehiculos Ingresados al Parqueo UMG");
        stage.setScene(new Scene(root,500,500));
        stage.show();


    }


    public void parqueoDisponible(ActionEvent actionEvent) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vista/parqueoDisponible.fxml"));
        Pane root = (Pane) fxmlLoader.load();
        Stage stage =  new Stage();
        stage.setTitle("Parqueos Disponible");
        stage.setScene(new Scene(root,600,600));
        stage.show();
    }
}
