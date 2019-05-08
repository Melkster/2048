package src;

public class Void extends Tile {

    public Void(int column, int row) {
        super(0, column, row);
    }

    public void draw() {}

    public void increment() {}

    public void decrement() {}

    @Override
    public String toString() {
        return "(" + Integer.toString(column) + ", " + Integer.toHexString(row) + ")";
    }
}
