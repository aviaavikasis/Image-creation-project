package Tests;
import org.junit.Test;
import renderer.*;

import java.awt.Color;
import java.util.Random;

public class ImageWriterTest {

@Test
    public void WriteImageTest()
{
    ImageWriter imagewriter = new ImageWriter("imagewritertest_1",16,10,1600,1000 );
    Random rand = new Random();

    for (int i = 0; i < imagewriter.getNx() ; i++)  // width - rows
    {
        for (int j = 0 ; j < imagewriter.getNy() ; j++) // height = columns
        {
            if (i % 100 == 0 || j % 100 == 0)
            imagewriter.writePixel(i,j,new Color(0,0,0));

            else imagewriter.writePixel(i,j,new Color(200,0,20));
        }
    }

    imagewriter.writeToImage();

}

}


