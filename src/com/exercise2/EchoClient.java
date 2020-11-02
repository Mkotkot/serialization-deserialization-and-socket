package com.exercise2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {
    public static void main(String[] args) throws IOException {
        Socket link = null;
        PrintWriter output = null;
        BufferedReader input = null;
        String headerText = "Please Enter a word";


        try {
            link = new Socket("127.0.0.1", 50000);
            output = new PrintWriter(link.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(link.getInputStream()));
            System.out.println(headerText);
        } catch (UnknownHostException e) {
            System.out.println("Unknown Host");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Cannot connect to host");
            System.exit(1);
        }
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String usrInput;
        while ((usrInput = stdIn.readLine()) != null) {
            output.println(usrInput.trim());
            System.out.println(input.readLine());
        }

        output.close();
        input.close();
        stdIn.close();
        link.close();
    }
}
