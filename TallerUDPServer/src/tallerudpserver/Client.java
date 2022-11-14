/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tallerudpserver;

import java.net.InetAddress;

/**
 *
 * @author josep
 */
public class Client{ //Clase para crear los parametros del cliente en el servidor
    private int port; //variable privada para obtener el puerto de enlace
    private InetAddress ip; //variable privada para obtener la direccion IP  
    private String flag; //variable privada para obtener el ID del Cliente

    public Client(int port, InetAddress ip, String flag) { //Constructor de la clase
        this.port = port;
        this.ip = ip;
        this.flag = flag;
    }

    public int getPort() { //Getter para el puerto
        return port;
    }

    public void setPort(int port) { //Setter para el puerto
        this.port = port;
    }

    public InetAddress getIp() { //Getter para la IP
        return ip;
    }

    public void setIp(InetAddress ip) { //Setter para la IP
        this.ip = ip;
    }

    public String getFlag() { //Getter para la bandera
        return flag;
    }

    public void setFlag(String flag) { //Setter para la bandera
        this.flag = flag;
    }
    

    
}
