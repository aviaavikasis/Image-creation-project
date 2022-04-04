package geometries;

import primitives.Point3D;
import primitives.Vector;

public abstract class Plane implements Geometry {
    Point3D P;
    Vector Vnormal;

    public Vector getNormal() {
        return new Vector(1.0D, 2.0D, 3.0D);
    }

    public Plane(Point3D Point, Vector NormalVector) {
        this.P = Point;
        this.Vnormal = NormalVector;
    }
}
