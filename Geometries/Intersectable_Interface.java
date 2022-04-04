package geometries;
import Primitives.*;

import java.util.ArrayList;
public interface Intersectable
{
    ArrayList<GeoPoint> findIntersections(Ray ray);

    public static class GeoPoint
    {
        public Geometry geometry;
        public Point3D point;

        public GeoPoint(Geometry _geometry, Point3D _point)
        {
            geometry = _geometry;
            point = _point;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (!(obj instanceof GeoPoint)) return false;
            GeoPoint oth = (GeoPoint) obj;
            return geometry.equals(oth.geometry) && point.equals(oth.point);
        }
    }
}
