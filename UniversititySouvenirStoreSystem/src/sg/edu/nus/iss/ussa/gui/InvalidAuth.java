package sg.edu.nus.iss.ussa.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;


public class InvalidAuth extends javax.swing.JDialog {

    private JTextField alert;
    public InvalidAuth(JFrame frame, String title, String msg) {
        super(frame);
        initGUI();
        this.setTitle(title);
        alert.setText(msg);
    }

    private void initGUI() {
        try {
            {
                alert = new JTextField();
                getContentPane().add(alert, BorderLayout.CENTER);
                alert.setEditable(false);
                alert.setPreferredSize(new java.awt.Dimension(300, 100));
                alert.setHorizontalAlignment(SwingConstants.CENTER);
            }
            pack();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
