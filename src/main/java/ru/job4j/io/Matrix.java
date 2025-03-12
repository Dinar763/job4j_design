package ru.job4j.io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Matrix {
    public static void multiple(int size) {
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = (i + 1) * (j + 1);
            }
        }

        String fileName = "data/matrix_output.txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))){
            for (int[] val: matrix) {
                for (int val1: val) {
                    writer.printf("%" + size  + "d", val1);
                }
                writer.println();
                writer.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        multiple(10);
    }
}
