package me.themgrf.landerdash.displays;

import me.themgrf.landerdash.Icons;

import javax.swing.*;
import javax.swing.text.*;

import java.awt.*;
import java.awt.event.*;

/**
 * Display system for showing the fuel to the user
 */
public class FuelDisplay extends JPanel {

    private final JTextField quantity;
    private final JLabel warninglight;
    private final JProgressBar gauge;

    /**
     * Constructor for creation of the fuel display
     */
    public FuelDisplay() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Fuel"));
        GridBagConstraints gc = new GridBagConstraints();

        gc.gridx = gc.gridy = 0;
        gc.gridwidth = gc.gridheight = 1;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.weightx = 1.0;
        gc.weighty = 0.1;
        add(new JLabel("Fuel Remaining:", SwingConstants.TRAILING), gc);

        gc.gridx = GridBagConstraints.RELATIVE;
        gc.weightx = 0.1;
        quantity = new JTextField(5);
        quantity.setHorizontalAlignment(JTextField.TRAILING);
        quantity.setEditable(false);
        add(quantity, gc);

        gc.gridx = 0;
        gc.gridy = GridBagConstraints.RELATIVE;
        gc.gridwidth = GridBagConstraints.REMAINDER;
        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 1.0;
        gauge = new JProgressBar();
        add(gauge, gc);

        warninglight = new JLabel("Fuel Low ", Icons.OFF, SwingConstants.LEADING);
        gc.gridx = 0;
        gc.gridy = GridBagConstraints.RELATIVE;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(warninglight, gc);
    }

    /**
     * Set the amount of fuel left in the rocket as a percentage
     * @param percent The percentage of fuel left
     */
    public void setFuel(float percent) {
        gauge.setValue((int) percent);
        quantity.setText(Float.toString(percent));
        warninglight.setIcon((percent > 10.0f) ? Icons.OFF : Icons.WARNING);
    }
}
