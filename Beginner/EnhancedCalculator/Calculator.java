import java.util.*;

public class Calculator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Calculator Menu ===");
            System.out.println("1. Add\n2. Subtract\n3. Multiply\n4. Divide");
            System.out.println("5. Square Root\n6. Power");
            System.out.println("7. Temp Conversion\n8. Currency Conversion\n9. Exit");
            System.out.print("Choose option: ");
            
            int choice = sc.nextInt();

            if (choice == 9) {
                System.out.println("Exiting...");
                break;
            }

            double a, b;
            switch (choice) {
                case 1:
                    System.out.print("Enter 2 numbers: ");
                    a = sc.nextDouble(); b = sc.nextDouble();
                    System.out.println("Result: " + (a + b));
                    break;
                case 2:
                    System.out.print("Enter 2 numbers: ");
                    a = sc.nextDouble(); b = sc.nextDouble();
                    System.out.println("Result: " + (a - b));
                    break;
                case 3:
                    System.out.print("Enter 2 numbers: ");
                    a = sc.nextDouble(); b = sc.nextDouble();
                    System.out.println("Result: " + (a * b));
                    break;
                case 4:
                    System.out.print("Enter 2 numbers: ");
                    a = sc.nextDouble(); b = sc.nextDouble();
                    if (b == 0) System.out.println("Cannot divide by zero!");
                    else System.out.println("Result: " + (a / b));
                    break;
                case 5:
                    System.out.print("Enter number: ");
                    a = sc.nextDouble();
                    System.out.println("Square Root: " + Math.sqrt(a));
                    break;
                case 6:
                    System.out.print("Enter base and exponent: ");
                    a = sc.nextDouble(); b = sc.nextDouble();
                    System.out.println("Power: " + Math.pow(a, b));
                    break;
                case 7:
                    System.out.print("1.C to F | 2.F to C: ");
                    int t = sc.nextInt();
                    System.out.print("Enter value: ");
                    a = sc.nextDouble();
                    if (t == 1)
                        System.out.println("Fahrenheit: " + ((a * 9 / 5) + 32));
                    else
                        System.out.println("Celsius: " + ((a - 32) * 5 / 9));
                    break;
                case 8:
                    System.out.print("1.INR to USD | 2.USD to INR: ");
                    int c = sc.nextInt();
                    System.out.print("Enter amount: ");
                    a = sc.nextDouble();
                    if (c == 1)
                        System.out.println("USD: " + (a / 83));
                    else
                        System.out.println("INR: " + (a * 83));
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        sc.close();
    }
}
