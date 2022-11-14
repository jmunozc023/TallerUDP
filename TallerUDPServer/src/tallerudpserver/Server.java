/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tallerudpserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author josep
 */
public class Server {

    private DatagramPacket out;
    private DatagramPacket in;
    private DatagramSocket socket;
    private Client client1;
    private Client client2;
    private static String[][] parejas = new String[10][10];

    public Server() {
        try {
            socket = new DatagramSocket(7800);
        } catch (SocketException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void read() {

        var buffer = new byte[200];
        out = new DatagramPacket(buffer, 0, buffer.length);
        try {
            socket.receive(out);
            ReadString(new String(out.getData()), out);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void ReadString(String value, DatagramPacket client){
        value=value.trim();
        var splitData= value.split(",");
        System.out.println(value);
        switch (splitData[0]) {
            case "inicio":
                if (client1==null) {
                    client1=new Client(client.getPort(), client.getAddress(), "Jugador1");
                    escribir("flag,jugador1", client.getPort(), client.getAddress());
                } else {
                    client2= new Client(client.getPort(), client.getAddress(), "Jugador2");
                    escribir("flag,jugador2", client.getPort(), client.getAddress());
                }
                break;
            /*case "jugar":
                var flag=splitData[1];
                var x= Integer.parseInt(splitData[2]);
                var y= Integer.parseInt(splitData[3]);
                if (parejas[x][y].isEmpty()) {
                    
                }*/
            default:
                throw new AssertionError();
        }
        print();
    }
    public void escribir (final String choice, final int port, final InetAddress IP){
        in =new DatagramPacket(choice.getBytes(), 0, choice.getBytes().length, IP, port);
        try {
            socket.send(in);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void print(){
        for (var i : parejas) {
            for (var j : i) {
                System.out.print(j+"|?|");
                
            }
            System.out.println("");
            
        }
    }

    static {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                parejas[i][j]= new String();
                
            }
            
        }
    }
    
}
