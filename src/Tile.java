package src;

import java.awt.*;

public class Tile implements CustGraphics, Cloneable {
    public int value;
    public int column;
    public int row;

    public Tile(int column, int row, int value) {
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
}
