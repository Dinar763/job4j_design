package ru.job4j.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream input = new FileInputStream("data/even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = input.read()) != -1) {
                text.append((char) read);
            }
            String[] arrText = text.toString().split(System.lineSeparator());
            for (String val: arrText) {
                if (Integer.valueOf(val) % 2 == 0) {
                    System.out.println(val + " четное число!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
