import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int n = 0;

        int googleBotCount = 0;
        int yandexBotCount = 0;
        int totalBotChecks = 0;
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
                Statistics stats = new Statistics();
                while ((line = reader.readLine()) != null) {
                    int length = line.length();
                    totalLines++;
                    if (length > 1024) {
                        throw new RuntimeException(
                                "Обнаружена строка длиной " + length + " символов. " +
                                        "Максимальное допустимое значение — 1024 символа."
                        );
                    }
                    try {
                        LogEntry entry = new LogEntry(line);
                        stats.addEntry(entry);
//                        System.out.println("Строка " + totalLines + ":");
//                        System.out.println("  IP: " + entry.getIp());
//                        System.out.println("  Пропущенное поле 1: " + entry.getDash1());
//                        System.out.println("  Пропущенное поле 2: " + entry.getDash2());
//                        System.out.println("  Дата и время: " + entry.getTimestamp());
//                        System.out.println("  Запрос: " + entry.getRequest());
//                        System.out.println("  HTTP-код: " + entry.getHttpCode());
//                        System.out.println("  Размер (байты): " + entry.getBytes());
//                        System.out.println("  Referer: " + entry.getReferer());
//                        System.out.println("  User-Agent: " + entry.getUserAgent());
//                        System.out.println();
                        String botName = entry.extractBotName();
                        if (botName != null) {
                            totalBotChecks++;
                            if (botName.equals("Googlebot")) {
                                googleBotCount++;
                            } else if (botName.equals("YandexBot")) {
                                yandexBotCount++;
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        System.err.println("Ошибка разбора строки " + totalLines + ": " + e.getMessage());
                    }
                }
                List<String> pages = stats.getExistingPages();
                System.out.println("Существующие страницы (HTTP 200):");
                if (pages.isEmpty()) {
                    System.out.println("  Нет страниц с кодом 200");
                } else {
                    for (String page : pages) {
                        System.out.println("  " + page);
                    }
                }
                List<String> pages1 = stats.getNonExistingPages();
                System.out.println("Страницы с кодом ответа 404:");
                if (pages1.isEmpty()) {
                    System.out.println("  Нет страниц с кодом 404");
                } else {
                    for (String page1 : pages1) {
                        System.out.println("  " + page1);
                    }
                }
                reader.close();
                Map<String, Double> osStatic = stats.getOperatingSystemStats();
                System.out.println("Статистика операционных систем:");

                for (Map.Entry<String, Double> entry : osStatic.entrySet()) {
                    System.out.printf("  %s: %.2f%%\n", entry.getKey(), entry.getValue() * 100);
                }
                Map<String, Double> browserStatic = stats.getBrowserStats();
                System.out.println("Статистика браузеров:");

                for (Map.Entry<String, Double> entry : browserStatic.entrySet()) {
                    System.out.printf("  %s: %.2f%%\n", entry.getKey(), entry.getValue() * 100);
                }
                System.out.println("Средняя посещаемость одним пользователем: " +
                        String.format("%.2f", stats.getAverageVisitsPerUser()));
                System.out.println("Среднее количество ошибочных запросов за час: " +
                        String.format("%.2f", stats.getAverageNumberOfErroneousRequestsPerHour()));
                System.out.println("Среднее количество посещений за час (не-боты): " +
                        String.format("%.2f", stats.getAverageVisitsPerHour()));
                System.out.println("Общее количество строк (запросов): " + totalLines);
                System.out.println("Итого:");
                System.out.println("Всего проанализировано User-Agent с потенциальными ботами: " + totalBotChecks);
                System.out.println("Запросы от Googlebot: " + googleBotCount);
                System.out.println("Запросы от YandexBot: " + yandexBotCount);

                if (totalBotChecks > 0) {
                    double googleShare = (double) googleBotCount / totalLines * 100;
                    double yandexShare = (double) yandexBotCount / totalLines * 100;
                    System.out.printf("Доля запросов от Googlebot ко всем запросам: %.2f%%\n", googleShare);
                    System.out.printf("Доля запросов от YandexBot ко всем запросам: %.2f%%\n", yandexShare);
                }
                System.out.println("Средний трафик за час: " + stats.getTrafficRate() + " байт/час");

            } catch (RuntimeException ex) {
                System.err.println("Ошибка: " + ex.getMessage());
                return;
            } catch (Exception ex) {
                System.err.println("Ошибка при чтении файла:");
                ex.printStackTrace();
            }
        }
    }
}






