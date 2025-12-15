import java.io.File;
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

        }
    }
}





