package com.ifmo.lesson15;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
    Реализуйте все методы с использованием потоков ввода-вывода.
 */
public class IOStreamTasks {

    public static void main(String[] args) throws IOException {
        File src = new File("src/main/resources/wap.txt");
        File dst = new File("src/main/resources/wap_out.txt");

        try (InputStream in = new FileInputStream(src);
             OutputStream out = new FileOutputStream(dst)) {
            copy(in, out);
        }

        File dir = new File("src/main/resources/file_size");
        List<File> files = split(src, dir, 2048);
        files.stream().forEach(System.out::println);
        assembly(files, new File("src/main/resources/wap_out_assembly.txt"));
        for (File file : files) {
            file.deleteOnExit();
        }

        File key = new File("src/main/resources/key");
        File encrypt = new File("src/main/resources/encrypt");

        try (InputStream in = new FileInputStream(src);
             OutputStream outEn = new FileOutputStream(encrypt)) {
            encrypt(in, outEn, getString(key));
        }

        File decrypt = new File("src/main/resources/decrypt");
        encrypt(encrypt, decrypt, key);
    }

    /**
     * Полностью копирует один поток в другой.
     *
     * @param src Входящий поток.
     * @param dst Выходящий поток.
     * @throws IOException Будет выброшен в случае ошибки.
     */
    public static void copy(InputStream src, OutputStream dst) throws IOException {
        BufferedInputStream in = new BufferedInputStream(src);
        BufferedOutputStream out = new BufferedOutputStream(dst);

        byte[] buf = new byte[1000000];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
            out.flush();
        }
    }

    /**
     * Последовательно разбивает файл на несколько более мелких, равных
     * указанному размеру. Последний файл может быть меньше задданого
     * размера.
     *
     * @param file   Файл, который будет разбит на несколько.
     * @param dstDir Директория, в которой будут созданы файлы меньшего размера.
     * @param size   Размер каждого файла-части, который будет создан.
     * @return Список файлов-частей в том порядке, в котором они должны считываться.
     * @throws IOException Будет выброшен в случае ошибки.
     */
    public static List<File> split(File file, File dstDir, int size) throws IOException {
        List<File> list = new ArrayList<>();
        byte[] buf = new byte[size];
        try (InputStream in = new FileInputStream(file)) {
            for (int i = 1; ; i++) {
                int len;
                if ((len = in.read(buf)) < 0) break;
                File outFile = new File(dstDir.getAbsolutePath() + "/" + file.getName() + "_" + i);
                list.add(outFile);
                try (OutputStream out = new FileOutputStream(outFile)) {
                    out.write(buf, 0, len);
                }
            }
        }
        return list;
    }

    /**
     * Собирает из частей один файл.
     *
     * @param files Список файлов в порядке чтения.
     * @param dst   Файл, в который будут записаны все части.
     * @throws IOException Будет выброшен в случае ошибки.
     */
    public static void assembly(List<File> files, File dst) throws IOException {
        byte[] buf = new byte[1024];
        try (OutputStream out = new FileOutputStream(dst)) {
            for (File file : files) {
                try (InputStream in = new FileInputStream(file)) {
                    int len;
                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                }
            }
        }
    }

    /**
     * Шифрует/дешифрует поток с помощью XOR. В качестве ключа используется пароль.
     *
     * @param src        Входящий поток, данные которого будут зашифрованы/расшифрованы.
     * @param dst        Выходящий поток, куда будут записаны зашифрованные/расшифрованные данные.
     * @param passphrase Пароль.
     * @throws IOException Будет выброшен в случае ошибки.
     */
    public static void encrypt(InputStream src, OutputStream dst, String passphrase) throws IOException {
        int len;
        byte[] buf = new byte[1024];
        while ((len = src.read(buf)) > 0) {
            encrypt(buf, passphrase);
            dst.write(buf, 0, len);
        }
    }

    /**
     * Шифрует/дешифрует файл с помощью файла-ключа.
     *
     * @param src Файл, который должен быть зашифрован/расшифрован.
     * @param dst Файл, куда будут записаны зашифрованные/расшифрованные данные.
     * @param key Файл-ключ.
     * @throws IOException Будет выброшен в случае ошибки.
     */
    public static void encrypt(File src, File dst, File key) throws IOException {
        byte[] bufIn = new byte[1024];
        String passphrase = getString(key);
        try (InputStream in = new BufferedInputStream(new FileInputStream(src));
             OutputStream out = new BufferedOutputStream(new FileOutputStream(dst))) {
            encrypt(in, out, passphrase);
        }
    }

    /**
     * Шифрует/дешифрует массив байт с помощью ключевой строки
     *
     * @param array      массив, который должен быть зашифрован/расшифрован.
     * @param passphrase строка, с помощью которой должен быть зашшифрован/расшифрован массив.
     */
    private static void encrypt(byte[] array, String passphrase) {
        encrypt(array, passphrase.getBytes());
    }

    /**
     * Шифрует/дешифрует массив байт с помощью ключивого массива байт
     *
     * @param array массив, который должен быть зашифрован/расшифрован.
     * @param key   массив, с помощью которого должен быть зашшифрован/расшифрован массив.
     */
    private static void encrypt(byte[] array, byte[] key) {
        for (int i = 0, j = 0; i < array.length; i++) {
            array[i] ^= key[j];
            j = ++j < key.length ? j : 0;
        }
    }

    /**
     * Возвращает строку полученную из файла
     *
     * @param file файл, из которого должна быть получена строка.
     * @return {@code String} строка.
     * @throws IOException
     */
    private static String getString(File file) throws IOException {
        StringBuilder sb = new StringBuilder();
        char[] chars = new char[1024];
        try (Reader reader = new FileReader(file)) {
            int len;
            while ((len = reader.read(chars)) > 0) {
                sb.append(chars, 0, len);
            }
        }
        return sb.toString();
    }
}
