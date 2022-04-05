package renderer;
import Primitives.Color;
import geometries.*;
//import geometries.Intersectable;
import geometries.Intersectable.*;
import scene.*;
import Primitives.*;
import elements.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * The Render class will produce the final image given all parts
 * of the scene: camera, geometric shapes, light sources and colors
 *
 * @author : Avia Avikasis
 */
public class Render {

    ImageWriter imageWriter;
    Scene scene;
    private static final double DELTA = 0.1;
    private static final int MAX_CALC_COLOR_LEVEL = 5;
    private static final double MIN_CALC_COLOR_K = 0.01;

//__________________________________________________________
    private int _threads = 1;
    private final int SPARE_THREADS = 2;
    private boolean _print = false;


////////////////////////////////////////////////////////////////////////////


    //ctor:
public Render(ImageWriter imagewriter,Scene scene1)
{
    imageWriter = imagewriter;
    scene = scene1;
}



    /**
     *  The function colors the pixels of the image according to the colors needed.
     *  Where the camera beams do not cut the geometry of the function,
     *  the color pixels will be colored in the background. In contrast,
     *  where the function will find the camera's cut-points with the geometries,
     *  it will take the closest cut point to the camera, and the pixel of that
     *  point will be the color of the appropriate color
     *
     * @param nx: Number of pixels in width
     * @param ny: Number of pixels in height
     * @param dist: Distance from camera to simulated screen
     * @param width: Width of screen in units
     * @param height: height of screen in units
     * @param camera: the camera of scene
     * @param geometries: A list of the geometric shapes found in the scene
     */
    public void renderImage()
    {
        int nx = imageWriter.getNx();
        int ny = imageWriter.getNy();
        double dist = scene.getDistance();
        double width = imageWriter.getWidth();
        double height = imageWriter.getHeight();
        Camera camera = scene.getCamera();
        Geometries geometries = scene.getGeometries();
        java.awt.Color background  = scene.getBackground().getColor();
        GeoPoint ClosestPoint;
        ArrayList<GeoPoint> IntersectionPoint = new ArrayList<GeoPoint>();

        for (int i = 0 ; i < nx ; i++)  // Loop that passes the pixels in width
        {
           for (int j = 0 ; j < ny ; j++) // Loop that passes the pixels in height
           {
               Ray ray = camera.constructRayThroughPixel(nx,ny,j,i,dist,width,height); // The ray that exits the camera and passes through the current pixel
               IntersectionPoint = geometries.findIntersections(ray); // The list of the foundation's intersection points with all the geometries of the scene
               if (IntersectionPoint == null) // not intersection point with any geometry
                   imageWriter.writePixel(i,j,background); // color in background
               else
                   {
                   ClosestPoint = getClosestPoint(IntersectionPoint);
                   Ray ray1 = new Ray(scene.getCamera().getLocation(),ClosestPoint.point.subtract(scene.getCamera().getLocation()));
                   Color C = CalcColor(ClosestPoint,ray1);
                   java.awt.Color C1 = C.getColor();
                   imageWriter.writePixel(i, j, C1); // color in appropriate color
               }
           }

        }

    }


    /**
     * The function will accept as a geometric shape and point on it,
     * and return the appropriate color for the point
     *
     * @param G: The cut point we want to find color for
     * @return: The color that matches the pixel
     */
    public Color CalcColor(GeoPoint G,Ray inRay)
    {
        return CalcColor(G,inRay,0,1.0);
    }



    public Color CalcColor(GeoPoint G,Ray inRay,int Level,double k)
    {
        Color Ip;
        Ip = new Color(0.0,0.0,0.0);

        Color IE = G.geometry.get_emmission(); // emmision light(IE)
        Color Ambient = scene.getAmbientlight().get_intensity(); // Ambient light(Kim*Iam)
        Ip = Ip.add(IE,Ambient);
        double Si;

        for(LightSource L : scene.get_lights())
        {
            double CosAlpha = G.geometry.getNormal(G.point).dotProduct(L.getL(G.point)); // Scalar multiplication between the vector l and the normal vector of geometry = the cosine angled between them(|l*n|)
            Vector V = G.point.subtract(scene.getCamera().getLocation()).normalized(); // A vector that comes out of the camera to the point we need to paint
            Vector r = L.getL(G.point).subtract(G.geometry.getNormal(G.point).scale(CosAlpha).scale(2)).normalized(); // the specular vector "r" = 𝒍 − 𝟐 ∙ |𝒍 ∙ 𝒏 |∙ 𝒏
            double VdotN = V.dotProduct(G.geometry.getNormal(G.point));
            if(scene.getSoftShadow() == false || L instanceof DirectionalLight)
            Si = transparency(L.getL(G.point),G.geometry.getNormal(G.point),G,L);
            else
                Si = SoftShadow(G,(PointLight) L);
            if((CosAlpha < 0 && VdotN < 0)  || (CosAlpha > 0 && VdotN > 0) ){
                Color Diffiuse = L.getIntensity(G.point).scale(G.geometry.get_material().getkD() * Math.abs(CosAlpha)*Si); // The diffiuse component(kD*|l*n|*IL)
                Ip = Ip.add(Diffiuse);
                Color Specular = L.getIntensity(G.point).scale(G.geometry.get_material().getkS() * Math.pow(Math.max(0, V.scale(-1).dotProduct(r)), G.geometry.get_material().getnShininess())*Si); //the specular component
                Ip = Ip.add(Specular);


                //recursive of reflected ray:
                if (G.geometry.get_material().get_KR() != 0 && Level < MAX_CALC_COLOR_LEVEL && k > MIN_CALC_COLOR_K)
                {
                    Level+=1;
                    k*=G.geometry.get_material().get_KR();
                    Ray reflectedRay = constructReflectedRay(G.geometry.getNormal(G.point),G, inRay);
                    GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
                    Color reflectedLight = (reflectedPoint == null? scene.getBackground() : CalcColor(reflectedPoint, reflectedRay,Level,k));
                    Ip = Ip.add(reflectedLight.scale(G.geometry.get_material().get_KR()));
                }

                //recursive of refracted ray:
                if (G.geometry.get_material().get_KT() != 0 && Level < MAX_CALC_COLOR_LEVEL && k > MIN_CALC_COLOR_K)
                {
                    Level+=1;
                    k*=G.geometry.get_material().get_KT();
                    Ray refractedRay = constructRefractedRay(G,G.geometry.getNormal(G.point), inRay) ;
                    GeoPoint refractedPoint = findClosestIntersection(refractedRay);
                    Color refractedLight = (refractedPoint == null? scene.getBackground() : CalcColor(refractedPoint, refractedRay,Level,k));
                    Ip =  Ip.add(refractedLight.scale(G.geometry.get_material().get_KT()));
                }

            }
        }
        return Ip;
    }


    /**
     * The function calculates the reflection ray from a given point, according to the impact ray
     * @param n: the normal vector in the point
     * @param G: The geometric shape the dot sits on
     * @param inray:The fund hits a point
     * @return: reflected ray from point
     */
    public Ray constructReflectedRay(Vector n,GeoPoint G,Ray inray)
    {
        Point3D P;
        Vector V = inray.getv().normalize();
        if(V.dotProduct(n) > 0)
            P = G.point.add(n.scale(-DELTA));
        else P = G.point.add(n.scale(DELTA));
       return new Ray(P,V.subtract(n.scale(2*V.dotProduct(n))));
    }


    /**
     *The function returns a transparency ray that passes through
     *  a translucent body to the subsequent body
     * @param G:The geometric shape the dot sits on
     * @param n:the normal vector in the point
     * @param inray:The ray hits a point
     * @return: refracted ray from the point
     */
    public Ray constructRefractedRay(GeoPoint G,Vector n,Ray inray)
    {
        Point3D P;
        if(G.geometry instanceof  FlatGeometries)
            P = G.point;
        else
            P = G.point.add(n.scale(-DELTA));
        return new Ray(P,inray.getv().normalized());
    }


    /**
     * The function returns the nearest cut point of the ray, that given as a parameter
     * @param inray: reflected/refracted ray
     * @return: the nearest cut point
     */
    public GeoPoint findClosestIntersection(Ray inray)
    {
        ArrayList<GeoPoint> Intersections = new ArrayList<GeoPoint>();
        Intersections =  scene.getGeometries().findIntersections(inray);

   if (Intersections != null)
       Intersections.removeIf(g-> g.geometry instanceof FlatGeometries && g.point==inray.getp());

        if(Intersections != null)
        {
            GeoPoint ClosestPoint = null;
            Point3D P0 = inray.getp();
            double ClosestDistance = Intersections.get(0).point.distance(P0);
            for (GeoPoint G : Intersections)
            {
                if (G.point.distance(P0) < ClosestDistance)
                {
                    ClosestDistance = G.point.distance(P0);
                    ClosestPoint = G;
                }
            }
            return ClosestPoint;
        }
        else return null;
    }




    /**
     *The function receives as a parameter a list of cut points of a particular
     *  ray coming out of the camera, with a particular scene. We only want
     *  the closest point to the camera
     *
     * @param intersectionpoints: List of ray cut-out points (coming out of camera) with the scene's geometry
     * @return: The closest point to the camera
     */
    private GeoPoint getClosestPoint(ArrayList<GeoPoint> intersectionpoints)
    {
     GeoPoint ClosestPoint = null;
     Point3D P0 = scene.getCamera().getLocation();
     double ClosestDistance = intersectionpoints.get(0).point.distance(P0);
     for (GeoPoint G : intersectionpoints)
     {
         if (G.point.distance(P0) <= ClosestDistance) {
             ClosestDistance = G.point.distance(P0);
             ClosestPoint = G;
         }

     }
     return ClosestPoint;
    }


    /**
     *The function takes the size of the spacing we want between the
     *  grid lines, as well as the color of the grid lines, and prints a grid
     *
     * @param interval: The spacing of the grid lines
     * @param color: The color of the grid lines
     */
    public void printGrid(int interval, java.awt.Color color)
    {
        int nx = imageWriter.getNx();
        int ny = imageWriter.getNy();

        for (int i = 0 ; i < nx ; i++)
        {
            for (int j = 0 ; j < ny ; j++)
            {
                if (i%interval == 0 || j%interval == 0)
                {
                    imageWriter.writePixel(j,i,color);
                }
            }
        }

    }

//write to image:
    public void writeToImage() {

        imageWriter.writeToImage();

    }


    /**
     * The function calculates the shadow of the geometric shapes in the image.
     * Only two options: there is shadow or no shadow
     * @param l:the vector from light to point
     * @param n: the normal vector in the point
     * @param gp: The geometric shape on which the point rests
     * @param light:light source
     * @return: true if there is no shadow at a point, and false if there is a shadow at a point
     */
    private boolean unshaded(Vector l, Vector n, GeoPoint gp,LightSource light)
    {
        Ray Shadowray;
        Point3D Point;
        if (gp.geometry instanceof FlatGeometries) {
            Shadowray = new Ray(gp.point, l.scale(-1));
            Point = gp.point;
        }
        else
            {
        Point = gp.point.add(n.scale(DELTA));
        Shadowray = new Ray(Point,l.scale(-1));
        }
        ArrayList<GeoPoint> Intersections = new ArrayList<GeoPoint>();
        Intersections = scene.getGeometries().findIntersections(Shadowray);

      if(Intersections != null && gp.geometry instanceof FlatGeometries && Intersections.contains(gp))
          Intersections.remove(gp);

    if (Intersections == null)   // The foundation does not cut the geometry
        return true;
    else
        {
            for(GeoPoint G : Intersections)
            {
                if(Point.distance(G.point) < light.getDistance(Point) && gp.geometry.get_material().get_KT() == 0)
                        return false;
            }
            return true;
    }
    }


    /**
     * The function returns a number between 0 and 1, the closer the number to
     * zero the heavier the shadow, and the closer to 1 the weaker the shadow
     * @param l:the vector from light to point
     * @param n: the normal vector in the point
     * @param gp: The geometric shape on which the point rests
     * @param light:light source
     * @return: double num
     */
    private double transparency(Vector l,Vector n, GeoPoint gp,LightSource light)
    {
        Ray Shadowray;
        Point3D Point;
       // Point3D PointLight = gp.point.add(l.scale(-1));
        if (gp.geometry instanceof FlatGeometries) {
            Shadowray = new Ray(gp.point, l.scale(-1));
            Point = gp.point;
        } else {
            Point = gp.point.add(n.scale(DELTA));
            Shadowray = new Ray(Point, l.scale(-1));
        }
        ArrayList<GeoPoint> Intersections = new ArrayList<GeoPoint>();
        Intersections = scene.getGeometries().findIntersections(Shadowray);

        if (Intersections != null && gp.geometry instanceof FlatGeometries && Intersections.contains(gp))
            Intersections.remove(gp);

        if (light instanceof  PointLight && scene.getSoftShadow() == true && Intersections != null)
        Intersections.removeIf(g->g.geometry == ((PointLight)light).get_Sphere());

        if (Intersections == null)   // The foundation does not cut the geometry
            return 1.0;

        else {
            double ktr = 1.0;
            for (GeoPoint G : Intersections) {
                if (Point.distance(G.point) < light.getDistance(Point)) {
                    ktr *= G.geometry.get_material().get_KT();
                    if (ktr < MIN_CALC_COLOR_K) return 0.0;
                }
            }
            return ktr;
        }
    }


    /**
     * The function returns a number between 0 and 1, which is the average of a
     * shadow test of 64 rays from the same point (beam), the returned number is
     * the shadow coefficient in the calcolor function
     * @param G:The geometry on which the point we paint is
     * @param light: source light
     * @return:A number between 0 and 1 that will be the shadow coefficient
     */
    public double SoftShadow(GeoPoint G,PointLight light)  // new
    {
        int nx = 8;
        int ny = 8;
        double dist = light.getDistance(G.point) - light.get_Sphere().get_radius()-0.2;
        double width = light.get_Sphere().get_radius() * 1.8;
        double height = width;
        Vector Vto = light.get_pisition().subtract(G.point).normalized();
        Vector Vup = Vto.FindNormalofVector().normalized();
        Camera camera = new Camera(G.point, Vto, Vup);
        Ray ray;
        ArrayList<GeoPoint> Intersections = new ArrayList<GeoPoint>();
        GeoPoint Intersection;
        double sum = 0;
        for (int i = 0; i < nx; i++) {
            for (int j = 0; j < ny; j++) {
                ray = camera.constructRayThroughPixel(nx, ny, j, i, dist, width, height);
                Intersections = light.get_Sphere().findIntersections(ray);
                if (Intersections == null)
                    continue;
                else
                    Intersection = Intersections.get(0);
                Vector l = G.point.subtract(Intersection.point);
                sum += transparency(l, G.geometry.getNormal(G.point), G, light);
            }
        }
        return sum/64;
    }









//===============================================================================================================================
//===============================================================================================================================
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    /**
     * Renderer class is responsible for generating pixel color map from a graphic
     * scene, using ImageWriter class
     *
     * @author Dan
     */
    //public class Render {
        // ...........
        //private int _threads = 1;
        //private final int SPARE_THREADS = 2;
        //private boolean _print = false;

        /**
         * Pixel is an internal helper class whose objects are associated with a Render object that
         * they are generated in scope of. It is used for multithreading in the Renderer and for follow up
         * its progress.<br/>
         * There is a main follow up object and several secondary objects - one in each thread.
         * @author Dan
         *
         */
        private class Pixel {
            private long _maxRows = 0;
            private long _maxCols = 0;
            private long _pixels = 0;
            public volatile int row = 0;
            public volatile int col = -1;
            private long _counter = 0;
            private int _percents = 0;
            private long _nextCounter = 0;

            /**
             * The constructor for initializing the main follow up Pixel object
             * @param maxRows the amount of pixel rows
             * @param maxCols the amount of pixel columns
             */
            public Pixel(int maxRows, int maxCols) {
                _maxRows = maxRows;
                _maxCols = maxCols;
                _pixels = maxRows * maxCols;
                _nextCounter = _pixels / 100;
                if (Render.this._print) System.out.printf("\r %02d%%", _percents);
            }

            /**
             *  Default constructor for secondary Pixel objects
             */
            public Pixel() {}

            /**
             * Internal function for thread-safe manipulating of main follow up Pixel object - this function is
             * critical section for all the threads, and main Pixel object data is the shared data of this critical
             * section.<br/>
             * The function provides next pixel number each call.
             * @param target target secondary Pixel object to copy the row/column of the next pixel
             * @return the progress percentage for follow up: if it is 0 - nothing to print, if it is -1 - the task is
             * finished, any other value - the progress percentage (only when it changes)
             */
            private synchronized int nextP(Pixel target) {
                ++col;
                ++_counter;
                if (col < _maxCols) {
                    target.row = this.row;
                    target.col = this.col;
                    if (_counter == _nextCounter) {
                        ++_percents;
                        _nextCounter = _pixels * (_percents + 1) / 100;
                        return _percents;
                    }
                    return 0;
                }
                ++row;
                if (row < _maxRows) {
                    col = 0;
                    if (_counter == _nextCounter) {
                        ++_percents;
                        _nextCounter = _pixels * (_percents + 1) / 100;
                        return _percents;
                    }
                    return 0;
                }
                return -1;
            }

            /**
             * Public function for getting next pixel number into secondary Pixel object.
             * The function prints also progress percentage in the console window.
             * @param target target secondary Pixel object to copy the row/column of the next pixel
             * @return true if the work still in progress, -1 if it's done
             */
            public boolean nextPixel(Pixel target) {
                int percents = nextP(target);
                if (percents > 0)
                    if (Render.this._print) System.out.printf("\r %02d%%", percents);
                if (percents >= 0)
                    return true;
                if (Render.this._print) System.out.printf("\r %02d%%", 100);
                return false;
            }
        }

        /**
         * This function renders image's pixel color map from the scene included with
         * the Renderer object
         */
        public void renderImage1() {

            final int nx;
            final int ny;
            final double width;
            final double height;

                nx = imageWriter.getNx();
                ny = imageWriter.getNy();
                width = imageWriter.getWidth();
                height = imageWriter.getHeight();
            final double dist = scene.getDistance();
            final Camera camera = scene.getCamera();
            final Geometries geometries = scene.getGeometries();
            final java.awt.Color background  = scene.getBackground().getColor();

            final Pixel thePixel = new Pixel(ny, nx);

            // Generate threads
            Thread[] threads = new Thread[_threads];
            for (int i = _threads - 1; i >= 0; --i) {
                threads[i] = new Thread(() -> {
                    Pixel pixel = new Pixel();
                    while (thePixel.nextPixel(pixel)) {

                        GeoPoint ClosestPoint;
                        ArrayList<GeoPoint> IntersectionPoint = new ArrayList<GeoPoint>();
                        Ray ray = camera.constructRayThroughPixel(nx, ny, pixel.col, pixel.row, dist, width, height); // The ray that exits the camera and passes through the current pixel
                        IntersectionPoint = geometries.findIntersections(ray); // The list of the foundation's intersection points with all the geometries of the scene
                        if (IntersectionPoint == null) // not intersection point with any geometry
                            imageWriter.writePixel(pixel.col, pixel.row, background); // color in background
                        else {
                            ClosestPoint = getClosestPoint(IntersectionPoint);
                            Ray ray1 = new Ray(scene.getCamera().getLocation(), ClosestPoint.point.subtract(scene.getCamera().getLocation()));
                            Color C = CalcColor(ClosestPoint, ray1);
                            java.awt.Color C1 = C.getColor();
                            imageWriter.writePixel(pixel.col, pixel.row, CalcColor(ClosestPoint, ray).getColor());
                        }
                    }
                });
                }

            // Start threads
            for (Thread thread : threads) thread.start();

            // Wait for all threads to finish
            for (Thread thread : threads) try { thread.join(); } catch (Exception e) {}
            if (_print) System.out.printf("\r100%%\n");
        }






    public void SuperSampling(int I, int J, int nx,int ny,int Iteration) {
        double dist = scene.getDistance();
        Camera camera = scene.getCamera();
        Geometries geometries = scene.getGeometries();
        java.awt.Color background = scene.getBackground().getColor();
        double width = imageWriter.getWidth();
        double height = imageWriter.getHeight();
        int Nx = imageWriter.getNx();
        int Ny = imageWriter.getNy();

        ArrayList<Ray> RaysThroughPixel = new ArrayList<Ray>();
        RaysThroughPixel.add(camera.constructRayThroughPixel(Nx, Ny, J, I, dist, width, height));
        RaysThroughPixel.add(camera.constructRayThroughPixel(Nx, Ny, J, nx, dist, width, height));
        RaysThroughPixel.add(camera.constructRayThroughPixel(Nx, Ny, ny, I, dist, width, height));
        RaysThroughPixel.add(camera.constructRayThroughPixel(Nx, Ny, ny, nx, dist, width, height));


        if (Iteration == 1)  // We haven't split the screen yet
        {
            SuperSampling(I, J, nx / 2, ny, Iteration+1);
            SuperSampling(nx / 2, J, nx, ny, Iteration+1);
        }

        if (Iteration == 2) // Second run, we divided the screen only once, into two halves
        {
            SuperSampling(I, J, nx, ny / 2, Iteration+1);
            SuperSampling(I,ny/2,nx,ny,Iteration+1);
        }


        Boolean Test = false;  // A test variable, is there one or more rays that cut a geometrical shape in the scene
        for (Ray r : RaysThroughPixel) {
            if (geometries.findIntersections(r) != null)
            {
               Test = true;
               break;
           }
        }



            if (Test == true && Iteration > 2 && Iteration <6 && Iteration%2 != 0)
            {
                SuperSampling(I,J,nx / 2, ny, Iteration+1); // Dividing the current square laterally, and treating the left square
                SuperSampling(nx/2,J,nx,ny,Iteration+1);// Continue treatment in the right square
            }
                else if (Test == true && Iteration > 2 && Iteration < 6 && Iteration%2 == 0 ) {
                SuperSampling(I, J, nx, ny / 2, Iteration+1); //Divide the square by its length
                SuperSampling(I,ny/2,nx,ny, Iteration+1);
            }


            else if(Test == true && Iteration == 6)
            {
                GeoPoint ClosestPoint;
                ArrayList<GeoPoint> IntersectionPoint = new ArrayList<GeoPoint>();
                Ray ray;
                Ray ray1;

                for (int i =  I ; i < nx ; i++)  // Loop that passes the pixels in width
                {
                    for (int j = J ; j < ny ; j++) // Loop that passes the pixels in height
                    {
                        ray = camera.constructRayThroughPixel(Nx, Ny, j, i, dist, width, height); // The ray that exits the camera and passes through the current pixel
                        IntersectionPoint = geometries.findIntersections(ray); // The list of the foundation's intersection points with all the geometries of the scene
                        if (IntersectionPoint == null) // not intersection point with any geometry
                            imageWriter.writePixel(i, j, background); // color in background
                        else {
                            ClosestPoint = getClosestPoint(IntersectionPoint);
                            ray1 = new Ray(scene.getCamera().getLocation(), ClosestPoint.point.subtract(scene.getCamera().getLocation()));
                            Color C = CalcColor(ClosestPoint, ray1);
                            java.awt.Color C1 = C.getColor();
                            imageWriter.writePixel(i, j, C1); // color in appropriate color
                        }
                    }
                }
            }



            if (Iteration > 2 && Test == false)
            {
                for(int i=I;i<nx; i++)  // Loop that passes the pixels in width
                   {
                        for(int j=J;j<ny; j++) // Loop that passes the pixels in height
                             {
                                 imageWriter.writePixel(i,j,background); // color in background
                             }
                   }
            }

    }





        /**
         * Set multithreading <br>
         * - if the parameter is 0 - number of coress less 2 is taken
         *
         * @param threads number of threads
         * @return the Render object itself
         */
        public Render setMultithreading(int threads) {
            if (threads < 0)
                throw new IllegalArgumentException("Multithreading patameter must be 0 or higher");
            if (threads != 0)
                _threads = threads;
            else {
                int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
                if (cores <= 2)
                    _threads = 1;
                else
                    _threads = cores;
            }
            return this;
        }

        /**
         * Set debug printing on
         *
         * @return the Render object itself
         */
        public Render setDebugPrint() {
            _print = true;
            return this;
        }

    }

/** Example for setting multithreading and debug print in test files:
        ......
    ImageWriter imageWriter = new ImageWriter("teapot", 200, 200, 800, 800);
    Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

		render.renderImage();
    // render.printGrid(50, java.awt.Color.YELLOW);
		render.writeToImage();

*/























