package com.encoding;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileUtil {
    public static void writeBytes(List<Byte> bytes, String filePath){
        try (FileOutputStream output = new FileOutputStream(filePath)){
            for(byte byt : bytes){
                output.write(byt);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void writeString (String str, String filePath){
        Path path = Paths.get(filePath);

        // Try block to check for exceptions
        try {
            // Now calling Files.writeString() method
            // with path , content & standard charsets
            Files.writeString(path, str,
                    StandardCharsets.UTF_8);
        }

        // Catch block to handle the exception
        catch (IOException ex) {
            // Print messqage exception occurred as
            // invalid. directory local path is passed
            System.out.print("Invalid Path");
        }
    }

    public static String readFile(String filePath) throws IOException {
        Path fileName
                = Path.of(filePath);
        return Files.readString(fileName);
    }

    public static byte[] readBytes(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.readAllBytes(path);
    }
}
