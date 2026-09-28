package stream;

import java.util.ArrayList;

/**
 * StreamAPI Learn
 */
public class StreamAPI {

    public static void main(String[] args) {
        // List<Integer> nums = Arrays.asList(34,23,54,64,34,23,99,23);
        // List<Integer> even = nums.stream().filter(n -> n%2==0).collect(Collectors.toList());
        // List<Integer> even = nums.stream().sorted().collect(Collectors.toList());
        ArrayList<Integer> al = new ArrayList<>();
        al.add(34);
        al.add(22);
        al.add(99);
        // first way of getting stream instance -->
        // Stream<Integer> s1 = al.stream();
        // s1.forEach(e->System.out.println(e));

        // Second way of getting stream instance -->  Static methods - of(), of(args), empty(), builder()
        // @SuppressWarnings("rawtypes")
        // Stream<ArrayList> s2 = Stream.of(al);
        // s2.forEach(e->System.out.println(e));
        // --------------------------------------
        // Stream s3 = Stream.of(34,24,23,66);
        // s3.forEach(e->System.out.println(e));

        // Fourth way of getting stream instance -->
        // int[] arr = {23,42,55,22};
        // IntStream s4 = Arrays.stream(arr);
        // s4.forEach(e->System.out.println(e));

        // 5th way of getting stream empty instance -->
        // Stream s5 = Stream.empty();

        // 6th way of getting stream instance -->
        // Stream s6 = Stream.builder().build();
    }
}