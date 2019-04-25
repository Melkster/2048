public class Tile implements CustGraphics {
    int value;

    public Tile(int value) {
        this.value = value;
    }

    public void draw() {
        // TODO 
    }

    public void increment() {
        value *= 2;
    }

    public void decrement() {
        value /= 2;
    }

}
