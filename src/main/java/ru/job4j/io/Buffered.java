package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Buffered {
    public static void main(String[] args) {
        try (BufferedInputStream input = new BufferedInputStream(new FileInputStream("data/newData.txt"));
             BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream("data/output.txt", true))) {
            String newLine = System.lineSeparator();
            output.write(newLine.getBytes(StandardCharsets.UTF_8));
            output.write(input.readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
