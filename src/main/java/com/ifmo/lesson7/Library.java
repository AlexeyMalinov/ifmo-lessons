package com.ifmo.lesson7;

/**
 * Библиотека помогает вести учет книг: какие книги и сколько в ней хранятся.
 * Библиотека ограничена по числу типов книг, это ограничение задается аргументом
 * конструктора maxBookKinds. Например, если библиотека ограничена числом 10,
 * то это означает, что она может хранить 10 разных книг, но любое их количество.
 * <p>
 * Если из библиотеки убираются все книги одного типа, то освобождается место,
 * на которое можно добавить книгу другого типа.
 * Например:
 * <pre>
 *     Library library = new Library(2);
 *     library.put(new Book("Stephen King", "Shining"), 2); // return true
 *     library.put(new Book("Stephen King", "Dark Tower"), 3); // return true
 *
 *     // Эту книгу добавить не можем, т.к. лимит 2
 *     library.put(new Book("Tolstoy", "War and peace"), 6); // return false
 *
 *     // Забираем все книги Тёмной башни, чтобы освободить место.
 *     library.take(new Book("Stephen King", "Dark Tower"), 3) // return 3
 *
 *     // Теперь мы можем успешно добавить "Войну и мир".
 *     library.put(new Book("Tolstoy", "War and peace"), 6); // return true
 * </pre>
 * <p>
 * Если попытаться взять из библиотеки больше книг, чем у нее есть, то она
 * должна вернуть только число книг, которые в ней находились и освободить место.
 * Например:
 *
 * <pre>
 *     Library library = new Library(2);
 *     library.put(new Book("Stephen King", "Shining"), 2); // return true
 *
 *     // Все равно вернет 2, т.к. больше нет.
 *     library.take(new Book("Stephen King", "Shining"), 10) // return 2
 * </pre>
 */
public class Library {

    Shelf[] shelves;

    private static class Shelf {
        Book value;
        int quantity;
        Shelf next;

        Shelf(Book value, int quantity) {
            this.value = value;
            this.quantity = quantity;
        }
    }

    public Library(int maxBookKinds) {
        shelves = new Shelf[maxBookKinds];
    }

    /**
     * Add books to library.
     *
     * @param book     Book to add.
     * @param quantity How many books to add.
     * @return {@code True} if book successfully added, {@code false} otherwise.
     */
    public boolean put(Book book, int quantity) {
        int shelfIndex = getShelfIndex(book);
        if (shelves[shelfIndex] == null) {
            shelves[shelfIndex] = new Shelf(book, quantity);
            return true;
        }
        Shelf shelf = shelves[shelfIndex];
        while (shelf != null) {
            if (book.equals(shelf.value)) {
                shelf.quantity += quantity;
                return true;
            } else {
                if (shelf.next != null) {
                    shelf = shelf.next;
                } else {
                    shelf.next = new Shelf(book, quantity);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Take books from library.
     *
     * @param book     Book to take.
     * @param quantity How many books to take.
     * @return Actual number of books taken.
     */
    public int take(Book book, int quantity) {
        Shelf shelf = findShelf(book);
        if (shelf != null) {
            if (shelf.quantity > quantity) {
                shelf.quantity -= quantity;
                return quantity;
            } else {
                remove(book);
                return shelf.quantity;
            }
        }
        return 0;
    }

    /**
     * Метод поиска книги я массиве ячеек библиотеки
     *
     * @param book книга которую необходимо найти
     * @return {@code int} индекс ячейки в массиве, -1 в случаи если книга не найдена
     */
    private Shelf findShelf(Book book) {
        int shelfIndex = getShelfIndex(book);
        if (shelves[shelfIndex] == null) return null;
        Shelf shelf = shelves[shelfIndex];
        while (shelf != null){
            if(book.equals(shelf.value)) break;
            shelf = shelf.next;
        }
        return shelf;
    }

    /**
     * Метод удаляет полку для конкретной книге
     * @param book -  книга
     */
    private void remove(Book book){
        int shelfIndex = getShelfIndex(book);
        if(shelves[shelfIndex] == null) return;
        if(book.equals(shelves[shelfIndex].value)) {
            shelves[shelfIndex] = shelves[shelfIndex].next;
            return;
        }
        Shelf shelf = shelves[shelfIndex];
        while (shelf.next != null) {
            if(book.equals(shelf.next.value)) {
                shelf.next = shelf.next.next;
                return;
            }
            shelf = shelf.next;
        }
        return;
    }

    /**
     * Метод расчета индекса по hash коду
     * @param book - книга
     * @return {@code int} индек
     */
    private int getShelfIndex(Book book){
        return Math.abs(book.hashCode()) % shelves.length;
    }
}
