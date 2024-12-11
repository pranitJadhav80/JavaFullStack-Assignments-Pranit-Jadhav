package Dec11Assignment;
class Employee {
    private String name;
    private String department;
    private double basicSalary;

     double HRA_PERCENTAGE = 0.20; 
     static double TA_AMOUNT = 1500;
     static double DA_PERCENTAGE = 0.10; 
     static  double PERK_PERCENTAGE = 0.05; 
     static final double TAX_RATE = 0.15;

    public Employee(String name, String department, double basicSalary) {
        this.name = name;
        this.department = department;
        this.basicSalary = basicSalary;
    }

    public double calculateHRA() {
        return basicSalary * HRA_PERCENTAGE;
    }

    public double calculateDA() {
        return basicSalary * DA_PERCENTAGE;
    }

    public double calculatePerks() {
        return basicSalary * PERK_PERCENTAGE;
    }

    public double calculateTotalSalary() {
        return basicSalary + calculateHRA() + calculateDA() + TA_AMOUNT + calculatePerks();
    }

    public static double calculateTax(double totalSalary) {
        return totalSalary * TAX_RATE;
    }

    public void displaySalaryDetails() {
        double totalSalary = calculateTotalSalary();
        double tax = calculateTax(totalSalary);
        double netSalary = totalSalary - tax;

        System.out.println("Employee Name: " + name);
        System.out.println("Department: " + department);
        System.out.println("Basic Salary: " + basicSalary);
        System.out.println("HRA: " + calculateHRA());
        System.out.println("DA: " + calculateDA());
        System.out.println("TA: " + TA_AMOUNT);
        System.out.println("Perks: " + calculatePerks());
        System.out.println("Total Salary: " + totalSalary);
        System.out.println("Tax Deducted: " + tax);
        System.out.println("Net Salary: " + netSalary);
    }
}

public class EmpSalCalSystem {
    public static void main(String[] args) {
        Employee emp1 = new Employee("Xyz xyz", "IT", 50000);
        
        emp1.displaySalaryDetails();
    }
}