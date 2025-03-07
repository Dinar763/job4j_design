package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        expand();
        int index = getIndex(key);
        boolean result = table[index] == null;
        if (result) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
        }
        return result;
    }

    private int getIndex(K key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    @Override
    public V get(K key) {
        V result = null;
        int index = getIndex(key);
        if (table[index] != null && isABoolean(key, index)) {
            result = table[index].value;
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int index = getIndex(key);
        if (table[index] != null && isABoolean(key, index)) {
            table[index] = null;
            count--;
            modCount++;
            result = true;
        }
        return result;
    }

    private boolean isABoolean(K key, int index) {
        return Objects.hashCode(table[index].key) == Objects.hashCode(key) && Objects.equals(table[index].key, key);
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            final int expModCount = modCount;
            int index;
            int result = 0;

            @Override
            public boolean hasNext() {
                if (expModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < count;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw  new NoSuchElementException();
                }
                while (table[result] == null) {
                    result++;
                }
                index++;
                return  table[result++].key;
            }
        };
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        if (count >= (int) (capacity * LOAD_FACTOR)) {
            capacity <<= 1;
            MapEntry<K, V>[] newTable = new MapEntry[capacity];
            for (MapEntry<K, V> val : table) {
                if (val != null) {
                    newTable[getIndex(val.key)] = val;
                }
            }
            table = newTable;
        }
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}

