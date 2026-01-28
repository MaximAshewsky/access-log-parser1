package ru.courses;
import ru.courses.geometry.*;

public class Main {
    public static void main(String[] args) {
        Point p1 = new Point(1, 1);
        Point p2 = p1.clone();
        System.out.println(p1 == p2);
        System.out.println(p1.equals(p2));

    }
}





