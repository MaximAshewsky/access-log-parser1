package ru.courses.geometry;

import java.util.List;

public class ClosedPolyLine extends PolyLine {
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