package stream;

import java.util.ArrayList;

/**
 * StreamAPIPractice
 */
public class StreamAPIPractice {

    public static void main(String[] args) {
        ArrayList<Integer> al = new ArrayList<>();
        al.add(34);
        al.add(22);
        al.add(99);


    // normal way with collections ->
    // for(int i:al){
    //     if(i%2==0){
    //         System.out.println(i);
    //     }
    // }

    // now with Stream API ---->
    // Stream<Integer> s1 = al.stream();
    // List l = s1.filter(n->n%2==0).collect(Collectors.toList());
    // System.out.println(l);

    // Stream<Integer> s1 = al.stream();
    // s1.filter(n->n%2==0).forEach(e->System.out.println(e));

    al.stream().filter(n->n%2==0).forEach(e->System.out.println(e));

    }}