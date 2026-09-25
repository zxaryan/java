package map;

import java.util.SortedMap;
import java.util.TreeMap;

public class SortedMapPractice {
    public static void main(String[] args) {
        SortedMap<Integer, String> sm = new TreeMap();
        sm.put(1,"Hello");
        sm.put(2, "Hi");
        sm.put(3, "welcome");
        sm.put(0,"Come");
        System.out.println(sm);
        System.out.println(sm.firstKey());
        System.out.println(sm.headMap(3));
        System.out.println(sm.tailMap(3));
        System.out.println(sm.subMap(2,4));

    }
    

}
