package collection.set;

import java.util.SortedSet;
import java.util.TreeSet;

// It doesn't follow insertion order but follow sorting order & it stores homogeneous data type only. java.lang.ClassCast Exception in heterogeneous type
// J2SE 1.2V -storing null value throws nullPointerException & it stores only Comparable-objects(cuz they have CompareTo() method to compare in sorting) otherwise it throws exception -
public class SortedSetPrac {
    public static void main(String[] args) {
        SortedSet ss = new TreeSet();
        ss.add("zz");
        ss.add("gg");
        ss.add("ff");
        ss.add("bb");
        ss.add("pp");
        System.out.println(ss);
        System.out.println(ss.first());
        System.out.println(ss.last());
        System.out.println(ss.headSet("gg"));
        System.out.println(ss.tailSet("gg"));
        System.out.println(ss.subSet("bb", "pp"));
    }
}
