package sample.Vistas;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ClienteSocket {

    /*private ServerSocket servidor;
    private Socket cliente;

    private int noClienteConectado = 0;

    public void iniciarSocket(){
        try {
            servidor = new ServerSocket(5000); //Se establece el canal de comunicación
            while (true) {
                cliente = servidor.accept();
                noClienteConectado++;
                System.out.println("Llegó el cliente número: "+noClienteConectado);

                PrintStream salida = new PrintStream(cliente.getOutputStream());
                salida.println("Bienvenido, usted es mi cliente número: "+noClienteConectado);
                //Flujo de entrada
                BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                System.out.println(entrada.readLine());

                cliente.close();
            }
        } catch(Exception e){e.printStackTrace();}
    }*/

    private InetAddress direccionServidor;
    private Socket cliente;
    private byte[] ip = new byte[]{(byte)192,(byte)168,(byte)0,(byte)1};

    public void conectarCliente(){
        try{
            direccionServidor = InetAddress.getByAddress(ip);
        }catch(Exception e){}
    }
}
