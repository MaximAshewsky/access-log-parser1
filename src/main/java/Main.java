import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите первое число: ");
        String xStr = scanner.nextLine();
        System.out.print("Введите второе число: ");
        String yStr = scanner.nextLine();
        System.out.print("Введите третье число: ");
        String zStr = scanner.nextLine();
        String[] numbers = {xStr, yStr, zStr};
        double result = Sum.sum(numbers);
        System.out.println("Сумма: " + result);
    }
}






