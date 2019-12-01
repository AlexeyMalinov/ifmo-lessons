package com.ifmo.lesson21;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Счетчик слов выполняющий подсчет слов в списке и записывающий результат подсчета в ассоциативный массив
 */
public class WordsCounter extends Thread {

    private final List<String> listWords;
    private final Map<String, Integer> map;

    public WordsCounter(List<String> listWorlds, Map<String, Integer> map) {
        this.listWords = listWorlds;
        this.map = map;
    }

    @Override
    public void run() {
        var countMap = count();
        synchronized (map) {
            for (String word : countMap.keySet()) {
                map.merge(word, countMap.get(word), Integer::sum);
            }
        }
    }

    private Map<String, Integer> count() {
        Map<String, Integer> mapPart = new HashMap<>();
        for (String word : listWords) {
            mapPart.merge(word, 1, (oldValue, value) -> ++oldValue);
        }
        return mapPart;
    }
}
