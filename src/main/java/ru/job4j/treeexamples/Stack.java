package ru.job4j.treeexamples;

public interface Stack<T> {
    void push(T item);

    T pop();

    boolean isEmpty();
}
