package elements;
import Primitives.Color;
import Primitives.Point3D;
import Primitives.Vector;


/**
 * The Directionallight class represents light coming from a certain
 * direction, illuminating only a certain direction of the scene.
 * This light has no particular location. It is similar to sunlight
 * @author : Avia Avikasis
 */
public  class DirectionalLight extends Light implements LightSource{

    Vector _direction;

/*********************************************************************/
    
    //ctor:
    public DirectionalLight(Color intensity,Vector v)
    {
        super(intensity);
        _direction = v.normalized();
    }

    //Returns the original light intensity of the "sun"
    public Color getIntensity(Point3D p)
    {
        return super.get_intensity();
    }

    //Returns the direction vector of the light, normalized
    public Vector getL(Point3D p)
    {
       return _direction.normalized();
    }

    public  double getDistance(Point3D point)
    {
        return Double.POSITIVE_INFINITY;
    }

}
