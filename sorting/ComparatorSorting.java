package sorting;
import java.util.Arrays;
import java.util.Comparator;

class Student1 {

    int age;

    Student1(int age) {
        this.age = age;
    }
}

public class ComparatorSorting {

    public static void main(String[] args) {

        Student1[] students = {
                new Student1(25),
                new Student1(20),
                new Student1(30)
        };

        Comparator<Student1> byAge =
                (a, b) -> a.age - b.age;

        Arrays.sort(students, byAge);

        for (Student1 s : students) {
            System.out.println(s.age);
        }
    }
}
