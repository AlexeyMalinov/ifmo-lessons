package com.ifmo.lesson5;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

public class ItemHashSet {
    boolean[] array;

    public ItemHashSet() {
        array = new boolean[100000];
    }

    public void put(Item item) {
        if (item == null) return;

        if (hasItem(item)) return;

        int i = Objects.hash(item);
        if (i > array.length) exArray(i);
        array[Math.abs(Objects.hashCode(item))] = true;
    }

    public void remove(Item item) {
        if(item == null) return;
        int i = Objects.hashCode(item);
        if (i < array.length) {
            array[i] = false;
        }
    }

    public boolean hasItem(Item item) {
        if(item == null) return false;
        int i = Objects.hashCode(item);
        if (i < array.length) {
            return array[i];
        }
        return false;
    }

    private void exArray(int length) {
        array = Arrays.copyOf(array, length);
    }

}
