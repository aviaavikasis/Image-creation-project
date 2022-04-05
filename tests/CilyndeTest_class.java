package Tests;

import geometries.*;
import org.junit.Test;
import Primitives.*;

import static org.junit.Assert.*;

public class CylinderTest {


    @Test
    public void getNormal()
    {
        Cylinder C = new Cylinder(new Point3D(1,2,3),3.5);
        Point3D P = new Point3D(2,4,6);
        Vector V = P.subtract(new Point3D(1,2,3));
        Vector V1 = V.normalized();
        assertEquals("Unsuccessful",V1,C.getNormal(P));
    }
}
