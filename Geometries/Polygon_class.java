package geometries;

import java.util.List;
import primitives.Point3D;
import primitives.Util;
import primitives.Vector;

public class Polygon implements Geometry {
    protected List<Point3D> _vertices;
    protected Plane _plane;

    public Polygon(Point3D... vertices) {
        if (vertices.length < 3) {
            throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
        } else {
            this._vertices = List.of(vertices);
            if (vertices.length != 3) {
                Vector n = this._plane.getNormal();
                Vector edge1 = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
                Vector edge2 = vertices[0].subtract(vertices[vertices.length - 1]);
                boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0.0D;

                for(int i = 1; i < vertices.length; ++i) {
                    if (!Util.isZero(vertices[i].subtract(vertices[0]).dotProduct(n))) {
                        throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
                    }

                    edge1 = edge2;
                    edge2 = vertices[i].subtract(vertices[i - 1]);
                    if (positive != edge1.crossProduct(edge2).dotProduct(n) > 0.0D) {
                        throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
                    }
                }

            }
        }
    }

    public Vector getNormal(Point3D point) {
        return this._plane.getNormal();
    }
}
