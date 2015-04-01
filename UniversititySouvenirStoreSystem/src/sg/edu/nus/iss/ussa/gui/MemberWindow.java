package sg.edu.nus.iss.ussa.gui;

import sg.edu.nus.iss.ussa.application.Shopping;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import sg.edu.nus.iss.ussa.domain.*;
import sg.edu.nus.iss.ussa.util.StringDocument;

public class MemberWindow extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Shopping manager;
	private StoreWindow mainScreen;
	private int index;

	private JTextField name;
	private JTextField memberID;
	private JTextField loyaltyPoint;

	public MemberWindow(Shopping manager, String title) {
		super(manager.getStoreWindow(), title);
		this.manager = manager;
		this.mainScreen = manager.getStoreWindow();
		initGUI();
		add("South", createAddBottomPanel());
	}

	public MemberWindow(Shopping manager, String title, String id) {
		super(manager.getStoreWindow(), title);
		this.manager = manager;
		this.mainScreen = manager.getStoreWindow();
		this.index = manager.getMemberList().indexOf(manager.getMemberById(id));
		initGUI();
		add("South", createModifyBottomPanel());
		Member m = manager.getMemberList().get(index);
		setData(m.getName(), m.getMemberID(), m.getLoyaltyPoint());
	}

	private void initGUI() {
		try {
                    System.out.println("Member Dialog initGUI");
			setLayout(new BorderLayout());
			add("Center", createCenterPanel());
			setSize(400, 160);
			setLocationRelativeTo(null);
			setModal(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private JPanel createCenterPanel() {
		JPanel p = new JPanel(new BorderLayout());
		p.setBorder(new EmptyBorder(10, 10, 0, 10));

		JPanel panel = new JPanel(new GridLayout(3, 1));
		panel.add(new JLabel("Name: "));
		panel.add(new JLabel("Member Id: "));
		panel.add(new JLabel("Loyalty Points: "));
		p.add("West", panel);

		name = new JTextField();
		name.setDocument(new StringDocument());
		memberID = new JTextField();
		memberID.setDocument(new StringDocument());
		loyaltyPoint = new JTextField();

		panel = new JPanel(new GridLayout(3, 1));
		panel.add(name);
		panel.add(memberID);
		panel.add(loyaltyPoint);
		p.add("Center", panel);

		return p;
	}

	public void setData(String memberName, String id, int loyalty) {

		name.setText(memberName);
		memberID.setText(id);
		loyaltyPoint.setText(Integer.toString(loyalty));

	}

	public boolean validateData() {
		if (name.getText().isEmpty() || memberID.getText().isEmpty()
				|| loyaltyPoint.getText().isEmpty()) {
			return false;
		} else {
			return true;
		}

	}

	private JPanel createAddBottomPanel() {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel(new FlowLayout());
		JButton button = new JButton("Add");
		loyaltyPoint.setEditable(false);
		loyaltyPoint.setText("-1");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (validateData()) {
					manager.registerMember(getNameText(), getIdText(), -1);
					mainScreen.getMemberPanel().refreshTable();
					dispose();
				} else {
					System.out.println("invalid data");
					JOptionPane.showMessageDialog(new JFrame(),"Enter All/Correct Details");
				}

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

				if (validateData()) {
					manager.modifyMember(getNameText(), getIdText(),
							getLoyaltyText(), index);

					mainScreen.getMemberPanel().refreshTable();
					dispose();
				} else {
					JOptionPane.showMessageDialog(new JFrame(),"Enter All/Correct Details");
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
				dispose();
			}
		});
		panel.add(button);
		return panel;
	}

	public String getIdText() {
		return memberID.getText();
	}

	public String getNameText() {
		return name.getText();

	}

	public int getLoyaltyText() {
		return Integer.parseInt(this.loyaltyPoint.getText());
	}

}
