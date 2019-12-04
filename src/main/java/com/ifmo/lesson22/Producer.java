package com.ifmo.lesson22;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

public class Producer {



    public static void main(String[] args) throws IOException, InterruptedException {
        // Создаем файл, указывая путь к текстовому файлу на диске
        File text = new File("src\\main\\resources\\wap.txt");

        // Вычитываем все строки из файла
        List<String> lines = Files.readAllLines(text.toPath());

        System.out.println(top10Words(lines));
    }

    /**
     * Формирует ассоциативный массив десяти самых часто встречаемых, в исходном списке строк, слов и их количества
     * @param lines - строки
     * @return
     * @throws InterruptedException
     */
    private static Map<String, Integer> top10Words(List<String> lines) throws InterruptedException {

        final String poisonPill = new String();
        BlockingQueue<String> lineQueue = new LinkedBlockingQueue<>();
        BlockingQueue<Map<String, Integer>> resultQueue = new LinkedBlockingQueue<>();

        List<Thread> threads = initializeWorkerThreadList(lineQueue,resultQueue,poisonPill);

        startThreads(threads);

        lineQueue.addAll(lines);

        lineQueue.add(poisonPill);

        return mergeResultMap(threads.size(), resultQueue)
                .entrySet().stream()
                .sorted((e1, e2) -> e2.getValue() - e1.getValue())
                .limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


    /**
     * Инициализирует список потоков из объектов {@code Worker}
     * @param lineQueue - входящая очередь для объектов {@code Worker}
     * @param resultQueue - исходящая очередь для объектов {@code Worker}
     * @param poisonPill - ключивое объект типа {@code String} обозначающее завершение входящего потока данных
     * @return Список потоков
     */
    private static List<Thread> initializeWorkerThreadList(BlockingQueue<String> lineQueue,
                                                 BlockingQueue<Map<String, Integer>> resultQueue,
                                                 String poisonPill){
        int processors = Runtime.getRuntime().availableProcessors();
        List<Thread> threads = new ArrayList<>(processors);

        for (int i = 0; i < processors; i++) {
            threads.add(new Worker(poisonPill, lineQueue, resultQueue));
        }

        return threads;
    }

    /**
     * Запускает все потоки из списка
     * @param threads
     */
    private static void startThreads(List<Thread> threads) {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    /**
     * Формирует результируеющий ассоциативный массив из результатов полученых от разных потоков
     * @param numberOfResults - количесво результатов которые нужно получить и объединить
     * @param resultQueue - очередь для получения результатов от потоков
     * @return Ассоциативный массив с общим результатом
     * @throws InterruptedException - исключения которое может возникнуть при ожидании результатов в очереди.
     */
    private static Map<String, Integer> mergeResultMap(int numberOfResults, BlockingQueue<Map<String, Integer>> resultQueue)
            throws InterruptedException {

        Map<String, Integer> resultMap = new HashMap<>();

        for (int i = 0; i < numberOfResults; i++) {
            var map = resultQueue.take();
            mergeMap(map, resultMap);
        }

        return resultMap;
    }

    /**
     * Сливает два ассоциативных массива.
     * @param producer
     * @param consumer
     */
    private static void mergeMap(Map<String, Integer> producer, Map<String, Integer> consumer) {
        for (String key : producer.keySet()) {
            consumer.merge(key, producer.get(key), Integer::sum);
        }
    }
}
