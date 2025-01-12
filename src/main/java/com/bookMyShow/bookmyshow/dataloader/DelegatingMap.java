package com.bookMyShow.bookmyshow.dataloader;

import java.util.Collection;
import java.util.Map;
import java.util.Set;


public class DelegatingMap<K,V> implements Map<K,V> {


    private final DataLoader<K,V> dataLoader;
    private final Map<K,V> map;



    public DelegatingMap(DataLoader<K,V> dataLoader,Map<K,V> map){
        this.dataLoader=dataLoader;
        this.map=map;
    }


    public V find(Object k1){
        K k = (K) k1;
        if(!map.containsKey((k))){
           V v= dataLoader.load(k);
            map.put(k,v);
            return v;
        }

        return map.get(k);

    }


    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey((K)key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue((V)value);
    }

    @Override
    public V get(Object key) {
        return map.get((K)key);
    }

    @Override
    public V put(K key, V value) {
        return map.put(key,value);
    }

    @Override
    public V remove(Object key) {
        return map.remove((K)key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        map.putAll(m);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        return map.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return map.entrySet();
    }
}
