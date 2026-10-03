package oops.inheritance;

class Parent {

    void parent() {
        System.out.println("Parent class");
    }
}

class Child extends Parent {

    @Override
    void parent() {
        super.parent();
        System.out.println("Child overriding parent()");
    }

    void child() {
        System.out.println("Child class");
    }
}

public class Inheritances {

    public static void main(String[] args) {

        Child c = new Child();

        c.parent();
        c.child();
    }
}
