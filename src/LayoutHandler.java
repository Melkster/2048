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
    private GameBoard gb;
    private Settings st;
    private Tutorial tut;

    private int currGBX;
    private int currGBY;

    /*
    *   Base Constructor which receives the Components from the Game Class
    */
    public LayoutHandler(GameBoard theGB, Settings theST, Tutorial theTut, int currWidth) {

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(500, 500));

        this.gb = theGB;
        this.st = theST;
        this.tut = theTut;

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

        this.gb.setBounds(this.currGBX, this.currGBY, GBSize, GBSize);
        this.st.setBounds(origin1.x, origin1.y, GBSize/2, newStSize);
        this.tut.setBounds(origin2.x, origin2.y, GBSize/2, newStSize);

        this.layeredPane.add(this.gb, 4);
        this.layeredPane.add(this.st, 4);
        this.layeredPane.add(this.tut, 4);

        addComponentListener(this);

        add(Box.createRigidArea(new Dimension(0, 10)));
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
        System.out.println("componentResized");
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
        this.tut.setBounds(this.currGBX+newGBSize/2, this.currGBY+newGBSize, newGBSize/2, newSTSize);

        animateTile();
    }

    public void addTileToLayout(Tile tile) {
        standardSet(tile);

        int recSize = this.gb.getRecSize();
        int tileX = this.currGBX + this.gb.getStartX() + tile.getTileX(recSize) + 15;
        int tileY = this.currGBY + this.gb.getStartY() + tile.getTileY(recSize) + 15;

        tile.setBounds(tileX, tileY, recSize-10, recSize-10);

        this.layeredPane.add(tile, 0);

        this.revalidate();
        this.repaint();
    }

    /*
    *   Function to handle component resizing of Tiles either
    *   when the window changes size or when Tiles are moved.
    */
    public void animateTile() {
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
                this.layeredPane.add(curr, 0);
            }
        }

        this.revalidate();
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
