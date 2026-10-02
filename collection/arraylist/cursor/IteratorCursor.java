package collection.arraylist.cursor;

import java.util.ArrayList;
import java.util.Iterator;

// Universal Cursor - used with any Collection Object
public class IteratorCursor {
    public static void main(String[] args) {
        ArrayList a = new ArrayList();
        a.add("aaa");
        a.add("bbb");
        a.add("ccc");
        Iterator i = a.iterator();
//        System.out.println(i.next());
        while (i.hasNext()){
            System.out.println(i.next());
        }
//        System.out.println(a);
    }
}
