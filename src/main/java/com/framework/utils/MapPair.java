package com.framework.utils;

import java.util.HashMap;

public class MapPair<K, V> {
    public HashMap<K, V> userStatus;
    public HashMap<K, V> testResults;

    public MapPair(HashMap<K, V> userStatus, HashMap<K, V> testResults) {
        this.userStatus = userStatus;
        this.testResults = testResults;
    }
}

