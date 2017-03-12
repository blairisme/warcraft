package com.evilbird.engine.common.collection;

import com.evilbird.engine.common.function.Comparator;

import java.util.Collection;
import java.util.Iterator;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class Collections
{
    public static <T> T min(Collection<? extends T> collection, Comparator<? super T> comparator)
    {
        Iterator<? extends T> iterator = collection.iterator();
        T result = iterator.next();

        while(iterator.hasNext())
        {
            T other = iterator.next();
            if(comparator.compare(other, result) < 0)
            {
                result = other;
            }
        }
        return result;
    }

}
