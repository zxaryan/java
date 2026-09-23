package set;

import java.util.NavigableSet;
import java.util.TreeSet;

// it was introduced in Java SE 6
public class NavigableSetPrac {
    public static void main(String[] args) {
        NavigableSet ns = new TreeSet();
        ns.add("dd");
        ns.add("hh");
        ns.add("aa");
        ns.add("ee");

        System.out.println(ns.descendingSet());
        System.out.println(ns.ceiling("cc")); //returns self or bigger elem
        System.out.println(ns.higher("dd"));
        System.out.println(ns.lower("ff"));
        System.out.println(ns.floor("cc"));
        System.out.println(ns.pollFirst());
        System.out.println(ns.pollLast());
    }
}
