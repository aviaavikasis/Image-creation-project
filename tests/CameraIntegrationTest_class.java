package Tests;
import Primitives.*;
import geometries.*;
import elements.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *The CameraIntegrationTest class tests the program's ability to find
 *  the intersection points of the rays from a camera, with
 *  geometric shapes in space. The class checks the operation of the functions:
 *  constructRayThroughPixel (camera), findintersection that belongs to all geometries
 *
 * @author: Avia Avikasis
 */

public class CameraIntegrationTest {
    @Test
    public void Camera_With_Sphere(){
        Point3D[][] Screen = new Point3D [3][3]; //array of viewplane
///////////////////////////////////////////////////////////////////////////////////////////////////////////


        //TC01: All the rays do not hit the ball, except for the Vto vector(2 points)
        Sphere sphere = new Sphere(new Point3D(4,-1,0),1.0);
        Camera camera = new Camera(new Point3D(0 ,-1 ,-0.5),new Vector(3,0,0.5),new Vector(-1,0,6));
       int sum = 0; // Counts the number of intersection points for all geometries

        for(int i = 0; i<3;i++) // loop of index i
        {
           for (int j = 0; j<3 ; j++ ) // loop of index j
           {
               Ray ray;
               ray = camera.constructRayThroughPixel (3,3, j,i,2.0, 3, 3);
               if (sphere.findIntersections(ray) != null)
               sum += sphere.findIntersections(ray).size();
           }
        }

        // Checking whether we received the number of coated cut points (for all geometries and all the rays coming out of the camera together)
        assertEquals("",2,sum);



        //TC02: The viewplane is inside the sphere, so all the rays coming out of the camera cut the sphere in two points(18 points)
        Screen = null;
        sum = 0;
        sphere = new Sphere(new Point3D(0,0,2.5),2.5);
        camera = new Camera(new Point3D(0 ,0 ,-0.5),new Vector(0,0,0.5),new Vector(1,1,0));

        for(int i = 0; i<3;i++) // loop of index i
        {
            for (int j = 0; j<3 ; j++ ) // loop of index j
            {
                Ray ray;
                ray = camera.constructRayThroughPixel (3,3, j,i,2.0, 3, 3); //
                if (sphere.findIntersections(ray) != null)
                    sum += sphere.findIntersections(ray).size();
            }
        }
        assertEquals("",18,sum);



        // TC03: Some of the viewplane is inside the sphere and some of it is not inside the sphere.
        // Some of the rays coming out of the camera cross the ball and some do not(10 points)

        sum = 0;
        sphere = new Sphere(new Point3D(0,0,2),2.0);
        camera = new Camera(new Point3D(0 ,0 ,-0.5),new Vector(0,0,2.5),new Vector(1,1,0));

        for(int i = 0; i<3;i++) // loop of index i
        {
            for (int j = 0; j<3 ; j++ ) // loop of index j
            {
                Ray ray;
                ray = camera.constructRayThroughPixel (3,3, j,i,1.0, 3, 3); //
                if (sphere.findIntersections(ray) != null)
                    sum += sphere.findIntersections(ray).size();
            }
        }
        assertEquals("",10,sum);



        // TC04: The entire viewplane is inside the sphere, so all the rays cut the sphere from the inside only once.

        sum = 0;
        sphere = new Sphere(new Point3D(4,0,0),4.0);
        camera = new Camera(new Point3D(6 ,0 ,0),new Vector(-2,0,0),new Vector(0,1,1));

        for(int i = 0; i<3;i++) // loop of index i
        {
            for (int j = 0; j<3 ; j++ ) // loop of index j
            {
                Ray ray;
                ray = camera.constructRayThroughPixel(3,3, j,i,1.0, 3, 3); //
                if (sphere.findIntersections(ray) != null)
                    sum += sphere.findIntersections(ray).size();
            }
        }
        assertEquals("",9,sum);




        // TC05: The camera is after the sphere, so no ray cut the ball

        sum = 0;
        sphere = new Sphere(new Point3D(0,0,-1),0.5);
        camera = new Camera(new Point3D(0 ,0 ,0),new Vector(0,0,3),new Vector(1,1,0));

        for(int i = 0; i<3;i++) // loop of index i
        {
            for (int j = 0; j<3 ; j++ ) // loop of index j
            {
                Ray ray;
                ray = camera.constructRayThroughPixel(3,3, j,i,1.0, 3, 3);
                if (sphere.findIntersections(ray) != null)
                    sum += sphere.findIntersections(ray).size();
            }
        }
        assertEquals("",0,sum);

    }


    @Test
    public void Camera_With_Plane()
    {
        Plane plane = new Plane(new Point3D(3,2,3),new Vector(4,1,1)); // point of plane: A(3,2,3),B(3,1,4),C(4,-1,2)
        Camera camera = new Camera(new Point3D(-1,2,1),new Vector(4,1,1),new Vector(0,-1,1));
        int sum = 0;

        // TC10: The viewplane is between the camera and the plane, so all the camera rays cut the plane(9 points).

        for(int i = 0; i<3;i++) // loop of index i
        {
            for (int j = 0; j<3 ; j++ ) // loop of index j
            {
                Ray ray;
                ray = camera.constructRayThroughPixel(3,3, j,i,1.0, 3, 3);
                if (plane.findIntersections(ray) != null)
                    sum += plane.findIntersections(ray).size();
            }
        }
        assertEquals("",9,sum);



        // TC11: The plane is placed so that some of the rays coming out of the
        // camera cut the plane, and some of them do not cut(6 points)
        plane = new Plane(new Point3D(-13.524,2.561,3),new Vector(-1.278,25.68,24.582)); // point of plane: A(-13.524,2.561,3),B(2.476,2.4,4),C(-3.844,4,2)
        camera = new Camera(new Point3D(-1,2,1),new Vector(4,1,1),new Vector(0,-1,1));
        sum = 0;

        for(int i = 0; i<3;i++) // loop of index i
        {
            for (int j = 0; j<3 ; j++ ) // loop of index j
            {
                Ray ray;
                ray = camera.constructRayThroughPixel(3,3, j,i,1.0, 3, 3);
                if (plane.findIntersections(ray) != null)
                    sum += plane.findIntersections(ray).size();
            }
        }
        assertEquals("",6,sum);
    }




    @Test
    public void Camera_With_Triangle()
    {
        Triangle triangle = new Triangle(new Point3D(0,-1,2),new Point3D(1,1,2),new Point3D(-1,1,2));
        Camera camera = new Camera(new Point3D(0,0,-2),new Vector(0,0,5),new Vector(2,2,0));
        int sum = 0;
//////////////////////////////////////////////////////////////////////////////////////////////////////////

// TC21: Only the middle ray cuts the triangle, and all the other rays pass outside the triangle(1 point)

        for(int i = 0; i<3;i++) // loop of index i
        {
            for (int j = 0; j<3 ; j++ ) // loop of index j
            {
                Ray ray;
                ray = camera.constructRayThroughPixel(3,3, j,i,1.0, 3, 3);
                if (triangle.findIntersections(ray) != null)
                    sum += triangle.findIntersections(ray).size();
            }
        }
        assertEquals("",1,sum);




// TC22:Two rays cut the triangle in the middle, all the other rays outside(2 points)

        triangle = new Triangle(new Point3D(0,-20,2),new Point3D(1,1,2),new Point3D(-1,1,2));
        camera = new Camera(new Point3D(0,-2,-3),new Vector(0,0,9),new Vector(2,2,0));
        sum = 0;

        for(int i = 0; i<3;i++) // loop of index i
        {
            for (int j = 0; j<3 ; j++ ) // loop of index j
            {
                Ray ray;
                ray = camera.constructRayThroughPixel(3,3, j,i,1.0, 3, 3);
                if (triangle.findIntersections(ray) != null)
                    sum += triangle.findIntersections(ray).size();
            }
        }
        assertEquals("",2,sum);

    }

}
