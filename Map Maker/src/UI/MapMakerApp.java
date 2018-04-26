package UI;

/*
* Class that runs the app
* Handles parsing MapGen graphics and MapGEn.map to ManManager for saving
 */

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MapMakerApp extends JFrame {
    private static MapGen generator = MapGen.getInstance();
    private static ToolBar toolBar = ToolBar.getInstance();

    public MapMakerApp() {
        getContentPane().add(generator, BorderLayout.CENTER);
        getContentPane().add(toolBar, BorderLayout.PAGE_START);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static BufferedImage getBufferedImage() {
        int length = generator.getMap().getCols();
        int height = generator.getMap().getRows();
        BufferedImage bImage = new BufferedImage(length,height,BufferedImage.TYPE_INT_RGB);
        generator.getGraphics(bImage.getGraphics());
        return bImage;
    }

    public static void main(String[] args) {new MapMakerApp();}
}
