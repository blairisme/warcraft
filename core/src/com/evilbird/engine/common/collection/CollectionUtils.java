/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.collection;

import org.apache.commons.lang3.Validate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;

/**
 * Instances of this class provide helper functions for working with
 * CollectionUtils.
 *
 * @author Blair Butterworth
 */
public class CollectionUtils
{
    /**
     * Disable construction of this static utility class.
     */
    private CollectionUtils() {
    }

    /**
     * Iterates through the given {@link Collection} returning the first
     * element that matches the given {@link Predicate}. If no match is found
     * then {@code null} is returned.
     *
     * @param collection    the {@code Collection} to search.
     * @param predicate     the condition that the desired element fulfils.
     * @param <T>           the type of elements in the {@code Collection}.
     *
     * @return  the first element that matches the given {@code Predicate}, or
     *          {@code null} if no match is found.
     */
    public static <T> T find(Collection<T> collection, Predicate<? super T> predicate) {
        Validate.notNull(collection);
        Validate.notNull(predicate);

        Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            final T element = iterator.next();
            if (predicate.test(element)) {
                return element;
            }
        }
        return null;
    }

    public static <T> Collection<T> retain(Collection<? extends T> collection, Predicate<? super T> predicate) {
        Collection<T> result = new ArrayList<>(collection.size());
        for (T element: collection) {
            if (predicate.test(element)) {
                result.add(element);
            }
        }
        return result;
    }
}
