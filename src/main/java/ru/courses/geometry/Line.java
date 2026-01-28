package ru.courses.geometry;

import java.util.Objects;

public class Line implements Cloneable {
    private final Point start;
    private final Point end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "Линия от " + start +
                ", до " + end
                ;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return Objects.equals(start, line.start) && Objects.equals(end, line.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    public Line clone() {
        try {
            Line cloned = (Line) super.clone();
            Point Start = this.start.clone();
            Point End = this.end.clone();
            return new Line(Start, End);
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Клонирование точки не поддерживается", e);
        }
    }
}