package com.exercise1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSock = null;
        String headerText = "Please Select an option (Date/Time)";

        try {
            serverSock = new ServerSocket(50000);
        } catch (IOException ie) {
            System.out.println("Can't listen on 50000");
            System.exit(1);
        }
        Socket link = null;
        System.out.println("Listening for connection ...");
        try {
            link = serverSock.accept();
        } catch (IOException ie) {
            System.out.println("Accept failed");
            System.exit(1);
        }
        System.out.println("Connection successful");
        PrintWriter output = new PrintWriter(link.getOutputStream(), true);
        BufferedReader input = new BufferedReader(new InputStreamReader(link.getInputStream()));
        String inputLine;
        while ((inputLine = input.readLine()) != null) {
            if (inputLine.trim().isEmpty()) {
                continue;
            }
            if (inputLine.equalsIgnoreCase("Date")) {
                output.write(populateDateNow() + "\n" + headerText);
            }
            if (inputLine.equalsIgnoreCase("Time")) {
                output.write(populateTimeNow() + "\n" + headerText);
            }
            output.println();
        }
        output.close();
        input.close();
        link.close();
        serverSock.close();
    }


    private static String populateDateNow() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateNow = new Date();
        return dateFormat.format(dateNow);
    }

    private static String populateTimeNow() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date timeNow = new Date();
        return dateFormat.format(timeNow);
    }


}
