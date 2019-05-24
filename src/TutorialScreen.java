package src;

import javax.swing.*;
import java.awt.*;

import java.awt.event.*;

public class TutorialScreen extends JLabel {
    private Settings st;

    private int phase;

    private static int num_phases = 5;

    public TutorialScreen(Settings settings) {
        this.st = settings;
        this.phase = 0;
    }

    public int getPhase() {
        return this.phase;
    }

    public static int getNumPhases() {
        return num_phases;
    }

    public void nextPhase() {
        if (this.phase < num_phases) {
            this.phase++;
        }
        else {
            this.phase = 0;
        }
    }

    /*public void checkPhase(int keycode, Game gm, LayoutHandler lh, Tutorial tut, CommandManager cm) {
        switch (this.phase) {
            case 1:
                if (keycode == KeyEvent.VK_RIGHT) {
                    gm.move(Direction.RIGHT);
                    lh.animateTile();
                    nextPhase();
                }
                else if (keycode == KeyEvent.VK_LEFT) {
                    gm.move(Direction.LEFT);
                    lh.animateTile();
                    nextPhase();
                }
                break;
            case 2:
                if (keycode == KeyEvent.VK_ENTER) {
                    nextPhase();
                }
                break;
            case 3:
                if (keycode == KeyEvent.VK_U) {
                    cm.undoCommand();
                    cm.undoCommand();
                    lh.animateTile();
                    lh.animateTile(); // Without running this twice, sometimes not all tiles are drawn on undo
                    nextPhase();
                }
                break;
            case 4:
                if (keycode == KeyEvent.VK_R) {
                    cm.redoCommand();
                    cm.redoCommand();
                    lh.animateTile();
                    nextPhase();
                }
                break;
            case 5:
                if (keycode == KeyEvent.VK_ENTER) {
                    nextPhase();
                }
                break;
        }
        if (this.phase == 0) {
            tut.changeActive();
            lh.disableTutorialActive();
        }
    }*/

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = this.getWidth();
        int height = this.getHeight();

        Graphics2D g2d = (Graphics2D) g;
        
        // Set the desired font if different from default font
        g2d.setFont(new Font("Serif", Font.PLAIN, 20));

        String txt1 = this.st.getLookup().lookupLang(this.st.getLanguage(), "tut");

        FontMetrics metrics = g2d.getFontMetrics();
        int valueX1 = (width/2 - metrics.stringWidth(txt1)/2);
        int valueY1 = 20;

        g2d.drawString(txt1, valueX1, valueY1);

        g2d.setFont(new Font("Serif", Font.PLAIN, 15));

        String txt2 = "";

        switch (this.phase) {
            case 1:
                txt2 = this.st.getLookup().lookupLang(this.st.getLanguage(), "info1");
                break;
            case 2:
                txt2 = this.st.getLookup().lookupLang(this.st.getLanguage(), "info2");
                break;
            case 3:
                txt2 = this.st.getLookup().lookupLang(this.st.getLanguage(), "info3");
                break;
            case 4:
                txt2 = this.st.getLookup().lookupLang(this.st.getLanguage(), "info4");
                break;
            case 5:
                txt2 = this.st.getLookup().lookupLang(this.st.getLanguage(), "info5");
            default:
                break;
        }

        int middle_len = txt2.length()/2;
        if (middle_len > 0) {
            while (!(txt2.charAt(middle_len) == ' ')) {
                middle_len++;
            }
        }

        String txt3 = txt2.substring(0,middle_len);
        String txt4 = txt2.substring(middle_len);

        int valueX2 = (width/2 - (metrics.stringWidth(txt3)/2)) + 20;
        int valueY2 = 40;
        int valueX3 = (width/2 - (metrics.stringWidth(txt4)/2)) + 20;
        int valueY3 = 60;

        g2d.drawString(txt3, valueX2, valueY2);
        g2d.drawString(txt4, valueX3, valueY3);
       
    }
}
