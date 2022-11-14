/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tallerudpclient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author josep
 */
public class Client extends Thread { //Clase del Cliente

    private DatagramPacket out, in; //Variable de Datagramas de entrada y salida
    private DatagramSocket socket; //Variable del socket
    private String flag; //Variable de la bandera

    public Client() { //Clase para crear los socket
        try {
            socket = new DatagramSocket();
            escribir("inicio"); //Comando enviado al servidor para recibir la convexion
        } catch (SocketException ex) {

        }
    }

    public void read() { //Funcion para leer los datos de los Datagramas

        try {
            var buffer = new byte[400];
            out = new DatagramPacket(buffer, 0, buffer.length);
            socket.receive(out);
            readString(new String(out.getData()));
            socket.receive(out);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void readString(String value){ //Funcion para leer los datos y darles formato
        value=value.trim();
        System.out.println(value);
        var splitString= value.split(",");
        switch (splitString[0]) {
            case "flag" : //Caso para enviar el nombre del Cliente
                flag= splitString[1];
                System.out.println(flag);
                send();               
                break;
            case "jugar": //Caso para jugar
                send();
            case "Ganaste!": //Caso cuando el jugador gana
                System.out.println("Ganaste!");
            case "Mejor suerte la proxima": //Caso cuando el jugador pierde
                System.out.println("Mejor suerte la proxima");
                System.exit(0);
            default:
                throw new AssertionError();
        }
    }
    public void send(){ //Clase para enviar los datos al servidor
        var text =JOptionPane.showInputDialog("Escoja una pareja en las cordenadas (x1,y1,x2,y2)");
        var data= "jugar,"+flag+","+text;
        escribir(data);
    }
    private void escribir(String text){ //Funcion para escribir los datagramas
        try {
            in= new DatagramPacket(text.getBytes(), 0, text.getBytes().length, InetAddress.getLocalHost(), 7800);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            socket.send(in);
        } catch (IOException ex) {
            
        }
    }
    @Override //Override de la Clase Run
    public void run(){
        while (true) {            
            read();
        }
    }
}
