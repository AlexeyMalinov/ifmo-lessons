package com.ifmo.lesson18;

import java.io.BufferedOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

/**
 * Реализовать шифрующий (XOR) поток вывода.
 */
public class CryptoOutputStream extends FilterOutputStream {

    byte [] key;
    int index;
    /**
     * Создаёт новый {@link CryptoOutputStream}.
     * При записи применяет операцию XOR последовательно:
     * каждый байт из ключа ^ каждый байт из выходящего потока.
     * Когда встречается конец ключа, то на следующий байт потока
     * берётся первый байт из ключа (по принципу кольцевого буфера).
     *
     * @param out Поток вывода.
     * @param key Ключ шифрования.
     */
    public CryptoOutputStream(OutputStream out, byte[] key) {
        super(out);
        this.key = key;
    }

    @Override
    public void write(int b) throws IOException {
        index = index >= key.length ? 0 : index;
        b ^= key[index++];
        out.write(b);
    }

    @Override
    public void write(byte b[]) throws IOException {
        write(b, 0, b.length);
    }

    @Override
    public void write(byte b[], int off, int len) throws IOException {
        Objects.checkFromIndexSize(off, len, b.length);
        for (int i = 0 ; i < len ; i++) {
            checkKeyIndex();
            out.write(b[off + i] ^ key[index++]);
        }
    }

    private void checkKeyIndex(){
        index = index >= key.length ? 0 : index;
    }
}
