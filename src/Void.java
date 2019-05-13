package src;

public class Void extends Tile {

    public Void(int column, int row) {
        super(column, row, 0);
    }

    public void draw() {}

    public void increment() {}

    public void decrement() {}

    @Override
    public String toString() {
        return "[ ]";
    }
}
