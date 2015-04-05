package sg.edu.nus.iss.ussa.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class AlertMsgPopUp extends javax.swing.JDialog {

    private JTextField txtNotification;

    public AlertMsgPopUp(JFrame frame, String title, String msg) {
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
