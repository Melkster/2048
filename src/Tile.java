package src;

import java.awt.*;

public class Tile implements CustGraphics {
    public int value;
    public int column;
    public int row;

    public Tile(int column, int row, int value) {
        this.value = value;
        this.column = column;
        this.row = row;
    }

    public void draw(int x, int y, int width, int height, Graphics graphics) {
        // TODO: x and y should be calculated by Tile based on `column` and
        // `row `, instead of passed as arguments
        graphics.drawRect(x, y, width, height);

        String text = Integer.toString(this.value);
        FontMetrics metrics = graphics.getFontMetrics();
        int valueX = x + (width - metrics.stringWidth(text)) / 2;
        int valueY = y + (height - metrics.getHeight()) / 2 + metrics.getAscent();
        graphics.drawString(text, valueX, valueY);
    }

    public void increment() {
        value *= 2;
    }

    public void decrement() {
        value /= 2;
    }

    @Override
    public String toString() {
        return "<" + value + ">";
    }
}
