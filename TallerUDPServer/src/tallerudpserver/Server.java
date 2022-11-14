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
public class Server { //Clase para generar el servidor local

    private DatagramPacket out; //variable para crear un paquete de datos de salida
    private DatagramPacket in; //variable para crear un paquete de datos de entrada
    private DatagramSocket socket; //Socket para recibir o enviar paquetes
    private Client client1; //Variable local para el jugador1
    private Client client2; //Variable local para el jugador2
    private final static String[][] parejas = new String[10][10]; //Matriz de 10x10 para las parejas
    private int puntaje1, puntaje2 = 0; //Variable para almacenar los puntajes

    public Server() { //Creacion de el socket de Datagramas y designacion del puerto a utilizar
        try {
            socket = new DatagramSocket(7800);
        } catch (SocketException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void read() { //Funcion para leer los datos de los datagramas

        var buffer = new byte[400];
        out = new DatagramPacket(buffer, 0, buffer.length);
        try {
            socket.receive(out);
            ReadString(new String(out.getData()), out);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ReadString(String value, DatagramPacket client) { //Funcion para interpretar y dar formato a los datos del datagrama, ademas establece quien sera jugador1 y quien jugador2.
        value = value.trim();
        var splitData = value.split(",");
        System.out.println(value);
        switch (splitData[0]) { //Switch para realizar las funciones de acuerdo a cual hilo este corriendo y las acciones
            case "inicio": //Caso para cuando se escribe inicio desde el Cliente, asigna cada Jugador.
                if (client1 == null) {
                    client1 = new Client(client.getPort(), client.getAddress(), "Jugador1");
                    escribir("flag,jugador1", client.getPort(), client.getAddress());
                } else {
                    client2 = new Client(client.getPort(), client.getAddress(), "Jugador2");
                    escribir("flag,jugador2", client.getPort(), client.getAddress());
                }
                break;
            case "jugar": //Caso con la logica para jugar y obtener las parejas

                var flag = splitData[1];
                var x = Integer.parseInt(splitData[2]);
                var y = Integer.parseInt(splitData[3]);
                var x1 = Integer.parseInt(splitData[4]);
                var y1 = Integer.parseInt(splitData[5]);
                try {
                    if (parejas[x][y].equals(parejas[x1][y1])) {
                        parejas[x][y] = null;
                        parejas[x1][y1] = null;
                        System.out.println(flag + " Ha encontrado una pareja.");
                        if (flag.equals("jugador1")) {
                            puntaje1++;
                        } else {
                            puntaje2++;
                        }
                    } else {
                        System.out.println("No son pareja");

                    }
                } catch (Exception nullException) { //Se adiciona un try catch en caso de que el cliente seleccione celdas vacias de la matriz
                    System.out.println("Pareja no disponible, intente de nuevo");
                }
                if (gano(flag)) { //if dedidado a verificar si tenemos o no ganador
                    if (flag.equals("jugador1")) {
                        escribir("Ganaste!", client1.getPort(), client1.getIp());
                    } else {
                        escribir("Mejor suerte la proxima", client2.getPort(), client2.getIp());
                    }
                } 
                break;

            default:
                throw new AssertionError();
        }
        print();
    }

    private boolean gano(String flag) { //Funcion booleana para determinar el ganador
        if (puntaje1 == 15) {
            return true;
        } else {
            if (puntaje2 == 15) {
                return true;
            }
        }
        return false;
    }

    public void escribir(final String choice, final int port, final InetAddress IP) { //Funcion para escribir y enviar Datagramas a los clientes.
        in = new DatagramPacket(choice.getBytes(), 0, choice.getBytes().length, IP, port);
        try {
            socket.send(in);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void print() { //Funcion que imprime la matriz 
        for (var i : parejas) {
            for (var j : i) {
                System.out.print( j+"|");

            }
            System.out.println("");

        }
    }

    static { //Funcion utilizada para rellenar aleatoriamente la matriz
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                parejas[i][j] = String.valueOf(Math.floor(Math.random() * 50 + 1));
            }

        }
    }

}
