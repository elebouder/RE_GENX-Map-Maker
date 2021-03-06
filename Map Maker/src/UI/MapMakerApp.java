package UI;

/*
* Class that runs the app
* Handles parsing MapGen graphics and MapGEn.map to ManManager for saving
 */

import Model.MapTemp;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MapMakerApp extends JFrame {
    private static MapGen generator = MapGen.getInstance();
    private static ToolBar toolBar = ToolBar.getInstance();
    private static JFileChooser fileChooser = new JFileChooser(".\\Map Maker\\maps");

    public MapMakerApp() {
        getContentPane().add(generator, BorderLayout.CENTER);
        getContentPane().add(toolBar, BorderLayout.PAGE_START);
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static BufferedImage getBufferedImage() {
        int length = generator.getMap().getCols();
        int height = generator.getMap().getRows();
        BufferedImage bImage = new BufferedImage(length,height,BufferedImage.TYPE_INT_RGB);
        generator.getGraphics(bImage.getGraphics());
        return bImage;
    }
    public static MapTemp getMap() {
        return generator.getMap();
    }

    public static String saveDialog() {
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int FCVal = fileChooser.showSaveDialog(generator);
        if(FCVal == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getAbsolutePath();
        }
        if(FCVal == JFileChooser.CANCEL_OPTION) {
            System.out.println("Save cancelled");
            return null;
        }
        else {
            System.out.println("ERROR: FIle chosen was not approved!");
            return null;
        }
    }
    public static String nameMapDialog() {
        String JOPStr = JOptionPane.showInputDialog(
                generator,
                "Name of Map?",
                "Name file",
                JOptionPane.QUESTION_MESSAGE
        );
        if(JOPStr !=null && JOPStr.length() >0) {return JOPStr;}
        else {
            alertDialog("Enter a filename to save");
            return null;
        }
    }
    public static void messageDialog(String s) {
        JOptionPane.showMessageDialog(
                generator,
                s
        );
    }
    public static void alertDialog(String s) {
        JOptionPane.showMessageDialog(
                generator,
                s,
                "Alert",
                JOptionPane.WARNING_MESSAGE
        );
    }
    public static void errorDialog(String s) {
        JOptionPane.showMessageDialog(
                generator,
                s,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
    public static int confirmDialog(String s) {
        return JOptionPane.showConfirmDialog(
                generator,
                s,
                "Please Confirm",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );
    }

    public static void main(String[] args) {new MapMakerApp();}
}
