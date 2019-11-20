/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.function.Function;

/**
 * This class provides utility functions that operate on {@link List Lists}.
 *
 * @author Blair Butterworth
 */
public class Lists
{
    /**
     * Disable construction of this static utility class.
     */
    private Lists() {
    }

    /**
     * Creates a new mutable list containing the given element.
     *
     * @param element   the initial contents of the resulting list.
     * @param <T>       the type of the elements in the resulting list.
     *
     * @return a new {@link ArrayList}.
     */
    public static <T> List<T> asList(T element) {
        List<T> result = new ArrayList<>();
        result.add(element);
        return result;
    }

    /**
     * Returns a new {@link List} containing the contents of the given lists.
     *
     * @param listA the first list to add.
     * @param listB the second list to add.
     * @param <T>   the type of the elements in the given lists.
     *
     * @return a new {@link List} containing the contents of the given lists.
     */
    public static <T> List<T> union(List<T> listA, List<T> listB) {
        List<T> result = new ArrayList<>(listA.size() + listB.size());
        result.addAll(listA);
        result.addAll(listB);
        return result;
    }

    /**
     * Returns a new {@link List} containing the given element and collection.
     *
     * @param element       an element to add.
     * @param collection    a collection to add.
     * @param <T>           the type of the elements in the given lists.
     *
     * @return a new {@link List} containing the given element and collection.
     */
    public static <T> List<T> union(T element, Collection<T> collection) {
        List<T> result = new ArrayList<>(collection.size() + 1);
        result.add(element);
        result.addAll(collection);
        return result;
    }

    public static <T> List<T> replace(List<T> list, T target, T replacement) {
        int index = list.indexOf(target);
        if (index != -1) {
            list.remove(index);
            list.add(index, replacement);
        }
        return list;
    }

    /**
     * Returns a new {@link List} containing the given values.
     *
     * @param elements  an array of values.
     * @param <T>       the type of elements in the set.
     *
     * @return a new {@code Sets} containing the given values.
     */
    public static <T> List<T> of(T ... elements) {
        Objects.requireNonNull(elements);
        List<T> list = new ArrayList<>();
        for (T element: elements) {
            list.add(element);
        }
        return list;
    }

    public static <A, B> List<B> convert(List<A> list, Function<A, B> converter) {
        List<B> converted = new ArrayList<>(list.size());
        for (A element: list) {
            converted.add(converter.apply(element));
        }
        return converted;
    }

    /**
     * Sorts this list according to the order induced by the specified
     * {@link Comparator}.
     *
     * <p>All elements in this list must be <i>mutually comparable</i> using the
     * specified comparator (that is, {@code c.compare(e1, e2)} must not throw
     * a {@code ClassCastException} for any elements {@code e1} and {@code e2}
     * in the list).
     *
     * <p>If the specified comparator is {@code null} then all elements in this
     * list must implement the {@link Comparable} interface and the elements'
     * {@linkplain Comparable natural ordering} should be used.
     *
     * <p>This list must be modifiable, but need not be resizable.
     *
     * @implSpec
     * The default implementation obtains an array containing all elements in
     * this list, sorts the array, and iterates over this list resetting each
     * element from the corresponding position in the array. (This avoids the
     * n<sup>2</sup> log(n) performance that would result from attempting
     * to sort a linked list in place.)
     *
     * @implNote
     * This implementation is a stable, adaptive, iterative mergesort that
     * requires far fewer than n lg(n) comparisons when the input array is
     * partially sorted, while offering the performance of a traditional
     * mergesort when the input array is randomly ordered.  If the input array
     * is nearly sorted, the implementation requires approximately n
     * comparisons.  Temporary storage requirements vary from a small constant
     * for nearly sorted input arrays to n/2 object references for randomly
     * ordered input arrays.
     *
     * <p>The implementation takes equal advantage of ascending and
     * descending order in its input array, and can take advantage of
     * ascending and descending order in different parts of the same
     * input array.  It is well-suited to merging two or more sorted arrays:
     * simply concatenate the arrays and sort the resulting array.
     *
     * <p>The implementation was adapted from Tim Peters's list sort for Python
     * (<a href="http://svn.python.org/projects/python/trunk/Objects/listsort.txt">
     * TimSort</a>).  It uses techniques from Peter McIlroy's "Optimistic
     * Sorting and Information Theoretic Complexity", in Proceedings of the
     * Fourth Annual ACM-SIAM Symposium on Discrete Algorithms, pp 467-474,
     * January 1993.
     *
     * @param c the {@code Comparator} used to compare list elements.
     *          A {@code null} value indicates that the elements'
     *          {@linkplain Comparable natural ordering} should be used
     * @throws ClassCastException if the list contains elements that are not
     *         <i>mutually comparable</i> using the specified comparator
     * @throws UnsupportedOperationException if the list's list-iterator does
     *         not support the {@code set} operation
     * @throws IllegalArgumentException
     *         (<a href="Collection.html#optional-restrictions">optional</a>)
     *         if the comparator is found to violate the {@link Comparator}
     *         contract
     * @since 1.8
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T> List<T> sort(List<T> list, Comparator<? super T> c) {
        Object[] a = list.toArray();
        Arrays.sort(a, (Comparator) c);
        ListIterator<T> i = list.listIterator();
        for (Object e : a) {
            i.next();
            i.set((T) e);
        }
        return list;
    }
}