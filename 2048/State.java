import java.util.*;

class State {
    private ArrayList<ArrayList<Tile>> layout;

    public Tile getTile(int x, int y) {
        return layout.get(y).get(x);
    }

    public boolean addTile(int x, int y, Tile tile) {
        if (layout.get(y).get(x) instanceof Void) {
            return layout.get(y).add(tile);
        } else {
            return false;
        }
    }

    public boolean removeTile(int x, int y) {
        Tile tile = layout.get(y).get(x);

        if (tile instanceof Void) {
            return false;
        } else {
            return layout.get(y).remove(tile);
        }
    }
}
