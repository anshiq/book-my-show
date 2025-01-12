package com.bookMyShow.bookmyshow.dataloader;

@FunctionalInterface
public interface DataLoader<K,V> {

        V load(K key);
}
