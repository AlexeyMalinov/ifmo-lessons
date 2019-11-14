package com.ifmo.lesson11;

import com.ifmo.lesson11.inner.Message;
import com.ifmo.lesson11.inner.MessagePriority;
import com.ifmo.lesson11.inner.User;

import java.util.*;

import static com.ifmo.lesson11.inner.UserGenerator.generate;

/**
 * Created by xmitya on 20.10.16.
 */
public class Tasks2 {
    public static void main(String[] args) {
        System.out.println(generate(10));
    }

    private static void sortByPriority(List<Message> messages, MessagePriority priority) {

        messages.sort(Comparator.comparing(Message::getPriority));

    }

    private static NavigableSet<User> sortedByCompanyAndName(List<User> users) {

        users.sort((u1, u2) -> (u1.getCompany().compareTo(u2.getCompany()) == 0)
                ? u1.getName().compareTo(u2.getName())
                : u1.getCompany().compareTo(u2.getCompany()));

        return new TreeSet<>(users);
    }

    private static NavigableSet<User> sortedBySalaryAndName(List<User> users) {

        users.sort((u1, u2) -> (u1.getSalary() - u2.getSalary() == 0)
                ? u1.getName().compareTo(u2.getName())
                : u1.getSalary() - u2.getSalary()
        );

        return new TreeSet<>(users);
    }

    private static NavigableSet<User> sortedBySalaryAgeCompanyAndName(List<User> users) {

        users.sort(Tasks2::compareUserBySalaryAgeCompanyAndName);

        return new TreeSet<>(users);
    }

    private static <T> Iterator<T> viewIterator(Iterable<T> it1, Iterable<T> it2) {

        return null;
    }

    private static int compareUserBySalaryAgeCompanyAndName(User u1, User u2){
        int result = 0;
        if((result =(u1.getSalary() - u2.getSalary())) != 0) return result;
        if((result =(u1.getAge() - u2.getAge()))!= 0) return result;
        if((result =(u1.getCompany().compareTo(u2.getCompany()))) != 0) return result;
        return u1.getName().compareTo(u2.getName());
    }


}
