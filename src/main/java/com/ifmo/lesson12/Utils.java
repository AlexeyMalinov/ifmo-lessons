package com.ifmo.lesson12;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Utils {
    public static <T> Iterable<T> view(Iterable<T>... iterables) {
        if(iterables.length == 0){
            return List.of();
        }
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return new Iterator<T>() {
                    private int pos;
                    private Iterator<T> current;

                    @Override
                    public boolean hasNext() {
                        if(current == null){
                            current = iterables[pos].iterator();
                        }
                        if(!current.hasNext()){
                            pos++;

                            if(pos < iterables.length){
                                current = iterables[pos].iterator();
                            } else {
                                return false;
                            }
                        }
                        return current.hasNext();
                    }

                    @Override
                    public T next() {
                        return current.next();
                    }
                };
            }
        };
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> filter) {
        List<T> result = new ArrayList<>(list.size());

        for (T item: list){
            if(filter.test(item)){
                result.add(item);
            }
        }

        return result;
    }

    public static <T, R> Iterable<R> view(Predicate<T> predicate, Transformer<T, R> transformer, Iterable<T>...iterables){
        if(iterables.length == 0){
            return List.of();
        }

        return new Iterable<R>() {

            @Override
            public Iterator<R> iterator() {
                return new Iterator<R>() {
                    private int pos;
                    private Iterator<T> current;
                    private T item;
                    private boolean flag;

                    @Override
                    public boolean hasNext() {
                        if (current == null) {
                            current = iterables[pos].iterator();
                        }
                        if (!current.hasNext()) {
                            pos++;
                            if (pos < iterables.length) {
                                current = iterables[pos].iterator();
                            } else {
                                return false;
                            }
                        }
                        item = current.next();
                        while (item != null && !predicate.test(item)) {
                            item = current.next();
                        }
                        flag = true;
                        return item != null;
                    }

                    @Override
                    public R next() {
                        if(!flag){
                            hasNext();
                            flag = false;
                        }
                        return transformer.transform(item);
                    }
                };
            }
        };
    }
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();

        list1.add("1");
        list1.add("2");

        list2.add("3");
        list2.add("4");

        list3.add("5");
        list3.add("6");

        Iterable<String> view = view(list1, list2, list3);

//        for (String s : view) {
//            System.out.println(s);
//        }

        Iterable<Integer> view2 = view(Utils::isValid, Integer::valueOf, list1, list2, list3);

        for (Integer s : view2) {
            System.out.println(s);
        }
    }

    private static boolean isValid(String string){
        return (Integer.parseInt(string) & 1) == 0;
    }
}
