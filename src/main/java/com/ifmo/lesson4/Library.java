package com.ifmo.lesson4;

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

    LibraryCell[] cells;

    public Library(int maxBookKinds) {
        // Возможно здесь следует сынициализировать массив.
        cells = new LibraryCell[maxBookKinds];
    }

    /**
     * Add books to library.
     *
     * @param book     Book to add.
     * @param quantity How many books to add.
     * @return {@code True} if book successfully added, {@code false} otherwise.
     */
    public boolean put(Book book, int quantity) {
        int cellIndex;
        if ((cellIndex = findBook(book)) > -1) {
            LibraryCell cell = cells[cellIndex];
            cell.setQuantity(cell.getQuantity() + quantity);
            return true;
        }
        for (int i = 0; i < cells.length; i++) {
            if (cells[i] == null) {
                cells[i] = new LibraryCell(book, quantity);
                return true;
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
        int cellIndex = findBook(book);
        if (cellIndex > -1) {
            int cellQuantity = cells[cellIndex].getQuantity();
            if (cellQuantity > quantity) {
                cells[cellIndex].setQuantity(cellQuantity - quantity);
                return quantity;
            } else {
                cells[cellIndex] = null;
                return cellQuantity;
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
    private int findBook(Book book) {
        int index = -1;
        for (int i = 0; i < cells.length; i++) {
            if (cells[i] != null) {
                if (cells[i].getBook().author.equals(book.author)
                        && cells[i].getBook().title.equals(book.title)) {
                    index = i;
                }
            }
        }
        return index;
    }
}
