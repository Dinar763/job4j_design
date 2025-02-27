package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;
import java.util.Iterator;
import java.util.Objects;

public class SimpleArraySet<T> implements SimpleSet<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        boolean flag = contains(value);
        if (!flag) {
            set.add(value);
        }
        return !flag;
    }

    @Override
    public boolean contains(T value) {
        boolean flag = false;
        Iterator<T> iterator = set.iterator();
        while (iterator.hasNext()) {
            T elem = iterator.next();
            if (Objects.equals(elem, value)){
                flag = true;
                break;
            }
        }
        return flag;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }

    public static void main(String[] args) {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        set.add(1);
        System.out.println(set.add(1));
    }
}
