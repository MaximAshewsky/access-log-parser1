public class Sum {
    public static double sum(String[] numbers) {
        double total = 0.0;

        for (String numStr : numbers) {
            try {
                double number = Double.parseDouble(numStr);
                total += number;
            } catch (NumberFormatException e) {
            }
        }

        return total;
    }
}