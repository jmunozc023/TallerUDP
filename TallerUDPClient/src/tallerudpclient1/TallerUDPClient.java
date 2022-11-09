/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tallerudpclient1;

/**
 *
 * @author josep
 */
public class TallerUDPClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        var client= new Client1();
        client.sendData();
        client.getData();
    }
    
}
