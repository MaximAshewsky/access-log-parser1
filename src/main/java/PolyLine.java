import java.util.ArrayList;
import java.util.List;

public class PolyLine {
    private final List<Point> points;


    public PolyLine() {
        this.points = new ArrayList<>();
    }

    public PolyLine(Point[] points) {
        this.points = new ArrayList<>();
        if (points != null) {
            for (Point p : points) {
                this.points.add(p);
            }
        }
    }

    public List<Point> getPoints() {
        return new ArrayList<>(points);
    }

    @Override
    public String toString() {
        return "Ломаная: " +
                "Координаты точек ломаной " + points;
    }

    public Line[] getLines() {
        if (points.size() < 2) {
            return new Line[0];
        }

        Line[] lines = new Line[points.size() - 1];
        for (int i = 0; i < points.size() - 1; i++) {
            lines[i] = new Line(points.get(i), points.get(i + 1));
        }
        return lines;
    }

    public double getLength() {
        double totalLength = 0.0;
        Line[] lines = getLines();
        for (Line line : lines) {
            totalLength += line.getLength();
        }
        return totalLength;
    }
}

class ClosedPolyLine extends PolyLine {
    public ClosedPolyLine(Point[] points) {
        super(points);
    }

    public Line[] getLines() {
        List<Point> allPoints = super.getPoints();
        if (allPoints.size() < 2) return new Line[0];

        Line[] lines = new Line[allPoints.size()];
        for (int i = 0; i < allPoints.size() - 1; i++) {
            lines[i] = new Line(allPoints.get(i), allPoints.get(i + 1));
        }
        lines[allPoints.size() - 1] = new Line(
                allPoints.get(allPoints.size() - 1),
                allPoints.get(0)
        );
        return lines;
    }

    @Override
    public double getLength() {
        double sum = 0.0;
        for (Line line : getLines()) sum += line.getLength();
        return sum;
    }
}



