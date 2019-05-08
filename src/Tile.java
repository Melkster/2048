package src;

import java.awt.*;

public class Tile implements CustGraphics {
    public int value;
    public int column;
    public int row;

    public Tile(int value, int column, int row) {
        this.value = value;
        this.column = column;
        this.row = row;
    }

    public void draw(int x, int y, int rec_size, Graphics g) {
        // TODO: x and y should be calculated by Tile based on `column` and
        // `row `, instead of passed as arguments
        g.drawRect(x+5, y+5, rec_size-10, rec_size-10);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.fillRect(x+7, y+7, rec_size-10, rec_size-10);

        String text = Integer.toString(this.value);
        FontMetrics metrics = g.getFontMetrics();
        int valueX = x + (rec_size - 10 - metrics.stringWidth(text)) / 2;
        int valueY = y + (rec_size - 10 - metrics.getHeight()) / 2 + metrics.getAscent();
        g.drawString(text, valueX, valueY);
    }

    public void increment() {
        value *= 2;
    }

    public void decrement() {
        value /= 2;
    }

}
