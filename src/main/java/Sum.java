public class Sum {
    private static boolean isValidNumber(String str) {
        if (str == null || str.trim().isEmpty()) {
            return false;
        }
        return str.trim().matches("^[+-]?(\\d+\\.?\\d*|\\.\\d+)([eE][+-]?\\d+)?$");
    }
    public static double sum(String[] numbers) {
        double total = 0.0;

        for (String numStr : numbers) {
            if (isValidNumber(numStr)) {
                total += Double.parseDouble(numStr);
            }
        }

        return total;
    }
}
