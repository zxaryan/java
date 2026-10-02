package oops;

/* What is Constructor Chaining?
   Calling one constructor from another constructor:
   - Within the same class  → this(...)
   - From a parent class    → super(...)

   Why use it?
     ✅ Avoid duplicate initialization code (DRY)
     ✅ Provide multiple ways to create an object
     ✅ Centralize default value logic
*/

// ─────────────────────────────────────────────
// 1) Chaining WITHIN the same class (this(...))
// ─────────────────────────────────────────────
class Staff {
    private String name;
    private int age;
    private String department;

    Staff() {
        this("Unknown", 0, "Unknown");
    }

    Staff(String name) {
        this(name, 0, "Unknown");
    }

    Staff(String name, int age, String department) {
        this.name = name;
        this.age = age;
        this.department = department;
    }

    @Override
    public String toString() {
        return "Staff{name='" + name + "', age=" + age +
                ", department='" + department + "'}";
    }
}

// ─────────────────────────────────────────────
// 2) Chaining to PARENT constructor (super(...))
// ─────────────────────────────────────────────
class Person {
    String name;

    Person(String name) {
        this.name = name;
        System.out.println("Person constructor");
    }
}

class Employee extends Person {
    String department;

    Employee(String name, String department) {
        super(name);                 // must be first statement
        this.department = department;
        System.out.println("Employee constructor");
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', department='" + department + "'}";
    }
}

// ─────────────────────────────────────────────
// 3) Driver
// ─────────────────────────────────────────────
public class AdvConstructors {
    public static void main(String[] args) {

        // ---- this() chaining demo ----
        Staff s1 = new Staff();
        Staff s2 = new Staff("Ajay");
        Staff s3 = new Staff("Vikash", 25, "IT");

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);

        System.out.println("----");

        // ---- super() chaining demo ----
        Employee e1 = new Employee("Ajay", "IT");
        System.out.println(e1);
    }
}