package UI;

import javax.swing.*;
import java.awt.*;

/*
* Just a test class...still haven't created test classes.
 */
public class MainFrame extends JFrame {
    private  MapGen test;
    private ToolBar test2;

    MainFrame() {
        test = MapGen.getInstance();
        test2 = ToolBar.getInstance();
        getContentPane().add(test, BorderLayout.CENTER);
        getContentPane().add(test2, BorderLayout.PAGE_START);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
