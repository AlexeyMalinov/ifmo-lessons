package com.ifmo.lesson23;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class TopWords {
    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        // Создаем файл, указывая путь к текстовому файлу на диске
        File text = new File("src\\main\\resources\\wap.txt");

        // Вычитываем все строки из файла
        List<String> lines = Files.readAllLines(text.toPath());
        int cpus = Runtime.getRuntime().availableProcessors();
        // Создём пул потоков.
        ExecutorService pool = Executors.newFixedThreadPool(cpus);

        List<Future<Map<String, Integer>>> futures = new ArrayList<>(cpus);

        int partSize = lines.size() / cpus;
        // Отправляем задачи на обработку.
        for (int i = 0; i < cpus; i++) {
            List<String> partLines = new ArrayList<>(lines.subList((i * partSize), ((i + 1) * partSize))); // todo 1/cpus строк

            Future<Map<String, Integer>> future =
                    pool.submit(() -> countWords(partLines));

            futures.add(future);
        }

        List<Map<String, Integer>> counts = new ArrayList<>(cpus);
        // Собираем результаты выполнения.
        for (Future<Map<String, Integer>> future : futures) {
            Map<String, Integer> map = future.get();

            counts.add(map);
        }
        // Объединяем все счётчики и находим 10 наиболее часто
        // встречающихся слов.
        List<String> top10Words = top10(counts);

        System.out.println(top10Words);

        pool.shutdown();
    }

    private static Map<String, Integer> countWords(List<String> lines) {
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

        Map <String, Integer> countMap = new HashMap<>();

        for (String word : words) {
            countMap.merge(word, 1, Integer::sum);
        }
        return countMap;
    }

    private static List<String> top10(Collection<Map<String, Integer>> counters) {
        Map<String, Integer> resultMap = new HashMap<>();
        for (Map<String, Integer> counter : counters) {
                mergeMap(counter, resultMap);
        }
        return resultMap.entrySet().stream()
                .sorted((e1,e2) -> e2.getValue() - e1.getValue())
                .limit(10).map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private static void mergeMap(Map<String, Integer> producer, Map<String, Integer> consumer) {
        for (String key : producer.keySet()) {
            consumer.merge(key, producer.get(key), Integer::sum);
        }
    }
}
