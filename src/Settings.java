package src;

import javax.swing.*;
import java.awt.*;

/*
*   Class Settings which handles interaction with dictionary
*   and other Settings which might be needed
*/
public class Settings extends JLabel
{
    private int language;
    private Lookup lookup;

    /*
    *   Base Constructor
    */
    public Settings() {
        //0 = Swedish, 1= English
        this.language = 1;
        this.lookup = new Lookup();
    }

    /*
    *   Function to check if the x and y values are within
    *   the constraints of the Settings size
    */
    /*public Boolean checkInsideSetting(int x, int y) {
        if ((x > this.settingX) && (x < this.settingX+this.settingSize)) {
            if ((y > this.settingY+30) && (y < this.settingY+this.settingSize+30)) {
                return true;
            }
        }

        return false;
    }*/

    /*
    *   Standard setter function
    */
    public void setLanguage(int newLang) {
        this.language = newLang;
    }

    /*
    *   Standard getter function
    */
    public int getLanguage() {
        return this.language;
    }

    /*
    *   Standard getter function
    */
    public Lookup getLookup() {
        return this.lookup;
    }

    /*
    *   Function to draw the Component and its content
    */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth()/2;
        int height = getHeight()/2;

        Graphics2D g2d = (Graphics2D) g;
        
        // Set the desired font if different from default font
        Font font = new Font("Serif", Font.PLAIN, 15);
        g2d.setFont(font);

        String txt = this.lookup.lookupLang(this.language, "set");

        FontMetrics metrics = g2d.getFontMetrics();
        int valueX = (width - metrics.stringWidth(txt)/2);
        int valueY = (height + metrics.getAscent()/4);

        // Draw a string such that its base line is at x, y
        g2d.drawString(txt, valueX, valueY);
    }
}