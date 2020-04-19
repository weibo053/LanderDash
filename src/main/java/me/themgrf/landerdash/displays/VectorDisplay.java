package me.themgrf.landerdash.displays;

import me.themgrf.landerdash.Icons;

import javax.swing.*;
import javax.swing.text.*;

import java.awt.*;
import java.awt.event.*;

/**
 * Display system for showing the user their current vector
 */
public class VectorDisplay extends JPanel {

    /**
     * Constructor for creation the vector display
     */
    public VectorDisplay() {
        setBorder(BorderFactory.createTitledBorder("Vectors"));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(200, 200));
        add(new JLabel("too fast", Icons.WARNING, SwingConstants.LEADING));
    }
}
