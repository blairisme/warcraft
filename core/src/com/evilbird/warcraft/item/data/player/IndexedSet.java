///*
// * Blair Butterworth (c) 2019
// *
// * This work is licensed under the MIT License. To view a copy of this
// * license, visit
// *
// *      https://opensource.org/licenses/MIT
// */
//
//package com.evilbird.warcraft.item.data.player;
//
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.function.Function;
//
//public class IndexedSet<T, I> extends HashSet<T>
//{
//    private transient Map<Integer, T> index;
//
//    public IndexedSet() {
//    }
//
//    @Override
//    public boolean add(T entry) {
//        boolean result = super.add(entry);
//        buildIndex();
//        index.put(indexer.apply(entry), entry);
//        return result;
//    }
//
//    @Override
//    public boolean addAll(Collection<? extends T> collection) {
//        boolean result = super.addAll(collection);
//        buildIndex();
//        collection.forEach(entry -> index.put(indexer.apply(entry), entry));
//        return result;
//    }
//
//    public boolean containsKey(I key) {
//        buildIndex();
//        return index.containsKey(key);
//    }
//
//    public T get(I key) {
//        buildIndex();
//        return index.get(key);
//    }
//
//    @Override
//    @SuppressWarnings("unchecked")
//    public boolean remove(Object entry) {
//        boolean result = super.remove(entry);
//        buildIndex();
//        index.remove(indexer.apply((T)entry));
//        return result;
//    }
//
//    @Override
//    @SuppressWarnings("unchecked")
//    public boolean removeAll(Collection<?> collection) {
//        boolean result = super.removeAll(collection);
//        buildIndex();
//        collection.forEach(entry -> index.remove(indexer.apply((T)entry)));
//        return result;
//    }
//
//    public boolean removeKey(I key) {
//        T entry = get(key);
//        if (entry != null) {
//            return remove(entry);
//        }
//        return false;
//    }
//
//    private void buildIndex() {
//        if (index == null) {
//            index = new HashMap<>();
//            forEach(entry -> index.put(indexer.apply(entry), entry));
//        }
//    }
//}
