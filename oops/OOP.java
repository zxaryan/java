package oops;
/* OOP (Object-Oriented Programming)
 Why OOP was introduced:
 To organize programs around objects and their behavior,
 making large software easier to develop, maintain, and reuse.

 Object:
 A runtime entity that has state (data) and behavior (methods).
 Example: Car, Employee, BankAccount.

 Class:
 A blueprint/template that defines what data and behavior
 objects of that type will have. */

class  Student{
    String name;
    Integer age;
    public void printDetails(){
        System.out.println(this.name  +" : "+ this.age);
    }
}
public class OOP {
    public static void main(String[] args) {
         Student std1 = new Student();
         std1.name="Aryan";
         std1.age=24;
         std1.printDetails();
    }
}
