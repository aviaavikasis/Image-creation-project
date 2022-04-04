package primitives;

public final class Point3D {
    Coordinate X;
    Coordinate Y;
    Coordinate Z;
    public static Point3D ZERO = new Point3D(0.0D, 0.0D, 0.0D);

    public Point3D(Coordinate x, Coordinate y, Coordinate z) {
        this.X = x;
        this.Y = y;
        this.Z = z;
    }

    public Point3D(double x, double y, double z) {
        this.X = new Coordinate(x);
        this.Y = new Coordinate(y);
        this.Z = new Coordinate(z);
    }

    public Point3D get() {
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (!(obj instanceof Point3D)) {
            return false;
        } else {
            Point3D oth = (Point3D)obj;
            return this.X.equals(oth.X) && this.Y.equals(oth.Y) && this.Z.equals(oth.Z);
        }
    }

    public Vector subtract(Point3D p) {
        return new Vector(this.X.get() - p.X.get(), this.Y.get() - p.Y.get(), this.Z.get() - p.Z.get());
    }

    public Point3D add(Vector v) {
        return new Point3D(new Coordinate(this.X.get() + v.get().X.get()), new Coordinate(this.Y.get() + v.get().Y.get()), new Coordinate(this.Z.get() + v.get().Z.get()));
    }

    public double distanceSquared(Point3D p) {
        return (this.X.get() - p.X.get()) * (this.X.get() - p.X.get()) + (this.Y.get() - p.Y.get()) * (this.Y.get() - p.Y.get() + (this.Z.get() - p.Z.get()) * (this.Z.get() - p.Z.get()));
    }

    public double distance(Point3D p) {
        return Math.sqrt((this.X.get() - p.X.get()) * (this.X.get() - p.X.get()) + (this.Y.get() - p.Y.get()) * (this.Y.get() - p.Y.get()) + (this.Z.get() - p.Z.get()) * (this.Z.get() - p.Z.get()));
    }
}
