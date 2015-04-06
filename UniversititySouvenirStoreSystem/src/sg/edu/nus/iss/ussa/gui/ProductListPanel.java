/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ussa.gui;

/**
 *
 * @author andypan
 */
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import sg.edu.nus.iss.ussa.domain.*;
import sg.edu.nus.iss.ussa.application.*;
import sg.edu.nus.iss.ussa.util.TableUtil;

public class ProductListPanel extends javax.swing.JPanel {

    /**
     * Creates new form ProductListPanel
     */
    public ProductListPanel(Shopping shopping) {
        this.shopping = shopping;
        initComponents();
        //setLayout(new BorderLayout());
        //add("West",createMiddlePanel(loadTableData(shopping.getProductList())));
        //createMiddlePanel(loadTableData(shopping.getProductList()));
        Object[][] data = loadTableData(shopping.getProductList());
        tableModel = new DefaultTableModel(data, columnNames) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        jTableProduct.setModel(tableModel);
        jTableProduct.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        setTableFormat();
        jTableProduct.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTableProduct.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (jTableProduct.getSelectionModel().isSelectionEmpty()) {
                    jButtonModify.setEnabled(false);
                    jButtonDelete.setEnabled(false);
                } else {
                    jButtonModify.setEnabled(true);
                    jButtonDelete.setEnabled(true);
                }
            }
        });;
        jTableProduct.setFillsViewportHeight(true);
        jTableProduct.setAutoCreateRowSorter(true);
        //JScrollPane p = new JScrollPane(jTableProduct);
        refreshList();
        setVisible(true);
    }

    private Object[][] loadTableData(ArrayList<Product> products) {
        Object[][] data = new Object[products.size()][5];
        Product p;
        for (int i = 0; i < products.size(); i++) {
            p = products.get(i);
            data[i][0] = p.getProductId();
            data[i][1] = p.getProductName();
            data[i][2] = p.getDescription();
            data[i][3] = p.getPrice();
            data[i][4] = p.getQuantity();
        }
        return data;
    }

    private void setTableFormat() {
        TableUtil tca = new TableUtil(jTableProduct);
        tca.setColumnHeaderIncluded(true);
        tca.setColumnDataIncluded(true);
        tca.adjustColumns();
    }

    public void refreshList() {
        tableModel.setDataVector(loadTableData(shopping.getProductList()), columnNames);
        tableModel.fireTableDataChanged();
        setTableFormat();
    }

    public JTable getProductTable() {
        return jTableProduct;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
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
        jTableProduct = new javax.swing.JTable();
        jButtonAdd = new javax.swing.JButton();
        jButtonCheck = new javax.swing.JButton();
        jButtonBack = new javax.swing.JButton();
        jButtonModify = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();

        jTableProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableProduct.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(jTableProduct);
        jTableProduct.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        jButtonAdd.setText("Add");
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        jButtonCheck.setText("Check");
        jButtonCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCheckActionPerformed(evt);
            }
        });

        jButtonBack.setText("Back");
        jButtonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBackActionPerformed(evt);
            }
        });

        jButtonModify.setText("Modify");
        jButtonModify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModifyActionPerformed(evt);
            }
        });

        jButtonDelete.setText("Delete");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonModify, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonModify, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        // TODO add your handling code here:
        ProdDia d = new ProdDia(shopping, "Add Product");
        d.setVisible(true);
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModifyActionPerformed
        // TODO add your handling code here:
        int r = jTableProduct.convertRowIndexToModel(jTableProduct.getSelectedRow());
        int c = jTableProduct.getColumnModel().getColumnIndex("Id");
        String id = (String) tableModel.getValueAt(r, c);
        Object[][] data = loadTableData(shopping.getProductList());
        //int index = Integer.parseInt(id.substring(4));
        ProdDia d = new ProdDia(shopping, "Modify Product", id);
        d.setVisible(true);
    }//GEN-LAST:event_jButtonModifyActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        // TODO add your handling code here:
        int rowIndex = jTableProduct.convertRowIndexToModel(jTableProduct.getSelectedRow());
        int columnIndex = jTableProduct.getColumnModel().getColumnIndex("Id");
        String id = (String) tableModel.getValueAt(rowIndex, columnIndex);
        tableModel.removeRow(rowIndex);
        shopping.deleteProduct(id);
        refreshList();
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCheckActionPerformed
        // TODO add your handling code here:
        shopping.getShopWindow().changePanel("checkInventory");
    }//GEN-LAST:event_jButtonCheckActionPerformed

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBackActionPerformed
        // TODO add your handling code here:
        shopping.getShopWindow().changePanel("checkOut");
    }//GEN-LAST:event_jButtonBackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonCheck;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonModify;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableProduct;
    // End of variables declaration//GEN-END:variables

    private final String[] columnNames = {"Id", "Name", "Description", "Price", "Quantity"};
    private JTextField filterText;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> sorter;
    private Shopping shopping;

}
