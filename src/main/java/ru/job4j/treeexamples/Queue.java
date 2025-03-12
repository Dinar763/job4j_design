package ru.job4j.treeexamples;

public interface Queue<T> {
    void add(T item);
    T remove();

    boolean isEmpty();
}
