package src;

import javax.swing.*;
import java.awt.*;

/*
*   Class to host the GameBoard and its related information
*/
public class GameBoard extends JLabel
{
    private int startX = 0;
    private int startY = 0;
    private int recSize = 0;
    private int size = 0;

    public State state;

    /*
    *   Base Constructor
    */
    public GameBoard(int boardSize) {
        this.state = new State(boardSize);
        int width = getWidth();
        int height = getHeight();
        int recHei = height / 8;

        this.startX = ((width/2)-((recHei*4+50)/2));
        this.startY = ((height/2)-((recHei*4+50)/2));
        this.recSize = recHei;
        this.size = ((recHei*4)+50);
    }

    public void resetState() {
        this.state = new State(this.state.size);
    }

    /*
    *   Standard getter function for private variable
    */
    public int getStartX() {
        return this.startX;
    }

    /*
    *   Standard getter function for private variable
    */
    public int getStartY() {
        return this.startY;
    }

    /*
    *   Standard getter function for private variable
    */
    public int getRecSize() {
        return this.recSize;
    }

    /*
    *   Standard getter function for private variable
    */
    public int getGameBoardSize() {
        return this.size;
    }

    /*
    *   Function that checks whether the x and y values
    *   are inside the borders of the GameBoard
    */
    public Boolean checkInsideGB(int x, int y) {
        if ((x > this.startX) && (x < this.startX+this.size)) {
            if ((y > this.startY+30) && (y < this.startY+this.size+30)) {
                return true;
            }
        }
        return false;
    }

    /*
    *   Function needed by extends JPanel
    *   Draws out the contents of GameBoard
    */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();
        int recHei = height / 8;

        // Draw The Information String
        Graphics2D g2d = (Graphics2D) g;

        // Set the desired font if different from default font
        Font font = new Font("Serif", Font.PLAIN, 20);
        g2d.setFont(font);

        FontMetrics metrics = g2d.getFontMetrics();
        int txtPlacement = (width/2 - metrics.stringWidth("2048")/2);

        // Draw a string such that its base line is at x, y
        g2d.drawString("2048", txtPlacement, 40);

        this.startX = ((width/2)-((recHei*4+50)/2));
        this.startY = ((height/2)-((recHei*4+50)/2));
        this.recSize = recHei;
        this.size = ((recHei*4)+50);

        g.drawRect(startX, startY, this.size, this.size);

        for (int i = 0; i < 4; i++)
        {
            for (int k=0; k<4; k++) {
                g.drawRect(((startX+10)+(recHei+10)*i), ((startY+10)+(recHei+10)*k), recHei, recHei);
            }
        }
    }
}
