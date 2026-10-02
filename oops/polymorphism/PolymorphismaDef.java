package oops.polymorphism;

/*
Types of Polymorphism:

1. Method Overloading
   - Same method name
   - Different parameter list
   - Compile-time polymorphism

2. Method Overriding
   - Same method signature in parent and child class
   - Runtime polymorphism
*/

class Calculator {

    int add(int a, int b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }
}

class Animal {

    void sound() {
        System.out.println("Animal makes sound");
    }
}

class Dog extends Animal {

    @Override                     // Method Overriding
    void sound() {
        System.out.println("Dog barks");
    }
}

public class PolymorphismaDef {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println(calculator.add(23, 21));
        System.out.println(calculator.add(23, 12, 42));
       Animal animal = new Dog();  // Method Overriding
       animal.sound();
    }
}
