public class Main {
    public static void main(String[] args) {
        Fraction f1 = new Fraction(1, 3);
        Fraction f2 = new Fraction(2, 5);
        Fraction f3 = new Fraction(7, 8);
        Fraction result = f1.sum(f2).sum(f3).minus(5);
        System.out.println("Результат = " + result);
        System.out.println("intValue = " + result.intValue());
        System.out.println("doubleValue = " + result.doubleValue());
        System.out.println("floatValue = " + result.floatValue());
        System.out.println("longValue = " + result.longValue());


    }
}






