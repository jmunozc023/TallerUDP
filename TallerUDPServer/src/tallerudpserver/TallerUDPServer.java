/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tallerudpserver;

/**
 *
 * @author josep
 */
public class TallerUDPServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        var server = new Server(); //Llamada para la clase servidor
        while (true) {            
            server.read();
        }
    }
    
}
