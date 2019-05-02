import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.border.*;
import javax.accessibility.*;

import java.awt.*;
import java.awt.event.*;

public class TrialPane extends JPanel {
    
    private JLayeredPane layeredPane;
    private GameBoard gb;
    private Settings st;

    private JComponent background;

    public TrialPane(int currWidth) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(500, 500));

        Point origin1 = new Point(15, 15);
        Point origin2 = new Point(currWidth/2-150, 100);

        this.gb = new GameBoard("GameBoard", origin2);
        this.st = new Settings("Settings", origin1);

        this.gb.setVerticalAlignment(JLabel.TOP);
        this.gb.setHorizontalAlignment(JLabel.CENTER);
        this.gb.setOpaque(true);
        this.gb.setBackground(Color.white);
        this.gb.setForeground(Color.black);
        this.gb.setBorder(BorderFactory.createLineBorder(Color.black));
        this.gb.setBounds(currWidth/2-200, origin2.y, 500, 500);

        this.st.setVerticalAlignment(JLabel.CENTER);
        this.st.setHorizontalAlignment(JLabel.CENTER);
        this.st.setOpaque(true);
        this.st.setBackground(Color.white);
        this.st.setForeground(Color.black);
        this.st.setBorder(BorderFactory.createLineBorder(Color.black));
        this.st.setBounds(origin1.x, origin1.y, 60, 60);

        JLabel test = new JLabel();
        test.setOpaque(true);
        test.setBackground(new Color(0,0,0,90));
        test.setForeground(Color.black);
        test.setBorder(BorderFactory.createLineBorder(Color.black));
        test.setBounds(origin2.x+40, origin2.y+40, 30, 30);

        layeredPane.add(this.gb, 4);
        layeredPane.add(this.st, 4);
        layeredPane.add(test, 0);

        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                // This is only called when the user releases the mouse button.
                System.out.println("componentResized");
                int width = getWidth();
                int height = getHeight();
                int newGBSize;
                int newSTSize;

                if (width > height) {
                    newGBSize = ((height/4)*3);
                    newSTSize = height/10;
                }
                else {
                    newGBSize = ((width/4)*3);
                    newSTSize = width/10;
                }

                gb.setBounds(width/2-(newGBSize/2), height/2-(newGBSize/2), newGBSize, newGBSize);
                st.setBounds(width/2-(newGBSize/2), height/2-(newGBSize/2)+newGBSize, newGBSize/2, newSTSize);
            }
        });

        add(Box.createRigidArea(new Dimension(0, 10)));
        add(layeredPane);
    }
}