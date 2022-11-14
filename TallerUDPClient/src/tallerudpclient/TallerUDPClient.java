/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tallerudpclient;

/**
 *
 * @author josep
 */
public class TallerUDPClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        var client= new Client(); //Llamada para la clase Cliente
        client.start();
        while (true) {            
            client.send();
        }
    }
    
}
