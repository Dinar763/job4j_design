package ru.job4j.collection;

import java.awt.*;
import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }


    @Override
    public void add(T value) {
        growUpCapacity();
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T oldVal = container[Objects.checkIndex(index, size)];
        container[index] = newValue;
        return oldVal;
    }

    @Override
    public T remove(int index) {
        T oldVal = container[Objects.checkIndex(index, size)];
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        container[--size] = null;
        modCount++;
        return oldVal;
    }

    @Override
    public T get(int index) {
        return container[Objects.checkIndex(index, size)];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int point = 0;
            int cursor = modCount;

            @Override
            public boolean hasNext() {
                if (cursor != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point != size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw  new NoSuchElementException();
                }
                return container[point++];
            }
        };
    }

    public void growUpCapacity() {
        int oldCapacity = container.length;
        if (oldCapacity == 0) {
            container = Arrays.copyOf(container, 1);
        } else if (oldCapacity == size) {
            container = Arrays.copyOf(container, container.length * 2);
        }
    }

    public static void main(String[] args) {
        SimpleList<Integer> list = list = new SimpleArrayList<>(3);
        Iterator<Integer> iterator = list.iterator();
        System.out.println(iterator.hasNext());
    }
}
