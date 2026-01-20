public class Line {
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

    public double getLength() {
        double ax = end.getX() - start.getX();
        double ay = end.getY() - start.getY();
        return Math.sqrt(ax * ax + ay * ay);
    }

}
