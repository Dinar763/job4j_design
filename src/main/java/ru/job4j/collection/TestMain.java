package ru.job4j.collection;

import java.util.List;

public class TestMain {
    public static void main(String[] args) {
        List<String> result = List.of("one", "two", "three");
        //result.add("DDD");
        System.out.println(result.get(2));
    }
}
