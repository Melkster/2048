
import javax.swing.*;
import javax.swing.border.*;
import javax.accessibility.*;

import java.awt.*;
import java.awt.event.*;

public class HelloWorld {
    public static void main(String[] args) {
        JFrame frame = new JFrame("LayeredPaneDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LayeredPaneDemo newest = new LayeredPaneDemo();

        newest.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newest);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}