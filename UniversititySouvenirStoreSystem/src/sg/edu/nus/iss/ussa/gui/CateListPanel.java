/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ussa.gui;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import sg.edu.nus.iss.ussa.application.Shopping;
import sg.edu.nus.iss.ussa.domain.Category;
import sg.edu.nus.iss.ussa.domain.Product;
import sg.edu.nus.iss.ussa.domain.Vendor;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author andypan
 */
public class CateListPanel extends javax.swing.JPanel {

    /**
     * Creates new form CategoryPanel
     */
    private Shopping shopping;
    
    public CateListPanel(Shopping shopping) {
        this.shopping = shopping;
        initComponents();
        jTableCate.setModel(new javax.swing.table.DefaultTableModel(new Object [][] {},  columnNames ) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class<?> getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        initLook();
        reloadData();
        
    }
    
        private void initLook() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CateListPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CateListPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CateListPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CateListPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    private void reloadData() {

        this.UI_CategoryList = this.shopping.getCategoryList();//Retriving Saved UI_CategoryList form File
        if (this.UI_CategoryList.isEmpty()) {
            this.UI_CategoryList = new ArrayList<Category>();
        }
        this.tableModel = (DefaultTableModel) this.jTableCate.getModel();//Creating Table model

        this.LoadTable();

    }
    private void LoadTable() {

        tableModel.setRowCount(0);
        if (this.UI_CategoryList != null) {
            if (!this.UI_CategoryList.isEmpty()) {
                for (int i = 0; i < this.UI_CategoryList.size(); i++) {
                    this.tableModel.addRow(new Object[]{this.UI_CategoryList.get(i).getCode(), this.UI_CategoryList.get(i).getName()});
                }
            }
        } else {
            System.out.println(this.UI_CategoryList);
        }

        tableModel.fireTableDataChanged();
    }
    private boolean init() {
        System.out.println("Category init Initialized!");
        this.categoryCode = this.jTextCateCode.getText().trim().toUpperCase();
        this.categoryName = this.jTextCateName.getText().trim();

        if (this.categoryName.isEmpty() || this.categoryCode.isEmpty()) {
            UIError.openDialog("Category Name or ID should not be empty.");
        } else if (this.categoryCode.length() != 3) {
            UIError.openDialog(" ID Should be 3 characters long!!");
        } else if (this.categoryName.contains(",") || this.categoryCode.contains(",")) {
            UIError.openDialog("Please avoid COMMA(,)!!");
        } else {
            return true;
        }
        return false;
    }

    private boolean validAdd() {
        boolean result = true;

        // duplicate check
        for (Category category : this.UI_CategoryList) {
            if (category.getCode().equals(this.categoryCode)) {
                result = false;
                break;
            }
        }
        return result;
    }

    private boolean validUpd() {
        String originCode = this.tableModel.getValueAt(this.jTableCate.getSelectedRow(), 0).toString();
        return (originCode.equals(this.categoryCode));
    }

    private boolean validDel(String code) {
        boolean result = true;

        // check whether any product in this category
        for (Product product : this.shopping.getProductList()) {
            if (product.getCategory().getCode().equals(code)) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCate = new javax.swing.JTable();
        jButtonAdd = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonBack = new javax.swing.JButton();
        jTextCateName = new javax.swing.JTextField();
        jTextCateCode = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButtonVendor = new javax.swing.JButton();

        jTableCate.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableCate.setColumnSelectionAllowed(true);
        jTableCate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableCateMouseClicked(evt);
            }
        });
        jTableCate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTableCateKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableCateKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTableCate);
        jTableCate.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jButtonAdd.setText("Add");
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        jButtonDelete.setText("Delete");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        jButtonBack.setText("Back");
        jButtonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBackActionPerformed(evt);
            }
        });

        jLabel1.setText("Category Code");

        jLabel2.setText("Category Name");

        jButton1.setText("Modify");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButtonVendor.setText("Manage Vendor");
        jButtonVendor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVendorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jTextCateCode, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextCateName, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButtonVendor, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextCateCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextCateName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonVendor, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 177, Short.MAX_VALUE)
                .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        // TODO add your handling code here:
        if (this.init()) {
            if (!this.validAdd()) {
                UIError.openDialog("Duplicate Category ID `" + categoryCode + "`");
            } else {
                this.shopping.addCategory(this.categoryCode, this.categoryName, new ArrayList<Vendor>());
                reloadData();
            }
        }
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jTableCateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCateMouseClicked
        // TODO add your handling code here:
        int selectedIndex = this.jTableCate.getSelectedRow();
        if (selectedIndex > -1) {
            this.jTextCateCode.setText(this.jTableCate.getValueAt(selectedIndex, 0).toString());
            this.jTextCateName.setText(this.jTableCate.getValueAt(selectedIndex, 1).toString());
        }
    }//GEN-LAST:event_jTableCateMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int selectedIndex = this.jTableCate.getSelectedRow();
        if (selectedIndex == -1 || this.jTableCate.getRowCount() == 0) {
            UIError.openDialog("Please select an item.");
        } else if (this.init()) {
            if (this.validUpd()) {
                String code = this.tableModel.getValueAt(this.jTableCate.getSelectedRow(), 0).toString();
                String name = this.jTextCateName.getText().toString();
                this.shopping.changeCate(code, name);
                reloadData();
            } else {
                UIError.openDialog("Category Code should not be changed");
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        // TODO add your handling code here:
        int selectedIndex = this.jTableCate.getSelectedRow();
        if (selectedIndex == -1 || this.jTableCate.getRowCount() == 0) {
            UIError.openDialog("Please select an item.");
        } else {
            String code = this.tableModel.getValueAt(this.jTableCate.getSelectedRow(), 0).toString();
            if (validDel(code)) {
                shopping.deleteCate(code);
                reloadData();
            } else {
                UIError.openDialog("there have product in this Category `" + code + "`, should not be deleted");
            }

        }
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jTableCateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableCateKeyPressed
        // TODO add your handling code here:
        int selectedIndex = this.jTableCate.getSelectedRow();
        if (selectedIndex > -1) {
            this.jTextCateCode.setText(this.jTableCate.getValueAt(selectedIndex, 0).toString());
            this.jTextCateName.setText(this.jTableCate.getValueAt(selectedIndex, 1).toString());
        }
    }//GEN-LAST:event_jTableCateKeyPressed

    private void jTableCateKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableCateKeyReleased
        // TODO add your handling code here:
        int selectedIndex = this.jTableCate.getSelectedRow();
        if (selectedIndex > -1) {
            this.jTextCateCode.setText(this.jTableCate.getValueAt(selectedIndex, 0).toString());
            this.jTextCateName.setText(this.jTableCate.getValueAt(selectedIndex, 1).toString());
        }
    }//GEN-LAST:event_jTableCateKeyReleased

    private void jButtonVendorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVendorActionPerformed
        // TODO add your handling code here:
        int selectedIndex = this.jTableCate.getSelectedRow();
        if (selectedIndex == -1 || this.jTableCate.getRowCount() == 0) {
            UIError.openDialog("Please select an item.");
        } else {
            VenDia vendorDlg = new VenDia(shopping, this.tableModel.getValueAt(selectedIndex, 0).toString());
            vendorDlg.setVisible(true);

        }
    }//GEN-LAST:event_jButtonVendorActionPerformed

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBackActionPerformed
        // TODO add your handling code here:
        shopping.getShopWindow().changePanel("checkOut");
    }//GEN-LAST:event_jButtonBackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonVendor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableCate;
    private javax.swing.JTextField jTextCateCode;
    private javax.swing.JTextField jTextCateName;
    // End of variables declaration//GEN-END:variables
    private final String[] columnNames = {"Category Code", "Categry Name"};
    private Shopping manager;
    private String categoryName = new String();
    private String categoryCode = new String();
    private ArrayList<Category> UI_CategoryList = new ArrayList<Category>();
    private DefaultTableModel tableModel = new DefaultTableModel();
}
