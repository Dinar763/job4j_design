package ru.job4j;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = in.nextInt();
        in.nextLine();
        String inputLine = in.nextLine();
        String[] numbers = inputLine.split(" ");
        int[] arr = new int[count];
        int flag = 0;
        for (int i = 0; i < count; i++) {
            arr[i] = Integer.parseInt(numbers[i]);
            if (arr[i] > flag){
                flag = arr[i];
            }
        }
        System.out.println(flag);
    }
}
