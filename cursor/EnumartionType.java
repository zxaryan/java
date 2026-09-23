package cursor;

import java.util.Enumeration;
import java.util.Vector;

// only for legacy classes - vector, stack
public class EnumartionType {

    public static void main(String[] args) {
        Vector v = new Vector<>();
        v.add("aaa");
        v.add("bbb");
        v.add("ccc");
        Enumeration e = v.elements();
        while (e.hasMoreElements()){
            System.out.println(e.nextElement());
        }
        System.out.println(v);
    }
}
