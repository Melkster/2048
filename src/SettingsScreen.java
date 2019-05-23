package src;

import javax.swing.*;
import java.awt.*;

import java.awt.geom.Ellipse2D;

public class SettingsScreen extends JLabel {
    private Settings st;

    private Point langLoc;
    private Point soundLoc;
    private Point restartLoc;

    private Point WHSize;

    public SettingsScreen(Settings theST) {
        this.st = theST;
        
        int width = this.getWidth();
        int height = this.getHeight();

        int startX = width/4;
        int startY = height/5;

        this.langLoc = new Point(startX, startY);
        this.soundLoc = new Point(startX, startY*2);
        this.restartLoc = new Point(startX, startY*3);

        this.WHSize = new Point(width/2, startY-height/10);
    }

    public int checkInsideSTS(int x, int y) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int width = this.getWidth();
        int height = this.getHeight();

        int startX = this.getX() + width/4;
        int startY = height/5;

        this.langLoc.x = startX;
        this.langLoc.y = this.getY() + startY;
        this.soundLoc.x = startX;
        this.soundLoc.y = this.getY() + startY*2;
        this.restartLoc.x = startX;
        this.restartLoc.y = this.getY() + startY*3;

        this.WHSize.x = width/2;
        this.WHSize.y = startY-height/10;

        if ((x > this.langLoc.x) && (x < this.langLoc.x+this.WHSize.x)) {
            if ((y > this.langLoc.y) && (y < this.langLoc.y+this.WHSize.y)) {
                return 0;
            }
        }
        if ((x > this.soundLoc.x) && (x < this.soundLoc.x+this.WHSize.x)) {
            if ((y > this.soundLoc.y) && (y < this.soundLoc.y+this.WHSize.y)) {
                return 1;
            }
        }
        if ((x > this.restartLoc.x) && (x < this.restartLoc.x+this.WHSize.x)) {
            if ((y > this.restartLoc.y) && (y < this.restartLoc.y+this.WHSize.y)) {
                return 2;
            }
        }

        return -1;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = this.getWidth();
        int height = this.getHeight();

        int startX = width/4;
        int startY = height/5;

        this.langLoc.x = startX;
        this.langLoc.y = startY;
        this.soundLoc.x = startX;
        this.soundLoc.y = startY*2;
        this.restartLoc.x = startX;
        this.restartLoc.y = startY*3;

        this.WHSize.x = width/2;
        this.WHSize.y = startY-height/10;

        Graphics2D g2d = (Graphics2D) g;
        
        // Set the desired font if different from default font
        Font font = new Font("Serif", Font.PLAIN, 15);
        g2d.setFont(font);

        String txt1 = this.st.getLookup().lookupLang(this.st.getLanguage(), "setLang");
        String txt2 = this.st.getLookup().lookupLang(this.st.getLanguage(), "setSound");
        String txt3 = this.st.getLookup().lookupLang(this.st.getLanguage(), "setRestart");

        txt1 += ": " + this.st.getLookup().lookupLang(this.st.getLanguage(), "language");

        if (this.st.getSound()) {
            txt2 += ": on";
        }
        else {
            txt2 += ": off";
        }

        FontMetrics metrics = g2d.getFontMetrics();
        int valueX1 = (startX + this.WHSize.x/2 - metrics.stringWidth(txt1)/2);
        int valueX2 = (startX + this.WHSize.x/2 - metrics.stringWidth(txt2)/2);
        int valueX3 = (startX + this.WHSize.x/2 - metrics.stringWidth(txt3)/2);
        int valueY1 = (this.langLoc.y + this.WHSize.y/2 + metrics.getAscent()/4);
        int valueY2 = (this.soundLoc.y + this.WHSize.y/2 + metrics.getAscent()/4);
        int valueY3 = (this.restartLoc.y + this.WHSize.y/2 + metrics.getAscent()/4);

        Ellipse2D.Double circle1 = new Ellipse2D.Double(this.langLoc.x, this.langLoc.y, this.WHSize.x, this.WHSize.y);
        Ellipse2D.Double circle2 = new Ellipse2D.Double(this.soundLoc.x, this.soundLoc.y, this.WHSize.x, this.WHSize.y);
        Ellipse2D.Double circle3 = new Ellipse2D.Double(this.restartLoc.x, this.restartLoc.y, this.WHSize.x, this.WHSize.y);

        g2d.fill(circle1);
        g2d.fill(circle2);
        g2d.fill(circle3);

        g2d.setColor(Color.white);

        // Draw a string such that its base line is at x, y
        g2d.drawString(txt1, valueX1, valueY1);
        g2d.drawString(txt2, valueX2, valueY2);
        g2d.drawString(txt3, valueX3, valueY3);
    }
}