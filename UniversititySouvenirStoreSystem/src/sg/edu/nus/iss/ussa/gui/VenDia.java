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
	private final String[] columnNames = new String [] {"Vendor Name", "Description"};
			
	private Shopping manager;

    private String VendorName = new String();
    private String VendorDescription  = new String();
    private Category selectedCategory;
    private ArrayList<Category> CategoryList = new ArrayList<Category>();
    private ArrayList<Vendor> UI_VendorList = new ArrayList<Vendor>();
    private DefaultTableModel tableModel = 
    		new javax.swing.table.DefaultTableModel(new Object [][] { },columnNames) {
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

        @SuppressWarnings({ "rawtypes", "unchecked" })
		public Class getColumnClass(int columnIndex) {
            return types [columnIndex];
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    };
    
    public VenDia(Shopping manager, String categoryCode) {
    	this.manager = manager;
    	
        initComponents();
        
        initLook();
        initData(categoryCode);
        
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        T_SSA_VendorTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TF_SSA_VendorName = new javax.swing.JTextField();
        TF_SSA_VendorDescription = new javax.swing.JTextField();
        BT_SSA_AddNewVendor = new javax.swing.JButton();
        BT_SSA_UpdateVendor = new javax.swing.JButton();
        BT_SSA_DeleteVendor = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        TF_CategoryName = new javax.swing.JTextField();
        TF_CategoryCode = new javax.swing.JTextField();
        COMBO_Category = new JComboBox<String>();

        setTitle("Vendor Manager");
        setAlwaysOnTop(true);
        setBounds(new java.awt.Rectangle(310, 100, 600, 400));
        setResizable(false);

        T_SSA_VendorTable.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        T_SSA_VendorTable.setForeground(new java.awt.Color(51, 51, 51));
        T_SSA_VendorTable.setModel(tableModel);
        T_SSA_VendorTable.setSelectionBackground(new java.awt.Color(51, 51, 51));
        T_SSA_VendorTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                T_SSA_VendorTableMouseClicked(evt);
            }
        });
        T_SSA_VendorTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                T_SSA_VendorTableKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                T_SSA_VendorTableKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(T_SSA_VendorTable);

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Vendor Name");

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Description");

        TF_SSA_VendorName.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        TF_SSA_VendorName.setForeground(new java.awt.Color(51, 51, 51));

        TF_SSA_VendorDescription.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        TF_SSA_VendorDescription.setForeground(new java.awt.Color(51, 51, 51));

        BT_SSA_AddNewVendor.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        BT_SSA_AddNewVendor.setForeground(new java.awt.Color(51, 51, 51));
        BT_SSA_AddNewVendor.setText("Add New");
        BT_SSA_AddNewVendor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BT_SSA_AddNewVendorMouseClicked(evt);
            }
        });

        BT_SSA_UpdateVendor.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        BT_SSA_UpdateVendor.setForeground(new java.awt.Color(51, 51, 51));
        BT_SSA_UpdateVendor.setText("Update");
        BT_SSA_UpdateVendor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BT_SSA_UpdateVendorMouseClicked(evt);
            }
        });

        BT_SSA_DeleteVendor.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        BT_SSA_DeleteVendor.setForeground(new java.awt.Color(51, 51, 51));
        BT_SSA_DeleteVendor.setText("Delete");
        BT_SSA_DeleteVendor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BT_SSA_DeleteVendorMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel3.setText("Category Name:");

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel4.setText("Category ID:");
        
        TF_CategoryCode.setEditable(false);
        TF_CategoryCode.setBackground(new java.awt.Color(51, 51, 51));
        TF_CategoryCode.setFont(new java.awt.Font("Verdana", 2, 11)); // NOI18N
        TF_CategoryCode.setForeground(new java.awt.Color(0, 255, 204));
        TF_CategoryCode.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        TF_CategoryCode.setSelectionColor(new java.awt.Color(204, 204, 204));

        TF_CategoryName.setEditable(false);
        TF_CategoryName.setBackground(new java.awt.Color(51, 51, 51));
        TF_CategoryName.setFont(new java.awt.Font("Verdana", 2, 11)); // NOI18N
        TF_CategoryName.setForeground(new java.awt.Color(0, 255, 204));
        TF_CategoryName.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        TF_CategoryName.setSelectionColor(new java.awt.Color(204, 204, 204));

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
                    .addComponent(TF_CategoryName, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TF_SSA_VendorName, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TF_SSA_VendorDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TF_CategoryCode, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(BT_SSA_UpdateVendor, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BT_SSA_AddNewVendor, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BT_SSA_DeleteVendor, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
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
                    .addComponent(TF_SSA_VendorName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BT_SSA_AddNewVendor)
                    .addComponent(BT_SSA_DeleteVendor))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TF_SSA_VendorDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(BT_SSA_UpdateVendor)
                    .addComponent(COMBO_Category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TF_CategoryCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(TF_CategoryName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BT_SSA_AddNewVendorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BT_SSA_AddNewVendorMouseClicked
         if(this.init())
        {        
            if(this.validName()){
            	UIError.openDialog("Duplicate vendor name is not possible.");
            }
            else
            {
                Vendor tempVen = new Vendor(this.VendorName,this.VendorDescription);
                this.UI_VendorList.add(tempVen);
                this.tableModel.addRow(new Object[]{this.VendorName,this.VendorDescription});
            }        
        }
    }//GEN-LAST:event_BT_SSA_AddNewVendorMouseClicked

    private void T_SSA_VendorTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T_SSA_VendorTableMouseClicked
       int selectedIndex = this.T_SSA_VendorTable.getSelectedRow();
       if(selectedIndex > -1)
       {
        this.TF_SSA_VendorName.setText(this.T_SSA_VendorTable.getValueAt(selectedIndex, 0).toString());
        this.TF_SSA_VendorDescription.setText(this.T_SSA_VendorTable.getValueAt(selectedIndex, 1).toString());
       }
    }//GEN-LAST:event_T_SSA_VendorTableMouseClicked

    private void T_SSA_VendorTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_T_SSA_VendorTableKeyPressed
       int selectedIndex = this.T_SSA_VendorTable.getSelectedRow();
       if(selectedIndex > -1)
       {
        this.TF_SSA_VendorName.setText(this.T_SSA_VendorTable.getValueAt(selectedIndex, 0).toString());
        this.TF_SSA_VendorDescription.setText(this.T_SSA_VendorTable.getValueAt(selectedIndex, 1).toString());
       }
    }//GEN-LAST:event_T_SSA_VendorTableKeyPressed

    private void T_SSA_VendorTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_T_SSA_VendorTableKeyReleased
        int selectedIndex = this.T_SSA_VendorTable.getSelectedRow();
       if(selectedIndex > -1)
       {
        this.TF_SSA_VendorName.setText(this.T_SSA_VendorTable.getValueAt(selectedIndex, 0).toString());
        this.TF_SSA_VendorDescription.setText(this.T_SSA_VendorTable.getValueAt(selectedIndex, 1).toString());
       }
    }//GEN-LAST:event_T_SSA_VendorTableKeyReleased

    private void BT_SSA_UpdateVendorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BT_SSA_UpdateVendorMouseClicked
        int selectedIndex = this.T_SSA_VendorTable.getSelectedRow();
       if(selectedIndex == -1 || this.T_SSA_VendorTable.getRowCount() == 0)
           UIError.openDialog("Please select an item.");
       else if(this.init())
       {              
           if(!this.validName()){
               this.tableModel.setValueAt(this.VendorName,selectedIndex, 0);
               this.tableModel.setValueAt(this.VendorDescription,selectedIndex, 1);
               //this.UI_VendorList.clear();
           
                    
           }
       }
    }//GEN-LAST:event_BT_SSA_UpdateVendorMouseClicked

    private void BT_SSA_DeleteVendorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BT_SSA_DeleteVendorMouseClicked
        int selectedIndex = this.T_SSA_VendorTable.getSelectedRow();
       if(selectedIndex == -1 || this.T_SSA_VendorTable.getRowCount() == 0)
           UIError.openDialog("Please select an item.");
       else
       {
           	this.tableModel.removeRow(selectedIndex);
            //this.UI_VendorList.clear();
                
       }
    }//GEN-LAST:event_BT_SSA_DeleteVendorMouseClicked

    private void COMBO_CategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_COMBO_CategoryMouseClicked
        
    }//GEN-LAST:event_COMBO_CategoryMouseClicked

    private void COMBO_CategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_COMBO_CategoryActionPerformed
        String categoryCode = this.COMBO_Category.getModel().getSelectedItem().toString();
        this.TF_SSA_VendorName.setText("");
        this.TF_SSA_VendorDescription.setText("");

        this.selectedCategory = getSelectedCategory(categoryCode);
        this.TF_CategoryCode.setText(this.selectedCategory.getCode());
        this.TF_CategoryName.setText(this.selectedCategory.getName());
           
        //this.UI_VendorList.clear();
        this.UI_VendorList = selectedCategory.getVendorList();
        
        if(this.UI_VendorList.isEmpty())
            this.UI_VendorList = new ArrayList<Vendor>();
        
        this.LoadTable();
            
    }//GEN-LAST:event_COMBO_CategoryActionPerformed
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BT_SSA_AddNewVendor;
    private javax.swing.JButton BT_SSA_DeleteVendor;
    private javax.swing.JButton BT_SSA_UpdateVendor;
    private javax.swing.JComboBox<String> COMBO_Category;
    private javax.swing.JTextField TF_CategoryCode;
    private javax.swing.JTextField TF_CategoryName;
    private javax.swing.JTextField TF_SSA_VendorDescription;
    private javax.swing.JTextField TF_SSA_VendorName;
    private javax.swing.JTable T_SSA_VendorTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    
    private void initLook(){
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
    
    private void initData(String categoryCode){
    	
        this.CategoryList = this.manager.getCategoryList();
       
        this.selectedCategory = getSelectedCategory(categoryCode);
        this.TF_CategoryCode.setText(selectedCategory.getCode());
        this.TF_CategoryName.setText(selectedCategory.getName());
        
        this.UI_VendorList = selectedCategory.getVendorList();
        
        if(this.UI_VendorList.isEmpty())
        	this.UI_VendorList = new ArrayList<Vendor>();
        
        this.LoadList();
        this.tableModel = (DefaultTableModel) this.T_SSA_VendorTable.getModel();//Creating Table model
        
        this.LoadTable();
    }
    
    private void LoadTable()
    {
        tableModel.setRowCount(0);
        if(this.UI_VendorList != null) 
	    {  
		    if(!this.UI_VendorList.isEmpty())
		    {
		        for(int i = 0;i < this.UI_VendorList.size() ; i++)
		        {   
		            this.tableModel.addRow(new Object[]{this.UI_VendorList.get(i).getName(),this.UI_VendorList.get(i).getDescription()});
		        }
		    }
        }
        else
            System.out.println("this.UI_VendorList is null");
    }
    
    
    private void LoadList()
    {
    	DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
    	
        for(int i = 0;i< this.CategoryList.size();i++){
        	String code = this.CategoryList.get(i).getCode();
        	model.addElement(code);
        	if (this.CategoryList.get(i) == this.selectedCategory){
        		model.setSelectedItem(code);
        	}
        		
        }
        COMBO_Category.setModel(model);
       
    }
    
    private boolean init() 
    {
        this.VendorName = this.TF_SSA_VendorName.getText().trim();
        this.VendorDescription = this.TF_SSA_VendorDescription.getText().trim();
        if(this.VendorName.isEmpty() || this.VendorDescription.isEmpty())
            UIError.openDialog("Vendor Name or Description should not be empty.");
        else if(this.VendorDescription.contains(",") || this.VendorName.contains(","))
            UIError.openDialog("Please avoid COMMA(,)!!");
        else
            return true;
        return false;
    }

    private boolean validName() {
                
        boolean result = true;
    	
    	// duplicate check
        for(Vendor vendor : this.UI_VendorList){
        	if(vendor.getName().equals(this.VendorName)){
        		result = false;
            	break;
        	}
        }
        return result;
    }
    
    private Category getSelectedCategory(String code){
    	Category result = null;
    	for(Category category : CategoryList){
    		if(code.equals(category.getCode())){
    			result = category;
    			break;
    		}
    	}
    	return result;
    	
    }
    
    private void UpdVendorForCategory(){
    	
    	
    	//this.selectedCategory
    	
    	
    	
    }

}
