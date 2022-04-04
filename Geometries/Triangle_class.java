package geometries;
import Primitives.*;
import java.util.ArrayList;

/**
 * The Triangle class is inherited from the Polygon class and
 * it represents a triangle. The triangle consists of three Point3D points
 * @author: Avia Avikasis
 */
public class Triangle extends Polygon implements FlatGeometries
{
    Point3D P1;
    Point3D P2;
    Point3D P3;

/////////////////////////////////////////////////////////////////////////////

    //A constructor that accepts 3 Point3D points and places the given points in the fields
    public  Triangle(Point3D p1,Point3D p2,Point3D p3)
    {
        super(p1,p2,p3);
        P1 = p1;
        P2 = p2;
        P3 = p3;
    }

    //A constructor that accepts 3 vectors and builds a triangle from the three points of vectors
    public Triangle(Vector V1,Vector V2,Vector V3)
    {
        super(V1.get(),V2.get(),V3.get());
        P1 = V1.get();
        P2 = V2.get();
        P3 = V3.get();
    }


    public Triangle(Point3D p1,Point3D p2,Point3D p3,Material material)
    {
        this(p1, p2, p3);
        _material = material;
    }


    public Triangle(Vector V1,Vector V2,Vector V3,Material material)
    {
        this(V1, V2, V3);
        _material = material;
    }


    public Triangle(Point3D p1,Point3D p2,Point3D p3,Color color)
    {
        this(p1, p2, p3);
        _emmission = color;
    }


    public Triangle(Point3D p1,Point3D p2,Point3D p3,Color color,Material material)
    {
        this(p1, p2, p3);
        _emmission = color;
        _material = material;
    }




    //get normal of triangle
    public Vector getNormal(Point3D p){return _plane.Vnormal.normalized();}


    /**
     *The function receives a ray as a parameter,
     *  and checks at which points the beam cuts the triangle
     *
     * @param ray : The ray for which we are looking for cutting points
     * @return : A list of geopoint, all containing the cut points of the ray with the triangle
     */
    public ArrayList<GeoPoint> findIntersections(Ray ray)
    {
        ArrayList<GeoPoint> G = this._plane.findIntersections(ray);
        if (G == null)
            return null;
        Point3D P = G.get(0).point;
        Vector V_normal = ray.getv().normalized();
        Triangle T1 = new Triangle(ray.getp(),P1,P2);
        Triangle T2 = new Triangle(ray.getp(),P2,P3);
        Triangle T3 = new Triangle(ray.getp(),P3,P1);

        double Projection1 = ray.getv().dotProduct(T1.getNormal(new Point3D(0,0,0)));
        double Projection2 = ray.getv().dotProduct(T2.getNormal(new Point3D(0,0,0)));
        double Projection3 = ray.getv().dotProduct(T3.getNormal(new Point3D(0,0,0)));

        if ((Projection1 > 0 && Projection2 > 0 && Projection3 > 0) || (Projection1 < 0 && Projection2 < 0 && Projection3 < 0))
        {
            ArrayList<GeoPoint> Result = new ArrayList<GeoPoint>();//Array list of intersection pionts
            Result.add(new GeoPoint(this,P));
            return Result;
        }
        else
            return null;
    }
}

