package map;

import java.util.NavigableMap;
import java.util.SortedMap;
import java.util.TreeMap;

public class NavigableMapPrac {
    public static void main(String[] args) {
        NavigableMap<Integer, String> sm = new TreeMap();
        sm.put(1,"Hello");
        sm.put(2, "Hi");
        sm.put(3, "welcome");
        sm.put(0,"Come");
        System.out.println(sm.ceilingKey(2));
        System.out.println(sm.ceilingEntry(2));
        System.out.println(sm.floorKey(3));
        System.out.println(sm.lowerKey(3));
        System.out.println(sm.higherKey(2));
        System.out.println(sm.descendingMap());
        System.out.println(sm.pollFirstEntry());
        System.out.println(sm);

    }


}
