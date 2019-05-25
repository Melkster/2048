package src;

import java.awt.*;
import javax.swing.*;

/*
*   Class Tile which is the Tile object used in the Game.
*   The base Components which determine the progress of
*   the game when merged or placed on the board.
*/
public class Tile extends JLabel implements Cloneable {

    public int value;
    public int column;
    public int row;

    /*
    *   Base Constructor
    */
    public Tile(int column, int row, int value) {
        this.value = value;
        this.column = column;
        this.row = row;
    }

    /*
    *   Standard getter function
    */
    public int getTileX(int width) {
        return (this.column*width) + (this.column*10);
    }

    /*
    *   Standard getter function
    */
    public int getTileY(int width) {
        return (this.row*width) + (this.row*10);
    }

    /*
    *   The "execute" function for the Tile.
    *   When two Tiles are merged one Tiles value
    *   is increased and the other is removed
    */
    public void increment() {
        value *= 2;
    }

    /*
    *   The "undo" function for the Tile.
    *   When an undo action occurs the Tiles value
    *   is divided and the other is returned to the Game.
    */
    public void decrement() {
        value /= 2;
    }

    /*
    *   Override function to handle Equality for the Tile object
    */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Tile) return this.value == ((Tile) o).value;
        else return false;
    }

    /*
    *   Override function to handle printout for the Tile object
    */
    @Override
    public String toString() {
        return "<" + value + ">";
    }

    /*
    *   Override function to handle Cloning for the Tile object
    */
    @Override
    public Tile clone() throws CloneNotSupportedException {
        return (Tile) super.clone();
        // return new Tile(column, row, value);
    }

    /*
    *   Function to draw out the Component and its inner Components.
    */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        int height = getHeight()/2;
        int width = getWidth()/2;

        Graphics2D g2d = (Graphics2D) g;

        int fontSize = 15;

        Font font = new Font("Serif", Font.PLAIN, fontSize);
        FontMetrics metrics = g2d.getFontMetrics();
        g2d.setFont(font);

        String text = Integer.toString(this.value);
        int valueX = (width - metrics.stringWidth(text)/2);
        int valueY = (height + metrics.getAscent()/4);

        // Draw a string such that its base line is at x, y
        g2d.drawString(text, valueX, valueY);

        setBackground(TileColor.tileColor(this.value));
    }
}
