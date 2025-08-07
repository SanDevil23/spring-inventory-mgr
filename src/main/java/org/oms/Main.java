package org.oms;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
            int port = 8080;

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