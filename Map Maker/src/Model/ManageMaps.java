package Model;

import UI.MapMakerApp;
import com.google.gson.JsonArray;
import com.google.gson.stream.JsonWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
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

    public static void saveMap() throws IOException {
        File test = new File("map.json");
        FileWriter jOut = new FileWriter(test);
        JsonWriter jMap = new JsonWriter(jOut);
        MapTemp mTemp = MapMakerApp.getMap();
        jMap.beginArray();
        for(int j = 0; j < mTemp.getRows(); ++j) {
            jMap.beginArray();
            for(int i = 0; i < mTemp.getCols(); ++i) {
                jMap.value(mTemp.getVal(i,j));
            }
            jMap.endArray();
        }
        jMap.endArray();
        jMap.flush();
        jOut.flush();
        jOut.close();
        System.out.println("Map saved to: " + test.getAbsolutePath());
    }
}
