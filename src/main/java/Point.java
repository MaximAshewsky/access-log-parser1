import java.util.Objects;

class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
    class Point3D extends Point{
        private final int z;

        public Point3D(int x, int y, int z) {
            super(x, y);
            this.z = z;
        }

        public int getZ() {
            return z;
        }

        @Override
        public String toString() {
            return "Point3D{" +
                    "x=" + getX() +
                    ", y=" + getY() +
                    ", z=" + z +
                    '}';
        }
    }


