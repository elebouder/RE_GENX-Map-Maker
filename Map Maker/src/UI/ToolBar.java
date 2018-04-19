package UI;

import Model.RoadState;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
* JToolBar that holds all JButtons that effect MapGen
* Singleton class
 */
public class ToolBar  extends JToolBar {
    private static ToolBar onlyOne;

    JButton bNormal = new JButton("Normal Road");
    JButton bFriction = new JButton("Friction Road");
    JButton bErase = new JButton("Erase Road");
    JButton bStartLine = new JButton("Start Line");
    JButton bFinishLine = new JButton("Finish Line");
    JButton bUndoSeg = new JButton("Undo");
    JButton bClearMap = new JButton("Clear");
    JButton bSaveMap = new JButton("Save");

    ButtonHandler bHandler = new ButtonHandler();

    private void initialiseButtons() {
        bNormal.setActionCommand("NORMAL");
        bFriction.setActionCommand("FRICTION");
        bErase.setActionCommand("ERASE");
        bStartLine.setActionCommand("START");
        bFinishLine.setActionCommand("FINISH");
        bUndoSeg.setActionCommand("UNDO");
        bClearMap.setActionCommand("CLEAR");
        bSaveMap.setActionCommand("SAVE");

        bNormal.isDefaultButton();
        bNormal.addActionListener(bHandler);
        bFriction.addActionListener(bHandler);
        bErase.addActionListener(bHandler);
        bStartLine.addActionListener(bHandler);
        bFinishLine.addActionListener(bHandler);
        bUndoSeg.addActionListener(bHandler);
        bClearMap.addActionListener(bHandler);
        bSaveMap.addActionListener(bHandler);
    }

    private ToolBar() {
        setRollover(true);
        setFloatable(false);
        initialiseButtons();
        add(bNormal);
        add(bFriction);
        add(bErase);
        add(bUndoSeg);
        add(bClearMap);
    }
    public static ToolBar getInstance() {
        if(onlyOne == null) {
            onlyOne = new ToolBar();
        }
        return onlyOne;
    }


    /*
    * ActionListener for all Jbuttons on ToolBar
    * Uses MapGen.checkSeg to determine if the MapGen has already set up a start line
      a finish line
     */
    private class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals(bNormal.getActionCommand())) {
                MapGen.setRoadState(RoadState.NORMAL);
            }

            if(e.getActionCommand().equals(bFriction.getActionCommand())) {
                MapGen.setRoadState(RoadState.FRICTION);
            }

            if(e.getActionCommand().equals(bErase.getActionCommand())) {
                MapGen.setRoadState(RoadState.ERASE);
            }
            if(e.getActionCommand().equals(bUndoSeg.getActionCommand())) {
                MapGen.undoNewSeg();
            }
            if(e.getActionCommand().equals(bClearMap.getActionCommand())) {
                MapGen.clearAll();
            }
        }
    }
}
