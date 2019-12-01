package com.ifmo.lesson21;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;


public class TopWords {

    public static void main(String[] args) throws IOException, InterruptedException {

        // Создаем файл, указывая путь к текстовому файлу на диске
        File text = new File("src\\main\\resources\\wap.txt");

        // Вычитываем все строки из файла
        List<String> lines = Files.readAllLines(text.toPath());

        // Создаем пустую коллекцию для слов.
        List<String> words = new ArrayList<>();

        for (String line : lines) {
            // Для каждой строки
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

        }
        System.out.println(top10Words(words));
    }


    /**
     * Формирует ассоциативный массив десяти самых часто встречаемых в исходном списке слов и их количества
     * @param words - список слов для анализа
     * @return
     * @throws InterruptedException
     */
    public static Map<String, Integer> top10Words(List<String> words) throws InterruptedException {

        Map<String, Integer> map = new HashMap<>();

        int processors = Runtime.getRuntime().availableProcessors();
        List<WordsCounter> threads = new ArrayList<>(processors);
        int partSize = words.size() / processors;

        for (int i = 0; i < processors; i++) {
            List<String> partWord = new ArrayList<>(words.subList(i * partSize, (i + 1) * partSize));
            threads.add(new WordsCounter(partWord, map));
        }

        for (WordsCounter thread : threads) {
            thread.start();
        }

        for (WordsCounter thread : threads) {
            thread.join();
        }

        return map.entrySet().stream()
                .sorted((e1,e2) -> e2.getValue() - e1.getValue())
                .limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}
