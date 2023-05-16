package org.example;

import java.io.FileWriter;

public class SaveFile {
    public static void saveFile(String folderName, String titleName, String content) {
        // initialize a string
        String str = content;
        try {

            // attach a file to FileWriter
            FileWriter fw
                    = new FileWriter("/media/duy/33e9c786-2adc-46bf-8c3b-56bb1843c2e7/WorkSpace/Java/atHome/Crawler/" + folderName + "/" + titleName);

            // read each character from string and write
            // into FileWriter
            for (int i = 0; i < str.length(); i++)
                fw.write(str.charAt(i));

            System.out.println("Successfully written");

            // close the file
            fw.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
