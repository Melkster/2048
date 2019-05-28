package src;

import java.awt.event.*;
import javax.swing.Timer;
import javax.swing.JFrame;
import java.awt.*;

public class AnimatedActionListener implements ActionListener {
    private int x;
    private int y;
    private int valAdd = 1;

    private Arrow arrow;
    private Timer timer;
    private LayoutHandler lh;
    private TutorialScreen tutS;

    private int LIMIT;

    private int startX;
    private int startY;

    private int currCount;

    public AnimatedActionListener(TutorialScreen theTutS, LayoutHandler lha) {
        this.lh = lha;
        this.tutS = theTutS;

        this.arrow = this.lh.getArrow();

        this.x = this.arrow.getX();
        this.y = this.arrow.getY();

        this.startX = this.x;

        this.currCount = 0;

        this.LIMIT = this.arrow.getWidth()/2;

        this.lh.setArrowActive();
    }

    public void setTimer(Timer time) {
        this.timer = time;
    }

    public void actionPerformed(ActionEvent evt) {
        this.x = this.arrow.getX();
        this.y = this.arrow.getY();

        this.startX = this.x - this.currCount;
        this.LIMIT = this.arrow.getWidth()/2;

        System.out.println(this.currCount);

        if (valAdd < 0) {
            this.currCount--;
        }
        else if (valAdd > 0) {
            this.currCount++;
        }

        this.x += valAdd;

        if (x > startX + LIMIT) {
            valAdd = -1;
        }
        else if (x < startX) {
            valAdd = 1;
        }

        this.lh.changeArrowPos(this.x,this.y);

        if (this.tutS.getPhase() != 1){
            System.out.println("I Exit");
            this.timer.stop();
            this.lh.disableArrowActive();
        }
    }
}