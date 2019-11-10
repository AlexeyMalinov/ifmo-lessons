package com.ifmo.lesson12;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static <T> List<T> filter(List<T> list, Predicate<T> filter) {
        return list.stream().filter(filter::test).collect(Collectors.toList());
    }

    public static <T,R> List<R> transform(List<T> list, Transformer<T, R> transformer){
        return list.stream().map(transformer::transform).collect(Collectors.toList());
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

        Iterable<Integer> view2 = view(Utils::isValid, Integer::valueOf, list1, list2, list3);

        List<Integer> list4 = List.of(0,1,2,3,4,5,6,7,8,9);
        List<String> list5 = List.of("aaaaaa","bbbbbb","cc","ddd","eeeee");

        List<Integer> listFilter1 = filter(list4,n -> (n & 1) == 0);
        List<String> listFilter2 = filter(list5,n -> n.length() > 3);

        List<String> listTransform1 = transform(list4, n -> n.toString());
        List<Integer> listTransform2 = transform(listTransform1, n -> Integer.valueOf(n));


        for (Integer s : view2) {
            System.out.println(s);
        }
    }

    private static boolean isValid(String string){
        return (Integer.parseInt(string) & 1) == 0;
    }
}
