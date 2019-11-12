package com.ifmo.lesson9;

public interface Appendable<T, A extends Appendable> {
    /**
     *
     * @param type
     * @return
     */
    A append(T type);

    /**
     *
     * @return
     */
    T value();
}
