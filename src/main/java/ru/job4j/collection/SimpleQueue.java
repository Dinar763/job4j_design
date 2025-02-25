package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();
    private T value;
    private int indexOfPush;
    private int indexOfPoll;

    public T poll() {
        if (indexOfPush == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        while (indexOfPoll != indexOfPush) {
            output.push(input.pop());
            indexOfPoll++;
        }
        value = output.pop();
        indexOfPoll--;
        indexOfPush--;
        while (indexOfPoll > 0) {
            input.push(output.pop());
            indexOfPoll--;
        }
        return value;
    }

    public void push(T value) {
        input.push(value);
        indexOfPush++;
    }
}
