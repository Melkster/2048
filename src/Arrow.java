package src;

import javax.swing.*;
import java.awt.*;

public class Arrow extends JLabel {
    public Arrow() {}

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = this.getWidth();
        int height = this.getHeight();

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(new Color(0, 0, 0));

        int xval = (width/4);
        int yval = (height/4);

        g2d.fillRect(xval, yval, (width/2), (height/2));
    }
}