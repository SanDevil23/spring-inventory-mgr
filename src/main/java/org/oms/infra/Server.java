package org.oms.infra;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private Socket client;
    private ServerSocket serverSocket;
    private DataInputStream in;

    public Server(int port){
        try (ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("Server started on the port: " + port);

            while (true){
                Socket client  = serverSocket.accept();
                BufferedWriter writer =  new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                writer.write("HTTP/1.1 200 OK\r\n");
                writer.write("Content-Type: text/plain\r\n");
                writer.write("\r\n");
                writer.write("Hello from simple Java server!");
                writer.flush();
                client.close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
