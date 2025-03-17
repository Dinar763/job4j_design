package ru.job4j.io;

import java.io.IOException;
import java.io.PrintWriter;

public class ResultFile {
    public static void main(String[] args) {
        try (PrintWriter output = new PrintWriter("data/dataresult.txt")) {
            output.println("Hello, world!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
