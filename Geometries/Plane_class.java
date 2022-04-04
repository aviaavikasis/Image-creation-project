package geometries;
import Primitives.*;
import java.util.ArrayList;
import static Primitives.Util.*;
/**
 * The Plane class represents a geometric body in space.
 * From this department all departments that represent geometric bodies in space will be inherited
 * @author: Avia Avikasis
 */
public class Plane extends Geometry implements FlatGeometries
{

    Point3D P;
    Vector Vnormal;

    //A constructor that accepts a Point3d vector point, and places the values in the fields of the new instance
    public Plane(Point3D Point,Vector NormalVector)
    {
        P = Point;
        Vnormal = NormalVector.normalized();
    }

    /**
     *A constructor that accepts three points Point3d,
     *  calculates the normal vector itself and places
     *  the midpoint and the vector calculated in the relevant fields
     */
    public Plane(Point3D p1,Point3D p2, Point3D p3)
    {
        P = p1;
        Vector V1 = p2.subtract(p1);
        Vector V2 = p3.subtract(p1);
        Vnormal = V1.crossProduct(V2).normalized();
    }


    public Plane(Point3D Point,Vector NormalVector,Material material)
    {
        this(Point,NormalVector);
        _material = material;
    }


    public Plane(Point3D p1,Point3D p2, Point3D p3,Material material)
    {
        this(p1, p2, p3);
        _material = material;
    }


// Exercise the getNormal function. Returns the normal vector
    public Vector getNormal(Point3D p)
    {
        return Vnormal.normalized();
    }

    public ArrayList<GeoPoint> findIntersections(Ray ray)
    {
        Vector V_normal = ray.getv().normalized();//the vector of ray after Normalize
        double t = Vnormal.dotProduct(P.subtract(ray.getp()))/Vnormal.dotProduct(V_normal);//t
        if (t<0)
            return null;
        ArrayList<GeoPoint> Result = new ArrayList<GeoPoint>();//Array list of intersection pionts
        Point3D Point;
        if (t == 0 )
            Point = ray.getp();
        else
        Point = ray.getp().add(V_normal.scale(t));//P = P0 + t*v, Some point on the ray
        Vector Vtest = Point.subtract(P).normalized();//vector from P to Q0
        if (isZero(Vtest.dotProduct(Vnormal)) && Point != ray.getp())//the vector Vtest normal of Vnormal
        { Result.add(new GeoPoint(this,Point));// the point Point is intersect point of plane with ray
        return Result;
        }
        else
        return null;
    }

}
