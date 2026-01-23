public class Main {
    public static void main(String[] args) {
        Fraction f1 = new Fraction(3, 5);
        Fraction f2 = new Fraction(49, 12);
        Fraction f3 = new Fraction(3, 2);
        Fraction f4 = new Fraction(1, 3);
        double result1 = SumAll.sumAll(2,f1,2.3);
        double result2 = SumAll.sumAll(3.6,f2,3,f3);
        double result3 = SumAll.sumAll(f4,1);
        System.out.println("Результат 1 = " + result1);
        System.out.println("Результат 2 = " + result2);
        System.out.println("Результат 3 = " + result3);
    }
}






