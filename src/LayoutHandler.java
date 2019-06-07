package src;

import javax.swing.*;
import javax.swing.border.*;
import javax.accessibility.*;

import java.awt.*;
import java.awt.event.*;

import java.util.*;

/*
*   Class to handle the general layout structure in the window.
*   The LayoutHandler manages on which levels different Components
*   are placed.
*   Settings, Tutorial, and GameBoard Components are placed in the layer
*   furthest back. All Tiles are placed in the foremost layer.
*/
public class LayoutHandler extends JPanel implements ComponentListener {

    private JLayeredPane layeredPane;
    public  GameBoard gb;
    private Settings st;
    private Tutorial tut;
    private SettingsScreen sts;
    private TutorialScreen tutS;

    private int currGBX;
    private int currGBY;

    private static int TOP_LAYER = 0;
    private static int TILE_LAYER = 2;
    private static int BACKGROUND_LAYER = 3;

    /*
    *   Base Constructor which receives the Components from the Game Class
    */
    public LayoutHandler(GameBoard theGB, Settings theST, SettingsScreen theSTS, Tutorial theTut, TutorialScreen theTutS, int currWidth) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(500, 500));

        this.gb = theGB;
        this.st = theST;
        this.sts = theSTS;
        this.tut = theTut;
        this.tutS = theTutS;

        this.currGBX    = currWidth/2-150;
        this.currGBY    = 100;
        int GBSize      = 500;

        Point origin1 = new Point(this.currGBX, this.currGBY+GBSize);
        Point origin2 = new Point(this.currGBX+GBSize/2, this.currGBY+GBSize);

        int height = getHeight();
        int newStSize = height/10;

        standardSet(this.gb);
        standardSet(this.st);
        standardSet(this.tut);

        this.sts.setOpaque(false);
        this.tutS.setOpaque(false);
        this.sts.setBorder(BorderFactory.createLineBorder(Color.black));

        this.gb.setBounds(this.currGBX, this.currGBY, GBSize, GBSize);
        this.st.setBounds(origin1.x, origin1.y, GBSize/2, newStSize);
        this.sts.setBounds(this.currGBX, this.currGBY, GBSize, GBSize + newStSize);
        this.tut.setBounds(origin2.x, origin2.y, GBSize/2, newStSize);
        this.tutS.setBounds(0,0,this.getWidth(), height);

        this.sts.setVisible(false);
        this.tutS.setVisible(false);

        this.layeredPane.add(this.gb, BACKGROUND_LAYER);
        this.layeredPane.add(this.st, BACKGROUND_LAYER);
        this.layeredPane.add(this.tut, BACKGROUND_LAYER);

        this.layeredPane.add(this.sts,TOP_LAYER);
        this.layeredPane.add(this.tutS, TOP_LAYER);

        addComponentListener(this);

        add(layeredPane);
    }

    /*
    *   Private help function to reduce code size
    */
    private void standardSet(JLabel temp) {
        temp.setOpaque(true);
        temp.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    /*
    *   Override function for ComponentListener functionality.
    *   Manages when the user decides to resize the window, to
    *   resize Components along with the window.
    */
    @Override
    public void componentResized(ComponentEvent e) {
        // This is only called when the user releases the mouse button.
        int width = getWidth();
        int height = getHeight();
        int newGBSize;
        int newSTSize;

        if (width > height) {
            newGBSize = ((height/4)*3);
            newSTSize = height/10;
        }
        else {
            newGBSize = ((width/4)*3);
            newSTSize = width/10;
        }

        this.currGBX = width/2-(newGBSize/2);
        this.currGBY = height/2-(newGBSize/2);

        this.gb.setBounds(this.currGBX, this.currGBY, newGBSize, newGBSize);
        this.st.setBounds(this.currGBX, this.currGBY+newGBSize, newGBSize/2, newSTSize);
        this.sts.setBounds(this.currGBX, this.currGBY, newGBSize, newGBSize + newSTSize);
        this.tut.setBounds(this.currGBX+newGBSize/2, this.currGBY+newGBSize, newGBSize/2, newSTSize);
        this.tutS.setBounds(0,0,width, height);

        drawTiles();
    }

    public void addTileToLayout(Tile tile) {
        standardSet(tile);

        int recSize = this.gb.getRecSize();
        int tileX = this.currGBX + this.gb.getStartX() + tile.getTileX(recSize) + 15;
        int tileY = this.currGBY + this.gb.getStartY() + tile.getTileY(recSize) + 15;

        tile.setBounds(tileX, tileY, recSize-10, recSize-10);

        this.layeredPane.add(tile, TILE_LAYER);

        this.revalidate();
        this.repaint();
    }

    /*
    *   Function to handle component resizing of Tiles either
    *   when the window changes size or when Tiles are moved.
    */
    public void drawTiles() {
        Component[] tmp = this.layeredPane.getComponentsInLayer(0);

        for(Component curr : tmp) {
            if (curr instanceof Tile) {
                this.layeredPane.remove(curr);
            }
        }

        this.revalidate();
        this.repaint();

        ArrayList<Tile> newList = new ArrayList<Tile>();

        for(int i=0; i<4; i++) {
            for(int k=0; k<4; k++) {
                newList.add(this.gb.state.getTile(k, i));
            }
        }

        for(Tile curr : newList) {
            if ((curr instanceof Tile) && (!(curr instanceof Void))) {
                int newSize = this.gb.getRecSize();
                int newTx = this.currGBX + this.gb.getStartX() + ((Tile) curr).getTileX(newSize) + 15;
                int newTy = this.currGBY + this.gb.getStartY() + ((Tile) curr).getTileY(newSize) + 15;
                ((Tile) curr).setBounds(newTx, newTy, newSize-10, newSize-10);
                this.layeredPane.add(curr, TILE_LAYER);
            }
        }

        this.revalidate();
        this.repaint();
    }

    public void drawTile(Tile tile, int x, int y) {
        standardSet(tile);

        int recSize = this.gb.getRecSize();
        int tileX = this.currGBX + this.gb.getStartX() + x + 15;
        int tileY = this.currGBY + this.gb.getStartY() + y + 15;

        tile.setBounds(tileX, tileY, recSize-10, recSize-10);

        this.layeredPane.add(tile, TILE_LAYER);

        this.revalidate();
        this.repaint();
    }

    /*
    *   Function to create an Interactie JLabel to work as setting interface
    */
    public void setActiveMenu() {
        this.sts.setVisible(true);
        this.repaint();
    }

    public void disableActiveMenu() {
        this.sts.setVisible(false);
        this.repaint();
    }

    public void setTutorialActive() {
        this.tutS.setVisible(true);
        this.repaint();
    }

    public void disableTutorialActive() {
        this.tutS.setVisible(false);
        this.repaint();
    }

    /*
    *   Override function for ComponentListener functionality
    */
    @Override
    public void componentHidden(ComponentEvent e) {}

    /*
    *   Override function for ComponentListener functionality
    */
    @Override
    public void componentMoved(ComponentEvent e) {}

    /*
    *   Override function for ComponentListener functionality
    */
    @Override
    public void componentShown(ComponentEvent e) {}
}
