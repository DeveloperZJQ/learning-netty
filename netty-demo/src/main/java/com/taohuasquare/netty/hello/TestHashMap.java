package com.taohuasquare.netty.hello;

import java.util.HashMap;
import java.util.Map;

public class TestHashMap {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);
        Integer integer = map.get("1");
        System.out.println(integer);
    }
}
