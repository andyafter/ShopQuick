package sg.edu.nus.iss.ussa.gui;

import sg.edu.nus.iss.ussa.application.Shopping;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.security.KeyStore;

import javax.security.auth.kerberos.KerberosKey;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import sg.edu.nus.iss.ussa.domain.*;

/*
 * Main Window with CardLayout and MenuBar
 * 
 * Register Panel here
 * 
 * For example:
 * ProductListPanel productListPanel = new ProductListPanel(manager);
 * cards.add(productListPanel,"cardName");
 * 
 * When call the panel, using changeCard(cardName) method
 * 
 * @ XIE JIABAO 
 */
public class StoreBase extends JFrame{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Shopping manager;
	private JMenuBar menuBar;
	private JPanel panels;
	private ProdListPanel prodListPanel;
	private CheckInventoryPanel checkInvPanel;
	//private LoginPanel loginPanel;
	private CheckOutPanel checkOutPanel;
	private MemListPanel memListPanel;
	//private ProductListPanel productListPanel;
	private CateListPanel cateListPanel;
	
	//private ReportPanel reportPanel;
	
	public StoreBase(Shopping shopping){
		super("University Souvenir Store System");
                System.out.println("Store Window constructor");
		this.manager = shopping;
		
		setJMenuBar(createMenu());
		this.panels = new JPanel(new CardLayout());
		this.checkOutPanel = new CheckOutPanel(shopping);
		this.prodListPanel = new ProdListPanel(shopping);
		this.checkInvPanel = new CheckInventoryPanel(shopping);
		this.cateListPanel = new CateListPanel(shopping);
		this.memListPanel = new MemListPanel(shopping);
		
		
		//register cards with cardName
		panels.add(createMainPanel(),"mainScreen");
		panels.add(checkOutPanel,"checkOut");
		panels.add(prodListPanel,"productList");
		panels.add(checkInvPanel,"checkInventory");
		panels.add(cateListPanel,"categoryList");
		panels.add(memListPanel,"memberList");
		setContentPane(panels);
		
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		addWindowListener(windorListener);
		
		setPreferredSize(new Dimension(800,600));
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
                //checkOutPanel.setTransaction(manager.checkOut());
		//changePanel("checkOut");
		
	}
	
	private WindowListener windorListener = new WindowAdapter (){
		public void windowClosing (WindowEvent e) {
			exit();
        }
	};
	private JMenuItem menuItem_1;
	
	//menuBar
	private JMenuBar createMenu(){
		JMenu menu;
		JMenuItem menuItem;
		menuBar = new JMenuBar();
		//main menu
		menu = new JMenu("Main");
		menu.setMnemonic(KeyEvent.VK_M);
		menuItem = new JMenuItem("Main Screen",KeyEvent.VK_M);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,ActionEvent.ALT_MASK));
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				changePanel("mainScreen");
			}
		});
		menu.add(menuItem);
		menuItem = new JMenuItem("Exit",KeyEvent.VK_E);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.ALT_MASK));
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				exit();
			}
		});
		menu.add(menuItem);
		menuBar.add(menu);
		
		//member menu
		menu = new JMenu("Member");
		menuItem = new JMenuItem("Member List",KeyEvent.VK_M);
		menu.add(menuItem);
		menuBar.add(menu);
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				changePanel("memberList");
				
			}
		});
		
		//product menu
		menu = new JMenu("Product");
		//menu.setMnemonic(KeyEvent.VK_P);
		menuItem = new JMenuItem("Product List",KeyEvent.VK_P);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.ALT_MASK));
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				changePanel("productList");
			}
		});
		menu.add(menuItem);
		menuItem = new JMenuItem("Add Product",KeyEvent.VK_A);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.ALT_MASK));
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ProdDia d = new ProdDia(manager,"Add Product");
				d.setVisible(true);
			}
		});
		menu.add(menuItem);
		menuItem = new JMenuItem("Check Inventory",KeyEvent.VK_C);
		//menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.ALT_MASK));
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				changePanel("checkInventory");
			}
		});
		menu.add(menuItem);
		menuBar.add(menu);
		
		//Category menu
		menu = new JMenu("Category");
		//menu.setMnemonic(KeyEvent.VK_C);
		menuItem = new JMenuItem("Category List",KeyEvent.VK_C);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.ALT_MASK));
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				changePanel("categoryList");
			}
		});
		menu.add(menuItem);
		menuItem = new JMenuItem("Add Category",KeyEvent.VK_A);
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("add category");
			}
		});
		menu.add(menuItem);
		menuBar.add(menu);
		
		menu = new JMenu("Discount");
		menuItem_1 = new JMenuItem("Discount List",KeyEvent.VK_D);
		menuItem_1.setSelected(true);
		menuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,ActionEvent.ALT_MASK));
		menuItem_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Discount List");
			}
		});
		menu.add(menuItem_1);
		menuItem = new JMenuItem("Add Discount",KeyEvent.VK_A);
		menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Add Discount");
			}
		});
		menu.add(menuItem);
		menuBar.add(menu);
		
		return menuBar;
	}
	
	//main screen
	//cardName: mainScreen
	private Container createMainPanel(){
		JPanel mainWin;
		JButton button;
                
		mainWin = new JPanel();
		mainWin.setLayout(new BoxLayout(mainWin, BoxLayout.Y_AXIS));
		mainWin.add(createTransactionFactory());
		mainWin.add(createMemberFactory());
		mainWin.add(createDiscountFactory());
		mainWin.add(createCategoryFactory());
		mainWin.add(createProductFactory());
		mainWin.add(createReportFactory());

		return mainWin;
	}
	
	private JPanel createTransactionFactory(){
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createTitledBorder("TransactionFactory"));
		JButton button = new JButton("CheckOut");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("checkout");
				checkOutPanel.setTransaction(manager.checkOut());
				changePanel("checkOut");
				
			}
		});
		p.add(button);
		return p;
	
	}

	private JPanel createProductFactory(){
		JPanel p = new JPanel(new FlowLayout());
		p.setBorder(BorderFactory.createTitledBorder("ProductFactory"));
		JButton button = new JButton("Product List");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				changePanel("productList");
			}
		});
		p.add(button);
		button = new JButton("Add Product");
		button.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed (ActionEvent e) {
				ProdDia dialog = new ProdDia(manager,"Add Product");
				dialog.setVisible(true);
			}
		});
		p.add(button);
		button = new JButton("Check Inventory");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				changePanel("checkInventory");
			}
		});
		p.add(button);

		return p;
	}
	
	private JPanel createMemberFactory(){
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createTitledBorder("MemberFactory"));
		JButton button = new JButton("Member List");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("member list");
				changePanel("memberList");
			}
		});
		p.add(button);
		button = new JButton("Add Member");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MemDia memDialog = new MemDia(manager, "Add Member");
				memDialog.setVisible(true);
				System.out.println("add member");
				//changeCard("memberList");
			}
		});
		p.add(button);
		return p;
	}
	
	private JPanel createDiscountFactory(){
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createTitledBorder("DiscountFactory"));
		JButton button = new JButton("Discount List");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("discount list");
				//changeCard("discountList");
			}
		});
		p.add(button);
		button = new JButton("Add Discount");
		button.addActionListener(new ActionListener() {
			
	
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("add discount");
				//changeCard("discountList");
			}
		});
		p.add(button);
		return p;
	
	}
	
	private JPanel createCategoryFactory(){
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createTitledBorder("CategoryFactory"));
		JButton button = new JButton("Category List");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				changePanel("categoryList");
			}
		});
		p.add(button);
		button = new JButton("Add Category");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("add category");
				//changeCard("memberList");
			}
		});
		p.add(button);
		return p;
	
	}
	
	private JPanel createReportFactory(){
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createTitledBorder("ReportFactory"));
		JButton button = new JButton("Category Report");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Category Report");
				//changeCard("discountList");
			}
		});
		p.add(button);
		button = new JButton("Product Report");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Product Report");
				//changeCard("discountList");
			}
		});
		p.add(button);
		button = new JButton("Transaction Report");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Report list");
				//changeCard("discountList");
			}
		});
		p.add(button);
		button = new JButton("Member Report");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Report list");
				//changeCard("discountList");
			}
		});
		p.add(button);
		return p;
	
	}
	
	public void changePanel(String panelName){
		CardLayout cl =  (CardLayout)panels.getLayout();
		cl.show(panels, panelName);
	}
	
	public void exit(){
		//dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSED));
		//System.exit(EXIT_ON_CLOSE);
		manager.shutdown();
		
	}

	public JPanel getCards() {
		return panels;
	}

	public ProdListPanel getProductListPanel() {
		return prodListPanel;
	}
	
	public MemListPanel getMemberPanel(){
		return memListPanel;
	}
	
	
}
