import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer>grades = new ArrayList<>();
        grades.add(5);
        grades.add(4);
        grades.add(5);
        grades.add(3);
        Student student = new Student("Максим", grades);
        System.out.println(student);
        student.addGrade(5);
        System.out.println(student);
        Student student2 = new Student("Таня", null);
        System.out.println(student2);
    }
}






