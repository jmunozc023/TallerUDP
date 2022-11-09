/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tallerudpserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author josep
 */
public class Server {
    private int[] [] parejas = new int[10][10];
    private DatagramPacket out;
    private DatagramPacket in;
    private DatagramSocket socket;
    
    public Server(){
        try {
            socket= new DatagramSocket(7800);
        } catch (SocketException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendData(String text, DatagramPacket client){
        try {
            out= new DatagramPacket(text.getBytes(), 0, text.getBytes().length, client.getAddress(), client.getPort());
            socket.send(out);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void getData(){
        var buffer=new byte[400];
        in= new DatagramPacket(buffer, buffer.length);
        try {
            socket.receive(in);
            var text= new String(in.getData());
            System.out.println(text.trim());
            sendData("hola cliente", in);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
