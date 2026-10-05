package oops.encaptulation;
class Employee {

    private String name;
    private int salary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {

        if (salary >= 0) {
            this.salary = salary;
        }
    }
}
public class EncaptulationExp {
    public static void main(String[] args) {
        Employee employee = new Employee();

        employee.setName("Aryan");
        employee.setSalary(50000);

        System.out.println(employee.getName());
        System.out.println(employee.getSalary());
    }
}
