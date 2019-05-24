package src;

import javax.swing.*;
import java.awt.*;

/*
*   Class to handle a Tutorial Game introduction
*   The Class allows the player to get a understanding of the
*   Game and how it is played.
*/
public class Tutorial extends JLabel {
    
    private Settings set;
    private boolean active;

    private boolean swiped = false;

    /*
    *   Base Constructor
    */
    public Tutorial(Settings settings) {
        this.set = settings;
    }

    public boolean getActive() {
        return this.active;
    }

    public void changeActive() {
        this.active = !this.active;
    }

    /*
    *   Function to check if the x and y values are inside
    *   the constraints of the component.
    */
    public Boolean checkInsideTutorial(int x, int y) {
        if ((x > this.getX()) && (x < this.getX()+this.getWidth())) {
            if ((y > this.getY()) && (y < this.getY()+this.getHeight())) {
                return true;
            }
        }
        return false;
    }

    /*
    *   Function to illustrate how a user can conduct and upward or
    *   downwards swipe within the Game.
    */
    /*public void swipeUporDown() {
        int x = 50;
        int y = 50;
        int changer = 1;

        while (!(this.swiped)) {
            if (y > 100) {
                changer = -1;
            }
            else if (y <50) {
                changer = 1;
            }

            y = y + changer;

            g.drawRect(x, y, 10, 10);

            try {
                Thread.sleep(25);
            }catch(Exception e)
            {
                e.printStackStrace();
            }
        }
    }

    /*
    *   Function to illustrate how a user can conduct and left or
    *   right swipe within the Game.
    */
    /*
    public void swipeLeftorRight(Graphics g) {

        int x = 50;
        int y = 50;
        int changer = 1;

        while (!(this.swiped)) {
            if (x > 100) {
                changer = -1;
            }
            else if (x <50) {
                changer = 1;
            }

            x = x + changer;

            this.repaint();

            g.drawRect(x, y, 10, 10);

            try {
                Thread.sleep(25);
            }catch(Exception e)
            {
                e.printStackStrace();
            }
        }

    }*/

    /*
    *   Function to show the undo function works within the Game.
    */
    /*
    public void undoRedoTut() {
    }*/

    /*
    *   Function to draw the Component and its containing components
    */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth()/2;
        int height = getHeight()/2;

        Graphics2D g2d = (Graphics2D) g;
        
        // Set the desired font if different from default font
        Font font = new Font("Serif", Font.PLAIN, 15);
        g2d.setFont(font);

        String txt = this.set.getLookup().lookupLang(this.set.getLanguage(), "tut");

        FontMetrics metrics = g2d.getFontMetrics();
        int valueX = (width - metrics.stringWidth(txt)/2);
        int valueY = (height + metrics.getAscent()/4);

        // Draw a string such that its base line is at x, y
        g2d.drawString(txt, valueX, valueY);
    }
}
