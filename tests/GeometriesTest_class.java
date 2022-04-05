package Tests;
import Primitives.*;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;
import geometries.*;

public class GeometriesTest {
@Test

public void testFindIntersections() {
    // =============== Boundary Values Tests ==================

    //TC01: Empty geometry collection
    ArrayList<Intersectable> L = null;
    Geometries G = new Geometries(L);
    assertEquals("", null, G.findIntersections(new Ray(new Point3D(1, 1, 1), new Vector(0, 1, 0))));

    //TC02: The ray does not cut any of the geometries in the collection
    L = null;
}

}
