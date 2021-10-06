package Control;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;

import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class VehiculoIngresadoControlador {

    public TextArea ingresoCarro;

    public static final int PUERTO=5000;
    public static ServerSocket ss; // servidor
    public static Socket cs; //cliente
    public static String mensajeServidor;
    DataInputStream in;

    public void conectarDisponible (ActionEvent actionEvent){

        try{
            ss = new ServerSocket(PUERTO);
            cs = new Socket();
            System.out.println("Escuchando!");
            cs = ss.accept();
            System.out.println("Conectado");
            BufferedReader entrada =
                    new BufferedReader(
                            new InputStreamReader(cs.getInputStream()));
            while ((mensajeServidor=entrada.readLine())!= null){


                ingresoCarro.appendText(mensajeServidor);



            }




        }catch (Exception ex){
            System.out.println("Error: "+ex.getMessage());
        }


    }
}
