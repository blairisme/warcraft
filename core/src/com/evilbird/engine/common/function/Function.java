package com.evilbird.engine.common.function;

//TODO: Replace with Java Comparator now that Android SDK has been bumped
public interface Function<T, R> {

    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     */
    R apply(T t);
}