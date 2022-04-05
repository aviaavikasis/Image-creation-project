package Tests;
import Primitives.*;
import geometries.*;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class PlaneTest {

    @Test
    public void getNormal()
    {
        /**
         * Test 1:
         * A validity test for the getNormal function that needs to return a normal vector to the plane
         */
        Point3D p1 = new Point3D(1,2,3);
        Point3D p2 = new Point3D(4,5,6);
        Point3D p3 = new Point3D(-1,0,-5);
        Plane Pl1 = new Plane(p1,p2,p3);
        assertEquals("unsuccessful",new Vector(-18,18,0),Pl1.getNormal(p1));
    }

    @Test
    public void testFindIntersections()
    {
        Plane plane = new Plane(new Point3D(1, 2, 3),new Vector(-6,-4,1)); //the points of plane: point a:(1,2,3) , point b(0,3,1) , point c:(2,0,1)
        ArrayList<Point3D> result = new ArrayList<Point3D>();
        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray intersects the plane
        assertEquals("",1,plane.findIntersections(new Ray(new Point3D(1, -2, 0), new Vector(1, 0, 0))).size());


        // TC02: Ray does not intersect the plane

        assertEquals("",null,plane.findIntersections(new Ray(new Point3D(-4, -4, -2), new Vector(-1, -1, 1))));


        // =============== Boundary Values Tests ==================
        //****Group: Ray is parallel to the plane, Two cases:

        //TC11: ray included in the plane
        result.clear();
        assertEquals("",null,plane.findIntersections(new Ray(new Point3D(2, 0, 1), new Vector(-1, 2, 2))));

        //TC12: ray is not included in the ray
        result.clear();
        assertEquals("",null,plane.findIntersections(new Ray(new Point3D(1, -2, 0), new Vector(0.315, -0.646, -0.695))));

        // TC13: P0 before the plane and intersect
        result.clear();
        result.add(new Point3D(0.5,2,0)); //the intersection point
        assertEquals("",result,plane.findIntersections(new Ray(new Point3D(-2, 2, 0), new Vector(1, 0, 0))));

        // TC14: P0 in the plane and ray intersect(0 point)
        result.clear();
        assertEquals("",null,plane.findIntersections(new Ray(new Point3D(2, 0, 1), new Vector(1, -1, 2))));

    }

}

