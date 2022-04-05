package Tests;
import static org.junit.Assert.*;
import org.junit.Test;
import Primitives.*;
import geometries.*;
import java.util.ArrayList;

public class TriangleTest
{
    @Test
        public void ConstructorsTests()
        {
            Triangle T1 = new Triangle(new Point3D(1,2,3),new Point3D(4,5,6),new Point3D(-1,0,-4));
            Triangle T2 = new Triangle(new Point3D(1,2,3),new Point3D(4,5,6),new Point3D(-1,0,-4));
        }
@Test
        public void getNormalTest()
        {

        }

        @Test
        public void testFindIntersections()
        {
            ArrayList<Point3D> result = new ArrayList<Point3D>();

            Triangle triangle = new Triangle(new Point3D(1,2,3),new Point3D(0,3,1),new Point3D(2,0,1));
            // ============ Equivalence Partitions Tests ==============

            //TC01: ray intersect Inside triangle
            assertEquals("",1,triangle.findIntersections(new Ray(new Point3D(-1, 1, 0), new Vector(2.034, 0.598, 1.596))).size());

            //TC02: ray move outside triangle
            result.clear();
            assertEquals("",null,triangle.findIntersections(new Ray(new Point3D(0, 1, 0), new Vector(0, -1, 3.428))));



            // =============== Boundary Values Tests ==================

            //TC11: ray move on edge of triangle
            assertEquals("",null,triangle.findIntersections(new Ray(new Point3D(-2, 3, 0), new Vector(3.641, -2.283, 1.717)))); // the ray paralel and included in triangle:(2,2,2),(1,1,0),(3,-1,2) (one of edge of "pyramid")


          Triangle triangle1 =  new Triangle(new Point3D(-150, 150, 150), new Point3D(-70, -70, 50), new Point3D(75, -75, 150));
          assertEquals("",1,triangle1.findIntersections(new Ray(new Point3D(0,0,-1000),new Vector(-49.44,-50.83,1078.83))).size());


        }
}
