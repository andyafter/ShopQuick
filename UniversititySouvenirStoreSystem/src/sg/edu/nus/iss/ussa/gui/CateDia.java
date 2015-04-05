package sg.edu.nus.iss.ussa.gui;

import sg.edu.nus.iss.ussa.application.Shopping;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import sg.edu.nus.iss.ussa.domain.*;
import sg.edu.nus.iss.ussa.util.DialogMode;
import sg.edu.nus.iss.ussa.util.StringDocument;
import sg.edu.nus.iss.ussa.util.TableColumnAdjuster;

/**
 * 
 * @author Xu Minsheng
 *
 */
public class CateDia extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String[] columnNames = {"Name","Description"};
	
	private Shopping manager;
	private StoreBase mainScreen;
	
	private DefaultTableModel tableModel;
	
	private JTextField codeText;
	private JTextField nameText;
	
	private Category category;
	
	public CateDia( Shopping manager, String title, Category category, DialogMode dialogMode){
		super(manager.getStoreWindow(),title);
		this.manager = manager;
		this.mainScreen = manager.getStoreWindow();
		this.category = category;
		initGUI();
		
		switch (dialogMode) {
			case ADD:
				add("South",createAddBottomPanel());
				break;
			case MODIFY:
				setData(category);
				add("South",createModifyBottomPanel());
				break;
			case VIEW:
				setData(category);
				add("South",createViewBottomPanel());
				break;
			default:
				setData(category);
				add("South",createViewBottomPanel());
				
				break;
		}
		
	}
	
	private void initGUI() {
		try {
			System.out.println("Category Dialog Initialized!");
			setLayout(new BorderLayout());
			add("Center",createCenterPanel(loadTableData(this.category.getVendorList())));
			setSize(600, 400);
			setLocationRelativeTo(null);
			setModal(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private JPanel createCenterPanel(Object[][] data){
		

		tableModel = new DefaultTableModel(data,columnNames){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		
		JPanel contentPanel = new JPanel(new BorderLayout());
		
		// baisc info. code & name
		JPanel basicPanel = new JPanel(new BorderLayout());
		basicPanel.setBorder(new EmptyBorder(10, 10, 0, 10));
		
		JPanel panel = new JPanel(new GridLayout(2,1));
		panel.add(new JLabel("Code: "));
		panel.add(new JLabel("Name: "));
		basicPanel.add(BorderLayout.WEST,panel);
		
		codeText = new JTextField();
		codeText.setDocument(new StringDocument());
		nameText = new JTextField();
		nameText.setDocument(new StringDocument());

		panel = new JPanel(new GridLayout(2,1));
		panel.add(codeText);
		panel.add(nameText);
		basicPanel.add(BorderLayout.CENTER, panel);

		// vendor info 
		JPanel vendorPanel = new JPanel(new BorderLayout());
		vendorPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		JTable vendorTable = new JTable(tableModel);
		setTableFormat(vendorTable);
		JComboBox<String> vendorCombo = new JComboBox<String>(); 
		
		
		
		vendorPanel.add(BorderLayout.CENTER, vendorTable);
		vendorPanel.add(BorderLayout.SOUTH, vendorCombo);
		
		
		contentPanel.add(BorderLayout.NORTH, basicPanel);
		contentPanel.add(BorderLayout.CENTER, vendorPanel);
		return contentPanel;
	}
	
	
	/**
	 * 
	 * @param vendorList
	 * @return 2 dimensional array of vendor list
	 */
	private Object[][] loadTableData(ArrayList<Vendor> vendorList){
		Object[][] data =  new Object[vendorList.size()][2];
		Vendor vendor;
		for(int i=0;i<vendorList.size();i++){
			vendor = vendorList.get(i);
			data[i][0] = vendor.getName();
			data[i][1] = vendor.getDescription();
		}
		return data;
	}
	
	public void setData(Category category){
		codeText.setText(category.getCode());
		nameText.setText(category.getName());
	}

	
	private JPanel createAddBottomPanel() {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel(new FlowLayout());
		JButton button = new JButton("Add");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				addCateBtnClicked();
			}
		});
		panel.add(button);
		
		button = new JButton("Cancel");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		panel.add(button);
	
		return panel;
	}
	
	private JPanel createModifyBottomPanel() {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel(new FlowLayout());
		JButton button = new JButton("Modify");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				modifyCateBtnClicked();
				
			}
		});
		panel.add(button);
		
		button = new JButton("Delete");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				delCateBtnClicked();
				dispose();
			}
		});
		panel.add(button);
		
		button = new JButton("Cancel");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		panel.add(button);
		return panel;
	}
	
	private JPanel createViewBottomPanel() {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel(new FlowLayout());
		
		JButton button = new JButton("Cancel");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		panel.add(button);
		return panel;
	}
	
	private void setTableFormat(JTable table){
		TableColumnAdjuster tca = new TableColumnAdjuster(table);
		tca.setColumnHeaderIncluded(true);
		tca.setColumnDataIncluded(true);
		tca.adjustColumns();
	}
	
	private void addCateBtnClicked(){
		if(validateData()){
			
			
		}else{
			System.out.println("invalid data");
		}
	}
	
	private void modifyCateBtnClicked(){
		if(validateData()){
			
			
		}else{
			System.out.println("invalid data");
		}
	}
	
	private void delCateBtnClicked(){
		if(validateData()){
			
			
		}else{
			System.out.println("invalid data");
		}
	}

	private boolean validateData(){
		return false;
	}
	
	
	
}
