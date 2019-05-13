package src;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.border.*;
import javax.accessibility.*;

import java.awt.*;
import java.awt.event.*;

public class LayoutHandler extends JPanel implements ComponentListener {

    private JLayeredPane layeredPane;
    private GameBoard gb;
    private Settings st;

    private int currGBX;
    private int currGBY;

    public LayoutHandler(GameBoard theGB, Settings theST, int currWidth) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(500, 500));

        Point origin1 = new Point(15, 15);

        this.gb = theGB;
        this.st = theST;

        this.currGBX = currWidth/2-150;
        this.currGBY = 100;

        this.gb.setVerticalAlignment(JLabel.TOP);
        this.gb.setHorizontalAlignment(JLabel.CENTER);
        this.gb.setOpaque(true);
        this.gb.setBackground(Color.white);
        this.gb.setForeground(Color.black);
        this.gb.setBorder(BorderFactory.createLineBorder(Color.black));
        this.gb.setBounds(this.currGBX, this.currGBY, 500, 500);

        this.st.setVerticalAlignment(JLabel.CENTER);
        this.st.setHorizontalAlignment(JLabel.CENTER);
        this.st.setOpaque(true);
        this.st.setBackground(Color.white);
        this.st.setForeground(Color.black);
        this.st.setBorder(BorderFactory.createLineBorder(Color.black));
        this.st.setBounds(origin1.x, origin1.y, 60, 60);

        Tile testTile = new Tile(0,0,2);

        testTile.setVerticalAlignment(JLabel.CENTER);
        testTile.setHorizontalAlignment(JLabel.CENTER);
        testTile.setOpaque(true);
        testTile.setBackground(Color.white);
        testTile.setForeground(Color.black);
        testTile.setBorder(BorderFactory.createLineBorder(Color.black));
        testTile.setBounds(this.currGBX, this.currGBY, this.gb.getRecSize()-10, this.gb.getRecSize()-10);

        layeredPane.add(this.gb, 4);
        layeredPane.add(this.st, 4);
        layeredPane.add(testTile, 0);

        addComponentListener(this);

        add(Box.createRigidArea(new Dimension(0, 10)));
        add(layeredPane);
    }

    public void componentHidden(ComponentEvent e) {

    }

    public void componentMoved(ComponentEvent e) {

    }

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

        gb.setBounds(this.currGBX, this.currGBY, newGBSize, newGBSize);
        st.setBounds(this.currGBX, this.currGBY+newGBSize, newGBSize/2, newSTSize);

        animateTile();
    }

    public void componentShown(ComponentEvent e) {

    }

    public void animateTile() {
        Component[] tmp = this.layeredPane.getComponentsInLayer(0);

        int newSize = this.gb.getRecSize();
        int newTx = this.currGBX + this.gb.getStartX() + ((Tile) tmp[0]).getTileX(newSize) + 15;
        int newTy = this.currGBY + this.gb.getStartY() + ((Tile) tmp[0]).getTileY(newSize) + 15;
        ((Tile) tmp[0]).setBounds(newTx, newTy, newSize-10, newSize-10);
    }
}
