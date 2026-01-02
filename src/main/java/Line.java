public class Line {
    Point start;
    Point end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "Линия от " + start +
                ", до " + end
                ;
    }

    public double getLength() {
        double ax = end.x - start.x;
        double ay = end.y - start.y;
        return Math.sqrt(ax * ax + ay * ay);
    }

}
