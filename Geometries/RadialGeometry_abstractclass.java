package geometries;

/**
 * The RadialGeometry class represents circular geometric bodies
 * @author : Avia Avikasis
 */

public abstract class RadialGeometry extends Geometry {

    double _radius;

////////////////////////////////////////////////////////////

    public RadialGeometry(double radius){
        _radius = radius;
    }

    public RadialGeometry(RadialGeometry r){
       _radius = r.get_radius();
    }

    public RadialGeometry() {

    }

    public double get_radius(){ return _radius;  }


}
