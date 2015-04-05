package sg.edu.nus.iss.ussa.gui;

import sg.edu.nus.iss.ussa.application.Shopping;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sg.edu.nus.iss.ussa.domain.*;
import sg.edu.nus.iss.ussa.util.DigitDocument;
import sg.edu.nus.iss.ussa.util.StringDocument;

public class ProdDia extends JDialog{

	private Shopping manager;
	private StoreBase mainScreen;
	private String id;
	
	private JTextField idText;
	private JTextField nameText;
	//private JTextField categoryText;
	private JComboBox<String> categoryList;
	private JTextField descriptionText;
	private JTextField quantityText;
	private JTextField priceText;
	private JTextField barCodeText;
	private JTextField reorderQtyText;
	private JTextField orderQtyText;
	
	public ProdDia(Shopping manager, String title){
		super(manager.getStoreWindow(),title);
		this.manager = manager;
		this.mainScreen = manager.getStoreWindow();
		initGUI();
		add("South",createAddBottomPanel());
		String code = (String)categoryList.getSelectedItem();
		this.id = manager.getNewProductIdByCategory(code);
		idText.setText(id);
	}
	
	public ProdDia(Shopping manager,String title,String id){
		super(manager.getStoreWindow(),title);
		this.manager = manager;
		this.mainScreen = manager.getStoreWindow();
		this.id = id;
		initGUI();
		add("South",createModifyBottomPanel());
		Product p = manager.getProductById(id);
		setData(p.getProductId(), p.getName(), p.getCategory().getCode(), p.getBriefDescription(), p.getQuantityAvailable(), 
				p.getPrice(), p.getBarCodeNumber(), p.getReorderQuantity(), p.getOrderQuantity());
		categoryList.setEnabled(false);
	}
	
	private void initGUI() {
		try {
			setLayout(new BorderLayout());
			add("Center",createCenterPanel());
			setSize(400, 300);
			setLocationRelativeTo(null);
			setModal(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private JPanel createCenterPanel(){
		JPanel p = new JPanel(new BorderLayout());
		p.setBorder(new EmptyBorder(10, 10, 0, 10));
		
		JPanel panel = new JPanel(new GridLayout(9,1));
		panel.add(new JLabel("Name: "));
		panel.add(new JLabel("Id: "));
		panel.add(new JLabel("Category: "));
		panel.add(new JLabel("Description: "));
		panel.add(new JLabel("Price: "));
		panel.add(new JLabel("Avaliable Quantity: "));
		panel.add(new JLabel("Bar Code Number: "));
		panel.add(new JLabel("Reorder Quantity: "));
		panel.add(new JLabel("Order Quantity: "));
		p.add("West",panel);
		
		
		loadCategoryList();
		nameText = new JTextField();
		nameText.setDocument(new StringDocument());
		idText = new JTextField();
		idText.setEditable(false);
		//categoryText = new JTextField();
		descriptionText = new JTextField();
		descriptionText.setDocument(new StringDocument());
		priceText = new JTextField();
		priceText.setDocument(new DigitDocument());
		quantityText = new JTextField();
		quantityText.setDocument(new DigitDocument());
		barCodeText = new JTextField();
		barCodeText.setDocument(new StringDocument());
		reorderQtyText = new JTextField();
		reorderQtyText.setDocument(new DigitDocument());
		orderQtyText = new JTextField();
		orderQtyText.setDocument(new DigitDocument());
		panel = new JPanel(new GridLayout(9,1));
		panel.add(nameText);
		panel.add(idText);
		panel.add(categoryList);
		panel.add(descriptionText);
		panel.add(priceText);
		panel.add(quantityText);
		panel.add(barCodeText);
		panel.add(reorderQtyText);
		panel.add(orderQtyText);
		p.add("Center",panel);
		
		return p;
	}
	
	public void loadCategoryList(){
		categoryList = new JComboBox<String>();
		int lenght = manager.getCategoryList().size();
		if(lenght>0){
			for(int i=0;i<lenght;i++){
				categoryList.addItem(manager.getCategoryList().get(i).getCode());
			}
			categoryList.updateUI();
		}
	}
	
	public void setData(String id,String name, String categoryCode, String briefDescription, 
			int quantityAvailable, double price, String barCode, int threshold, int orderQuantity){
		//categoryList.setSelectedItem(categoryCode);
		nameText.setText(name);
		idText.setText(id);
		categoryList.setSelectedItem(categoryCode);
		descriptionText.setText(briefDescription);
		priceText.setText(Double.toString(price));
		quantityText.setText(Integer.toString(quantityAvailable));
		barCodeText.setText(barCode);
		reorderQtyText.setText(Integer.toString(threshold));
		orderQtyText.setText(Integer.toString(orderQuantity));
		
	}

	public boolean validateData(){
		if(idText.getText()!="" && nameText.getText()!="" && categoryList.getSelectedItem()!="" && descriptionText.getText()!="" 
				&& priceText.getText()!="" && quantityText.getText()!="" && barCodeText.getText()!="" 
				&& reorderQtyText.getText()!="" && orderQtyText.getText()!=""){
			return true;
		}
		return false;
	}
	
	private JPanel createAddBottomPanel() {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel(new FlowLayout());
		JButton button = new JButton("Add");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//Product newProduct = new Product("4","animal","pig","something",12,20,"c123",100,200);
				
				if(validateData()){
					manager.addProduct((String)categoryList.getSelectedItem(),getNameText(),getCategoryText(),getDescriptionText(),
								getQuantityText(),getPriceText(),getBarCodeText(),getReorderQtyText(),getOrderQtyText());
				
					mainScreen.getProductListPanel().refreshTable();
				}else{
					System.out.println("invalid data");
				}
				
			}
		});
		panel.add(button);
		button = new JButton("Cancel");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//setVisible(false);
				dispose();
			}
		});
		panel.add(button);
		categoryList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(categoryList.getSelectedIndex()!=-1){
					String code = (String)categoryList.getSelectedItem();
					idText.setText(manager.getNewProductIdByCategory(code));
				}
			}
		});
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
				//Product newProduct = new Product("4","animal","pig","something",12,20,"c123",100,200);
				
				if(validateData()){
					manager.modifyProduct(id,getNameText(),getCategoryText(),getDescriptionText(),getQuantityText(),
								getPriceText(),getBarCodeText(),getReorderQtyText(),getOrderQtyText());
				
					//manager.modifyProduct(newProduct, index);
					mainScreen.getProductListPanel().refreshTable();
				}else{
					System.out.println("invalid data");
				}
			}
		});
		panel.add(button);
		
		button = new JButton("Delete");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				manager.deleteProduct(id);
				mainScreen.getProductListPanel().refreshTable();
				dispose();
			}
		});
		panel.add(button);
		
		button = new JButton("Cancel");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//setVisible(false);
				dispose();
			}
		});
		panel.add(button);
//		categoryList.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO Auto-generated method stub
//				 
//				idText.setText(categoryList.getSelectedItem()+"/"+manager.getProductById(idText.getText()).getProductId().substring(4));
//			}
//		});
		return panel;
	}
	
	public String getIdText() {
		return idText.getText();
	}

	public String getNameText() {
		return nameText.getText();
	}

	public String getCategoryText() {
		return (String) categoryList.getSelectedItem();
	}

	public String getDescriptionText() {
		return descriptionText.getText();
	}

	public int getQuantityText() {
		return Integer.parseInt(quantityText.getText());
	}

	public float getPriceText() {
		return Float.parseFloat(priceText.getText());
	}

	public String getBarCodeText() {
		return barCodeText.getText();
	}

	public int getReorderQtyText() {
		return Integer.parseInt(reorderQtyText.getText());
	}

	public int getOrderQtyText() {
		return Integer.parseInt(orderQtyText.getText());
	}
	
}
