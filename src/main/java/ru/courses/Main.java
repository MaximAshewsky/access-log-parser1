package ru.courses;
import ru.courses.geometry.*;
public class Main {
    public static void main(String[] args) {
        Point p1 = new Point(1, 3);
        Point p2 = new Point(9, 15);
        Line l1 = new Line(p1, p2);
        Line l2 = l1.clone();
        System.out.println(l1.equals(l2));
        System.out.println(l1 == l2);
    }
}





