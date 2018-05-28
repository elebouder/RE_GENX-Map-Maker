package UI;

import Model.ManageMaps;
import Model.RoadState;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/*
* JToolBar that holds all JButtons that effect MapGen
* Singleton class
 */
public class ToolBar  extends JToolBar {
    private static ToolBar onlyOne;

    private JButton bNormal = new JButton("Normal Road");
    private JButton bFriction = new JButton("Friction Road");
    private JButton bErase = new JButton("Erase Road");
    private JButton bStartLine = new JButton("Start Line");
    private JButton bFinishLine = new JButton("Finish Line");
    private JButton bUndoSeg = new JButton("Undo");
    private JButton bClearMap = new JButton("Clear");
    private JButton bSaveMap = new JButton("Save");

    private ButtonHandler bHandler = new ButtonHandler();

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
        add(bStartLine);
        add(bFinishLine);
        add(bErase);
        add(bUndoSeg);
        add(bClearMap);
        add(bSaveMap);
    }
    public static ToolBar getInstance() {
        if(onlyOne == null) {
            onlyOne = new ToolBar();
        }
        return onlyOne;
    }


    /*
    * ActionListener for all Jbuttons on ToolBar
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
            if(e.getActionCommand().equals(bStartLine.getActionCommand())) {
                MapGen.setRoadState(RoadState.START_LINE);
            }
            if(e.getActionCommand().equals(bFinishLine.getActionCommand())) {
                MapGen.setRoadState(RoadState.FINISH_LINE);
            }
            if(e.getActionCommand().equals(bUndoSeg.getActionCommand())) {
                MapGen.undoNewSeg();
            }
            if(e.getActionCommand().equals(bClearMap.getActionCommand())) {
                MapGen.clearAll();
            }
            if(e.getActionCommand().equals(bSaveMap.getActionCommand())) {
                try {
                    ManageMaps.save();
                } catch (IOException error) {error.printStackTrace();}
            }
        }
    }
}
