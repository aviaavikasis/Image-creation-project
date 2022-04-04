package geometries;
import Primitives.*;
import java.util.ArrayList;


/**
 * The Geometries class represents a list of different geometric shapes.
 *The class implements the Intersectable interface
 * @author : Avia Avikasis
 */
public class Geometries implements Intersectable
{

    ArrayList<Intersectable> GeometriesList;  // List of geometries
////////////////////////////////////////////////////////////////////////////////////

    //constructors:

    public Geometries()
    {
        GeometriesList = null;
    }

    public Geometries(Intersectable... geometries){}


    public Geometries(ArrayList<Intersectable> L)
    {
        GeometriesList.addAll(L);
    }



    /**
     * The function is scheduled by a class instance, and receive
     * as a ray parameter. The function returns all the cut-points of
     * the ray with the geometric shapes found in the list of the
     * instance that invited it
     * @param ray: the ray that cut the geometries
     * @return: list of cut-points
     */
    public ArrayList<GeoPoint> findIntersections(Ray ray)
   {
       ArrayList<GeoPoint> Help;
       Help = new ArrayList<GeoPoint>();
       ArrayList<GeoPoint> Result = new ArrayList<>();

       for (Intersectable G : GeometriesList)
       {
           Help = G.findIntersections(ray);
           if (Help != null) {
               for (GeoPoint g : Help) {
                   Result.add(g);
               }
           }
       }
       if (Result.isEmpty()) return null;
     else return Result;
   }



//A function that adds a geometric shape to the geometry list
    public void add(Intersectable geometrie)
    {
        if (GeometriesList == null)
            GeometriesList = new ArrayList<Intersectable>();
        GeometriesList.add(geometrie);
    }


    // get GeometriesList
public ArrayList<Intersectable> getGeometriesList()
{
    return GeometriesList;
}

}
