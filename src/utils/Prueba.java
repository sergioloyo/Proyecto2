package utils;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import proyecto1.Camion;
import proyecto1.Carro;
import proyecto1.Moto;
import proyecto1.Vehiculo;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class Prueba {
    /**VARIABLE SOCKET CLIENTE**/
    public static final int PUERTO=5000;
    public static final String HOST="localhost";
    public static Socket cs; // cliente
    public static DataOutputStream salidaServidor;

    ArrayList<Vehiculo> Parqueo = new ArrayList<>();
    /**Estos atributos que se asingaron son para el funcionamiento de los contadores, entradas y salidas de los menus, */
    int exit = 0,exit2=0,exit3=0,opcion = 0, opcion2=0, opcion3=0;
    int contadorCarro=0;
    int  contadorMoto=0;
    int  contadorCamion=0;
    int moduloDeTiempo=5;
    String Nit="";
    String fechaHora="";
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    Calendar calendario = Calendar.getInstance();
    Date date = calendario.getTime();


    /**Esta es el main que ayuda a que se inicialice todo el proyecto*/
    public static void main(String[] args) {
        Prueba proyecto= new Prueba();
        proyecto.menuGeneral();
    }

    /**Este es un submenu general con todas opciones del programa*/
    public void menuGeneral() {
        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog("Menu \n\n 1. Ingreso de veciculo\n 2. Egreso de vehiculo\n 3. Validar ocupación de parqueos\n 4. Conexion servidor\n 5. Salir\n\nIngrese su opcion"));
            switch (opcion) {
                case 1:
                    menuIngreso();
                    break;
                case 2:{
                    menuEgreso();
                }

                break;
                case 3:{
                    if (Parqueo.isEmpty())
                        JOptionPane.showMessageDialog(null,"Parqueo Vacios, favor de ingresar un vehiculo");
                    else{
                        JOptionPane.showMessageDialog(null, Parqueo);
                        System.out.println(contadorCarro);
                        System.out.println(contadorMoto);
                    }
                }
                break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Gracias por usar nuestro parqueo");
                    System.exit(0);
                    break;
                case 4:
                    try{
                        starServer();
                    }catch(Exception e){
                        System.out.println("error:"+e.getMessage());
                    }
                break;
                default:
                    JOptionPane.showMessageDialog(null, "Ingrese un valor de 1 a 3");
                    break;
            }
        } while (exit != 4);
    }
    /**Este submenu ayuda al ingreso de ticket*/
    public void menuIngreso(){
        do {
            opcion2 = Integer.parseInt(JOptionPane.showInputDialog("Menu \n\n 1. Ingreso de Carro\n 2. Ingreso de moto\n 3. Ingreso de Camion\n 4. Regresar\n\nIngrese su opcion"));
            switch (opcion2) {
                case 1: {
                    fechaHora= dateFormat.format(date);
                    /**Funcion para ingreso de carros
                     esta funcion if sirve para delimitar la cantidad de parqueos, se le asigna una capacidad
                     maxima de 10 parqueos*/
                    if (contadorCarro <=10) {
                        contadorCarro = contadorCarro + 1;
                        String opcionCarro;
                        opcionCarro = JOptionPane.showInputDialog("Ingrese numero de placa del vehiculo: ");
                        Parqueo.add(new Carro(opcionCarro));
                        JOptionPane.showMessageDialog(null, "El Carro " + opcionCarro + " Fue guardado en el parqueo: 00" + contadorCarro);

                        try {
                            String dest = "D:\\Sergio\\Universidad\\4 Semestre\\01 Programación II\\Proyectos\\Proyecto1\\Reportes\\TicketCarro."+contadorCarro+".PDF";
                            PdfWriter writer = new PdfWriter(dest);
                            PdfDocument pdfDoc = new PdfDocument(writer);
                            Document document = new Document(pdfDoc);
                            pdfDoc.addNewPage();
                            Paragraph texto1 = new Paragraph("Recibo de parqueo No. 00"+contadorCarro);
                            Paragraph texto2 = new Paragraph ("Placa vehiculo: "+opcionCarro);
                            Paragraph texto3= new Paragraph ("Hora de ingreso: "+fechaHora+" , su tarifa es de Q5 por 10 Segundos");
                            document.add(texto1);
                            document.add(texto2);
                            document.add(texto3);
                            document.close();
                            JOptionPane.showMessageDialog(null,"Recibo generado");
                        }catch (Exception e){
                            JOptionPane.showMessageDialog(null,e.getMessage());
                        }
                    }
                    /**Esta funcion else muestra el mensaje de cuando ya el contador no supera la condición del if*/
                    else
                        JOptionPane.showMessageDialog(null, "Parqueo lleno");

                }
                break;
                case 2: {
                    fechaHora= dateFormat.format(date);
                    /**Funcion para ingreso de moto
                     esta funcion if sirve para delimitar la cantidad de parqueos, se le asigna una capacidad
                     maxima de 15 parqueos*/
                    if (contadorMoto <=15) {
                        contadorMoto = contadorMoto + 1;
                        String opcionMoto;
                        opcionMoto = JOptionPane.showInputDialog("Ingrese numero de placa del vehiculo: ");
                        Parqueo.add(new Moto(opcionMoto));
                        JOptionPane.showMessageDialog(null, "La motocicleta " + opcionMoto + " Fue guardado en el parqueo: 00" + contadorMoto);

                        try {
                            String dest = "D:\\Sergio\\Universidad\\4 Semestre\\01 Programación II\\Proyectos\\Proyecto1\\Reportes\\TicketMoto."+contadorMoto+".PDF";
                            PdfWriter writer = new PdfWriter(dest);
                            PdfDocument pdfDoc = new PdfDocument(writer);
                            Document document = new Document(pdfDoc);
                            pdfDoc.addNewPage();
                            Paragraph texto1 = new Paragraph("Recibo de parqueo No. 00"+contadorMoto);
                            Paragraph texto2 = new Paragraph ("Placa vehiculo: "+opcionMoto);
                            Paragraph texto3= new Paragraph ("Hora de ingreso: "+fechaHora+" , su tarifa es de Q5 por 10 Segundos menos un 10%");
                            document.add(texto1);
                            document.add(texto2);
                            document.add(texto3);
                            document.close();
                            JOptionPane.showMessageDialog(null,"Recibo generado");
                        }catch (Exception e){
                            JOptionPane.showMessageDialog(null,e.getMessage());
                        }
                    }

                    /**Esta funcion else muestra el mensaje de cuando ya el contador no supera la condición del if*/
                    else {
                        JOptionPane.showMessageDialog(null, "Parqueo lleno");
                    }
                }
                break;
                case 3: {
                    fechaHora= dateFormat.format(date);
                    /**Funcion para ingreso de camion
                     esta funcion if sirve para delimitar la cantidad de parqueos, se le asigna una capacidad
                     maxima de 5 parqueos*/
                    if (contadorCamion <=5) {
                        contadorCamion = contadorCamion + 1;
                        String opcionCamion;
                        opcionCamion = JOptionPane.showInputDialog("Ingrese numero de placa del vehiculo: ");
                        Parqueo.add(new Camion(opcionCamion));
                        JOptionPane.showMessageDialog(null, "El camion " + opcionCamion + " Fue guardado en el parqueo: 00" + contadorCamion);
                        try {
                            String dest = "D:\\Sergio\\Universidad\\4 Semestre\\01 Programación II\\Proyectos\\Proyecto1\\Reportes\\TicketCamion."+contadorCamion+".PDF";
                            PdfWriter writer = new PdfWriter(dest);
                            PdfDocument pdfDoc = new PdfDocument(writer);
                            Document document = new Document(pdfDoc);
                            pdfDoc.addNewPage();
                            Paragraph texto1 = new Paragraph("Recibo de parqueo No. 00"+contadorCamion);
                            Paragraph texto2 = new Paragraph ("Placa vehiculo: "+opcionCamion);
                            Paragraph texto3= new Paragraph ("Hora de ingreso: "+fechaHora+" , su tarifa es de Q5 por 10 Segundos");
                            Paragraph texto4 = new Paragraph ("por cada 2 periodos consumidos se cobrara Q5 más");
                            document.add(texto1);
                            document.add(texto2);
                            document.add(texto3);
                            document.add(texto4);
                            document.close();
                            JOptionPane.showMessageDialog(null,"Recibo generado");
                        }catch (Exception e){
                            JOptionPane.showMessageDialog(null,e.getMessage());
                        }
                    }
                    /**Esta funcion else muestra el mensaje de cuando ya el contador no supera la condición del if*/
                    else
                        JOptionPane.showMessageDialog(null, "Parqueo lleno");
                }
                break;
                case 4:
                    exit2 = 3;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Ingrese un valor de 1 a 4");
                    break;
            }//fin switch 2, ingreso de menu

        } while (exit2 != 3);
    }//fin menu de menu de ingreos

    /**Este submenu ayuda a la salida del vehiculo ticket*/
    public void menuEgreso() {
        do {
            opcion3 = Integer.parseInt(JOptionPane.showInputDialog("Menu \n\n 1. Salida de Carro\n 2. Salida de moto\n 3. Salida de Camion\n 4. Regresar\n\nIngrese su opcion"));
            switch (opcion3) {
                case 1: {
                    Iterator<Vehiculo> itCarro=Parqueo.iterator();
                    String placaCarro;
                    if (Parqueo.isEmpty()){
                        JOptionPane.showMessageDialog(null,"El parqueo se encuentra vacios, favor ingrese un vehiculo");
                        menuGeneral();
                    } else{
                        placaCarro=JOptionPane.showInputDialog("Ingrese el numero de placa: ");
                        while(itCarro.hasNext()){
                            String eliminarCarro=itCarro.next().getNumeroPlaca();
                            if (eliminarCarro.equals(placaCarro)){
                                itCarro.remove();
                                salidaCarro();
                            }else {JOptionPane.showMessageDialog(null,"La placa ingresada no se ubica, favor de revisar");
                            }
                        }
                    }
                }//fin case1
                break;
                case 2:{
                    Iterator<Vehiculo> itCarro=Parqueo.iterator();
                    String placaCarro;
                    if (Parqueo.isEmpty()){
                        JOptionPane.showMessageDialog(null,"El parqueo se encuentra vacios, favor ingrese un vehiculo");
                        menuGeneral();
                    } else{
                        placaCarro=JOptionPane.showInputDialog("Ingrese el numero de placa: ");
                        while(itCarro.hasNext()){
                            String eliminarCarro=itCarro.next().getNumeroPlaca();
                            if (eliminarCarro.equals(placaCarro)){
                                itCarro.remove();
                                salidaMoto();
                            }else {JOptionPane.showMessageDialog(null,"La placa ingresada no se ubica, favor de revisar");
                            }
                        }
                    }
                }


                break;
                case 3:{
                    Iterator<Vehiculo> itCarro=Parqueo.iterator();
                    String placaCarro;
                    if (Parqueo.isEmpty()){
                        JOptionPane.showMessageDialog(null,"El parqueo se encuentra vacios, favor ingrese un vehiculo");
                        menuGeneral();
                    } else{
                        placaCarro=JOptionPane.showInputDialog("Ingrese el numero de placa: ");
                        while(itCarro.hasNext()){
                            String eliminarCarro=itCarro.next().getNumeroPlaca();
                            if (eliminarCarro.equals(placaCarro)){
                                itCarro.remove();
                                salidaCamion();
                            }else {JOptionPane.showMessageDialog(null,"La placa ingresada no se ubica, favor de revisar");
                            }
                        }
                    }
                }

                break;
                case 4:
                    exit3 = 3;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Ingrese un valor de 1 a 4");
                    break;

            }//fin switch 2, ingreso de menu

        } while (exit3 != 3);
    }//fin menu de menu de ingreos

    /**Este metodo ayuda a calcular el pago de tarifa del carro*/
    public void salidaCarro(){

        String minutosEntrada;
        int valorAPagar;
        Calendar calendario2 = Calendar.getInstance();
        Date date2 = calendario2.getTime();
        minutosEntrada=JOptionPane.showInputDialog("Ingrese a la hora que ingreso su vehiculo: ");
        try {
            Date segundoSalida = dateFormat.parse(minutosEntrada);
            int segundoACobrar= (int) (date2.getTime()-segundoSalida.getTime())/1000;
            valorAPagar=(segundoACobrar/moduloDeTiempo)*10;
            fechaHora= dateFormat.format(date2);
            Nit=JOptionPane.showInputDialog(null,"Vehiculo Fue retirado \nValor a cancelar: "+valorAPagar+"\nIngrese su nit para facturar: ");
            String dest = "D:\\Sergio\\Universidad\\4 Semestre\\01 Programación II\\Proyectos\\Proyecto1\\Reportes\\FacturaCarro.pdf";
            PdfWriter writer = new PdfWriter(dest);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);
            pdfDoc.addNewPage();
            Paragraph texto1 = new Paragraph("***Parqueo UMG de Carros***");
            Paragraph texto2 = new Paragraph ("Hora de entrada: "+minutosEntrada);
            Paragraph texto3= new Paragraph ("Hora de Salida: "+fechaHora);
            Paragraph texto4 = new Paragraph ("Cancelar: Q"+valorAPagar);
            Paragraph texto5 = new Paragraph ("Nit: "+Nit);
            document.add(texto1);
            document.add(texto2);
            document.add(texto3);
            document.add(texto4);
            document.add(texto5);
            document.close();
            JOptionPane.showMessageDialog(null,"Recibo generado, Gracias por usar nuestro servicios");
            menuGeneral();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        menuGeneral();

    }
    /**Este metodo ayuda a calcular el pago de tarifa del moto*/
    public void salidaMoto(){

        String minutosEntrada;
        int valorAPagar;
        Calendar calendario3 = Calendar.getInstance();
        Date date3 = calendario3.getTime();
        minutosEntrada=JOptionPane.showInputDialog("Ingrese a la hora que ingreso su vehiculo: ");
        try {
            Date segundoSalida = dateFormat.parse(minutosEntrada);
            int segundoACobrar= (int) (date3.getTime()-segundoSalida.getTime())/1000;
            valorAPagar=((segundoACobrar/moduloDeTiempo)*10);
            double Descuento=valorAPagar*0.10;
            double valorAPagarTotal=valorAPagar-Descuento;
            fechaHora= dateFormat.format(date3);
            Nit=JOptionPane.showInputDialog(null,"Vehiculo Fue retirado \nValor a cancelar: "+valorAPagar+"\nIngrese su nit para facturar: ");
            String dest = "D:\\Sergio\\Universidad\\4 Semestre\\01 Programación II\\Proyectos\\Proyecto1\\Reportes\\FacturaMoto.pdf";
            PdfWriter writer = new PdfWriter(dest);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);
            pdfDoc.addNewPage();
            Paragraph texto1 = new Paragraph("***Parqueo UMG de Motos***");
            Paragraph texto2 = new Paragraph ("Hora de entrada: "+minutosEntrada);
            Paragraph texto3= new Paragraph ("Hora de Salida: "+fechaHora);
            Paragraph texto4= new Paragraph ("Valor de pago parcial: Q"+valorAPagar);
            Paragraph texto5= new Paragraph ("Descuento: Q"+Descuento);
            Paragraph texto6 = new Paragraph ("Total a Cancelar: Q"+valorAPagarTotal);
            Paragraph texto7 = new Paragraph ("Nit: "+Nit);
            document.add(texto1);
            document.add(texto2);
            document.add(texto3);
            document.add(texto4);
            document.add(texto5);
            document.add(texto6);
            document.add(texto7);
            document.close();
            JOptionPane.showMessageDialog(null,"Recibo generado, Gracias por usar nuestro servicios");
            menuGeneral();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        menuGeneral();

    }
    /**Este metodo ayuda a calcular el pago de tarifa del camion*/
    public void salidaCamion(){

        String minutosEntrada;
        int valorAPagar;
        Calendar calendario4 = Calendar.getInstance();
        Date date4 = calendario4.getTime();
        minutosEntrada=JOptionPane.showInputDialog("Ingrese a la hora que ingreso su Camiones: ");
        try {
            Date segundoSalida = dateFormat.parse(minutosEntrada);
            int segundoACobrar= (int) (date4.getTime()-segundoSalida.getTime())/1000;
            valorAPagar=((segundoACobrar/moduloDeTiempo)*10);
            int Cobro =((segundoACobrar/moduloDeTiempo)%20)*5;
            double valorAPagarTotal=valorAPagar+Cobro;
            fechaHora= dateFormat.format(date4);
            Nit=JOptionPane.showInputDialog(null,"Vehiculo Fue retirado \nValor a cancelar: "+valorAPagar+"\nIngrese su nit para facturar: ");
            String dest = "D:\\Sergio\\Universidad\\4 Semestre\\01 Programación II\\Proyectos\\Proyecto1\\Reportes\\FacturaCamion.pdf";
            PdfWriter writer = new PdfWriter(dest);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);
            pdfDoc.addNewPage();
            Paragraph texto1 = new Paragraph("***Parqueo UMG de Carros***");
            Paragraph texto2 = new Paragraph ("Hora de entrada: "+minutosEntrada);
            Paragraph texto3= new Paragraph ("Hora de Salida: "+fechaHora);
            Paragraph texto4= new Paragraph ("Valor de pago parcial: Q"+valorAPagar);
            Paragraph texto5= new Paragraph ("Recargo de parqueo: Q"+Cobro);
            Paragraph texto6 = new Paragraph ("Total a Cancelar: Q"+valorAPagarTotal);
            Paragraph texto7 = new Paragraph ("Nit: "+Nit);
            document.add(texto1);
            document.add(texto2);
            document.add(texto3);
            document.add(texto4);
            document.add(texto5);
            document.add(texto6);
            document.add(texto7);
            document.close();
            JOptionPane.showMessageDialog(null,"Recibo generado, Gracias por usar nuestro servicios");
            menuGeneral();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        menuGeneral();



    }//Salida Camion



    int disCarro=10-contadorCarro;
    int disMoto=15-contadorMoto;
    int disCamion=5-contadorCamion;


    public void starServer() {

        try {
            cs = new Socket(HOST, PUERTO);
            System.out.println("Cliente conectado!");
            //De aqui en adelante envia informacion al cliente
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            br.readLine();
            salidaServidor = new DataOutputStream(cs.getOutputStream());
            salidaServidor.writeUTF("  "+contadorCarro+"  "+contadorMoto+"  "+contadorCamion+"\n"+disCarro+"  "+disMoto+"  "+disCamion);

            System.out.println("Informacion enviada!");


        }catch (IOException ex){
            System.out.println("Erro en: "+ex);
        }


    }
}
