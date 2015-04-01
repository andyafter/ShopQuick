package sg.edu.nus.iss.ussa.gui;

public class UIError extends javax.swing.JFrame {

    public UIError() {
        initComponents();
    }

    private void initComponents() {

        L_SSA_ErrorMessage = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setTitle("Warning");
        setAlwaysOnTop(true);
        setBounds(new java.awt.Rectangle(400, 250, 0, 0));
        setResizable(false);

        L_SSA_ErrorMessage.setFont(new java.awt.Font("Verdana", 0, 14)); 
        L_SSA_ErrorMessage.setForeground(new java.awt.Color(255, 0, 0));
        L_SSA_ErrorMessage.setText("Press Ok to continue...");

        jButton1.setFont(new java.awt.Font("Verdana", 0, 14)); 
        jButton1.setForeground(new java.awt.Color(51, 51, 51));
        jButton1.setText("OK");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(L_SSA_ErrorMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(195, 195, 195)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(L_SSA_ErrorMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {
       
    }

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {
        this.setVisible(false);
    }

    public static void openDialog(String meaasge) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UIError.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UIError.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UIError.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UIError.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        UIError UIEDB = new UIError();
        UIEDB.L_SSA_ErrorMessage.setText(meaasge);
        UIEDB.setVisible(true);
        
                
    }
    private javax.swing.JLabel L_SSA_ErrorMessage;
    private javax.swing.JButton jButton1;
    
}
