package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             FileWriter writer = new FileWriter(target)) {

            boolean isUnavailable = false;
            String startTime = "";

            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] parts = line.split(" ");
                if (parts.length < 2) {
                    continue;
                }

                String status = parts[0];
                String time = parts[1];

                if (!isUnavailable && ("400".equals(status) || "500".equals(status))) {
                    isUnavailable = true;
                    startTime = time;
                } else if (isUnavailable && ("200".equals(status) || "300".equals(status))) {
                    writer.write(startTime + ";" + time + ";\n");
                    isUnavailable = false;
                }
            }

            if (isUnavailable) {
                writer.write(startTime + ";;\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}

