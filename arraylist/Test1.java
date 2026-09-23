package arraylist;

import java.util.ArrayList;

public class Test1 {
    public static void main(String[] args) {
        ArrayList al = new ArrayList<>();
        al.add(23);
        al.addFirst(2.3);
        al.add(21);
        al.addFirst(2.0);
        al.add(4,"Aryan");

//        System.out.println(al.get(1));
        System.out.println(al.set(1, "Don"));
                System.out.println(al);
    }
}
