import javax.swing.JPanel;
import java.awt.*;

public class Settings extends JPanel
{
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();
        int recWid = width / 8;
        int recHei = height / 8;

        //g.drawRect(RECT_X, RECT_Y, RECT_WIDTH, RECT_HEIGHT);

        g.drawRect(10, 10, (recWid*2), (recHei*2));
    }
}