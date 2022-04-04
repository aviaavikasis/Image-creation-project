package geometries;
import Primitives.*;

import java.util.ArrayList;
import static Primitives.Util.*;
/**
 * The Sphere class represents a sphere in space. The fields only have a point
 * @author: Avia Avikasis
 */
public class Sphere extends RadialGeometry {
    Point3D Point;

    //A constructor that gets a point and places it
    public Sphere(Point3D P,double radius)
    {
        super(radius);
        Point = P;
    }


    public Sphere(Point3D P,double radius,Material material)
    {
        this(P, radius);
        _material = material;
    }

    public Sphere(Point3D P,double radius,Color color ,Material material)
    {
        this(P, radius);
        _emmission = color;
        _material = material;
    }



    //Exercise the getNormal function
    public Vector getNormal(Point3D P) {
        Vector V = P.subtract(Point);
        return V.normalized();
    }


    /**
     * The function finds all the intersection points of the ray given as a parameter
     * with the sphere that implements the function
     * @param ray:The beam that cuts the sphere
     * @return:list of intersection points
     */
    public ArrayList<GeoPoint> findIntersections(Ray ray) {
        ArrayList<GeoPoint> Result = new ArrayList<GeoPoint>();
        Point3D P0 = ray.getp();
        Vector V = ray.getv();
        Vector V_normal = V.normalized();
        Point3D O = Point;
        Vector U = O.subtract(P0);
        double tm = V_normal.dotProduct(U);
        double D = Math.sqrt(Math.pow(U.length(), 2) - Math.pow(tm, 2));
        if (D -_radius > 0.00001)  // A case where the ray move outside the sphere
        return null;

        double th;
      if (D < _radius)
      th = Math.sqrt(Math.pow(_radius, 2) - Math.pow(D, 2));
      else th = 0;
        double t1 = tm + th;
        double t2 = tm - th;
        Point3D P1;
        Point3D P2;
        try{P1 = P0.add(V_normal.scale(t1));}
        catch(IllegalArgumentException e){P1 = P0;}
        try{P2 = P0.add(V_normal.scale(t2));}
        catch(IllegalArgumentException e){P2 = P0;}


    if (alignZero(D - _radius) == 0 && !isZero(tm)) { //A case where the ray tangent to the sphere
        Result.add(new GeoPoint(this,P1));
        return Result;
    } else if (alignZero(D - _radius) == 0 && isZero(tm)) { //A case where the ray starts on the sphere
        return null;
    } else if (D < _radius && U.length() > _radius && t1>0 && t2>0) //A case where the ray starts outside the sphere and crossing it
    {
        Result.add(new GeoPoint(this,P1));
        Result.add(new GeoPoint(this,P2));
        return Result;
    } else if (D < _radius && U.length() < _radius) //A case where the ray starts inside the sphere
    {
        if (t1>0)
        Result.add(new GeoPoint(this,P1));
        else if (t2>0)
            Result.add(new GeoPoint(this,P2));
        return Result;
    }

    return null;
    }
}




