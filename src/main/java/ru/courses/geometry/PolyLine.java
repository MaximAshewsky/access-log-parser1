package ru.courses.geometry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PolyLine polyLine = (PolyLine) o;
        return Objects.equals(points, polyLine.points);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(points);
    }
}

