public class SumAll {
    public static double sumAll(Number... numbers) {
        double sum = 0.0;
        for (int i = 0; i < numbers.length; i++) {
            Number num = numbers[i];
            sum += num.doubleValue();
        }
        return sum;
    }
}
