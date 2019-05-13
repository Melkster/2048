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

    public Color tileColor(Graphics g){

        if (value == 2) {
            return new Color(0xeee4da);
        }
        if (value == 4) {
            return new Color(0xede0c8);
        }
        if (value == 8) {
            return new Color(0xf2b179);
        }
        if (value == 16) {
            return new Color(0xf59563);
        }
        if (value == 32) {
            return new Color(0xf67c5f);
        }
        if (value == 64) {
            return new Color(0xf65e3b);
        }
        if (value == 128) {
            return new Color(0xedcf72);
        }
        if (value == 256) {
            return new Color(0xedcc61);
        }
        if (value == 512) {
            return new Color(0xedc850);
        }
        if (value == 1024) {
            return new Color(0xedc53f);
        }
        if (value == 2048) {
            return new Color(0xedc22e);
        }
        return Color.GRAY;

    }


}
