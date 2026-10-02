package collection.set;

import java.util.LinkedHashSet;
import java.util.Set;

//Its underline data structures are "HashTable" & "LinkedList" --> J2SE 1.4v --- It follows insertion order
public class LinkedHashSetPrac {
    public static void main(String[] args) {
        Set lhs = new LinkedHashSet();
        lhs.add("aa");
        lhs.add("cc");
        lhs.add("01");
        System.out.println(lhs);

    }
}
