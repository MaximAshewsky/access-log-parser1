public class Fraction extends Number {
    private final int numerator;
    private final int denominator;

    public Fraction(int numerator, int denominator) {
        if (denominator <= 0)
            throw new IllegalArgumentException("Знаменатель должен быть положительным числом");
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    public Fraction sum(Fraction other) {
        int newNumerator = this.numerator * other.denominator + other.numerator * this.denominator;
        int newDenominator = this.denominator * other.denominator;
        return new Fraction(newNumerator, newDenominator);
    }

    public Fraction minus(Fraction other) {
        int newNumerator = this.numerator * other.denominator - other.numerator * this.denominator;
        int newDenominator = this.denominator * other.denominator;
        return new Fraction(newNumerator, newDenominator);
    }

    public Fraction sum(int value) {
        return sum(new Fraction(value, 1));
    }

    public Fraction minus(int value) {
        return minus(new Fraction(value, 1));
    }

    public int intValue() {
        return (int) Math.round((double) numerator / denominator);
    }

    public long longValue() {
        return Math.round((double) numerator / denominator);
    }

    public float floatValue() {
        return (float) numerator / denominator;
    }

    public double doubleValue() {
        return (double) numerator / denominator;
    }

}
