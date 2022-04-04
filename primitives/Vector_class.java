package Primitives;

/**
 *A vector class will represent a vector that we will use throughout
 *the project for different uses. The vector consists of a point and a length
 * @author: Avia Avikasis
 */

public class Vector
{
    Point3D Zero;
    Point3D Head;
    int Size;

    /**
     * A constructor that accepts three coordinates and returns
     * a vector whose point is made up of the three coordinates
     */
    public Vector(Coordinate X, Coordinate Y, Coordinate Z )
    {

        if ((X.get() == 0) && (Y.get() == 0) && (Z.get() == 0))
            throw new IllegalArgumentException("A vector cannot have zero values");

     this.Head  = new Point3D(X,Y,Z);
    }

    /**
     *A constructor that accepts three double-type values and
     *  returns a vector whose point consists of the three coordinates
     */

    public Vector(double a,double b,double c)
    {
       // if ((a == 0) && (b == 0) && (c == 0))
           // throw new IllegalArgumentException("A point of vector cannot have zero values");
        this.Head = new Point3D(a,b,c);
    }

    /**
     *A constructor that accepts a Point3D point and returns a new vector with the given point
     */

    public Vector(Point3D p)
    {
        if ((p.X.get() == 0) && (p.Y.get() == 0) && (p.Z.get() == 0))
            throw new IllegalArgumentException("A vector cannot have zero values");
        this.Head =  p;
    }

    /**
     *A constructor that receives a vector and returns a new vector with the point of the given vector
     */
    public Vector(Vector V)
    {
    this.Head = V.get();
    }

    //get function of Head
    public Point3D get(){return Head;}

    /**
     *The add function will accept a given
     *  vector and return a new vector which is a vector of the application that implements it and the given vector
     */
    public Vector add(Vector v)
    {
        Coordinate x = new Coordinate(this.Head.X.get() + v.Head.X.get());
        Coordinate y = new Coordinate(this.Head.Y.get() + v.Head.Y.get());
        Coordinate z = new Coordinate(this.Head.Z.get() + v.Head.Z.get());
        return new Vector(x,y,z);
    }

    /**
     *The add function will accept a given vector and return a new
     *  vector which is a subtraction of the vector that applied it and the given vector
     */
    public Vector subtract(Vector v)
    {
        Coordinate x = new Coordinate(this.Head.X.get() - v.Head.X.get());
        Coordinate y = new Coordinate(this.Head.Y.get() - v.Head.Y.get());
        Coordinate z = new Coordinate(this.Head.Z.get() - v.Head.Z.get());
        return new Vector(x,y,z);
    }

    /**
     *The scale function receives a given number and returns
     *  a multiplier of a vector that implements it with the given number
     */
    public Vector scale(double Num)
    {
        return new Vector(Num*this.Head.X.get(),Num*this.Head.Y.get(),Num*this.Head.Z.get());
    }

    /**
     *The function receives a given vector and
     *  returns a scalar which is the result of a
     * multiplication between a given vector and a vector that implements the function
     */
    public double dotProduct(Vector v)
    {
        return (this.Head.X.get()*v.Head.X.get() + this.Head.Y.get()*v.Head.Y.get() + this.Head.Z.get()*v.Head.Z.get());
    }

    /**
     *The function receives a given vector and returns a vector which
     *  is the result of a multiplication between a given vector and a vector that implements the function
     */
    public Vector crossProduct(Vector v)
    {

        return new Vector((this.Head.Y.get()*v.Head.Z.get() - this.Head.Z.get()*v.Head.Y.get()) , (this.Head.Z.get()*v.Head.X.get() - this.Head.X.get()*v.Head.Z.get())  ,  (this.Head.X.get()*v.Head.Y.get() - this.Head.Y.get()*v.Head.X.get()));

    }

    /**
     *The function returns a square length of the vector that implements it
     */
    public double lengthSquared()
    {
        double lengthsquared =  this.Head.X.get()*this.Head.X.get() + this.Head.Y.get()*this.Head.Y.get() + this.Head.Z.get()*this.Head.Z.get();
        return  lengthsquared;
    }

    //The function will return a length of the vector that implements it
    public double length()
    {
        double length = Math.sqrt(this.lengthSquared());
        return length;
    }

    //The function will return the vector that implements it, after it is normalized
    public Vector normalize()
    {
        this.Head.X = new Coordinate(this.Head.X.get() / this.length());
        this.Head.Y = new Coordinate(this.Head.Y.get() / this.length());
        this.Head.Z = new Coordinate(this.Head.Z.get() / this.length());
        return this;
    }

    /**
     *The function returns a normalized new vector that will be in the same direction as the vector that implements it
     */
    public Vector normalized()
    {
        return new Vector((this.get().X.get() / this.length()),(this.get().Y.get() / this.length()),(this.get().Z.get() / this.length()));
    }

    /**
     * The function calculates a normal vector locator that implements it
     * @return: normal vector
     */
    public Vector FindNormalofVector()//new
    {
        double y = -2;
        double z = -2;
        double x = (-(y*this.Head.Y._coord + z*this.Head.Z._coord))/this.Head.X._coord;
        return new Vector(x,y,z);
    }

    //The function will return an equivalence test between two vectors
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Vector)) return false;
        return this.Head.equals(((Vector) obj).Head);
    }

}
