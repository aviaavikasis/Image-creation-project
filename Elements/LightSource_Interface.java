package elements;
import Primitives.Color;
import Primitives.*;

/**
 * The Lightsource interface connects all the different types of light sources
 */
public interface LightSource {

    public Color getIntensity(Point3D p);
    public Vector getL(Point3D p);
    double getDistance(Point3D point);
}
