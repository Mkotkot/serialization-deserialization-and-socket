package com.exercise2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSock = null;
        String headerText = "Please Enter a word";

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
            if (isPalindrome(inputLine)) {
                output.write(inputLine + " is a palindrome\n" + headerText);
            } else {
                output.write(inputLine + " is Not a palindrome \n" + headerText);
            }
            output.println();

        }
        output.close();
        input.close();
        link.close();
        serverSock.close();
    }


    static boolean isPalindrome(String str) {
        int i = 0, j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }
}
