import javax.swing.JPanel;
import java.awt.*;

public class GameBoard extends JPanel
{
    private int startX = 0;
    private int startY = 0;
    private int size = 0;

    private int settingX = 0;
    private int settingY = 0;
    private int settingSize = 0;

    public GameBoard() {
        int width = getWidth();
        int height = getHeight();
        int recHei = height / 8;

        startX = ((width/2)-((recHei*4+50)/2));
        startY = ((height/2)-((recHei*4+50)/2));
        size = ((recHei*4)+50);

        settingSize = recHei/2;
        settingX = 50;
        settingY = 50;
    }

    public int getGameBoardSize() {
        return this.size;
    }

    public int getStartX() {
        return this.startX;
    }

    public int getStartY() {
        return this.startY;
    }

    public Boolean checkInsideGB(int x, int y) {
        if ((x > this.startX) && (x < this.startX+this.size)) {
            if ((y > this.startY+30) && (y < this.startY+this.size+30)) {
                return true;
            }
        }

        return false;
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
        int recHei = height / 8;

        // Draw The Information String
        Graphics2D g2d = (Graphics2D) g;

        // Set the desired font if different from default font
        Font font = new Font("Serif", Font.PLAIN, 20);
        g2d.setFont(font);

        // Draw a string such that its base line is at x, y
        g2d.drawString("2048", (width/2-25), 40);
        FontMetrics fontMetrics = g2d.getFontMetrics();

        this.startX = ((width/2)-((recHei*4+50)/2));
        this.startY = ((height/2)-((recHei*4+50)/2));

        this.size = ((recHei*4)+50);
        this.settingSize = recHei/2;

        this.settingX = ((width/3)/2)-this.settingSize;

        g.drawRect(this.settingX, this.settingY, this.settingSize, this.settingSize);

        g.drawRect(startX, startY, this.size, this.size);

        for (int i = 0; i < 4; i++)
        {
            for (int k=0; k<4; k++) {
                g.drawRect(((startX+10)+(recHei+10)*i), ((startY+10)+(recHei+10)*k), recHei, recHei);
            }
        }
    }
}
