package src;

import java.awt.*;
import javax.swing.*;

public class Tile extends JLabel implements Cloneable {
    public int value;
    public int column;
    public int row;

    public Tile(int column, int row, int value) {
        this.value = value;
        this.column = column;
        this.row = row;
    }

    /*public void draw(int x, int y, int height, int width, Graphics graphics) {
        // TODO: x and y should be calculated by Tile based on `column` and
        // `row `, instead of passed as arguments
        graphics.drawRect(x + 5, y + 5, width - 10, height - 10);
        Graphics2D g2 = (Graphics2D) graphics;
        g2.setColor(Color.BLACK);
        g2.fillRect(x + 7, y + 7, width - 10, height - 10);

        String text = Integer.toString(this.value);
        FontMetrics metrics = graphics.getFontMetrics();
        int valueX = x + (width - 10 - metrics.stringWidth(text)) / 2;
        int valueY = y + (height  - 10 - metrics.getHeight()) / 2 + metrics.getAscent();
        graphics.drawString(text, valueX, valueY);
    }*/

    public int getTileX(int width) {
        return (this.column*width) + (this.column*10);
    }
    
    public int getTileY(int width) {
        return (this.row*width) + (this.row*10);
    }

    public void increment() {
        value *= 2;
    }

    public void decrement() {
        value /= 2;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Tile) return this.value == ((Tile) o).value;
        else return false;
    }

    @Override
    public String toString() {
        return "<" + value + ">";
    }

    @Override
    public Tile clone() throws CloneNotSupportedException {
        return (Tile) super.clone();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        int height = getHeight();
        int width = getWidth();

        // TODO: x and y should be calculated by Tile based on `column` and
        // `row `, instead of passed as arguments
        Graphics2D g2d = (Graphics2D) g;

        int fontSize = 17;

        Font font = new Font("Serif", Font.PLAIN, fontSize);
        FontMetrics metrics = g2d.getFontMetrics();
        g2d.setFont(font);

        String text = Integer.toString(this.value);
        int valueX = (width - metrics.stringWidth(text)) / 2;
        int valueY = (width - metrics.getHeight()) / 2 + metrics.getAscent();

        // Draw a string such that its base line is at x, y
        g2d.drawString(text, valueX, valueY);
      
        setBackground(TileColor.tileColor(this.value));
        /*
        FontMetrics metrics = g.getFontMetrics();
        
        int valueY = y + (width - 10 - metrics.getHeight()) / 2 + metrics.getAscent();
        g.drawString(text, valueX, valueY);*/
    }
}