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

    public TrialPane(int currWidth) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(500, 500));

        Point origin1 = new Point(20, 20);
        Point origin2 = new Point(currWidth/4, 100);

        this.gb = new GameBoard("GameBoard", origin2);
        this.st = new Settings("Settings", origin1);

        this.gb.setVerticalAlignment(JLabel.TOP);
        this.gb.setHorizontalAlignment(JLabel.CENTER);
        this.gb.setOpaque(true);
        this.gb.setBackground(Color.white);
        this.gb.setForeground(Color.black);
        this.gb.setBorder(BorderFactory.createLineBorder(Color.black));
        this.gb.setBounds(origin2.x, origin2.y, 300, 300);

        this.st.setVerticalAlignment(JLabel.CENTER);
        this.st.setHorizontalAlignment(JLabel.CENTER);
        this.st.setOpaque(true);
        this.st.setBackground(Color.white);
        this.st.setForeground(Color.black);
        this.st.setBorder(BorderFactory.createLineBorder(Color.black));
        this.st.setBounds(origin1.x, origin1.y, 100, 60);

        layeredPane.add(this.gb, 0);
        layeredPane.add(this.st, 0);

        add(Box.createRigidArea(new Dimension(0, 10)));
        add(layeredPane);
    }
}