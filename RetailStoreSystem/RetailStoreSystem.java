import java.util.Scanner;

public class RetailStoreSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double totalPrice = 0.0;
        double salesTaxRate;
        double discount;

        System.out.print("Enter sales tax rate (in %): ");
        salesTaxRate = scanner.nextDouble();
        System.out.print("Enter discount amount: ");
        discount = scanner.nextDouble();

        while (true) {
            System.out.print("Enter item price (or -1 to finish): ");
            double itemPrice = scanner.nextDouble();
            if (itemPrice == -1) {
                break;
            }

            System.out.print("Enter item quantity: ");
            int itemQuantity = scanner.nextInt();

            totalPrice += itemPrice * itemQuantity;
        }

        double salesTax = totalPrice * (salesTaxRate / 100);
        double finalTotal = totalPrice + salesTax - discount;

        System.out.printf("Subtotal: %.2f %n", totalPrice);
        System.out.printf("Sales Tax (%f.2f): %.2f %n", salesTaxRate, salesTax);
        System.out.printf("Discount: %.2f %n", discount);
        System.out.printf("Total: %.2f %n", finalTotal);

        scanner.close();
    }

}