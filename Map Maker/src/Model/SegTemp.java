package Model;

import java.awt.*;

/*
*Template for all possible road segments
* Contains graphics for all possible road segments
* Holds position at which segment was placed in a map
 */
public class SegTemp {
    private static final int SEG_PIXELS_Y = 50;
    private static final int SEG_PIXELS_X = 50;
    private RoadState segState;
    private int posX; //X position on map
    private int posY; //Y position on map

    public SegTemp(RoadState r, int x, int y) {
        posX = x;
        posY = y;
        segState = r;
    }

    public int getVal() {return segState.stateVal;}
    public static int getPixelsY() {return SEG_PIXELS_Y;}
    public static int getPixelsX() {return SEG_PIXELS_X;}
    public int getPosX() {return posX;}
    public int getPosY() {return posY;}

    /*
    Effect: returns right-most X pixel of the segment and the buttom-most Y pixel
    */
    public int getXBound() {return posX + SEG_PIXELS_X;}
    public int getYBound() {return posY + SEG_PIXELS_Y;}


    /*
    Effects: contains the graphics for each type of road segment that can be passed to a GUI
     */
    public void drawRoad(Graphics g) {
        if (segState == RoadState.ERASE) {
            g.setColor(Color.black);
            g.fillRect(posX - (SEG_PIXELS_X/2), posY - (SEG_PIXELS_Y/2),
                    SEG_PIXELS_X, SEG_PIXELS_Y);
        }
        if (segState == RoadState.NORMAL) {
            g.setColor(Color.green);
            g.fillRect(posX - (SEG_PIXELS_X/2), posY - (SEG_PIXELS_Y/2),
                    SEG_PIXELS_X, SEG_PIXELS_Y);
        }
        if (segState == RoadState.FRICTION) {
            g.setColor(Color.orange);
            g.fillRect(posX - (SEG_PIXELS_X/2), posY - (SEG_PIXELS_Y/2),
                    SEG_PIXELS_X, SEG_PIXELS_Y);
        }
        if (segState == RoadState.START_LINE) {
            g.setColor(Color.blue);
            g.fillRect(posX - (SEG_PIXELS_X/2), posY - (SEG_PIXELS_Y/2),
                    SEG_PIXELS_X, SEG_PIXELS_Y);
        }
        if (segState == RoadState.FINISH_LINE) {
            g.setColor(Color.red);
            g.fillRect(posX - (SEG_PIXELS_X/2), posY - (SEG_PIXELS_Y/2),
                    SEG_PIXELS_X, SEG_PIXELS_Y);
        }
    }
}
