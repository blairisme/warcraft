/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

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

    /**
     * Sorts this list according to the order induced by the specified
     * {@link Comparator}.
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