package sg.edu.nus.iss.ussa.gui;

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

import sg.edu.nus.iss.ussa.domain.Product;
import sg.edu.nus.iss.ussa.util.TableColumnAdjuster;

public class CheckInventoryPanel extends JPanel{

	private String[] columnNames = {"Id","Name","Available Quantity","Threshold","Order Quantity"};
	private StoreApplication manager;
	private JTable table;
	private DefaultTableModel tableModel;
	private JButton fire = new JButton("Fire");
	
	public CheckInventoryPanel(StoreApplication manager){
		this.manager = manager;
		setLayout(new BorderLayout());
		add("North",createNorthPanel());
		add("Center",createCenterPanel());
		add("South",createSouthPanel());
		setVisible(true);
		fire.setEnabled(false);
	}
	
	public JPanel createNorthPanel(){
		JPanel p = new JPanel(new FlowLayout());
		p.add(new JLabel("Replenish Inventory List"));
		return p;
	}
	
	public Container createCenterPanel(){
		tableModel = new DefaultTableModel(loadTableData(manager.getProductList()),columnNames){
			@Override
			public boolean isCellEditable(int row,int column){
				return false;
			}
		};
		
		table = new JTable(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		TableColumnAdjuster tca = new TableColumnAdjuster(table);
		tca.setColumnHeaderIncluded(true);
		tca.setColumnDataIncluded(true);
		tca.setOnlyAdjustLarger(true);
		tca.adjustColumns();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(table.getSelectionModel().isSelectionEmpty()){
					fire.setEnabled(false);
				}else{
					fire.setEnabled(true);
				}
			}

		});
		JScrollPane p = new JScrollPane(table);
		return p;
	}
	
	private JPanel createSouthPanel(){
		JPanel p = new JPanel(new FlowLayout());
		fire.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		p.add(fire);
		JButton b = new JButton("Generate Order List");
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
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
	
	private Object[][] loadTableData(ArrayList<Product> products){
		Object[][] data =  new Object[products.size()][5];
		for(int i=0;i<products.size();i++){
			data[i][0] = products.get(i).getProductId();
			data[i][1] = products.get(i).getName();
			data[i][2] = products.get(i).getQuantityAvailable();
			data[i][3] = products.get(i).getReorderQuantity();
			data[i][4] = products.get(i).getOrderQuantity();
		}
		return data;
	}
}
