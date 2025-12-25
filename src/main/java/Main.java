public class Main {
    public static void main(String[] args) {
        Point p1 = new Point(1, 3);
        Point p2 = new Point(9, 15);
        Point p3 = new Point(22, 33);
        Point p4 = new Point(15, 19);
        Line l1 = new Line (p1, p2);
        Line l2 = new Line (p3, p4);
        Line l3 = new Line (p2, p3);
        System.out.println(l3);
        double line = l1.getLength() + l2.getLength() + l3.getLength();
        System.out.println("Суммарная длинна всех трех линий: " + line);
    }
}





