package com.tomdog.version1;

import com.tomdog.utils.HttpUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyFirstTomdogApp {
    private static Logger logger = LogManager.getLogger(MyFirstTomdogApp.class.getName());
    private int port;

    public MyFirstTomdogApp(int port){
        this.port = port;
    }


    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        logger.info("tomdog start on port:"+port);

        while(true){
            //receive the request
            Socket socket = serverSocket.accept();

            //write some service code...
            serve(socket);

            //close connection
            close(socket);
        }
    }

    /**
     *
     * @param socket
     * @throws IOException
     */
    private void serve(Socket socket) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        String data = "hello,this is a tom dog!";
        String responeText = HttpUtil.getHttpHeader200(data.length()) + data;
        outputStream.write(responeText.getBytes());
    }

    /**
     *
     * @param socket
     * @throws IOException
     */
    private void close(Socket socket) throws IOException {
        socket.close();
    }


    public static void main(String[] args) throws IOException {
        MyFirstTomdogApp myFirstTomdogApp = new MyFirstTomdogApp(6667);
        myFirstTomdogApp.start();
    }
}
