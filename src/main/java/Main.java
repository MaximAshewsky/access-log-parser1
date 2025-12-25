public class Main {
    public static void main(String[] args) {
        Point p1 = new Point(1, 3);
        Point p2 = new Point(1, 3);//В задании просят вывести оба сравнения false, хотя у нас три точки и сравнения должно быть три. Если прописать  Point p2 = p1, то в первом сравнении выведится true
        Point p3 = new Point(5, 8);
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println(p1 == p2);
        System.out.println(p1 == p3);
        System.out.println(p2 == p3);
    }
}





