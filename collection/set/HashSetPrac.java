package collection.set;

import java.util.HashSet;
import java.util.Set;

public class HashSetPrac {
    public static void main(String[] args) {

//      Set --> HashCode based data structure - Unique elements, only one null value, don't follow insertion order
        Set s = new HashSet();
//      HashSet--> The underline data structure of HashSet is Hashtable (Use Map interface internally)
        s.add("A");
        s.add("b");
        System.out.println(s);
    }
}
