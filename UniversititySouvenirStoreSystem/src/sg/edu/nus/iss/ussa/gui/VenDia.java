package sg.edu.nus.iss.ussa.gui;

import sg.edu.nus.iss.ussa.application.Shopping;
import sg.edu.nus.iss.ussa.domain.Vendor;
import sg.edu.nus.iss.ussa.domain.Category;

import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

public class VenDia extends javax.swing.JDialog {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final String[] columnNames = new String[]{"Vendor Name", "Description"};

    private Shopping shopping;

    private String VendorName = new String();
    private String VendorDescription = new String();
    private Category selectedCategory;
    private ArrayList<Category> CategoryList = new ArrayList<Category>();
    private ArrayList<Vendor> UIVendorList = new ArrayList<Vendor>();
    private DefaultTableModel tableModel
            = new javax.swing.table.DefaultTableModel(new Object[][]{}, columnNames) {
                private static final long serialVersionUID = 1L;
                @SuppressWarnings("rawtypes")
                Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class
                };
                boolean[] canEdit = new boolean[]{
                    false, false
                };

                @SuppressWarnings({"rawtypes", "unchecked"})
                public Class getColumnClass(int columnIndex) {
                    return types[columnIndex];
                }

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            };

    public VenDia(Shopping manager, String categoryCode) {
        this.shopping = manager;

        initComponents();

        initLook();
        initData(categoryCode);

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableVendorTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextVendorName = new javax.swing.JTextField();
        jTextVendorDescription = new javax.swing.JTextField();
        jButtonAdd = new javax.swing.JButton();
        jButtonModify = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextCateName = new javax.swing.JTextField();
        jTextCateCode = new javax.swing.JTextField();
        COMBO_Category = new JComboBox<String>();

        setTitle("Vendor Manager");
        setAlwaysOnTop(true);
        setBounds(new java.awt.Rectangle(400, 100, 600, 400));
        setResizable(false);

        jTableVendorTable.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jTableVendorTable.setForeground(new java.awt.Color(51, 51, 51));
        jTableVendorTable.setModel(tableModel);
        jTableVendorTable.setSelectionBackground(new java.awt.Color(51, 51, 51));
        jTableVendorTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                T_SSA_VendorTableMouseClicked(evt);
            }
        });
        jTableVendorTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                T_SSA_VendorTableKeyPressed(evt);
            }

            public void keyReleased(java.awt.event.KeyEvent evt) {
                T_SSA_VendorTableKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTableVendorTable);

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Vendor Name");

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Description");

        jTextVendorName.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextVendorName.setForeground(new java.awt.Color(51, 51, 51));

        jTextVendorDescription.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextVendorDescription.setForeground(new java.awt.Color(51, 51, 51));

        jButtonAdd.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jButtonAdd.setForeground(new java.awt.Color(51, 51, 51));
        jButtonAdd.setText("Add New");
        jButtonAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BT_SSA_AddNewVendorMouseClicked(evt);
            }
        });

        jButtonModify.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jButtonModify.setForeground(new java.awt.Color(51, 51, 51));
        jButtonModify.setText("Update");
        jButtonModify.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BT_SSA_UpdateVendorMouseClicked(evt);
            }
        });

        jButtonDelete.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jButtonDelete.setForeground(new java.awt.Color(51, 51, 51));
        jButtonDelete.setText("Delete");
        jButtonDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BT_SSA_DeleteVendorMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel3.setText("Category Name:");

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel4.setText("Category ID:");

        jTextCateCode.setEditable(false);
        jTextCateCode.setBackground(new java.awt.Color(51, 51, 51));
        jTextCateCode.setFont(new java.awt.Font("Verdana", 2, 11)); // NOI18N
        jTextCateCode.setForeground(new java.awt.Color(0, 255, 204));
        jTextCateCode.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        jTextCateCode.setSelectionColor(new java.awt.Color(204, 204, 204));

        jTextCateName.setEditable(false);
        jTextCateName.setBackground(new java.awt.Color(51, 51, 51));
        jTextCateName.setFont(new java.awt.Font("Verdana", 2, 11)); // NOI18N
        jTextCateName.setForeground(new java.awt.Color(0, 255, 204));
        jTextCateName.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        jTextCateName.setSelectionColor(new java.awt.Color(204, 204, 204));

        COMBO_Category.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        COMBO_Category.setForeground(new java.awt.Color(51, 51, 51));
        COMBO_Category.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                COMBO_CategoryMouseClicked(evt);
            }
        });
        COMBO_Category.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                COMBO_CategoryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 864, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextCateName, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextVendorName, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextVendorDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextCateCode, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jButtonModify, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jButtonDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                                                .addComponent(COMBO_Category, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(jTextVendorName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButtonAdd)
                                .addComponent(jButtonDelete))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextVendorDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)
                                .addComponent(jButtonModify)
                                .addComponent(COMBO_Category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextCateCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)
                                .addComponent(jTextCateName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BT_SSA_AddNewVendorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BT_SSA_AddNewVendorMouseClicked
        if (this.init()) {
            if (this.validName()) {
                UIError.openDialog("Duplicate vendor name is not possible.");
            } else {
                Vendor tempVen = new Vendor(this.VendorName, this.VendorDescription);
                this.UIVendorList.add(tempVen);
                this.tableModel.addRow(new Object[]{this.VendorName, this.VendorDescription});
            }
        }
    }//GEN-LAST:event_BT_SSA_AddNewVendorMouseClicked

    private void T_SSA_VendorTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T_SSA_VendorTableMouseClicked
        int selectedIndex = this.jTableVendorTable.getSelectedRow();
        if (selectedIndex > -1) {
            this.jTextVendorName.setText(this.jTableVendorTable.getValueAt(selectedIndex, 0).toString());
            this.jTextVendorDescription.setText(this.jTableVendorTable.getValueAt(selectedIndex, 1).toString());
        }
    }//GEN-LAST:event_T_SSA_VendorTableMouseClicked

    private void T_SSA_VendorTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_T_SSA_VendorTableKeyPressed
        int selectedIndex = this.jTableVendorTable.getSelectedRow();
        if (selectedIndex > -1) {
            this.jTextVendorName.setText(this.jTableVendorTable.getValueAt(selectedIndex, 0).toString());
            this.jTextVendorDescription.setText(this.jTableVendorTable.getValueAt(selectedIndex, 1).toString());
        }
    }//GEN-LAST:event_T_SSA_VendorTableKeyPressed

    private void T_SSA_VendorTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_T_SSA_VendorTableKeyReleased
        int selectedIndex = this.jTableVendorTable.getSelectedRow();
        if (selectedIndex > -1) {
            this.jTextVendorName.setText(this.jTableVendorTable.getValueAt(selectedIndex, 0).toString());
            this.jTextVendorDescription.setText(this.jTableVendorTable.getValueAt(selectedIndex, 1).toString());
        }
    }//GEN-LAST:event_T_SSA_VendorTableKeyReleased

    private void BT_SSA_UpdateVendorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BT_SSA_UpdateVendorMouseClicked
        int selectedIndex = this.jTableVendorTable.getSelectedRow();
        if (selectedIndex == -1 || this.jTableVendorTable.getRowCount() == 0) {
            UIError.openDialog("Please select an item.");
        } else if (this.init()) {
            if (!this.validName()) {
                this.tableModel.setValueAt(this.VendorName, selectedIndex, 0);
                this.tableModel.setValueAt(this.VendorDescription, selectedIndex, 1);
               //this.UI_VendorList.clear();

            }
        }
    }//GEN-LAST:event_BT_SSA_UpdateVendorMouseClicked

    private void BT_SSA_DeleteVendorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BT_SSA_DeleteVendorMouseClicked
        int selectedIndex = this.jTableVendorTable.getSelectedRow();
        if (selectedIndex == -1 || this.jTableVendorTable.getRowCount() == 0) {
            UIError.openDialog("Please select an item.");
        } else {
            this.tableModel.removeRow(selectedIndex);
            //this.UI_VendorList.clear();

        }
    }//GEN-LAST:event_BT_SSA_DeleteVendorMouseClicked

    private void COMBO_CategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_COMBO_CategoryMouseClicked

    }//GEN-LAST:event_COMBO_CategoryMouseClicked

    private void COMBO_CategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_COMBO_CategoryActionPerformed
        String categoryCode = this.COMBO_Category.getModel().getSelectedItem().toString();
        this.jTextVendorName.setText("");
        this.jTextVendorDescription.setText("");

        this.selectedCategory = getSelectedCategory(categoryCode);
        this.jTextCateCode.setText(this.selectedCategory.getCode());
        this.jTextCateName.setText(this.selectedCategory.getName());

        //this.UI_VendorList.clear();
        this.UIVendorList = selectedCategory.getVendorList();

        if (this.UIVendorList.isEmpty()) {
            this.UIVendorList = new ArrayList<Vendor>();
        }

        this.LoadTable();

    }//GEN-LAST:event_COMBO_CategoryActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonModify;
    private javax.swing.JComboBox<String> COMBO_Category;
    private javax.swing.JTextField jTextCateCode;
    private javax.swing.JTextField jTextCateName;
    private javax.swing.JTextField jTextVendorDescription;
    private javax.swing.JTextField jTextVendorName;
    private javax.swing.JTable jTableVendorTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    private void initLook() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VenDia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VenDia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VenDia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VenDia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        setModal(true);
    }

    private void initData(String categoryCode) {

        this.CategoryList = this.shopping.getCategoryList();

        this.selectedCategory = getSelectedCategory(categoryCode);
        this.jTextCateCode.setText(selectedCategory.getCode());
        this.jTextCateName.setText(selectedCategory.getName());

        this.UIVendorList = selectedCategory.getVendorList();

        if (this.UIVendorList.isEmpty()) {
            this.UIVendorList = new ArrayList<Vendor>();
        }

        this.LoadList();
        this.tableModel = (DefaultTableModel) this.jTableVendorTable.getModel();//Creating Table model

        this.LoadTable();
    }

    private void LoadTable() {
        tableModel.setRowCount(0);
        if (this.UIVendorList != null) {
            if (!this.UIVendorList.isEmpty()) {
                for (int i = 0; i < this.UIVendorList.size(); i++) {
                    this.tableModel.addRow(new Object[]{this.UIVendorList.get(i).getName(), this.UIVendorList.get(i).getDescription()});
                }
            }
        } else {
            System.out.println("this.UI_VendorList is null");
        }
    }

    private void LoadList() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();

        for (int i = 0; i < this.CategoryList.size(); i++) {
            String code = this.CategoryList.get(i).getCode();
            model.addElement(code);
            if (this.CategoryList.get(i) == this.selectedCategory) {
                model.setSelectedItem(code);
            }

        }
        COMBO_Category.setModel(model);

    }

    private boolean init() {
        this.VendorName = this.jTextVendorName.getText().trim();
        this.VendorDescription = this.jTextVendorDescription.getText().trim();
        if (this.VendorName.isEmpty() || this.VendorDescription.isEmpty()) {
            UIError.openDialog("Vendor Name or Description should not be empty.");
        } else if (this.VendorDescription.contains(",") || this.VendorName.contains(",")) {
            UIError.openDialog("Please avoid COMMA(,)!!");
        } else {
            return true;
        }
        return false;
    }

    private boolean validName() {

        boolean result = true;

        // duplicate check
        for (Vendor vendor : this.UIVendorList) {
            if (vendor.getName().equals(this.VendorName)) {
                result = false;
                break;
            }
        }
        return result;
    }

    private Category getSelectedCategory(String code) {
        Category result = null;
        for (Category category : CategoryList) {
            if (code.equals(category.getCode())) {
                result = category;
                break;
            }
        }
        return result;

    }

    public void updVendorForCategory(String categoryCode, String oldName, String newName, String newDesc) {
        shopping.updVendorForCategory(categoryCode, oldName, newName, newDesc);
    }

}
