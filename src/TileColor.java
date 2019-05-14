package src;

import java.awt.Color;

/*
*   Class to handle fetching of TileColors based
*   on their specific values.
*/
public class TileColor {
    /*
    *   Static function which handles the Color fetching.
    */
    public static Color tileColor(int value){

        if (value == 2) {return new Color(0xeee4da);}
        if (value == 4) {return new Color(0xede0c8);}
        if (value == 8) {return new Color(0xf2b179);}
        if (value == 16) {return new Color(0xf59563);}
        if (value == 32) {return new Color(0xf67c5f);}
        if (value == 64) {return new Color(0xf65e3b);}
        if (value == 128) {return new Color(0xedcf72);}
        if (value == 256) {return new Color(0xedcc61);}
        if (value == 512) {return new Color(0xedc850);}
        if (value == 1024) {return new Color(0xedc53f);}
        if (value == 2048) {return new Color(0xedc22e);}
        
        return Color.GRAY;
    }
}