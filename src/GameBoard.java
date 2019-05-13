package src;

import javax.swing.*;
import java.awt.*;

public class GameBoard extends JLabel
{
    private int startX = 0;
    private int startY = 0;
    private int recSize = 0;
    private int size = 0;
    public State state;

    public GameBoard(String text, Point origin, int boardSize) {
        this.state = new State(boardSize);
        int width = getWidth();
        int height = getHeight();
        int recHei = height / 8;

        startX = ((width/2)-((recHei*4+50)/2));
        startY = ((height/2)-((recHei*4+50)/2));
        recSize = recHei;
        size = ((recHei*4)+50);
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

    public int getRecSize() {
        return this.recSize;
    }

    public Boolean checkInsideGB(int x, int y) {
        if ((x > this.startX) && (x < this.startX+this.size)) {
            if ((y > this.startY+30) && (y < this.startY+this.size+30)) {
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
