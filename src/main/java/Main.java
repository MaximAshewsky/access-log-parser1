import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int n = 0;
        for (; ; ) {
            System.out.println("Укажите путь до файла");
            String path = new Scanner(System.in).nextLine();

            File file = new File(path);
            boolean fileExists = file.exists();
            boolean isDirectory = file.isDirectory();

            if (!file.exists()) {
                System.out.println("Путь указан неверно");
                continue;
            }
            if (isDirectory) {
                System.out.println("Это путь до папки");
                continue;
            }
            if (file.exists() && file.isFile()) {
                n++;
                System.out.println("Путь указан верно. Этот файл номер " + n);
            }

            try {
                FileReader fileReader = new FileReader(path);
                BufferedReader reader = new BufferedReader(fileReader);
                String line;
                int totalLines = 0;
                int maxLength = 0;
                int minLength = Integer.MAX_VALUE;
                while ((line = reader.readLine()) != null) {
                    int length = line.length();

                    totalLines++;
//                    System.out.println("Строка " + totalLines + ": " + length + " символов");
//                    по заданию не очень понятно нужно ли чтобы был вывод каждой строки и колличество символов в этих строках, но на всякий я добавил такой вывод
                    if (length > 1024) {
                        throw new LineTooLongException(
                                "Обнаружена строка длиной " + length + " символов. " +
                                        "Максимальное допустимое значение — 1024 символа."
                        );
                    }
                    if (length > maxLength) {
                        maxLength = length;
                    }
                    if (length < minLength) {
                        minLength = length;
                    }
                }
                reader.close();
                System.out.println("Общее количество строк: " + totalLines);
                System.out.println("Длина самой длинной строки: " + maxLength);
                System.out.println("Длина самой короткой строки: " + minLength);
            } catch (LineTooLongException ex) {
                System.err.println("Ошибка: " + ex.getMessage());
                return;
            } catch (Exception ex) {
                System.err.println("Ошибка при чтении файла:");
                ex.printStackTrace();
            }
        }
    }
}






