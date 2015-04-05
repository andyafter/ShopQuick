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
import sg.edu.nus.iss.ussa.util.TableColumnAdjuster;




public class ProductListPanel extends javax.swing.JPanel {

    /**
     * Creates new form ProductListPanel
     */
    public ProductListPanel(Shopping shopping) {
        this.shopping = shopping;
        initComponents();
    }
    private Object[][] loadTableData(ArrayList<Product> products){
        Object[][] data =  new Object[products.size()][5];
        Product p;
        for(int i=0;i<products.size();i++){
                p = products.get(i);
                data[i][0] = p.getProductId();
                data[i][1] = p.getName();
                data[i][2] = p.getBriefDescription();
                data[i][3] = p.getPrice();
                data[i][4] = p.getQuantityAvailable();
        }
        return data;
    }
    private JPanel createTopPanel(){
        JPanel p = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel("Product List"));
        p.add("Center",panel);
        JButton b = new JButton("Refresh");
        b.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                        // TODO Auto-generated method stub
                        refreshTable();
                }
        });
        panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.add(b);
        p.add("East",panel);

        return p;
    }
    private Container createMiddlePanel(Object[][] data){
		
        tableModel = new DefaultTableModel(data,columnNames){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        productTable = new JTable(tableModel);
        productTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        setTableFormat();
        productTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        productTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

                @Override
                public void valueChanged(ListSelectionEvent arg0) {
                        // TODO Auto-generated method stub
                        if(productTable.getSelectionModel().isSelectionEmpty()){
                                modifyButton.setEnabled(false);
                                deleteButton.setEnabled(false);
                        }else{
                                modifyButton.setEnabled(true);
                                deleteButton.setEnabled(true);
                        }
                }
        });;
        productTable.setFillsViewportHeight(true);
        productTable.setAutoCreateRowSorter(true);

        JScrollPane p = new JScrollPane(productTable);

        return p;
    }
	
    private JPanel createBottomPanel(){
        JPanel p = new JPanel();
        JButton b = new JButton("Add");
        b.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                        // TODO Auto-generated method stub
                        ProdDia d = new ProdDia(shopping,"Add Product");				
                        d.setVisible(true);
                }
        });
        p.add(b);

        modifyButton = new JButton("Modify");
        modifyButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                        // TODO Auto-generated method stub
                        int rowIndex = productTable.convertRowIndexToModel(productTable.getSelectedRow());
                        int columnIndex = productTable.getColumnModel().getColumnIndex("Id");
                        String id = (String)tableModel.getValueAt(rowIndex, columnIndex);
                        //int index = Integer.parseInt(id.substring(4));
                        ProdDia d = new ProdDia(shopping,"Modify Product", id);
                        d.setVisible(true);
                }
        });
        modifyButton.setEnabled(false);
        p.add(modifyButton);

        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                        // TODO Auto-generated method stub
                        int rowIndex = productTable.convertRowIndexToModel(productTable.getSelectedRow());
                        int columnIndex = productTable.getColumnModel().getColumnIndex("Id");
                        String id = (String)tableModel.getValueAt(rowIndex, columnIndex);
                        tableModel.removeRow(rowIndex);
                        shopping.deleteProduct(id);
                        //shopping.getStore().getPm().showData();
//				deleteButton.setEnabled(false);
//				modifyButton.setEnabled(false);
                }
        });
        deleteButton.setEnabled(false);
        p.add(deleteButton);

        b = new JButton("Check Threshold");
        b.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                        // TODO Auto-generated method stub
                        shopping.getStoreWindow().changePanel("checkInventory");
                }
        });
        p.add(b);

        b = new JButton("Back");
        b.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                        // TODO Auto-generated method stub
                        shopping.getStoreWindow().changePanel("mainScreen");
                }
        });
        p.add(b);
        return p;
    }

    private void setTableFormat(){
        TableColumnAdjuster tca = new TableColumnAdjuster(productTable);
        tca.setColumnHeaderIncluded(true);
        tca.setColumnDataIncluded(true);
        tca.adjustColumns();
    }

    public void refreshTable(){
        tableModel.setDataVector(loadTableData(shopping.getProductList()), columnNames);
        tableModel.fireTableDataChanged();
        setTableFormat();
    }

    public JTable getProductTable() {
        return productTable;
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
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
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

        jButtonBack.setText("Back");

        jButtonModify.setText("Modify");

        jButtonDelete.setText("Delete");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonModify, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
    }//GEN-LAST:event_jButtonAddActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonCheck;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonModify;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableProduct;
    // End of variables declaration//GEN-END:variables

    private final String[] columnNames = {"Id","Name","Description","Price","Quantity"};
    private JButton modifyButton;
    private JButton deleteButton;
    private JTextField filterText;
    private JTable productTable;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> sorter;
    private Shopping shopping;
    
}
