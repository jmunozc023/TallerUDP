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
public class Client extends Thread {

    private DatagramPacket out, in;
    private DatagramSocket socket;
    private String flag;

    public Client() {
        try {
            socket = new DatagramSocket();
            escribir("inicio");
        } catch (SocketException ex) {

        }
    }

    public void read() {

        try {
            var buffer = new byte[200];
            out = new DatagramPacket(buffer, 0, buffer.length);
            socket.receive(out);
            readString(new String(out.getData()));
            socket.receive(out);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void readString(String value){
        value=value.trim();
        System.out.println(value);
        var splitString= value.split(",");
        switch (splitString[0]) {
            case "flag" :
                flag= splitString[1];
                System.out.println(flag);
                
                
                break;
            default:
                throw new AssertionError();
        }
    }
    public void send(){
        var text =JOptionPane.showInputDialog("Escoja una pareja en las cordenadas (x,y)");
        var data= "juega,"+flag+","+text;
        escribir(data);
    }
    private void escribir(String text){
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
    @Override
    public void run(){
        while (true) {            
            read();
        }
    }
}
