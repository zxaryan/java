package collection.arraylist.cursor;

import java.util.ArrayList;
import java.util.ListIterator;

// It is used only with list implemented classes
public class ListIteratorCursor {
    public static void main(String[] args) {
        ArrayList a = new ArrayList();
        a.add("aaa");
        a.add("bbb");
        a.add("ccc");
        ListIterator li = a.listIterator();

        while (li.hasNext()){
//            li.add("ddd");
            System.out.println(li.next());
        }
        System.out.println("----------------------------------------------------");
        while (li.hasPrevious()){
            System.out.println(li.previous());
        }

//        System.out.println(li);
    }
}
