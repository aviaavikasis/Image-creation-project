package geometries;
import Primitives.*;

import java.util.ArrayList;

/**
 *The cylinder class will represent a point and height cylinder (length)
 * The class inherits from the RadialGeometry interface
 * @author : Avia Avikasis
 */
public  class Cylinder extends RadialGeometry
{
    Point3D Point;
    double High;



    // A constructor that receives a Point3D point and height, and returns a cylinder with the data it receives

    public Cylinder(Point3D p, double H)
    {
        Point = p;
        High = H;
    }

    //Executes the getNormal function that returns the normal vector to the body
    public Vector getNormal(Point3D p)
    {
        Vector V = p.subtract(Point);
        return V.normalized();
    }

    public ArrayList<GeoPoint> findIntersections(Ray ray){return null;}

}
