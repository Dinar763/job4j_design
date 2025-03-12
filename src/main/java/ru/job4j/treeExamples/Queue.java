package ru.job4j.treeExamples;

public interface Queue<T> {
    void add(T item);   // добавить элемент в конец очереди
    T remove();         // извлечение элемента из начала очереди

    boolean isEmpty();
}
