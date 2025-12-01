import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите первое число:");
        int number = new Scanner(System.in) .nextInt();
        System.out.println("Введите второе число:");
        int number1 = new Scanner(System.in) .nextInt();
        double d = (double) number / number1;
        System.out.println("Частное:" + d);
        int s = (int) number + number1;
        System.out.println("Сумма:" + s);
        int r = (int) number - number1;
        System.out.println("Разность:" + r);
        int p = (int) number * number1;
        System.out.println("Произведение:" + p);

    }
}
