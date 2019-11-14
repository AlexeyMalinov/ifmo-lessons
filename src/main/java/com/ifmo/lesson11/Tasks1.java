package com.ifmo.lesson11;

import com.ifmo.lesson11.inner.Message;
import com.ifmo.lesson11.inner.MessageGenerator;
import com.ifmo.lesson11.inner.MessagePriority;

import java.util.*;

/**
 * Created by xmitya on 17.10.16.
 */
public class Tasks1 {

    public static void main(String[] args) {
        MessageGenerator generator = new MessageGenerator();

        List<Message> messages = generator.generate(100);

        countEachPriority(messages);
        countCountEachCode(messages);
        countUniqueMessages(messages);

        System.out.println("Genuine messages in natural order: \n" + genuineMessagesInOriginalOrder(messages));

        removeEach(generator.generate(100), MessagePriority.LOW);
        removeOther(generator.generate(100), MessagePriority.URGENT);
    }

    private static void countEachPriority(List<Message> messages) {
        // Сосчитайте количество сообщений для каждого приоритета.
        // Ответ необходимо вывести в консоль.

        Map <MessagePriority, Integer> map = new TreeMap<>();
        for(Message message: messages){
            map.merge(message.getPriority(), 0, Tasks1::increment);
        }

        printMap(map);


    }

    private static void countCountEachCode(List<Message> messages) {
        // Сосчитайте количество сообщений для каждого кода сообщения.
        // Ответ необходимо вывести в консоль.

        Map<Integer, Integer> map = new TreeMap<>();
        for(Message message: messages){
            map.merge(message.getCode(), 0, Tasks1::increment);
        }

        printMap(map);
    }

    private static void countUniqueMessages(List<Message> messages) {
        // Сосчитайте количество уникальных сообщений.
        // Ответ необходимо вывести в консоль.

        Map <Message, Integer> map = new TreeMap<>(Tasks1::compareMessage);
        for(Message message : messages){
            map.merge(message, 0, Tasks1::increment);
        }

        printMap(map);

    }

    private static List<Message> genuineMessagesInOriginalOrder(List<Message> messages) {
        // Здесь необходимо вернуть только неповторяющиеся сообщения и в том порядке, в котором
        // они встречаются в первоначальном списке. Например, мы на входе имеем такие сообщения:
        // [{URGENT, 4}, {HIGH, 9}, {LOW, 3}, {HIGH, 9}],
        // то на выходе должны получить:
        // [{URGENT, 4}, {HIGH, 9}, {LOW, 3}].
        // Т.е. остались только уникальные значения, и порядок их поступления сохранен.

        LinkedHashSet<Message> set = new LinkedHashSet<>(messages);

        return new ArrayList<>(set);
    }


    private static void removeEach(Collection<Message> messages, MessagePriority priority) {
        // Удалить из коллекции каждое сообщение с заданным приоритетом.
        System.out.printf("Before remove each: %s, %s\n", priority, messages);

        messages.removeIf(message -> message.getPriority().equals(priority));

        System.out.printf("After remove each: %s, %s\n", priority, messages);
    }

    private static void removeOther(Collection<Message> messages, MessagePriority priority) {
        // Удалить из коллекции все сообщения, кроме тех, которые имеют заданный приоритет.
        System.out.printf("Before remove other: %s, %s\n", priority, messages);

        messages.removeIf(message -> !message.getPriority().equals(priority));

        System.out.printf("After remove other: %s, %s\n", priority, messages);
    }

    /**
     * Выводит в консоль Map
     * @param map
     */
    private static void printMap(Map<?,?> map){
        for (Object o: map.keySet()){
            System.out.println(o + ": " + map.get(o));
        }
    }

    /**
     * Метод увеличивает страое значение на 1
     * метод соответствует функциональному интерфейсу {@code BiFunction}
     * @param oldValue
     * @param newValue
     * @return
     */
    private static Integer increment(Integer oldValue, Integer newValue){
        return ++oldValue;
    }

    private static int compareMessage(Message m1, Message m2){
        if(m1 == m2){
            return m1.getPriority().compareTo(m2.getPriority());
        }
        return m1.getCode() - m2.getCode();
    }

}
