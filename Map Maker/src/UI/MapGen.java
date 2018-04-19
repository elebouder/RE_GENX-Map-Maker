package UI;

import Model.MapTemp;
import Model.RoadState;
import Model.SegTemp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

/*
* JPanel that allows user to draw a map
* User's drawings will also map segments to a feature map
* Singleton class
 */
public class MapGen extends JPanel {
    private static int mousePosX;
    private static int mousePosY;
    private static MapTemp map = new MapTemp();
    private static LinkedList<SegTemp> segList = new LinkedList<SegTemp>(); //Graphics list for GUI
    private static RoadState currentRoadState = RoadState.NORMAL;
    private static LinkedList<Integer> trackNumSegAdded = new LinkedList<Integer>(); //List to track User's inputs
    private static int numSegAdded; //Counter for number of segments added during one input instance
    private static MapGen onlyOne;

    MouseHandler mHandler = new MouseHandler();

    /*
    Modifies: this.map, this.segList
    Effect: Adds a Segment to a map. Adds the same segment to a graphics list.
            Tracks how many segments were added in a single input instance
     */
    private void collectInput() {
        segList.addLast(new SegTemp(currentRoadState
                , mousePosX, mousePosY));
        map.addSeg(segList.getLast());
        ++numSegAdded;
    }
    /*
    Modifies: MapGen.trackNumSegAdded, this.numSegAdded
    Effect: Adds the total number of segments, inserted to the map during a single input
            instance, into a linkedList that tracks how many input instances there have been.
     */
    private void trackInput() {
        trackNumSegAdded.addLast(numSegAdded);
        System.out.println(numSegAdded + " Segment(s) added");
        numSegAdded = 0;
    }

    private MapGen() {
        setPreferredSize(new Dimension(map.getCols(),map.getRows()));
        setBackground(Color.blue);
        addMouseListener(mHandler);
        addMouseMotionListener(mHandler);
    }
    public static MapGen getInstance() {
        if(onlyOne == null) {
            onlyOne = new MapGen();
        }
        return onlyOne;
    }

    /*
    Modifies: this.map, this.SegList, this.trackNumSegAdded
    Effect: undo the user's latest input
     */
    public static void undoNewSeg() {
        for(int i = 0; i < trackNumSegAdded.getLast(); ++i) {
            map.addSeg(segList.getLast());
            segList.removeLast();
        }
        trackNumSegAdded.removeLast();
    }
    /*
    Modifies: this.currentRoadState
    Effect: Used by other GUI components to determine what type of road segment the user will
            input
     */
    public static void setRoadState(RoadState r) {
        currentRoadState = r;
    }
    /*
    Modifies: this
    Effect: Resets everthing to initialised state
     */
    public void clearAll() {
        map.clearMap();
        segList.clear();
        trackNumSegAdded.clear();
    }
    /*
    Effect: checks if a segment exists in the map
     */
    public void checkSeg(RoadState r) {
        map.checkSegExist(r);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(SegTemp s: segList) {
            s.drawRoad(g);
        }
        repaint();
    }

    /*
    * Nested MouseAdapter that handles the user's mouse inputs
    * mouseClicked is not overridden due to conflicts with mouseDraged, instead mousePressed
      and mouseReleased are overridden to prevent re-occurrence issues when adding segments
     */
    private class MouseHandler extends MouseAdapter {
        private boolean enabled;

        @Override
        public void mousePressed(MouseEvent e) {
            if(!enabled) return;
            mousePosX = e.getX();
            mousePosY = e.getY();
            collectInput();
            repaint();
            System.out.println("added segment at: " + mousePosX + ", " + mousePosY
                    + " Value is: " + map.getVal(mousePosX,mousePosY));
            e.consume();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if(!enabled) return;
            mousePosX = e.getX();
            mousePosY = e.getY();
            collectInput();
            repaint();
            System.out.println("added segment at: " + mousePosX + ", " + mousePosY
                    + " Value is: " + map.getVal(mousePosX,mousePosY));
            e.consume();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if(!enabled) return;
            trackInput();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            super.mouseExited(e);
            enabled = false;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            super.mouseEntered(e);
            enabled = true;
        }
    }
}
















