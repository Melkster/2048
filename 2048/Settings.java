import javax.swing.*;
import java.awt.*;

public class Settings extends JLabel
{

    private int settingX;
    private int settingY;
    private int settingSize;

    public Settings(String text, Point origin) {
        this.settingX = origin.x;
        this.settingY = origin.y;
        this.settingSize = 50;
    }

    public Boolean checkInsideSetting(int x, int y) {
        if ((x > this.settingX) && (x < this.settingX+this.settingSize)) {
            if ((y > this.settingY+30) && (y < this.settingY+this.settingSize+30)) {
                return true;
            }
        }

        return false;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();
        int recWid = width / 8;
        int recHei = height / 8;

        Graphics2D g2d = (Graphics2D) g;

        // Set the desired font if different from default font
        Font font = new Font("Serif", Font.PLAIN, 15);
        g2d.setFont(font);

        // Draw a string such that its base line is at x, y
        g2d.drawString("Settings", width/6, height/2);
        FontMetrics fontMetrics = g2d.getFontMetrics();
    }
}