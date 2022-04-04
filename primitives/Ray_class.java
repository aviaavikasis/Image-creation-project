package Primitives;

/**
 * The Ray class represents a beam that exits from a point in space with a direction vector
 * @author : Avia Avikasis
 */
public class Ray
{
    Point3D P;
    Vector V;
    Coordinate Zero;
    Coordinate A;

////////////////////////////////////////////////

    //ctor:
    public Ray(Point3D p,Vector v)
    {
        P = p;
        V = v;
    }

    public Point3D getp()
    {
        return P;
    }

    public Vector getv()
    {
        return V;
    }
}
