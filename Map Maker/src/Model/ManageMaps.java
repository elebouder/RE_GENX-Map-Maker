package Model;

import UI.MapGen;
import UI.MapMakerApp;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
*Class for saving map to a designated folder as:
* * PNG
* * JArray.....or something
 */
public class ManageMaps {

    //just to see if the buffered image actually saves
    public static void saveImage() {
        BufferedImage bImage = MapMakerApp.getBufferedImage();
        try {
            File test = new File("Image.png");
            ImageIO.write(bImage, "png", test);
            System.out.println("Saved map to" + test.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveMap() { }
}
