package geometries;
import Primitives.*;

//An abstract class that represents all circular bodies
 public abstract  class  Geometry implements Intersectable
 {
     protected Color _emmission;
     protected Material _material;

/////////////////////////////////////////////////////////////////

//ctor:

     public Geometry(Color emmission )
     {
         _emmission = emmission;
         _material = new Material(0,0,0);
     }

     public Geometry()
     {
         _emmission = Color.BLACK;
         _material = new Material(0,0,0);
     }

     public Geometry(Color C,Material M)
     {
         _emmission = C;
         _material = M;
     }

//////////////////////////////////////////////////////////////////

     public Material get_material() {
         return _material;
     }

     public abstract Vector getNormal(Point3D p);


     public Color get_emmission()
     {
         return _emmission;
     }

 }
