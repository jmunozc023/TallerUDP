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
public class Client extends Thread{
    private DatagramPacket out, in;
    private DatagramSocket socket;
    private String flag;
    
    public Client(){
        try {
            socket= new DatagramSocket();
        } catch (SocketException ex) {
            
        }
    }
    public void read(){
        var buffer= new byte[200];
        out= new DatagramPacket(buffer, 0, buffer.length);
    }
    public void sendData(){
        var text= JOptionPane.showInputDialog("text");
        try {
            var IP= InetAddress.getByName("192.168.0.22");
            out = new DatagramPacket(text.getBytes(), 0, text.getBytes().length, IP, 7800);
            try {
                socket.send(out);
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void getData (){
        var buffer=new byte[400];
        in = new DatagramPacket(buffer, buffer.length);
        try {
            socket.receive(in);
            var text = new String(in.getData());
            System.out.println(text.trim());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
