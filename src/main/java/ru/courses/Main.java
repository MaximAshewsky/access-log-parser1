package ru.courses;
import ru.courses.geometry.*;

public class Main {
    public static void main(String[] args) {
        Sauce s1 = new Sauce("Шрирача", Sauce.Sharpness.VERY_SHARP);
        Sauce s2 = new Sauce("Горчица", Sauce.Sharpness.SHARP);
        Sauce s3 = new Sauce("Кетчуп", Sauce.Sharpness.NOT_SHARP);
//        Sauce s4 = new Sauce("Лалала", null);
//        Sauce s5 = new Sauce("", Sauce.Sharpness.VERY_SHARP);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }
}
