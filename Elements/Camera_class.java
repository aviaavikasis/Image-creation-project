package elements;
import Primitives.*;
import Primitives.Util.*;

import static Primitives.Util.alignZero;

/**
 * @class Camera: Camera class represents a 3D camera.
 * The class includes three vectors that come out of the camera, and the location of the camera
 * @author : Avia Avikasis
 */
public class Camera {
    Point3D Location;
    Vector Vto;
    Vector Vup;
    Vector Vright;

    public Vector getVto()
    {
        return Vto;
    }

    public Vector getVup()
    {
        return Vup;
    }

    public Vector getVright()
    {
        return Vright;
    }

    /**
     *The constructor gets a location point, and two vectors, one vector faces
     *  straight and one vector faces up. The constructor makes sure that two
     *  obtained vectors are perpendicular to each other, and creates the third
     *  vector (Vright) by a vector multiplication between two vectors. In addition,
     *  the constructor normalizes the three vectors.
     *  If both data vectors are not vertical, the constructor will throw an error
     */
    public Camera(Point3D location,Vector vto,Vector vup)
    {
        //if (alignZero(vto.dotProduct(vup)) != 0)
        if (Vto != null && (Vto.dotProduct(Vup) > 0.0000001 || Vto.dotProduct(Vup) < -0.0000001) )
            throw new IllegalArgumentException("Two vectors entered in the camera ctor are not perpendicular");
        Location = location;
        Vto = vto.normalized();
        Vup = vup.normalized();
        Vright= Vto.crossProduct(Vup).normalized();
    }

    public Point3D getLocation()
    {
        return Location;
    }

    /**
     *
     * @param nX: The number of pixels in the viewplane width
     * @param nY: The number of pixels in the viewplane height
     * @param j: index column of intersection point
     * @param i: index row of intersection point
     * @param screenDistance: The distance between the camera position and the viewplane
     * @param screenWidth: width of viewplane
     * @param screenHeight: height of viewplan
     * @return: The ray that cuts the viewplane at a calculated point
     */
    public Ray constructRayThroughPixel (int nX, int nY, int j, int i, double screenDistance, double screenWidth, double screenHeight)
    {
        double D = screenDistance; // Distance between camera and viewplane
        Point3D Pc =  Location.add(Vto.scale(D));// The midpoint of the viewplane
        int I = i;// Index of the requested pixel row
        int J = j;// Index of the requested pixel column
        double  Width = screenWidth;
        double  Height = screenHeight;
        double Rx =  Width/nX;
        double Ry = Height/nY;
        double Num1 = (I-(double)nX/2)*Rx+Rx/2;
        double Num2 = (J-(double)nY/2)*Ry+Ry/2;
        Point3D P = null;  //// The point of intersection between the ray and the viewplane

        if (Num1 != 0 && Num2 !=0)
            P = Pc.add((Vright.scale(Num1)).subtract(Vup.scale(Num2)));
        else if (Num1 == 0 && Num2 != 0)
            P = Pc.add(Vup.scale(-Num2));
        else if (Num1 != 0  &&  Num2 == 0)
            P = Pc.add(Vright.scale(Num1));
        else if (Num1 == 0  &&  Num2 == 0)
            P = Pc;
        Ray RayThroughPixel = new Ray(Location,(P.subtract(Location)).normalized());
        return RayThroughPixel;
    }
}
