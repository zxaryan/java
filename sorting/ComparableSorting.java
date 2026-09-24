package sorting;

import java.util.Arrays;

class Student implements Comparable<Student> {
    int age;
    Student(int age){
        this.age = age;
    }
    @Override
    public int compareTo(Student o) {
        return -(this.age-o.age);
    }
}

public class ComparableSorting {

    public static void main(String[] args) {
        Student[] students = {
                new Student(25),
                new Student(20),
                new Student(30)
        };

        Arrays.sort(students);
        for (Student s : students) {
            System.out.println(s.age);
        }
    }
}
