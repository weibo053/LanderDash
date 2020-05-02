package me.themgrf.landerdash;

import me.themgrf.landerdash.displays.AltitudeDisplay;
import me.themgrf.landerdash.displays.FuelDisplay;

import javax.swing.*;
import java.net.*;

/**
 * Dashboard for the Lunar Lander
 *
 * @author alun.moon@northumbria.ac.uk
 * @author thomas.griffiths@northumbria.ac.uk
 * @version 1.1.0
 */
public class LanderDash extends JFrame implements Runnable {

    // TODO: Icons.SUCCESS (led-green.png) is never used

    // Information from Lander to Display
    public float altitude;  // height above ground
    public float fuel;      // percent fuel remaining
    public boolean flying;  // is it flying
    public boolean crashed; // crashed or not when down

    // Other information
    // float orientation; // altitude
    // float V_x; // X velocity
    // float V_y; // y velocity

    private final AltitudeDisplay alt = new AltitudeDisplay();
    private final FuelDisplay fuelpc = new FuelDisplay();
    private final DatagramPanel connection = new DatagramPanel();

    /**
     * Main method for running the dashboard
     * @param args The arguments for starting the program
     * @throws UnknownHostException If the host is unknown
     */
    public static void main(String[] args) throws UnknownHostException {
        SwingUtilities.invokeLater(LanderDash::new);
    }

    /**
     * Constructor for running the LanderDash system async
     */
    public LanderDash() {
        super("Lunar Lander Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        add(connection);
        add(fuelpc);
        add(alt);

        pack();
        setVisible(true);
        new Thread(this).start();
    }

    /**
     * Run the scheduler associated with the lander dash
     */
    public void run() {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            int portno = 65250;
            DatagramSocket socket = new DatagramSocket(portno, addr);
            connection.setAddress((InetSocketAddress) socket.getLocalSocketAddress());
            while (fuel >= 0) {
                // set up socket for reception
                if (socket != null) {
                    // start with fresh datagram packet
                    byte[] buffer = new byte[1024];
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    socket.receive(packet);
                    // extract message and pick appart into lines and key:value pairs
                    String message = new String(packet.getData());

                    String[] lines = message.trim().split("\n");
                    for (String l : lines) {
                        String[] pair = l.split(":");
                        switch (pair[0]) {
                            case "fuel":
                                if (alt.getAltitude() > 0)
                                fuelpc.setFuel(Float.parseFloat(pair[1]));
                                break;
                            case "altitude":
                                alt.setAltitude(Float.parseFloat(pair[1]));
                                break;
                            default:
                                break;
                        }
                    }
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
