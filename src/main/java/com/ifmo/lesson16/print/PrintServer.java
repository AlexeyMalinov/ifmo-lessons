package com.ifmo.lesson16.print;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;

public class PrintServer {

    private int port;
    private Set<String> banIp = new HashSet<>();
    private Set<String> users = new HashSet<>();

    private SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");

    public PrintServer(int port) {
        this.port = port;
    }

    private void start() throws IOException {
        try (ServerSocket ssocket = new ServerSocket(port)) {
            System.out.println("Server started on " + ssocket);

            while (true) {
                Socket sock = ssocket.accept();

                try {
                    process(sock);
                } catch (ClassNotFoundException e) {
                    System.err.println("Wrong message was received");

                    e.printStackTrace();
                } finally {
                    sock.close();
                }
            }
        }
    }

    private void process(Socket sock) throws IOException, ClassNotFoundException {
        String host = sock.getInetAddress().getHostAddress();
        if(banIp.contains(host)) return;
        try (ObjectInputStream objIn = new ObjectInputStream(sock.getInputStream());
             ObjectOutputStream objOut = new ObjectOutputStream(sock.getOutputStream())) {
            Object obj = objIn.readObject();

            if (obj instanceof Message) {
                Message msg = (Message) obj;
                users.add(msg.getSender());
                printMessage(msg, host);
            }

            if (obj instanceof Ping) {
                objOut.writeObject(obj);
            }

            if (obj instanceof ServerTime) {
                ServerTime serverTime = ((ServerTime) obj);
                serverTime.setTimeStamp(System.currentTimeMillis());
                objOut.writeObject(serverTime);
            }

            if(obj instanceof Ban){
                banIp.add(((Ban) obj).getIpAddress());
            }

            if(obj instanceof UnBan){
                banIp.remove(((UnBan) obj).getIpAddress());
            }

            if(obj instanceof UserList){
                UserList list = (UserList) obj;
                list.setNameUsers(users);
                objOut.writeObject(list);
            }

        } catch (IOException | ClassNotFoundException | RuntimeException e) {
            System.err.println("Failed process connection from: " + host);

            e.printStackTrace();

            throw e;
        }
    }

    private void printMessage(Message msg, String senderAddr) {
        System.out.printf("%s (%s) at %s wrote: %s\n", msg.getSender(), senderAddr, format.format(new Date(msg.getTimestamp())), msg.getText());
    }

    public static void main(String[] args) throws IOException {
        if (args == null || args.length == 0)
            throw new IllegalArgumentException("Port must be specified");

        int port = Integer.parseInt(args[0]);

        PrintServer printServer = new PrintServer(port);

        printServer.start();
    }
}
