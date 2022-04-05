package Tests;
import geometries.Intersectable.*;
import geometries.Sphere;
import org.junit.Test;
import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import static org.junit.Assert.*;

import java.util.ArrayList;



public class SphereTest
{

    @Test
    public void getNormal()
    {
        Sphere S = new Sphere(new Point3D(1,2,3),2.0);
        Point3D P = new Point3D(2,4,6);
        Vector V = P.subtract(new Point3D(1,2,3));
        Vector V1 = V.normalized();
        assertEquals("Unsuccessful",V1,S.getNormal(P));
    }


    @Test
    public void testFindIntersections()
    {
        Sphere sphere = new Sphere(new Point3D(1, 0, 0),2.0);
        ArrayList<GeoPoint> result = new ArrayList<GeoPoint>();
        ArrayList<GeoPoint> Help_findintersection = new ArrayList<GeoPoint>();

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertEquals("Ray's line out of sphere", null, sphere.findIntersections(new Ray(new Point3D(-3, 0, 0), new Vector(0, 1, 0))));

        // TC02: Ray starts before and crosses the sphere (2 points)

        // TC03: Ray starts after the sphere (0 points)
        //result = sphere.findIntersections(new Ray(new Point3D(4, 2, 0), new Vector(0, 3, 0)));
        assertEquals("kmkm",null,sphere.findIntersections(new Ray(new Point3D(4, 2, 0), new Vector(0, 3, 0))));

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        //TC11: ray croses the sphere in two points
        result.add(new GeoPoint(sphere,new Point3D(2.732,1,0)));
        result.add(new GeoPoint(sphere,new Point3D(-0.732,1,0)));
        assertEquals("",2,sphere.findIntersections(new Ray(new Point3D(4, 1, 0), new Vector(-5, 0, 0))).size());
        // TC12: Ray starts at sphere and goes inside (1 points)
        result.clear();
        Point3D P2 = new Point3D(2,-Math.sqrt(3),0);
        result.add(new GeoPoint(sphere,P2));
        assertEquals("",result,sphere.findIntersections(new Ray(new Point3D(2, Math.sqrt(3), 0), new Vector(0, -3-Math.sqrt(3), 0))));

        // TC13: Ray starts at sphere and goes outside (1 points)
        result.clear();
        result.add(new GeoPoint(sphere,new Point3D(1+Math.sqrt(3),1,0)));
        assertEquals("",result,sphere.findIntersections(new Ray(new Point3D(2,1,0), new Vector(2, 0, 0))));


        // **** Group: Ray's line goes through the center
        // TC14: Ray starts before the sphere (2 points)
        result.clear();
        result.add(new GeoPoint(sphere,new Point3D(1,-2,0)));
        result.add(new GeoPoint(sphere,new Point3D(1,2,0)));
        assertEquals("",result,sphere.findIntersections(new Ray(new Point3D(1, 3, 0), new Vector(0, -6, 0))));

        // TC15: Ray starts at sphere and goes inside (1 points)
        Point3D P4 = new Point3D(1,-2,0);
        result.clear();
        result.add(new GeoPoint(sphere,P4));
        assertEquals("",result,sphere.findIntersections(new Ray(new Point3D(1,1, 0), new Vector(0, -6, 0))));

        // TC16: Ray starts inside (1 points)
        result.clear();
        result.add(new GeoPoint(sphere,new Point3D(-1,0,0)));
        assertEquals("",result,sphere.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(-4, 0, 0))));

        // TC17: Ray starts at the center (1 points)
        /**
        result.clear();
        result.add(new Point3D(-1/5,-8/5,0));
        assertEquals("",result,sphere.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(-3, -4, 0))));
        */
        // TC18: Ray starts at sphere and goes outside (1 points)
        result.clear();
        result.add(new GeoPoint(sphere,new Point3D(3,0,0)));
        assertEquals("",result,sphere.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(4, 0, 0))));

        // TC18: Ray starts after sphere (0 points)
        assertEquals("",null,sphere.findIntersections(new Ray(new Point3D(2, 2, 0), new Vector(1, 1, 0))));

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)

        // TC19: Ray starts before the tangent point(1 point)
        result.clear();
        result.add(new GeoPoint(sphere,new Point3D(1,2,0)));
        assertEquals("",result,sphere.findIntersections(new Ray(new Point3D(3, 2, 0), new Vector(-3, 0, 0))));

        // TC20: Ray starts at the tangent point
        assertEquals("",null,sphere.findIntersections(new Ray(new Point3D(1, -2, 0), new Vector(2, 0, 0))));

        // TC21: Ray starts after the tangent point(0 points)
        assertEquals("",null,sphere.findIntersections(new Ray(new Point3D(2.5,-1.5, 0), new Vector(1, 0, 0))));

    }


}
