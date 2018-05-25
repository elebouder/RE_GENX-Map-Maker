package Model;

import UI.MapMakerApp;
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
    private static File savePath;
    private static String mapName = "/testFile123";

    public static void save(File f) throws IOException {
        savePath = new File(f.getAbsolutePath() + mapName);
        if(!savePath.exists()) {
            boolean DIRcheck = savePath.mkdirs();
            if(DIRcheck) {
                saveMap();
                saveImage();
            }
            else { System.out.println("Failed to create directory: " + savePath.getAbsolutePath());}
        }
        else { System.out.println("File already exists!");}
    }

    //just to see if the buffered image actually saves
    public static void saveImage() throws IOException {
        BufferedImage bImage = MapMakerApp.getBufferedImage();
        File mapImage = new File(savePath.getAbsolutePath() +  "/Image.png");
        mapImage.mkdirs();
        ImageIO.write(bImage, "png", mapImage);
        System.out.println("Saved map to" + mapImage.getAbsolutePath());
    }

    public static void saveMap() throws IOException {
        File featureMap = new File(savePath.getAbsolutePath() + "/map.json");
        FileWriter jOut = new FileWriter(featureMap);
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
        System.out.println("Map saved to: " + featureMap.getAbsolutePath());
    }
}
