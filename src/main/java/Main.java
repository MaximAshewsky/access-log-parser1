import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число X: ");
        String xStr = scanner.nextLine();
        System.out.print("Введите число Y: ");
        String yStr = scanner.nextLine();
        double result = PowerCalculator.computePower(xStr, yStr);
        System.out.println("Результат: " + result);
    }
}