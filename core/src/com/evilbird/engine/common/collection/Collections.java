package com.evilbird.engine.common.collection;

import com.badlogic.gdx.utils.Array;
import com.evilbird.engine.common.function.Comparator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

    public static <T> Array<T> union(Array<T> collectionA, Array<T> collectionB)
    {
        Array<T> result = new Array<>(collectionA.size + collectionB.size);
        result.addAll(collectionA);
        result.addAll(collectionB);
        return result;
    }

    public static <T> List<T> union(List<T> collectionA, List<T> collectionB)
    {
        List<T> result = new ArrayList<>(collectionA.size() + collectionB.size());
        result.addAll(collectionA);
        result.addAll(collectionB);
        return result;
    }

    public static <K, V> Map<K, V> union(Map<K, V> collectionA, Map<K, V> collectionB)
    {
        Map<K, V> result = new HashMap<>(collectionA.size() + collectionB.size());
        result.putAll(collectionA);
        result.putAll(collectionB);
        return result;
    }
}
