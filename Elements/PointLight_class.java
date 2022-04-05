package elements;
import Primitives.Color;
import Primitives.Point3D;
import Primitives.Vector;
import geometries.Sphere;


/**
 * The pointlight class represents light that emits from a certain point
 * in space, and emits energy rays in all directions. The light is similar to a pear bulb
 * @author : Avia Avikasis
 */
public class PointLight extends Light implements LightSource {

    protected Point3D _position;
    protected double _kC,_kL,_kQ;
    protected Sphere _Sphere; //new
///////////////////////////////////////////////////////////////////////////////////


    //ctor:
    public PointLight (Color intensity, Point3D position, double kC, double kL, double kQ)
    {
        super(intensity);
        _position = position; //location
        // Light attenuation coefficients
        _kC = kC;
        _kL = kL;
        _kQ = kQ;
    }

    public PointLight(Color intensity, Point3D position, double kC, double kL, double kQ,Sphere sp)//new
    {
        this(intensity,position,kC,kL,kQ);
        _Sphere = sp;
    }

////////////////////////////////////////////////////////////////////////////

    /**The function returns the light intensity that comes from the light
     * source to a certain point that is accepted as a parameter.
     * @param p: the certain point
    */
    public Color getIntensity(Point3D p)
    {
        double d = _position.distance(p);

        Color IL = _intensity.reduce(_kC + _kL*d + _kQ*Math.pow(d,2));

        return IL;
    }


    /**
     * The function returns the vector from the point of light
     * to the point obtained as a parameter. The vector will be normalized
     */
    public Vector getL(Point3D p)
    {
        return p.subtract(_position).normalized();
    }

    public double getDistance(Point3D point) {
        return point.distance(_position);
    }

    public Sphere get_Sphere() {return _Sphere;}//new

    public Point3D get_pisition(){return _position;}
}
