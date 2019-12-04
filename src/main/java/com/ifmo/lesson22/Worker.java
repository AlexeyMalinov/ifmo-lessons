package com.ifmo.lesson22;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class Worker extends Thread {
    private final String poisonPill;
    private final BlockingQueue<String> linesQueue;
    private final BlockingQueue<Map<String, Integer>> resultQueue;
    private final Map<String, Integer> resultMap = new HashMap<>();

    public Worker(String poison_pill, BlockingQueue<String> linesQueue, BlockingQueue<Map<String, Integer>> resultQueue) {
        this.poisonPill = poison_pill;
        this.linesQueue = linesQueue;
        this.resultQueue = resultQueue;
    }

    @Override
    public void run() {
        String line;
        try {
            while ((line = linesQueue.take()) != poisonPill) {
                List<String> words = getWords(line);
                for (String word : words) {
                    resultMap.merge(word, 1, (oldValue, newValue) -> ++oldValue);
                }
            }
            linesQueue.add(poisonPill);
            resultQueue.add(resultMap);
        } catch (InterruptedException e) {
            interrupt();
        }
    }

    /**
     * Формирует список слов из строки
     * @param line - строка
     * @return
     */
    private List<String> getWords(String line) {
        List<String> words = new ArrayList<>();
        String[] wordSplit =
                line.toLowerCase() // Переводим в нижний регистр
                        .replaceAll("\\pP", " ") // Заменяем все знаки на пробел
                        .trim() // Убираем пробелы в начале и конце строки.
                        .split("\\s"); // Разбиваем строки на слова
        for (String s : wordSplit) {
            // Выбираем только непустые слова.
            if (s.length() > 0)
                words.add(s.trim());
        }
        return words;
    }
}
