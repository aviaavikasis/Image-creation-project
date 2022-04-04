package Primitives;

/**
 * point3d is a class that represents a point in a space that has 3 scalar coordinates: x, y, z
 * @author Avia Avikasis
 * @param 'X' represents the point on the x pivot
 * @param 'Y' represents the point on the y pivot
 * @param 'Z' represents the point on the z pivot
 */

public final class Point3D
{
    Coordinate X;
    Coordinate Y;
    Coordinate Z;

    //A constructor that receives 3 coordinates and places them in the fields
    public Point3D(Coordinate x , Coordinate y , Coordinate z)
    {
        X = x;
        Y = y;
        Z = z;
    }

    //A constructor that receives 3 numbers, creates coordinates of the numbers, and places them in the fields
    public Point3D(double x,double y, double z){
        X = new Coordinate(x);
        Y = new Coordinate(y);
        Z = new Coordinate(z);
    }

    public static Point3D ZERO = new Point3D(0,0,0);

    public Point3D get()
    {
        return this;
    }
    public Coordinate getX(){return X;}

    //A function to compare two different points
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Point3D)) return false;
        Point3D oth = (Point3D)obj;
        return X.equals(oth.X) && Y.equals(oth.Y) && Z.equals(oth.Z);
    }

    /**
     * The subtract function is called by a Point3D point and receives as another point parameter.
     * It returns a vector from the given point as a parameter, to a point that implements the function
     */
    public Vector subtract(Point3D p)
    {
        return new Vector(this.X.get() - p.X.get(),this.Y.get() - p.Y.get(),this.Z.get() - p.Z.get());
    }

    /**
     * The add function accepts a vector as a parameter.
     * It adds the vector to the point that implements it, and returns a new point
     */
    public Point3D add(Vector v)
    {
        return new Point3D(new Coordinate(this.X.get()+ v.get().X.get()),new Coordinate(this.Y.get()+ v.get().Y.get()),new Coordinate(this.Z.get()+ v.get().Z.get()));
    }

    // The function returns the square distance between two points
    public double distanceSquared(Point3D p)
    {
        return ((this.X.get() - p.X.get())*(this.X.get() - p.X.get())+ (this.Y.get() - p.Y.get())*(this.Y.get() - p.Y.get()) + (this.Z.get() - p.Z.get())*(this.Z.get() - p.Z.get()));
    }

    // The function returns the distance between two points
    public double distance(Point3D p)
    {
    return Math.sqrt(((this.X.get() - p.X.get())*(this.X.get() - p.X.get())+ (this.Y.get() - p.Y.get())*(this.Y.get() - p.Y.get()) + (this.Z.get() - p.Z.get())*(this.Z.get() - p.Z.get())));
    }
}


