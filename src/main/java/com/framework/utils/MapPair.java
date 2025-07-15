package com.framework.utils;

import java.util.HashMap;

public class MapPair<K, V> {
    public HashMap<K, V> map1;
    public HashMap<K, V> map2;

    public MapPair(HashMap<K, V> map1, HashMap<K, V> map2) {
        this.map1 = map1;
        this.map2 = map2;
    }
}

