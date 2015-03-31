/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ussa.gui;

import sg.edu.nus.iss.ussa.domain.ShopKeeper;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

/**
 *
 * @author a0134449b
 */
public class Login extends JDialog implements ActionListener {

    JLabel jLabel1 = new JLabel();
    JTextField jTextField1 = new JTextField();
    JLabel jLabel2 = new JLabel();
    JPasswordField jPasswordField1 = new JPasswordField();
    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JPanel jPanel1 = new JPanel();
    GridLayout gridLayout1 = new GridLayout();
    BorderLayout borderLayout1 = new BorderLayout();
    JPanel jPanel2 = new JPanel();
    GridLayout gridLayout2 = new GridLayout();
    JPanel jPanel3 = new JPanel();
    JPanel jPanel4 = new JPanel();
    //JComboBox jComboBox1 = new JComboBox();
    private Border border1;
    private Border border2;
    private Border border3;
    private Component component1;
    //private boolean init = true;
    ShopKeeper user;
    FlowLayout flowLayout1 = new FlowLayout();
    JLabel jLabel3 = new JLabel();
    JLabel jLabel4 = new JLabel();
    /**
     * Creates new form Login
     */
    public Login(ShopKeeper user) {
        this.user=user;
           try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    private void jbInit() throws Exception {
        //setModal(true);
        border1 = BorderFactory.createEmptyBorder(5, 5, 0, 5);
        border2 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        border3 = BorderFactory.createEmptyBorder(5, 0, 0, 5);
        component1 = Box.createHorizontalStrut(5);
        jLabel1.setDisplayedMnemonic('N');
        jLabel1.setLabelFor(jTextField1);
        jLabel1.setText("Username" +":");
        jLabel4.setText("USSA Portal");
        jTextField1.setText("");
        jTextField1.setColumns(20);
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                jTextField1_keyTyped(e);
            }
        });
       // jTextField1.addMouseListener(new PopupMenuMouseListener());
        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                jPasswordField1_keyTyped(e);
            }
        });
       // jPasswordField1.addMouseListener(new PopupMenuMouseListener());
        jLabel2.setDisplayedMnemonic('W');
        jLabel2.setLabelFor(jPasswordField1);
        jLabel2.setText("Password" + ":");
        jButton2.setMnemonic('C');
        jButton2.setText("Cancel");
        jButton2.addActionListener(this);
        jButton1.addActionListener(this);
        jButton1.setMnemonic('O');
        jButton1.setText("OK");
        jPasswordField1.setText("");
        jPasswordField1.setColumns(20);
        //String[] userTypes = {"Employee", "Manager", "Administrator"};
        //jComboBox1.setModel(new DefaultComboBoxModel(userTypes));
        jPanel1.setLayout(gridLayout1);
        jPanel4.setLayout(borderLayout1);
        jPanel2.setLayout(gridLayout2);
        gridLayout1.setColumns(1);
        gridLayout1.setRows(3);
        gridLayout1.setVgap(5);
        gridLayout2.setColumns(1);
        gridLayout2.setRows(3);
        gridLayout2.setVgap(5);
        this.setResizable(false);
        this.setTitle("USSA Login");
        jPanel1.setBorder(border1);
        jPanel3.setBorder(border2);
        jPanel3.setLayout(flowLayout1);
        jPanel2.setBorder(border3);
        flowLayout1.setAlignment(FlowLayout.CENTER);
        flowLayout1.setHgap(0);
        flowLayout1.setVgap(0);
        jLabel3.setText("Station" +":");
        jPanel1.add(jLabel3);
        jPanel1.add(jLabel1);
        jPanel1.add(jLabel2);
        jPanel2.add(jLabel4);
        jPanel2.add(jTextField1);
        jPanel2.add(jPasswordField1);
        //jPanel2.add(jComboBox1);
        this.getContentPane().add(jPanel4);
        jPanel4.add(jPanel3, java.awt.BorderLayout.SOUTH);
        jPanel3.add(jButton1);
        jPanel3.add(component1, null);
        jPanel3.add(jButton2);
        jPanel4.add(jPanel2, java.awt.BorderLayout.CENTER);
        jPanel4.add(jPanel1, java.awt.BorderLayout.WEST);
    }

    private void login() {
        //String type = (String) jComboBox1.getSelectedItem();
        String name = jTextField1.getText().trim();

        if (jPasswordField1.getDocument() == null) {
            //setVisible(false);
            JOptionPane.showMessageDialog(this, "Please enter a password.",
                                          "Error",
                                          JOptionPane.ERROR_MESSAGE);
            //setVisible(true);
            return;
        }

        String pass = new String(jPasswordField1.getPassword()).trim();
        user = ShopKeeper.getUser(name, pass);

        if (user == null) {
            //setVisible(false);
            JOptionPane.showMessageDialog(this,
                                          "Invalid username and/or password!",
                                          "Error", JOptionPane.ERROR_MESSAGE);
            //setVisible(true);
            return;
        }

        setVisible(false);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == jButton1) {
            //String name = jTextField1.getText().trim();
            //String pass = jPasswordField1.getText().trim();
            login();
        }

        if (actionEvent.getSource() == jButton2) {
            System.exit(0);
        }
    }

    void jTextField1_keyTyped(KeyEvent e) {
        if (e.getKeyChar() == '\n') {
            //String name = jTextField1.getText().trim();
            //String pass = jPasswordField1.getText().trim();
            login();
        }
    }

    void jPasswordField1_keyTyped(KeyEvent e) {
        if (e.getKeyChar() == '\n') {
            login();
        }
    }

    public ShopKeeper getUser() {
        return user;
    }

 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
