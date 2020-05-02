package me.themgrf.landerdash;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.border.*;

import java.net.*;
import java.io.*;

/**
 * Place holder for ip-address and port number for internet addressing
 */
public class DatagramPanel extends JPanel {

    private final JTextField addressname, port;

    /**
     * Constructor for creation of the datagram panel
     */
    public DatagramPanel() {
        // Create a JPanel populated with border and text fields
        super(new FlowLayout(FlowLayout.LEFT, 5, 0));
        setBorder(BorderFactory.createTitledBorder("Socket Address"));
        add(new JLabel("IP:"));
        addressname = new JTextField(10);
        addressname.setEditable(false);
        add(addressname);
        add(new JLabel("port:"));
        port = new JTextField(5);
        port.setEditable(false);
        add(port);
    }

    /**
     * Set the current hostname address
     * @param address The current hostname address
     */
    public void setAddress(InetSocketAddress address) {
        addressname.setText(address.getHostString());
        port.setText(Integer.toString(address.getPort()));
    }
}
