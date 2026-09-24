package map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HashMapPractice {
    public static void main(String[] args) {
        Map<Integer, String> hm = new HashMap();
        hm.put(1,"Aryan");
        hm.put(2,"Vikash");
        hm.put(3,"Ajay");
        hm.put(4,"Raj");
        System.out.println(hm);
        Set s   = hm.entrySet();
        System.out.println(s);
        Iterator i = s.iterator();
        while (i.hasNext()){
            Map.Entry entry=(Map.Entry) i.next();
            System.out.print(entry.getKey()+":"+ entry.getValue() +", ");
        }
    }
}
