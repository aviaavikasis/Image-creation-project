package Tests;

import org.junit.Test;
import Primitives.Vector;
import Primitives.Coordinate;
import Primitives.Point3D;
import static org.junit.Assert.*;

/**
 * The following class is a class of tests for functions of the Point3D class
 * @author Avia Avikasis
 */
public class Point3DTest
{

@Test
    public void ConstructorsTest()
    {
        /**
         * Test 1:
         * Test for the Constructors of the Point3D class
         */
        // ============ Equivalence Partitions Tests ==============
        Point3D P = new Point3D(new Coordinate(1),new Coordinate(2), new Coordinate(3));
        assertEquals("Unsuccefull", new Point3D(1,2,3),P);
    }

    @Test
    public void testEquals()
    {
        /**
         * Test 2:
         * A test for the validity of the comparison function
         */
        // ============ Equivalence Partitions Tests ==============
Point3D P1 = new Point3D(1,2,3);
Point3D P2 = new Point3D(1,2,3);
        assertTrue("Unsuccefull", P1.equals(P2));

        // =============== Boundary Values Tests ==================
        Point3D P3 = new Point3D(1.27,2.34,3.21);
        Point3D P4 = new Point3D(1.26,2.34,3.21);
        assertFalse("Unsuccefull", P3.equals(P4));
    }

    @Test
    public void subtract()
    {
        /**
         * Test 3:
         * Test for subtraction between two points in space. Subtraction returns a vector from the second point to the first point
         */
        // ============ Equivalence Partitions Tests ==============
        Point3D P1 = new Point3D(1,2,3);
        Point3D P2 = new Point3D(3,4,5);
        Vector P1_sub_P2 = new Vector(-2,-2,-2);
        assertEquals("Unsuccefull", P1_sub_P2, P1.subtract(P2));
        // =============== Boundary Values Tests ==================
        Point3D P3 = new Point3D(1.34,2.69,0.27);
        Point3D P4 = new Point3D(3.25,4.89,5.54);
        Vector P3_sub_P4 = new Vector(-1.91,-2.2,-5.27);
        assertEquals("Unsuccefull", P3_sub_P4, P3.subtract(P4));
    }

    @Test
    public void add()
    {
        /**
         * Test 4:
         * A test for a function that connects a vector to a point. The function should return a new point
         */
        // ============ Equivalence Partitions Tests ==============
        Point3D P = new Point3D(-1,-2,-3);
        Vector V = new Vector(3.5,4.5,5.5);
        Point3D P_plus_V = new Point3D(2.5,2.5,2.5);
        assertEquals("Unsuccefull", P_plus_V,P.add(V));
    }

    @Test
    public void distanceSquared()
    {
        /**
         * Test 5:
         * A test for finding a square distance between two points in space. The function should return a number
         */
        // ============ Equivalence Partitions Tests ==============
        Point3D P1 = new Point3D(1,2,0);
        Point3D P2 = new Point3D(3,4,5);
        double distanceSquared = 33.0;
        assertEquals("Unsuccefull",distanceSquared,P1.distanceSquared(P2),0);
        // =============== Boundary Values Tests ==================
        Point3D P3 = new Point3D(0,1,0.5);
        Point3D P4 = new Point3D(3.5,4.5,5.5);
        double distanceSquared_ = 49.5;
        assertEquals("Unsuccefull",distanceSquared_,P3.distanceSquared(P4),0);
    }

    @Test
    public void distance()
    {
        /**
         * Test 6:
         * Test for distance between two points in space
         */
        // ============ Equivalence Partitions Tests ==============
        Point3D P1 = new Point3D(1,2,3);
        Point3D P2 = new Point3D(4,5,6);
        double distance = Math.sqrt(27);
        assertEquals("Unsuccefull",distance,P1.distance(P2),0 );
    }
}
