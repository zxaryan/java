package oops;

class Car{
    String brand;
    String model;
    public void printInfo(){
        System.out.println(this.brand + " " + this.model);
    }

    // Parameterised Constructor
    Car(String brand,String model ){
        this.brand = brand;
        this.model = model;
//        System.out.println("Constructor Called!...");
    }
    // Copy Constructor -- concept derived from C++
    Car(Car c2){
        this.brand = c2.brand;
        this.model = c2.model;
    }
}

public class Constructors {
    public static void main(String[] args) {
        Car car1 = new Car("BMW","X7");
        Car car2 = new Car(car1); // Copy Constructor
        car2.printInfo();
    }
}
