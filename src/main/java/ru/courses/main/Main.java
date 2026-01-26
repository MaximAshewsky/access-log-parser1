package ru.courses.main;
import ru.courses.geometry.*;

public class Main {
    public static double m(Measurable m) {
        return m.getLength();
    }
    public static void main(String[] args) {
        Point p1 = new Point(1, 5);
        Point p2 = new Point(12, 8);
        Point p3 = new Point(6, 3);
        Point p4 = new Point(8, 9);
        PolyLine pl = new PolyLine(new Point[]{p1, p2, p3, p4});
        ClosedPolyLine cpl = new ClosedPolyLine(new Point[]{p1, p2, p3, p4});
        System.out.println(pl);// создали ломаную
        double line = pl.getLength();
        double line1 = cpl.getLength();
        System.out.println("Длинна ломоной: " + line);// длина ломаной
        System.out.println("Длинна замкнутой: " + line1);
        System.out.println("Длинна ломоной интерфейс: " + m(pl));
        System.out.println("Длинна замкнутой интерфейс: " + m(cpl));
        Line[] segments = pl.getLines();
        System.out.println("Массив линий:");// массив линий
        for (int i = 0; i < segments.length; i++) {
            Line segment = segments[i];
            System.out.println("  Линия " + i + ": " + segments[i]);
        }
            double segmentsTotalLength = 0;
            for (int i = 0; i < segments.length; i++) {
                segmentsTotalLength += segments[i].getLength();
            }
                System.out.println("Суммарная длинна линий:" + segmentsTotalLength);// суммарная длина массива линий

            //сравнение
        if (Math.abs(line - segmentsTotalLength) < 1e-9) {
            System.out.println("Длины совпадают");
        } else {
            System.out.println("Длины НЕ совпадают!");
        }

        }
    }






