package com.ifmo.lesson16.print;

import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;

public class PrintClient {

    private SocketAddress serverAddr;

    private String name;

    private Scanner scanner;

    public PrintClient(SocketAddress serverAddr, Scanner scanner) {
        this.serverAddr = serverAddr;
        this.scanner = scanner;
    }

    private void start() throws IOException, ClassNotFoundException {
        System.out.println("Enter your name: ");

        name = scanner.nextLine();

        while (true) {
            System.out.println("Enter message to send: ");

            String msg = scanner.nextLine();

            if ("/exit".equals(msg))
                break;
            else if ("/nick".equals(msg)) {
                System.out.println("Enter new name:");

                name = scanner.nextLine();

                continue;
            } else if ("/myaddr".equals(msg)) {
                printAddresses();

                continue;
            } else if ("/ping".equals(msg)) {
                sendPing();

                continue;
            } else if ("/server_time".equals(msg)) {
                sendServerTime();

                continue;
            } else if ("/ban".equals(msg.split(" ")[0])) {
                sendObject(new Ban(msg.split(" ")[1]));

                continue;
            } else if ("/unban".equals(msg.split(" ")[0])) {
                sendObject(new UnBan(msg.split(" ")[1]));

                continue;
            } else if("/list_users".equals(msg)) {
                sendListUsers();

                continue;
            }

            buildAndSendMessage(msg);
        }
    }

    private void printAddresses() throws SocketException {
        Enumeration e = NetworkInterface.getNetworkInterfaces();

        while (e.hasMoreElements()) {
            NetworkInterface n = (NetworkInterface) e.nextElement();

            Enumeration ee = n.getInetAddresses();

            while (ee.hasMoreElements()) {
                InetAddress i = (InetAddress) ee.nextElement();

                System.out.println(i.getHostAddress());
            }
        }
    }

    private void buildAndSendMessage(String msg) throws IOException {
        Message message = new Message(System.currentTimeMillis(), name, msg);

        sendPrintMessage(message);

        System.out.println("Sent: " + message);
    }

    private void sendServerTime() throws IOException, ClassNotFoundException {
        try(Socket sock = new Socket()) {
            sock.connect(serverAddr);
            try (ObjectOutputStream objOut = new ObjectOutputStream(sock.getOutputStream());
                 ObjectInputStream objIn = new ObjectInputStream(sock.getInputStream())) {

                objOut.writeObject(new ServerTime());
                objOut.flush();

                Object obj = objIn.readObject();
                if (obj instanceof ServerTime) {
                    System.out.println((ServerTime) obj);
                }
            }
        }
    }

    private void sendPing() throws IOException, ClassNotFoundException {
        long average = 0;
        long[] times = new long[5];
        for (int i = 0; i < 5; i++) {
            try (Socket sock = new Socket()) {
                sock.connect(serverAddr);
                try (ObjectOutputStream objOut = new ObjectOutputStream(sock.getOutputStream());
                     ObjectInputStream objIn = new ObjectInputStream(sock.getInputStream())) {

                    objOut.writeObject(new Ping());
                    objOut.flush();

                    Object object = objIn.readObject();

                    if (object instanceof Ping) {
                        times[i] = System.currentTimeMillis() - ((Ping) object).getTimestamp();
                    }
                }
            }
        }
        average = Arrays.stream(times).reduce(Long::sum).orElse(0) / times.length;
        System.out.println(average);
    }

    private <T> void sendObject(T obj) throws IOException {
        try(Socket sock = new Socket()){
            sock.connect(serverAddr);
            try(ObjectOutputStream objOut = new ObjectOutputStream(sock.getOutputStream())){
                objOut.writeObject(obj);
                objOut.flush();
            }
        }
    }

    private void sendListUsers() throws IOException, ClassNotFoundException {
        try(Socket sock = new Socket()){
            sock.connect(serverAddr);
            try(ObjectOutputStream objOut = new ObjectOutputStream(sock.getOutputStream());
            ObjectInputStream objIn = new ObjectInputStream(sock.getInputStream())){
                objOut.writeObject(new UserList());
                objOut.flush();

                Object obj = objIn.readObject();
                if(obj instanceof UserList){
                    List<String> list = ((UserList) obj).getNameUsers();
                    list.stream().forEach(System.out::println);
                }
            }
        }
    }

    private void sendPrintMessage(Message msg) throws IOException {
        try (Socket sock = new Socket()) {
            sock.connect(serverAddr);

            try (ObjectOutputStream objOut = new ObjectOutputStream(sock.getOutputStream())) {

                objOut.writeObject(msg);

                objOut.flush();
            }
        }
    }

    private static SocketAddress parseAddress(String addr) {
        String[] split = addr.split(":");
        return new InetSocketAddress(split[0], Integer.parseInt(split[1]));
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String addr = null;

        if (args != null && args.length > 0)
            addr = args[0];

        Scanner scanner = new Scanner(System.in);

        if (addr == null) {
            System.out.println("Enter server address");

            addr = scanner.nextLine();
        }

        PrintClient client = new PrintClient(parseAddress(addr), scanner);

        client.start();
    }
}
