package com.ifmo.lesson13;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTasks {

    private static final Random RANDOM = new Random();

    static class Person {
        private final String name;
        private final int age;
        private final String country;

        public Person(String name, int age, String country) {
            this.name = name;
            this.age = age;
            this.country = country;
        }
    }

    public static void main(String[] args) {
        List<Person> people = generatePeople(100).collect(Collectors.toList());

        List<String> countries = countriesSortedByTheirPopulationDescending(people.stream());
        String countryThatHasMostKids = countryThatHasMostKids(people.stream());
        Map<String, Long> populationByCountry = populationByCountry(people.stream());

        System.out.println(countries);
        System.out.println(countryThatHasMostKids);
        System.out.println(populationByCountry);

        List<String> words = List.of("the", "hello", "approximation", "greetings", "java", "war");

        Map<Integer, Set<String>> wordsByLength = groupWordsByLength(words);
        int averageWordLength = averageWordLength(words);
        Set<String> longestWords = longestWords(words);

        System.out.println(wordsByLength);
        System.out.println(averageWordLength);
        System.out.println(longestWords);
    }

    // Метод возвращает страны в порядке убывания их населения.
    public static List<String> countriesSortedByTheirPopulationDescending(Stream<Person> people) {

        return people
                .collect(Collectors.groupingBy(p -> p.country, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    // Метод находит страну (или одну из стран), в которых больше всего человек в возрасте
    // до 18 лет.
    public static String countryThatHasMostKids(Stream<Person> people) {
        return people
                .filter(p -> p.age < 18)
                .collect(Collectors.groupingBy(p -> p.country, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse("Unknown");

    }

    // Метод возвращает карту стран их населения.
    // Надо поменять на Long
    public static Map<String, Long> populationByCountry(Stream<Person> people) {
        return people
                .collect(Collectors.groupingBy(p -> p.country, Collectors.counting()));
    }

    // Метод генерирует случайных людей в ограниченном наборе стран.
    // number - число желаемых людей.
    public static Stream<Person> generatePeople(int number) {
        return Stream.generate(() -> new Person(getRandomName(), RANDOM.nextInt(101), getRandomCountry()))
                .limit(number);
    }

    // Метод возвращает карту сгруппированных слов по их длине. Например, для
    // трёхбуквенных слов будет:
    // 3 -> "the", "map", "got", "war"...
    public static Map<Integer, Set<String>> groupWordsByLength(List<String> words) {
        return words
                .stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toMap(String::length, Set::of, (strings, strings2) -> {
                            Set<String> set = new HashSet<>(strings);
                            set.addAll(strings2);
                            return set;
                        })
                );
    }

    // Метод находит среднюю длину слов в списке.
    public static int averageWordLength(List<String> words) {
        Double average = words.stream().mapToInt(String::length).average().orElse(0D);
        return (int) Math.round(average);
    }

    // Метод находит самое длинное слово или слова, если таких несколько.
    public static Set<String> longestWords(List<String> words) {
        int max = words.stream().max(Comparator.comparing(String::length)).orElse("").length();
        return words
                .stream()
                .filter(w -> w.length() == max)
                .collect(Collectors.toSet());
    }

    /**
     * Метод возвращает произвольное имя
     * @return {@code String}
     */
    private static String getRandomName() {
        String[] names = {"Harry", "Oliver", "Jack", "Charlie", "Thomas", "Jacob", "Alfie", "Riley", "William", "James",
                "Amelia", "Olivia", "Jessica", "Emily", "Lily", "Ava", "Heather", "Sophie", "Mia", "Isabella"};
        return names[RANDOM.nextInt(20)];
    }

    /**
     * Метод возвращает произвольно одно из названий страны
     * @return {@code String}
     */
    private static String getRandomCountry() {
        String[] country = {
                "Estonia",
                "Finland",
                "Iceland",
                "Ireland",
                "Latvia",
                "Lithuania",
                "Norway",
                "Scotland",
                "Sweden"
        };

        return country[RANDOM.nextInt(9)];
    }

}
