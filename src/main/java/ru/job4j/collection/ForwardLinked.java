package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size;
    private int modCount;
    private Node<T> head;

    public void add(T value) {
        if (head == null) {
            head = new Node<>(value, null);
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node(value, null);
        }
        modCount++;
        size++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> elem = head;
        for (int i = 0; i < index; i++) {
            elem = elem.next;
        }
        return elem.item;
    }

    public T deleteFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        final T val = head.item;
        final Node<T> newNode = head.next;
        head.item = null;
        head.next = null;
        head = newNode;
        size--;
        modCount++;
        return val;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> current = head;
            int cursor = modCount;

            @Override
            public boolean hasNext() {
                if (cursor != modCount) {
                    throw new ConcurrentModificationException();
                }
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T item = current.item;
                current = current.next;
                return item;
            }
        };
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}
