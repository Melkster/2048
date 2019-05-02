import java.awt.*;

public class Tile implements CustGraphics {
    int value;

    public Tile(int value) {
        this.value = value;
    }

    public void draw(int x, int y, int width, int height, Graphics graphics) {
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

}
