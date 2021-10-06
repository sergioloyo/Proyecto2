package Control;

import com.sun.security.ntlm.Server;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import java.io.DataOutputStream;
import java.net.Socket;
import java.net.*;
import java.io.*;


public class ParqueoDisponibleControlador {

    //variables para capturar datos provenientes de clientes
    public TextArea disponibleCarro;
    public TextArea disponibleMoto;
    public TextArea disponibleCamion;

    //variables para conexion con cliente
    public static final int PUERTO=5000;
    public static ServerSocket ss; // servidor
    public static Socket cs; //cliente
    public static DataOutputStream salidaCliente;

    public void conectarDisponible (ActionEvent actionEvent){

        try{
            ss = new ServerSocket(PUERTO);
            cs = new Socket();
            cs = ss.accept();



        }catch (Exception ex){
            System.out.println("Error: "+ex.getMessage());
        }


    }



}
