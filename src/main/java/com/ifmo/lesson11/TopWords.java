package com.ifmo.lesson11;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class TopWords {

    private interface GeneratorMap<K, V> {
        Map<K, V> generate(List<K> list);
    }

    public static void main(String[] args) throws IOException {
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
        System.out.println(top10Phrases(words));
        System.out.println(charactersFrequency(words));
    }

    public static Map<String, Integer> top10Words(List<String> lines) {
        return getTop(lines, 10, TopWords::initialWordMap, TopWords::compare);
    }

    public static Map<String, Integer> top10Phrases(List<String> lines) {
        return getTop(lines, 10, TopWords::initialPhrasesMap, TopWords::compare);
    }

    public static Map<Character, Integer> charactersFrequency(List<String> lines) {

        return initialCharacterMap(lines);
    }

    /**
     * Метод сравнивает значения записей
     * @param o1 первая запись
     * @param o2 вторая запись
     * @return отрицательное значение, если первая запись больше второй
     * положительное, если значение второй, больше первой
     * и нулевое, если записи равны.
     */
    private static int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
        if (o2.getValue().compareTo(o1.getValue()) == 0) {
            return o1.getKey().compareTo(o2.getKey());
        }
        return o2.getValue().compareTo(o1.getValue());
    }

    /**
     * Метод формирует словарь в котором ключи - это фразы (два слова подряд),
     * а значения - количество раз, которые это фраза встречается в списке.
     * @param words список слов
     * @return словарь частот фраз
     */
    private static Map<String, Integer> initialPhrasesMap(List<String> words) {
        Map<String, Integer> map = new HashMap<>();

        for (int i = 1; i < words.size(); i++) {
            map.merge(words.get(i - 1) + " " + words.get(i), 1, (oldValue, newValue) -> ++oldValue);
        }

        return map;
    }

    /**
     * Метод формирует словарь в котором ключи - это слова из списка,
     * а значения - количество раз, которые это слово встречается в списке
     * @param words список слов
     * @return словарь частот слов
     */
    private static Map<String, Integer> initialWordMap(List<String> words) {
        Map<String, Integer> map = new HashMap<>();

        for (String word : words) {
            map.merge(word, 1, (oldValue, value) -> ++oldValue);
        }

        return map;
    }


    /**
     * Метод формирует словарь в котором ключи - это символы слов из списка,
     * а значения - количество раз, которые этот символ встречается в списке
     * @param words список слов
     * @return словарь частот символов
     */
    private static Map<Character, Integer> initialCharacterMap(List<String> words) {
        Map<Character, Integer> map = new HashMap<>();


        for(String word: words) {
            for (int i = 0; i < word.length(); i++) {
                map.merge(word.charAt(i), 1, (oldValue, newValue) -> ++oldValue);
            }
        }

        return map;
    }


    /**
     * етод формирует топ частот строк
     * @param lines список строк
     * @param limit количество элементов в топе
     * @param generator функция формирующая словарь частот строк
     * @param comparator функция сравнения записей словаря
     * @return словарь топ строк
     */
    private static Map<String, Integer> getTop(List<String> lines,
                                               int limit,
                                               GeneratorMap<String, Integer> generator,
                                               Comparator<Map.Entry<String, Integer>> comparator) {

        if(lines.size() == 0) return Map.of();

        Map<String, Integer> map = generator.generate(lines);

        if(map.size() == 0) return Map.of();
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(comparator);

        Map<String, Integer> resultMap = new TreeMap<>();
        for (int i = 0; i < limit; i++) {
            resultMap.put(list.get(i).getKey(), list.get(i).getValue());
        }

        return resultMap;
    }

}
