package sg.edu.nus.iss.ussa.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;


public class InvalidAuth extends javax.swing.JDialog {

    private JTextField txtNotification;

    public InvalidAuth(JFrame frame, String title, String msg) {
        super(frame);
        initGUI();
        this.setTitle(title);
        txtNotification.setText(msg);
    }

    private void initGUI() {
        try {
            {
                System.out.println("Alert Message Pop Up Initialized!");
                txtNotification = new JTextField();
                getContentPane().add(txtNotification, BorderLayout.CENTER);
                txtNotification.setEditable(false);
                txtNotification.setPreferredSize(new java.awt.Dimension(245, 47));
                txtNotification.setHorizontalAlignment(SwingConstants.CENTER);
            }
            pack();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
