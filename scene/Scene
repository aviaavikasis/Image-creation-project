package scene;
import elements.*;
import geometries.*;
import Primitives.Color;

import java.util.LinkedList;


/**
 * The Scene class represents a complete image, which
 * includes geometric shapes, background color,
 * different color shapes, and camera.
 * @author: Avia Avikasis
 */
public class Scene {
    String _name;
    Color _background;
    AmbientLight _ambientLight;
    Geometries _geometries;
    Camera _camera;
    double _distance;
    LinkedList<LightSource> _lights = new LinkedList<LightSource>();
    Boolean SoftShadow; //new
    Boolean SuperSampling;

//////////////////////////////////////////////////////


    //ctor
    public Scene(String scene_name)
    {
        _name = scene_name;
    }


// ============================== Getters and Setters ================================


    public String getName()
        {
            return _name;
        }

    public Color getBackground()
    {
        return _background;
    }

    public AmbientLight getAmbientlight()
    {
        return _ambientLight;
    }

    public Geometries getGeometries()
    {
        return _geometries;
    }

    public Camera getCamera()
    {
        return _camera;
    }

    public double getDistance()
    {
        return _distance;
    }

    public LinkedList<LightSource> get_lights(){return _lights;}

    public Boolean getSoftShadow(){return SoftShadow;}

    public Boolean getSuperSampling(){return SuperSampling;}

    public void setBackground(Color background)
    {
       _background = background;
    }

    public void setAmbientLight(AmbientLight ambientLight)
    {
        _ambientLight = ambientLight;
    }

    public void setCamera(Camera camera)
    {
        _camera = camera;
    }

    public void setDistance(double distance)
    {
        _distance = distance;
    }

    public void setSoftShadow(Boolean b){SoftShadow = b;}

    public void setSuperSampling(Boolean b){SuperSampling = b;}


    //================================================================================

    // A function that adds geometric shapes to the scene

   public void addGeometries(Intersectable... geometries)
    {
        if (_geometries == null)
            _geometries =  new Geometries();
        for (Intersectable G : geometries)
        {
            _geometries.add(G);
        }
    }




    public void addLights(LightSource... lights)
    {
        for (LightSource L : lights)
        {
           _lights.add(L);
        }
    }

}
