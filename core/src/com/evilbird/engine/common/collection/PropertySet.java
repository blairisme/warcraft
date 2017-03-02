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

    /*
    void	clearCells()
    Removes all of the mappings from this map (optional operation).
    boolean	containsKey(Object key)
    Returns true if this map contains a mapping for the specified key.
    boolean	containsValue(Object value)
    Returns true if this map maps one or more keys to the specified value.
    Set<Map.Entry<K,V>>	entrySet()
    Returns a Set view of the mappings contained in this map.
    boolean	equals(Object o)
    Compares the specified object with this map for equality.
        V	get(Object key)
    Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
    int	hashCode()
    Returns the hash code value for this map.
    boolean	isEmpty()
    Returns true if this map contains no key-value mappings.
    Set<K>	keySet()
    Returns a Set view of the keys contained in this map.
        V	put(K key, V value)
    Associates the specified value with the specified key in this map (optional operation).
    void	putAll(Map<? extends K,? extends V> m)
    Copies all of the mappings from the specified map to this map (optional operation).
    V	remove(Object key)
    Removes the mapping for a key from this map if it is present (optional operation).
    int	size()
    Returns the number of key-value mappings in this map.
        Collection<V>	values()
    Returns a Collection view of the values contained in this map.
    */
}
