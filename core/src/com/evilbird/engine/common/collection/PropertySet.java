package com.evilbird.engine.common.collection;

/**
 * Implementors of this TODO:Finish
 *
 * @author Blair Butterworth
 */
public interface PropertySet<K,V>
{
    V getProperty(K key);

    void setProperty(K key, V value);

    //void setProperties(Map<K,V> properties);
}
