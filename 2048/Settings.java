import javax.swing.*;
import java.awt.*;

public class Settings extends JLabel
{

    private int settingX;
    private int settingY;
    private int settingSize;

    public Settings(String text, Point origin) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel label = new JLabel(text);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setOpaque(true);
        label.setBackground(Color.WHITE);
        label.setForeground(Color.black);
        label.setBorder(BorderFactory.createLineBorder(Color.black));
        label.setBounds(origin.x+25, origin.y+25, 40, 40);
        
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(label);

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

    /*public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();
        int recWid = width / 8;
        int recHei = height / 8;

        //g.drawRect(RECT_X, RECT_Y, RECT_WIDTH, RECT_HEIGHT);

        g.drawRect(10, 10, (recWid*2), (recHei*2));
    }*/
}