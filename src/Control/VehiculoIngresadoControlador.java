package Control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class VehiculoIngresadoControlador implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }


    public TextField ingresoCarro;
    public Button conectarIngresado;

    public static final int PUERTO=5000;
    public static ServerSocket ss; // servidor
    public static Socket cs; //cliente
    public static String mensajeServidor;
    public static DataOutputStream salidaCliente;

    @FXML
    public void conectarDisponible (ActionEvent actionEvent){

        try{
            ss = new ServerSocket(PUERTO);
            cs = new Socket();
            System.out.println("Escuchando!");
            cs = ss.accept();
            System.out.println("Conectado");
            BufferedReader entrada = new BufferedReader(
                            new InputStreamReader(cs.getInputStream()));
            while ((mensajeServidor=entrada.readLine())!= null){
                salidaCliente = new DataOutputStream(cs.getOutputStream());
                salidaCliente.writeUTF("Total Ingresado: " + mensajeServidor + "\n");
                
                ingresoCarro.appendText(mensajeServidor);



            }

        }catch (Exception ex){
            System.out.println("Error: "+ex.getMessage());
        }


    }
    @FXML
    private void darInfo(ActionEvent event) {
    }
}
