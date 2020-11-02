package com.exercise3; /**
 * Started by M. Moussavi
 * March 2015
 * Completed by: STUDENT(S) NAME
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WriteRecord {

    ObjectOutputStream objectOut = null;
    MusicRecord record = null;
    Scanner stdin = null;
    Scanner textFileIn = null;

    /**
     * Creates an blank MusicRecord object
     */
    public WriteRecord() {
        record = new MusicRecord();
    }

    public static void main(String[] args) throws IOException {

        WriteRecord d = new WriteRecord();

        String textFileName = "someSongs.txt"; // Name of a text file that contains
        // song records
        String objectFileName = "mySongs.ser"; // Name of the binary file to
        // serialize record objects
        d.openFileInputStream(textFileName);   // open the text file to read from
        d.openObjectOutputStream(objectFileName); // open the object file to
        // write music records into it
        d.createObjectFile();   // read records from opened text file, and write

        d.deserialization();
    }

    /**
     * Opens a file input stream, using the data field textFileIn
     *
     * @param textFileName name of text file to open
     */
    public void openFileInputStream(String textFileName) {

        File text = new File(textFileName);
        //Creating Scanner instnace to read File in Java
        try {
            this.textFileIn = new Scanner(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the data fields of a record object
     *
     * @param year       - year that song was purchased
     * @param songName   - name of the song
     * @param singerName - singer's name
     * @param price      - CD price
     */
    public void setRecord(int year, String songName, String singerName,
                          double price) {
        record.setSongName(songName);
        record.setSingerName(singerName);
        record.setYear(year);
        record.setPrice(price);
    }

    /**
     * Opens an ObjectOutputStream using objectOut data field
     *
     * @param objectFileName name of the object file to be created
     */
    public void openObjectOutputStream(String objectFileName) {

        // TO BE COMPLETED BY THE STUDENTS
        // Serialization
        try {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(objectFileName);
            this.objectOut = new ObjectOutputStream(file);
        } catch (IOException ex) {
            System.out.println("IOException is caught");
        }

    }

    /**
     * Reads records from given text file, fills the blank MusicRecord
     * created by the constructor with the existing data in the text
     * file and serializes each record object into a binary file
     */
    public void createObjectFile() {
        List<MusicRecord> musicRecords = new ArrayList<>();
        while (textFileIn.hasNext()) // loop until end of text file is reached
        {
            int year = Integer.parseInt(textFileIn.nextLine());
            System.out.print(year + "  ");       // echo data read from text file

            String songName = textFileIn.nextLine();
            System.out.print(songName + "  ");  // echo data read from text file

            String singerName = textFileIn.nextLine();
            System.out.print(singerName + "  "); // echo data read from text file

            double price = Double.parseDouble(textFileIn.nextLine());
            System.out.println(price + "  ");    // echo data read from text file

            setRecord(year, songName, singerName, price);
            textFileIn.nextLine();   // read the dashed lines and do nothing
            musicRecords.add(new MusicRecord(year, songName, singerName, price));
        }
        // Method for serialization of object
        try {
            this.objectOut.writeObject(musicRecords);
            System.out.println("Objects has been serialized");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // YOUR CODE GOES HERE
    }

    //     this code for deserialization data from ser file
    public void deserialization() {
        try {
            FileInputStream fileIn = new FileInputStream("mySongs.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            List<MusicRecord> musicRecords = (List<MusicRecord>) in.readObject();
            System.out.println();
            System.out.println();
            System.out.println("Deserialized Data: \n");
            for (MusicRecord musicRecord : musicRecords) {
                System.out.print(musicRecord.getYear() + "  ");       // echo data read from text file
                System.out.print(musicRecord.getSongName() + "  ");  // echo data read from text file
                System.out.print(musicRecord.getSingerName() + "  "); // echo data read from text file
                System.out.println(musicRecord.getPurchasePrice() + "  ");    // echo data read from text file

            }
            in.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
