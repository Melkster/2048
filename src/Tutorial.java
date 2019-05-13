import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;

public class Tutorial {
    private boolean swiped = false;

    public Tutorial() {
    }

    /*public void swipeUporDown() {
        int x = 50;
        int y = 50;
        int changer = 1;

        while (!(this.swiped)) {
            if (y > 100) {
                changer = -1;
            }
            else if (y <50) {
                changer = 1;
            }

            y = y + changer;

            g.drawRect(x, y, 10, 10);

            try {
                Thread.sleep(25);
            }catch(Exception e)
            {
                e.printStackStrace();
            }
        }
    }

    public void swipeLeftorRight(Graphics g) {

        int x = 50;
        int y = 50;
        int changer = 1;

        while (!(this.swiped)) {
            if (x > 100) {
                changer = -1;
            }
            else if (x <50) {
                changer = 1;
            }

            x = x + changer;

            this.repaint();

            g.drawRect(x, y, 10, 10);

            try {
                Thread.sleep(25);
            }catch(Exception e)
            {
                e.printStackStrace();
            }
        }

    }

    public void undoRedoTut() {
    }*/

    /*public static void main(String[]args) {
        JFrame testFrame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Tutorial test = new Tutorial();

        frame.getContentPane().add(test);
        frame.setSize(500,500);
        frame.setVisibility(true);

        test.swipeUporDown(frame.graphics);
    }*/
}
