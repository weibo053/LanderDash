package me.themgrf.landerdash.displays;

import me.themgrf.landerdash.Icons;

import javax.swing.*;
import javax.swing.text.*;

import java.awt.*;
import java.awt.event.*;

/**
 * Display system for showing the altitude to the user
 */
public class AltitudeDisplay extends JPanel {

    private final JTextField quantity;
    private final JLabel warninglight;
    private final JLabel contactlight;
    private final JProgressBar gauge;

    private final float proximity = 15.0f;

    /**
     * Constructor for creation of the altitude display
     */
    public AltitudeDisplay() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Altitude"));
        GridBagConstraints gc = new GridBagConstraints();

        gc.gridx = 1;
        gc.gridy = 0;
        gc.gridwidth = gc.gridheight = 1;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.weightx = 0.7;
        gc.weighty = 0;
        add(new JLabel("Altitude:", SwingConstants.TRAILING), gc);

        gc.gridx = GridBagConstraints.RELATIVE;
        quantity = new JTextField(5);
        quantity.setHorizontalAlignment(JTextField.TRAILING);
        quantity.setEditable(false);
        add(quantity, gc);

        warninglight = new JLabel("Proximity ", Icons.OFF, SwingConstants.LEADING);
        gc.gridx = 1;
        gc.gridy = GridBagConstraints.RELATIVE;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(warninglight, gc);

        contactlight = new JLabel("Contact ", Icons.OFF, SwingConstants.LEADING);
        gc.gridx = 1;
        gc.gridy = GridBagConstraints.RELATIVE;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(contactlight, gc);

        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 1;
        gc.gridheight = GridBagConstraints.REMAINDER;
        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 0.1;
        gauge = new JProgressBar(SwingConstants.VERTICAL);
        add(gauge, gc);
    }

    /**
     * Set the current altitude of the rocket
     * @param alt The current altitude of the rocket
     */
    public void setAltitude(float alt) {
        if (alt < -1) return;
        gauge.setValue((int) alt / 5);
        quantity.setText(Float.toString(alt));
        warninglight.setIcon((alt > proximity) ? Icons.OFF : Icons.WARNING);
        contactlight.setIcon((alt > 1) ? Icons.OFF : Icons.CONTACT);
    }

    /**
     * Get the current altitude of the rocket from the gauge
     * @return The current altitude of the rocket
     */
    public int getAltitude() {
        return gauge.getValue();
    }
}
