package Tests;

import org.junit.Test;

import elements.*;
import geometries.*;
import Primitives.*;
import renderer.*;
import scene.Scene;

public class SofaTest {
    @Test
    public void SofaTest1()
    {
        Scene scene = new Scene("Test scene");

        scene.setCamera(new Camera(new Point3D(0, 80, 100),new Vector(-1,-79,-100),new Vector(-1,-1,0.8)));
        scene.setDistance(5000);
        scene.setBackground(new Color(java.awt.Color.BLACK));
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.addGeometries(
                //square 1:
                new Triangle(new Point3D(-5,3,0),new Point3D(-3,3,0),new Point3D(-3,1,0),new Color(java.awt.Color.ORANGE), new Material(0.5, 0.5, 30,0,0.5)),
                new Triangle(new Point3D(-5,3,0),new Point3D(-5,1,0),new Point3D(-3,1,0),new Color(java.awt.Color.ORANGE), new Material(0.5, 0.5, 30,0,0.5)),
                new Sphere(new Point3D(-4,2,0.75),0.75,new Color(100,450,350),new Material(0.5, 0.8, 30,0,0)),



                //square 2:
                new Triangle(new Point3D(-5,1,0),new Point3D(-3,1,0),new Point3D(-3,-1,0),new Color(java.awt.Color.GRAY), new Material(0.5, 0.5, 30,0,0.5)),
                new Triangle(new Point3D(-5,1,0),new Point3D(-5,-1,0),new Point3D(-3,-1,0),new Color(java.awt.Color.GRAY), new Material(0.5, 0.5, 30,0,0.5)),
                new Sphere(new Point3D(-4,0,0.75),0.75,new Color(100,450,350),new Material(0.5, 0.8, 30,0,0)),

                //square 3:
                new Triangle(new Point3D(-5,-1,0),new Point3D(-3,-1,0),new Point3D(-3,-3,0),new Color(java.awt.Color.ORANGE), new Material(0.5, 0.5, 30,0,0.5)),
                new Triangle(new Point3D(-5,-1,0),new Point3D(-5,-3,0),new Point3D(-3,-3,0),new Color(java.awt.Color.ORANGE), new Material(0.5, 0.5, 30,0,0.5)),
                new Sphere(new Point3D(-4,-2,0.75),0.75,new Color(100,450,350),new Material(0.5, 0.8, 30,0,0)),

                //square 4:
                new Triangle(new Point3D(-3,3,0),new Point3D(-1,3,0),new Point3D(-1,1,0),new Color(java.awt.Color.GRAY), new Material(0.5, 0.5, 30,0,0.5)),
                new Triangle(new Point3D(-3,3,0),new Point3D(-3,1,0),new Point3D(-1,1,0),new Color(java.awt.Color.GRAY), new Material(0.5, 0.5, 30,0,0.5)),
                new Sphere(new Point3D(-2,2,0.75),0.75,new Color(100,450,350),new Material(0.5, 0.8, 30,0,0)),

                //square 5:
                new Triangle(new Point3D(-3,1,0),new Point3D(-1,1,0),new Point3D(-1,-1,0),new Color(java.awt.Color.ORANGE), new Material(0.5, 0.5, 30,0,0.5)),
                new Triangle(new Point3D(-3,1,0),new Point3D(-3,-1,0),new Point3D(-1,-1,0),new Color(java.awt.Color.ORANGE), new Material(0.5, 0.5, 30,0,0.5)),
                new Sphere(new Point3D(-2,0,0.75),0.75,new Color(100,450,350),new Material(0.5, 0.8, 30,0,0)),

                //square 6:
                new Triangle(new Point3D(-3,-1,0),new Point3D(-1,-1,0),new Point3D(-1,-3,0),new Color(java.awt.Color.GRAY), new Material(0.5, 0.5, 30,0,0.5)),
                new Triangle(new Point3D(-3,-1,0),new Point3D(-3,-3,0),new Point3D(-1,-3,0),new Color(java.awt.Color.GRAY), new Material(0.5, 0.5, 30,0,0.5)),
                new Sphere(new Point3D(-2,-2,0.75),0.75,new Color(100,450,350),new Material(0.5, 0.8, 30,0,0)),

                //square 7:
                new Triangle(new Point3D(-1,3,0),new Point3D(1,3,0),new Point3D(1,1,0),new Color(java.awt.Color.ORANGE), new Material(0.5, 0.5, 30,0,0.5)),
                new Triangle(new Point3D(-1,3,0),new Point3D(-1,1,0),new Point3D(1,1,0),new Color(java.awt.Color.ORANGE), new Material(0.5, 0.5, 30,0,0.5)),
                new Sphere(new Point3D(0,2,0.75),0.75,new Color(100,450,350),new Material(0.5, 0.8, 30,0,0)),

                //square 8:
                new Triangle(new Point3D(-1,1,0),new Point3D(1,1,0),new Point3D(1,-1,0),new Color(java.awt.Color.GRAY), new Material(0.5, 0.5, 30,0,0.5)),
                new Triangle(new Point3D(-1,1,0),new Point3D(-1,-1,0),new Point3D(1,-1,0),new Color(java.awt.Color.GRAY), new Material(0.5, 0.5, 30,0,0.5)),
                new Sphere(new Point3D(0,0,0.75),0.75,new Color(100,450,350),new Material(0.5, 0.8, 30,0,0)),

                //square 9:
                new Triangle(new Point3D(-1,-1,0),new Point3D(1,-1,0),new Point3D(1,-3,0),new Color(java.awt.Color.ORANGE), new Material(0.5, 0.5, 30,0,0.5)),
                new Triangle(new Point3D(-1,-1,0),new Point3D(-1,-3,0),new Point3D(1,-3,0),new Color(java.awt.Color.ORANGE), new Material(0.5, 0.5, 30,0,0.5)),
                new Sphere(new Point3D(0,-2,0.75),0.75,new Color(100,450,350),new Material(0.5, 0.8, 30,0,0)),

                //square 10:
                new Triangle(new Point3D(1,3,0),new Point3D(3,3,0),new Point3D(3,1,0),new Color(java.awt.Color.GRAY), new Material(0.5, 0.5, 30,0,0.5)),
                new Triangle(new Point3D(1,3,0),new Point3D(1,1,0),new Point3D(3,1,0),new Color(java.awt.Color.GRAY), new Material(0.5, 0.5, 30,0,0.5)),
                new Sphere(new Point3D(2,2,0.75),0.75,new Color(100,450,350),new Material(0.5, 0.8, 30,0,0)),

                //square 11:
                new Triangle(new Point3D(1,1,0),new Point3D(3,1,0),new Point3D(3,-1,0),new Color(java.awt.Color.ORANGE), new Material(0.5, 0.5, 30,0,0.5)),
                new Triangle(new Point3D(1,1,0),new Point3D(1,-1,0),new Point3D(3,-1,0),new Color(java.awt.Color.ORANGE), new Material(0.5, 0.5, 30,0,0.5)),
                new Sphere(new Point3D(2,0,0.75),0.75,new Color(100,450,350),new Material(0.5, 0.8, 30,0,0)),

                //square 12:
                new Triangle(new Point3D(1,-1,0),new Point3D(3,-1,0),new Point3D(3,-3,0),new Color(java.awt.Color.GRAY), new Material(0.5, 0.5, 30,0,0.5)),
                new Triangle(new Point3D(1,-1,0),new Point3D(1,-3,0),new Point3D(3,-3,0),new Color(java.awt.Color.GRAY), new Material(0.5, 0.5, 30,0,0.5)),
                new Sphere(new Point3D(2,-2,0.75),0.75,new Color(100,450,350),new Material(0.5, 0.8, 30,0,0)));




                scene.addLights(new SpotLight(new Color(100, 100, 100),new Point3D(0, 0, 3),1, 4E-5, 2E-7, new Vector(-5, 0, -3),new Sphere(new Point3D(0,0,3),0.5)),
                        new SpotLight(new Color(100, 100, 100),new Point3D(0, 0, 3),1, 4E-5, 2E-7, new Vector(-5, 0, -3),new Sphere(new Point3D(0,0,3),0.5)),
                        new SpotLight(new Color(100, 100, 100),new Point3D(0, 0, 3),1, 4E-5, 2E-7, new Vector(-5, 0, -3),new Sphere(new Point3D(0,0,3),0.5)));
        scene.setSoftShadow(true);
        ImageWriter imageWriter = new ImageWriter("SofaTest1", 400, 400, 600, 600);
        Render render = new Render(imageWriter, scene);
        render.SuperSampling(0,0,600,600,1);

        //render.renderImage();
        render.writeToImage();
    }


    @Test
    public void SoftShadowTest1()
    {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, 400), new Vector(0, 0, -1), new Vector(1, 0, 0)));
        scene.setDistance(11000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.addGeometries(
                new Triangle(new Point3D(0,-5,0),new Point3D(-6.51,-6.3,5),new Point3D(-5,0,0),new Color(java.awt.Color.BLACK), new Material(0.5, 0.5, 30,0,0.5)),
                new Triangle(new Point3D(0,-5,0),new Point3D(-5,0,0),new Point3D(5,0,0),new Color(java.awt.Color.BLACK), new Material(0.5, 0.5, 30)),
                new Triangle(new Point3D(0,-5,0),new Point3D(7.09,-6.84,5),new Point3D(5,0,0),new Color(java.awt.Color.BLACK), new Material(0.5, 0.5, 30,0,0.5)),
                new Triangle(new Point3D(-5,0,0),new Point3D(0,5,0),new Point3D(5,0,0),new Color(java.awt.Color.BLACK), new Material(0.5, 0.5, 30)),
                new Triangle(new Point3D(-5,0,0),new Point3D(-6.43,6.36,0),new Point3D(0,5,0),new Color(java.awt.Color.BLACK), new Material(0.5, 0.5, 30)),
                new Triangle(new Point3D(5,0,0),new Point3D(0,5,0),new Point3D(6.64,7.05,0),new Color(java.awt.Color.BLACK), new Material(0.5, 0.5, 30)),

                new Sphere(new Point3D(4,4,3),1.2,new Color(100,300,750),new Material(0.3, 0.3, 30,0,0)),
                new Sphere(new Point3D(4,4,6),0.9,new Color(50,120,350),new Material(0.5, 0.5, 30,0,0)),
                new Sphere(new Point3D(4,4,7.5),0.6,new Color(200,0,750),new Material(0.5, 0.5, 30,0,0)),

                new Sphere(new Point3D(-4,4,3),1.2,new Color(100,450,350),new Material(0.5, 0.8, 30,0,0)),
                new Sphere(new Point3D(-4,4,6),0.9,new Color(100,450,350),new Material(0.5, 0.8, 30,0,0)),
                new Sphere(new Point3D(-4,4,7.5),0.6,new Color(100,50,350),new Material(0.5, 0.8, 30,0,0)));

        scene.addLights(new SpotLight(new Color(50, 400, 400),new Point3D(10, 10, 15),1, 4E-5, 2E-7, new Vector(-10, -10, -15),new Sphere(new Point3D(10,10,15),0.5,new Material(0.5, 0.5, 30,0,1))),
                new PointLight(new Color(10, 50, 50), new Point3D(-4, 4, 300), 1, 0.0000005, 0.000005,new Sphere(new Point3D(-4,4,300),0.5)),
                new SpotLight(new Color(200, 200, 200),new Point3D(-8, 8, 8),1, 4E-5, 2E-5, new Vector(8, -8, -8),new Sphere(new Point3D(-8,8,8),0.6,new Material(0.5, 0.5, 30,0,1))));

        scene.setSoftShadow(true);
        ImageWriter imageWriter = new ImageWriter("SoftShadowTest1", 400, 400, 600, 600);
        Render render = new Render(imageWriter, scene);
        //Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();//Run with the help of threading

        //render.renderImage();
        render.SuperSampling(0,0,600,600,1);
        render.writeToImage();
    }



}

