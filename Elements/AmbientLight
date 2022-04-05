package elements;
import Primitives.Color;

/**
 * The Ambientlight class represents the scene's ambient light,
 * which is equal to every point in the scene
 * @author : Avia Avikasis
 */

public class AmbientLight extends Light {

Color Ia; // Light intensity by components
double Ka; // Coefficient of attenuation of light

///////////////////////////////////////////////////////////////////////

//ctor:
public AmbientLight(Color _ia,double _ka)
{
    super(_ia.scale(_ka));
    Ia = _ia;
    Ka = _ka;
}


public Color getIa(){return Ia;}

//The function returns the original light intensity, with no changes
public Color get_intensity(){return super.get_intensity();}
}
