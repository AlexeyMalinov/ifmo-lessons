package com.ifmo.lesson18;

import java.io.BufferedInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * Реализовать шифрующий (XOR) поток вывода.
 */
public class CryptoInputStream extends FilterInputStream {

    private final byte[] key;
    private int index;

    /**
     * Создаёт новый {@link CryptoInputStream}.
     * При чтении применяет операцию XOR последовательно:
     * каждый байт из ключа ^ каждый байт из входящего потока.
     * Когда встречается конец ключа, то на следующий байт потока
     * берётся первый байт из ключа (по принципу кольцевого буфера).
     *
     * @param in  Поток ввода.
     * @param key Ключ шифрования.
     */
    public CryptoInputStream(InputStream in, byte[] key) {
        super(new BufferedInputStream(in));
        this.key = key;
    }

    @Override
    public int read() throws IOException {
        index = index >= key.length ? 0 : index;
        int result = in.read();
        return (result >= 0) ? result ^ key[index++] : -1;
    }

    @Override
    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        Objects.checkFromIndexSize(off, len, b.length);
        if (len == 0) {
            return 0;
        }

        int c = read();
        if (c == -1) {
            return -1;
        }
        b[off] = (byte) c;

        int i = 1;
        try {
            for (; i < len; i++) {
                c = read();
                if (c == -1) {
                    break;
                }
                b[off + i] = (byte) c;
            }
        } catch (IOException ee) {
        }
        return i;
    }

}

