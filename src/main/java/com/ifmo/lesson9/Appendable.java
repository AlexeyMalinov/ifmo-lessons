package com.ifmo.lesson9;

public interface Appendable<T, A> {
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
