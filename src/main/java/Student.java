import java.util.ArrayList;
import java.util.List;

public class Student {
    private final String name;
    private final List<Integer> grades;

    public Student(String name, List<Integer> grades) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Имя студента не может быть пустым");
        this.name = name.trim();
        this.grades = new ArrayList<>();
        if (grades != null) {
            for (int i = 0; i < grades.size(); i++) {
                Integer grade = grades.get(i);
                addGrade(grade);
            }
        }
    }

    public void addGrade(Integer grade) {
        if (grade == null || grade < 2 || grade > 5)
            throw new IllegalArgumentException("Оценка должна быть от 2 до 5");
        grades.add(grade);
    }


    public String getName() {
        return name;
    }

    public List<Integer> getGrades() {
        return new ArrayList<>(grades);
    }

    @Override
    public String toString() {
        return name + ":" +  grades;
    }
}
