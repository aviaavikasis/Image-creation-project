package elements;
import Primitives.Color;
import Primitives.Point3D;
import Primitives.Vector;
import geometries.Sphere;

/**
 * The Spotlight class represents light coming from a specific location,
 * and shines in a certain direction, and the beam of light is limited in
 * its width. This light is like a flashlight. The department inherits from the Pointlight department
 * @author : Avia Avikasis
 */
public class SpotLight extends PointLight {

    Vector _direction;

/*************************************************************************/
    
    //ctor:
    public SpotLight(Color intensity,Point3D position, double kC, double kL, double kQ, Vector v)
    {
        super(intensity ,position, kC,kL,kQ);
        _direction = v.normalized();
    }


    public SpotLight(Color intensity, Point3D position, double kC, double kL, double kQ, Vector v, Sphere sp)//new
    {
        this(intensity,position,kC,kL,kQ,v);
        _Sphere = sp;
    }


    //The function returns the intensity of light coming from
    // the flashlight to a specific point set as a parameter
    public Color getIntensity(Point3D P)
    {
        Vector l = getL(P);
        double d = _position.distance(P);
        Color IL = _intensity.scale(Math.max(0,_direction.dotProduct(l))).reduce(_kC + _kL*d + _kQ*Math.pow(d,2));
        return IL;
    }

    //The function returns the vector from the light source to a given point as a parameter
    public Vector getL(Point3D p)
    {
        return p.subtract(_position).normalized();
    }

    public double getDistance(Point3D Point)
    {
        return super.getDistance(Point);
    }

    public Sphere get_Sphere(){return super.get_Sphere();}


}

