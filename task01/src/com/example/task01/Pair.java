package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;


public class Pair<T, N>{

    private final T first;
    private final N second;

    private Pair(T first, N second) {
        this.first = first;
        this.second = second;
    }

    public static <T, N> Pair<T, N> of(T first, N second) {
        return new Pair<T,N>(first, second);
    }

    public T getFirst() {
        return first;
    }

    public N getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair<?, ?> pair = (Pair<?, ?>) o;

        return Objects.equals(first, pair.first) &&
                Objects.equals(second, pair.second);
    }


    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    public void ifPresent(BiConsumer<? super T, ? super N> consumer) {
        if (first != null && second != null) {
            consumer.accept(first, second);
        }
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
