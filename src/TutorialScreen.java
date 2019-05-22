package src;

import javax.swing.*;
import java.awt.*;

public class TutorialScreen extends JLabel {
    //private Tutorial tut;
    private Settings st;

    private Point WHSize;

    public TutorialScreen(Settings settings) {
        this.st = settings;

        
        int width = this.getWidth();
        int height = this.getHeight();

        int startX = width/4;
        int startY = height/5;


        this.WHSize = new Point(width/2, startY-height/10);
    }

    public int checkInsideSTS(int x, int y) {
        int width = this.getWidth();
        int height = this.getHeight();

        int startX = width/4;
        int startY = height/5;

        this.WHSize.x = width/2;
        this.WHSize.y = startY-height/10;

      /*Check if you are in a certain area
      //TODO
      */
 

        return -1;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = this.getWidth();
        int height = this.getHeight();

        int startX = width/4;
        int startY = height/5;

        this.WHSize.x = width/2;
        this.WHSize.y = startY-height/10;

        Graphics2D g2d = (Graphics2D) g;
        
        // Set the desired font if different from default font
        Font font = new Font("Serif", Font.PLAIN, 15);
        g2d.setFont(font);


        FontMetrics metrics = g2d.getFontMetrics();
        /*int valueX1 = (startX + this.WHSize.x/2 - metrics.stringWidth(txt1)/2);
        int valueX2 = (startX + this.WHSize.x/2 - metrics.stringWidth(txt2)/2);
        int valueX3 = (startX + this.WHSize.x/2 - metrics.stringWidth(txt3)/2);
        int valueY1 = (this.langLoc.y + this.WHSize.y/2 + metrics.getAscent()/4);
        int valueY2 = (this.soundLoc.y + this.WHSize.y/2 + metrics.getAscent()/4);
        int valueY3 = (this.restartLoc.y + this.WHSize.y/2 + metrics.getAscent()/4);
        */

        // Draw a string such that its base line is at x, y
       
    }
}