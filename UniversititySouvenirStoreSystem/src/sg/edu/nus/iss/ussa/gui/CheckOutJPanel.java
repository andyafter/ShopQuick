/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ussa.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import sg.edu.nus.iss.ussa.application.Shopping;

import sg.edu.nus.iss.ussa.domain.Customer;
import sg.edu.nus.iss.ussa.domain.Discount;
import sg.edu.nus.iss.ussa.domain.Product;
import sg.edu.nus.iss.ussa.domain.Transaction;
import sg.edu.nus.iss.ussa.domain.CartItem;
import sg.edu.nus.iss.ussa.exception.DataInputException;
import sg.edu.nus.iss.ussa.exception.DataNotFoundException;
import sg.edu.nus.iss.ussa.util.DigitDocument;
import sg.edu.nus.iss.ussa.util.Util;
import sg.edu.nus.iss.ussa.domain.Member;


/**
 *
 * @author andypan
 */
public class CheckOutJPanel extends javax.swing.JPanel {

    /**
     * Creates new form CheckOutJPanel
     */
    
    private int flowControlTag = 0;   ////Only for controlling the work flow
    private DefaultTableModel tableModel = null;
    private Product product = null;
    private DecimalFormat df = new DecimalFormat("#.00");
    //private DefaultTableModel tableModel = null;
    private Customer customer = null;
    private Discount discount;
    private int spwidth = 581;
    private int spheight = 454;
    //private int flowControlTag = 0;
    private double amount = 0;
    private String tempBarCode;
    private Vector vector = new Vector();
    //private CheckOutPanel.Listener listener = new CheckOutPanel.Listener();
    private Shopping shopping = null;
    private Transaction transaction;
    private ErrorMSG emsg = new ErrorMSG();
    private TableColumn column;
    
    
    public CheckOutJPanel(Shopping shopping) {
        this.shopping = shopping;
        initComponents();
        String[] tableTitle = { "Num", "Bar Code", "Product", "Quantity",
				"Price", "Total" };
        tableModel = new DefaultTableModel(null, tableTitle){
            public boolean isCellEditable(int row, int column)
            {
                if (column == 3)
                        return true;
                else
                        return false;
            }
        };
        checkOutTable.setModel(tableModel);
        for (int i = 0; i < checkOutTable.getColumnCount(); i++)
        {
            column = checkOutTable.getColumnModel().getColumn(i);
            if (i == 1 || i == 2)
            {
                    column.setPreferredWidth(spwidth / 4);
            } else
            {
                    column.setPreferredWidth(spheight / 8);
            }
        }
        tableModel.addTableModelListener(new TableModelListener()
        {
            @Override
            public void tableChanged(TableModelEvent e)
            {
                    int row = e.getFirstRow();
                    if (flowControlTag == 0)
                    {
                            int num = Integer.valueOf((String) tableModel.getValueAt(row, 3)).intValue();
                            transaction.getItemList().get(row).setQty(num);
                            productList();
                    }
                    setOutputValue();
            }
        });
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer()
        {
                public Component getTableCellRendererComponent(JTable table,
                                Object value, boolean isSelected, boolean hasFocus,
                                int row, int column)
                {
                        if (row % 2 == 0)
                                setBackground(Color.white);
                        else if (row % 2 == 1)
                                setBackground(new Color(206, 231, 255));
                        return super.getTableCellRendererComponent(table, value,
                                        isSelected, hasFocus, row, column);
                }
        };
        
        jTextCashPaid.setDocument(new DigitDocument());
        jTextCashPaid.getDocument().addDocumentListener(new DocumentListener()
        {
            public void insertUpdate(DocumentEvent e)
            {
                    // ������д��Ӧ�Ĵ������
                    double afterDiscount = Double.valueOf(jLabelAfterDiscount.getText()).doubleValue();
                    String cashPaid = jTextCashPaid.getText();
                    Double cashPaidDouble = Double.valueOf(cashPaid).doubleValue();
                    if (cashPaidDouble < afterDiscount)
                    {
                            jLabelErrorMSG.setText(emsg.getWrongPointForm());
                            jLabelChange.setText("**.**");

                    } else 
                    {
                            jLabelChange.setText(df.format(cashPaidDouble-afterDiscount));
                    }
            }
            public void removeUpdate(DocumentEvent e)
            {
                    double afterDiscount = Double.valueOf(
                                                    jLabelAfterDiscount.getText()).doubleValue();
                    if (jTextCashPaid.getText().length() != 0)
                    {
                        String cashPaid = jTextCashPaid.getText();
                        Double cashPaidDouble = Double.valueOf(cashPaid)
                                        .doubleValue();

                        if (cashPaidDouble < afterDiscount)
                        {
                                jLabelErrorMSG.setText(emsg.getWrongPointForm());
                                jLabelChange.setText("**.**");
                        } else
                        {
                                jLabelChange.setText(df.format(cashPaidDouble-afterDiscount));
                        }
                    }
            }
            public void changedUpdate(DocumentEvent e)
            {
                    // TODO Auto-generated method stub

            }
        });

        
        
    }
    
    public void productList()
    {	
            flowControlTag=1;
            ArrayList itemList = transaction.getItemList();
            Vector dataVector = tableModel.getDataVector();
            dataVector.clear();

            for (int i = 0;i< itemList.size();i++)
            {
                Vector subVector = new Vector();
                subVector.add(i+1);
                CartItem transactionitem = (CartItem) itemList.get(i);
                product = transactionitem.getProduct();
                subVector.add(product.getBarCodeNumber());
                subVector.add(product.getName());
                subVector.add(Integer.toString(transactionitem.getQty()));
                subVector.add(product.getPrice());
                subVector.add(transactionitem.calculateAmount());
                tableModel.addRow(subVector);
            }
            checkOutTable.validate();
            checkOutTable.repaint();
            flowControlTag=0;
            setOutputValue();
    }
    
    public void clear()
    {
        // refresh data
        {
                vector = tableModel.getDataVector();
                vector.clear();
                checkOutTable.validate();
                checkOutTable.repaint();
        }
        // refresh para
        {
                flowControlTag = 0;
                transaction.getDiscount().getPercent();
        }
        // refresh UI
        {
                jLabelTotalMoney.setText(Double.toString(transaction.calcTotalPrice()));
                jLabelAfterDiscount.setText(Double.toString(transaction.calcDiscountPrice()));
                jLabelMemberPoints.setText("0");
                jLabelAfterDiscount.setText(Double.toString(transaction.calcRest()));
                jLabelChange.setText(Double.toString(transaction.calcChange()));
                jTextBarCode.setText(null);
                jLabelMemberID.setText("Member ID");
                jLabelErrorMSG.setText(null);
                jTextCashPaid.setText(null);
                jTextUsedPoints.setText(null);
                
        }
    }
    
    public void setOutputValue()
    {
            jLabelTotalMoney.setText(df.format(transaction.calcTotalPrice()));
            //JlDiscountNum.setText(Double.toString(transaction.getDiscount().getPercent()));
            jLabelAfterDiscount.setText(df.format(transaction.calcDiscountPrice()));
    }

    public void addProduct(ArrayList<CartItem> arrayList,int qty)
    {
            int productAddFlag = -1;
            for (int m = 0;m < arrayList.size();m++)
            {
                    if(arrayList.get(m).getProduct()==product)
                    {
                            productAddFlag = m;
                    }
            }
            if (productAddFlag==-1)
            {
                    arrayList.add(new CartItem(product,product.getPrice(),qty));
            }
            else
            {
                    CartItem tempTransactionItem = arrayList.get(productAddFlag);
                    tempTransactionItem.setQty(tempTransactionItem.getQty()+qty);
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

        jScrollPane2 = new javax.swing.JScrollPane();
        checkOutTable = new javax.swing.JTable();
        jTextBarCode = new javax.swing.JTextField();
        jTextFieldQuantity = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabelMemberID = new javax.swing.JLabel();
        jButtonAddProduct = new javax.swing.JButton();
        jTextMemberID = new javax.swing.JTextField();
        jButtonSubmitMem = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabelTotalMoney = new javax.swing.JLabel();
        jTextCashPaid = new javax.swing.JTextField();
        jTextUsedPoints = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabelAfterDiscount = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButtonDelete = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabelChange = new javax.swing.JLabel();
        jButtonClear = new javax.swing.JButton();
        jButtonCheckOut = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        memberNameLabel = new javax.swing.JLabel();
        jLabelErrorMSG = new javax.swing.JLabel();
        jLabelMemberPoints = new javax.swing.JLabel();

        checkOutTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        checkOutTable.setColumnSelectionAllowed(true);
        jScrollPane2.setViewportView(checkOutTable);
        checkOutTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        jTextBarCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextBarCodeActionPerformed(evt);
            }
        });

        jTextFieldQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldQuantityActionPerformed(evt);
            }
        });

        jLabel1.setText("Bar Code:");

        jLabel2.setText("Quantity:");

        jLabelMemberID.setText("Member ID:");

        jButtonAddProduct.setText("Enter");
        jButtonAddProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddProductActionPerformed(evt);
            }
        });

        jTextMemberID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextMemberIDActionPerformed(evt);
            }
        });

        jButtonSubmitMem.setText("Submit");
        jButtonSubmitMem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSubmitMemActionPerformed(evt);
            }
        });

        jLabel4.setText("Total:");

        jLabelTotalMoney.setText("00.00");

        jTextCashPaid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextCashPaidActionPerformed(evt);
            }
        });

        jTextUsedPoints.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextUsedPointsActionPerformed(evt);
            }
        });

        jLabel6.setText("After Discount:");

        jLabelAfterDiscount.setText("00.00");

        jLabel8.setText("Cash Paid:");

        jButtonDelete.setText("Delete");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        jLabel9.setText("Change:");

        jLabelChange.setText("00.00");

        jButtonClear.setText("Clear");
        jButtonClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearActionPerformed(evt);
            }
        });

        jButtonCheckOut.setText("CHECK OUT");
        jButtonCheckOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCheckOutActionPerformed(evt);
            }
        });

        jLabel5.setText("Used Points:");

        jLabelMemberPoints.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextBarCode, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jTextFieldQuantity)))
                    .addComponent(jButtonAddProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextMemberID)
                    .addComponent(jButtonSubmitMem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelMemberID)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(memberNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelMemberPoints, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelChange))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelTotalMoney, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelAfterDiscount, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jButtonClear, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButtonDelete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jButtonCheckOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(37, 37, 37)
                                        .addComponent(jLabelErrorMSG, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextCashPaid, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jTextUsedPoints, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 9, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextBarCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jButtonAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabelMemberID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(memberNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabelMemberPoints))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextMemberID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSubmitMem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabelTotalMoney))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabelAfterDiscount))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextCashPaid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextUsedPoints, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabelChange))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelErrorMSG, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonCheckOut, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                    .addComponent(jButtonClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 62, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldQuantityActionPerformed

    private void jButtonAddProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddProductActionPerformed
        // TODO add your handling code here:
        flowControlTag = 1;
        tempBarCode = jTextBarCode.getText();
        product = shopping.getProductByBarCode(tempBarCode);
        String quan = jTextFieldQuantity.getText();
        if (tempBarCode.length() == 0)
        {
                jLabelErrorMSG.setText(emsg.getWrongBarCode());
        } else if (quan.length() == 0)
        {
                jLabelErrorMSG.setText(emsg.getWrongQuantityForm());
        } else if (Integer.valueOf(jTextFieldQuantity.getText()).intValue() < 1)
        {
                jLabelErrorMSG.setText(emsg.getWrongQuantityForm());
        } else
        {
                int intqty = Integer.parseInt(quan);

                if (jLabelErrorMSG.getText() == emsg.getNonExistingProd()
                                || jLabelErrorMSG.getText() == emsg.getWrongBarCode()
                                || jLabelErrorMSG.getText() == emsg.getQuantityNotEnough()
                                || jLabelErrorMSG.getText() == emsg.getWrongQuantityForm())
                {
                        jLabelErrorMSG.setText(null);
                }
                if (product == null)
                {
                        jLabelErrorMSG.setText(emsg.getNonExistingProd() );
                        return;
                }
                ArrayList<CartItem> tempTransactionList = transaction.getItemList();
                addProduct(tempTransactionList,intqty);
                productList();
                jTextBarCode.setText(null);
                jTextFieldQuantity.setText(null);
        }
        flowControlTag = 0;
        setOutputValue();
        
    }//GEN-LAST:event_jButtonAddProductActionPerformed

    private void jTextMemberIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextMemberIDActionPerformed
        // TODO add your handling code here
    }//GEN-LAST:event_jTextMemberIDActionPerformed

    private void jButtonSubmitMemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSubmitMemActionPerformed
        // TODO add your handling code here:
        String MemberID = jTextMemberID.getText();
        if (jTextMemberID.getText().length()==0)
        {
                jLabelErrorMSG.setText(emsg.getNonExistingMem());
        }
        else
        {
            try
            {
                    transaction = shopping.setBillCustomer(transaction, MemberID);
                    jLabelMemberID.setText(((Member) transaction.getCustomer()).getName());
                    StringBuilder sb = new StringBuilder();
                    sb.append("");
                    sb.append(((Member) transaction.getCustomer()).getLoyaltyPoint());
                    String strI = sb.toString();
                    jLabelMemberPoints.setText(strI);
            }
            catch (NullPointerException e2)
            {
                    jLabelErrorMSG.setText( emsg.getNonExistingMem());
            }
        }
        
        //jLabelMemberID.setText("andy");
    }//GEN-LAST:event_jButtonSubmitMemActionPerformed

    private void jTextUsedPointsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextUsedPointsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextUsedPointsActionPerformed

    private void jTextBarCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextBarCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextBarCodeActionPerformed

    private void jButtonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_jButtonClearActionPerformed

    private void jTextCashPaidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextCashPaidActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextCashPaidActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        // TODO add your handling code here:
                // TODO add your handling code here:
        if (checkOutTable.getSelectedRow() == -1){
                jLabelErrorMSG.setText("Select a row");
        } else{
                if (jLabelErrorMSG.getText() == emsg.getSelectRow()){
                        jLabelErrorMSG.setText(null);
                }
                int rowcount = tableModel.getRowCount();
                if (rowcount > 0){
                        transaction.getItemList().remove(checkOutTable.getSelectedRow());
                        productList();
                }
                checkOutTable.revalidate();
                setOutputValue();
        }
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonCheckOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCheckOutActionPerformed
        // TODO add your handling code here:
        try
        {
                if (jLabelAfterDiscount.getText().length()!=0)
                transaction.setCashAmount(Util.castDouble(jLabelAfterDiscount.getText()));
                if (discount.getPercent()!=0)
                transaction.setDiscount(discount);
                if (jTextCashPaid.getText().length()!=0)
                transaction.setRedeemedLoyaltyPoint(Util.castInt(jTextCashPaid.getText()));
        } catch (DataInputException e1)
        {
                // TODO Auto-generated catch block
                e1.printStackTrace();
        }
    }//GEN-LAST:event_jButtonCheckOutActionPerformed

    public void setTransaction(Transaction transaction)
    {
            this.transaction = transaction;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable checkOutTable;
    private javax.swing.JButton jButtonAddProduct;
    private javax.swing.JButton jButtonCheckOut;
    private javax.swing.JButton jButtonClear;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonSubmitMem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAfterDiscount;
    private javax.swing.JLabel jLabelChange;
    private javax.swing.JLabel jLabelErrorMSG;
    private javax.swing.JLabel jLabelMemberID;
    private javax.swing.JLabel jLabelMemberPoints;
    private javax.swing.JLabel jLabelTotalMoney;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextBarCode;
    private javax.swing.JTextField jTextCashPaid;
    private javax.swing.JTextField jTextFieldQuantity;
    private javax.swing.JTextField jTextMemberID;
    private javax.swing.JTextField jTextUsedPoints;
    private javax.swing.JLabel memberNameLabel;
    // End of variables declaration//GEN-END:variables
}
