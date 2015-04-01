package sg.edu.nus.iss.ussa.gui;

import sg.edu.nus.iss.ussa.application.Shopping;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import sg.edu.nus.iss.ussa.domain.*;
import sg.edu.nus.iss.ussa.util.TableColumnAdjuster;



/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
/**
* cardName: productList
* @ XIE JIABAO
*/
public class ProductsListPanel extends JPanel{
	
	private final String[] columnNames = {"Id","Name","Description","Price","Quality"};
	private JButton modifyButton;
	private JButton deleteButton;
	private JTable productTable;
	private DefaultTableModel tableModel;
	private Shopping manager;
	
	public ProductsListPanel(Shopping manager){
		this.manager = manager;
		setLayout(new BorderLayout());
		add("North",createTopPanel());
		add("Center",createMiddlePanel(loadTableData(manager.getProductList())));
		add("South",createBottomPanel());
		setVisible(true);
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
//		String[] columnNames = {"Id","Name","Category","Price","Quality"};
//		Object[][] data = {
//				{"1","cat","animal","$123","5"},
//				{"2","dog","animal","$123","5"},
//				{"3","cat","animal","$123","5"}
//		};
		
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
				ProductWindow d = new ProductWindow(manager,"Add Product");				
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
				ProductWindow d = new ProductWindow(manager,"Modify Product", id);
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
				manager.deleteProduct(id);
				//manager.getStore().getPm().showData();
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
				manager.getStoreWindow().changeCard("checkInventory");
			}
		});
		p.add(b);
		
		b = new JButton("Back");
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				manager.getStoreWindow().changeCard("mainScreen");
			}
		});
		p.add(b);
		return p;
	}
	
	private void setTableFormat(){
		TableColumnAdjuster tca = new TableColumnAdjuster(productTable);
		tca.setColumnHeaderIncluded(true);
		tca.setColumnDataIncluded(true);
		//tca.setOnlyAdjustLarger(true);
		tca.adjustColumns();
	}
	
	public void refreshTable(){
		tableModel.setDataVector(loadTableData(manager.getProductList()), columnNames);
		tableModel.fireTableDataChanged();
		setTableFormat();
	}

	public JTable getProductTable() {
		return productTable;
	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}
	
	


}
