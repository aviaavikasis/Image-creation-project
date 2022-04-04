package Primitives;

/**
 * The Material class represents geometric shape material.
 * @author : Avia Avikasis
 */
public class Material {

    double kD;
    double kS;
    int nShininess;
    double _KR;  // feflected
    double _KT;  // refracted


////////////////////////////////////////////////////////////////////////////


    //ctor:

    public Material(double kd, double ks, int nshininess)
    {
        this(kd,ks,nshininess,0,0);
    }

    public Material(double kd,double ks,int nshininess, double kt, double kr)
    {
        kD = kd;
        kS = ks;
        nShininess = nshininess;
        _KR = kr;
        _KT = kt;
    }

//==========Getters and Setters===============

    public double getkD(){return  kD;}

    public double getkS(){return  kS;}

    public int getnShininess(){return  nShininess;}

    public double get_KT() { return _KT; }

    public double get_KR() { return _KR; }
}
