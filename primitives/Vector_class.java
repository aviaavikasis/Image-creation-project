
package primitives;

public class Vector {
    Point3D Zero;
    Point3D Head;
    int Size;

    public Vector(Coordinate X, Coordinate Y, Coordinate Z) {
        if (X.get() == 0.0D && Y.get() == 0.0D && Z.get() == 0.0D) {
            throw new IllegalArgumentException("A vector cannot have zero values");
        } else {
            this.Head = new Point3D(X, Y, Z);
        }
    }

    public Vector(double a, double b, double c) {
        if (a == 0.0D && b == 0.0D && c == 0.0D) {
            throw new IllegalArgumentException("A point of vector cannot have zero values");
        } else {
            this.Head = new Point3D(a, b, c);
        }
    }

    public Vector(Point3D p) {
        if (p.X.get() == 0.0D && p.Y.get() == 0.0D && p.Z.get() == 0.0D) {
            throw new IllegalArgumentException("A vector cannot have zero values");
        } else {
            this.Head = p;
        }
    }

    public Vector(Vector V) {
        this.Head = V.get();
    }

    public Point3D get() {
        return this.Head;
    }

    public Vector add(Vector v) {
        Coordinate x = new Coordinate(this.Head.X.get() + v.Head.X.get());
        Coordinate y = new Coordinate(this.Head.Y.get() + v.Head.Y.get());
        Coordinate z = new Coordinate(this.Head.Z.get() + v.Head.Z.get());
        return new Vector(x, y, z);
    }

    public Vector subtract(Vector v) {
        Coordinate x = new Coordinate(this.Head.X.get() - v.Head.X.get());
        Coordinate y = new Coordinate(this.Head.Y.get() - v.Head.Y.get());
        Coordinate z = new Coordinate(this.Head.Z.get() - v.Head.Z.get());
        return new Vector(x, y, z);
    }

    public Vector scale(int Num) {
        return new Vector((double)Num * this.Head.X.get(), (double)Num * this.Head.Y.get(), (double)Num * this.Head.Z.get());
    }

    public double dotProduct(Vector v) {
        return this.Head.X.get() * v.Head.X.get() + this.Head.Y.get() * v.Head.Y.get() + this.Head.Z.get() * v.Head.Z.get();
    }

    public Vector crossProduct(Vector v) {
        return new Vector(this.Head.Y.get() * v.Head.Z.get() - this.Head.Z.get() * v.Head.Y.get(), this.Head.Z.get() * v.Head.X.get() - this.Head.X.get() * v.Head.Z.get(), this.Head.X.get() * v.Head.Y.get() - this.Head.Y.get() * v.Head.X.get());
    }

    public double lengthSquared() {
        return this.Head.X.get() * this.Head.X.get() + this.Head.Y.get() * this.Head.Y.get() + this.Head.Z.get() * this.Head.Z.get();
    }

    public double length() {
        return Math.sqrt(this.lengthSquared());
    }

    public Vector normalize() {
        this.Head.X = new Coordinate(this.Head.X.get() / this.length());
        this.Head.Y = new Coordinate(this.Head.Y.get() / this.length());
        this.Head.Z = new Coordinate(this.Head.Z.get() / this.length());
        return this;
    }

    public Vector normalized() {
        return new Vector(this.get().X.get() / this.length(), this.get().Y.get() / this.length(), this.get().Z.get() / this.length());
    }
}
