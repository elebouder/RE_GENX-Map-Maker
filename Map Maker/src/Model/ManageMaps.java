package Model;

import UI.MapMakerApp;
import com.google.gson.stream.JsonWriter;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

/*
*Class for saving map to a designated folder as:
* * PNG
* * JArray.....or something
 */
public class ManageMaps {
    private static String mapName;
    private static File saveFile;


    private static boolean checkInvalidName(String s) {
        return s.matches(".*[?/<>|*:\"{\\\\}].*");
    }

    //just to see if the buffered image actually saves
    private static void saveImage() throws IOException {
        BufferedImage bImage = MapMakerApp.getBufferedImage();
        File mapImage = new File(saveFile.getAbsolutePath() +  "/" + mapName + ".png");
        ImageIO.write(bImage, "png", mapImage);
    }

    private static void saveMap() throws IOException {
        File featureMap = new File(saveFile.getAbsolutePath() + "/" + mapName + ".json");
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
    }

    public static void save() throws IOException {
        String savePath = MapMakerApp.saveDialog();
        if(savePath == null) {
            return;
        }
        mapName = MapMakerApp.nameMapDialog();
        if(mapName == null){
            return;
        }
        if(checkInvalidName(mapName)) {
            MapMakerApp.errorDialog("ERROR: illegal filename!");
            return;
        }
        saveFile = new File(savePath + "/" + mapName);
        if(!saveFile.exists()) {
            boolean DIRcheck = saveFile.mkdirs();
            if(DIRcheck) {
                saveMap();
                saveImage();
                MapMakerApp.messageDialog("Saved to: \n" + saveFile.getAbsolutePath());
            }
            else { MapMakerApp.errorDialog("Failed to create directory: \n" + saveFile.getAbsolutePath());}
        }
        else { MapMakerApp.alertDialog("File already exists!");}
    }

}
