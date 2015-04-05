package sg.edu.nus.iss.ussa.gui;

import sg.edu.nus.iss.ussa.application.Shopping;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import sg.edu.nus.iss.ussa.domain.Member;
import sg.edu.nus.iss.ussa.exception.DataFileException;
import sg.edu.nus.iss.ussa.util.TableColumnAdjuster;

public class MemListPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private final String[] columnNames = { "Name", "Member ID",
			"Loyalty Points" };

	private Shopping manager;
	private JButton modifyButton;
	private JButton deleteButton;
	private JTable memberTable;
	private DefaultTableModel tableModel;

	/**
	 * 
	 * @param manager
	 */
	public MemListPanel(Shopping manager) {
		this.manager = manager;
		setLayout(new BorderLayout());
		add("North", createTopPanel());
		add("Center", createMiddlePanel(loadTableData(manager.getMemberList())));
		add("South", createBottomPanel());
		refreshTable();
		setVisible(true);
	}

	/**
	 * 
	 * @param categoryList
	 * @return
	 */
	private Object[][] loadTableData(ArrayList<Member> memberList) {
		Object[][] data = new Object[memberList.size()][3];
		Member member;
		for (int i = 0; i < memberList.size(); i++) {
			member = memberList.get(i);
			data[i][0] = member.getName();
			data[i][1] = member.getMemberID();
			data[i][2] = member.getLoyaltyPoint();
		}
		return data;
	}

	/**
	 * 
	 * @return
	 */
	private JPanel createTopPanel() {
		JPanel p = new JPanel(new BorderLayout());
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(new JLabel("Member List"));
		p.add("Center", panel);
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
		p.add("East", panel);

		return p;
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	private Container createMiddlePanel(Object[][] data) {

		tableModel = new DefaultTableModel(data, columnNames) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		memberTable = new JTable(tableModel);
		memberTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		TableColumnAdjuster tca = new TableColumnAdjuster(memberTable);
		tca.setColumnHeaderIncluded(true);
		tca.setColumnDataIncluded(true);
		// tca.setOnlyAdjustLarger(true);
		tca.adjustColumns();
		memberTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		memberTable.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent arg0) {
						// TODO Auto-generated method stub
						if (memberTable.getSelectionModel().isSelectionEmpty()) {
							modifyButton.setEnabled(false);
							deleteButton.setEnabled(false);
						} else {
							modifyButton.setEnabled(true);
							deleteButton.setEnabled(true);
						}
					}
				});
		;
		memberTable.setFillsViewportHeight(true);
		memberTable.setAutoCreateRowSorter(true);

		JScrollPane p = new JScrollPane(memberTable);

		return p;
	}

	private JPanel createBottomPanel() {
		JPanel p = new JPanel();
		JButton b = new JButton("Add");
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MemDia memberDlg = new MemDia(manager, "Add Member");
				memberDlg.setVisible(true);
			}
		});
		p.add(b);

		modifyButton = new JButton("Modify");
		modifyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String id = (String) memberTable.getValueAt(
						memberTable.getSelectedRow(), 1);
				MemDia memberDlg = new MemDia(manager,
						"Modify Member", id);

				memberDlg.setVisible(true);
			}
		});
		modifyButton.setEnabled(false);
		p.add(modifyButton);

		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				try {
					delMemBtnClicked();
				} catch (DataFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		deleteButton.setEnabled(false);
		p.add(deleteButton);

		b = new JButton("Back");
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				manager.getStoreWindow().changePanel("mainScreen");
			}
		});
		p.add(b);
		return p;
	}

	/*
	 * private void setTableFormat(JTable table){ TableColumnAdjuster tca = new
	 * TableColumnAdjuster(table); tca.setColumnHeaderIncluded(true);
	 * tca.setColumnDataIncluded(true); tca.adjustColumns(); }
	 */

	public void refreshTable() {
		tableModel.setDataVector(loadTableData(manager.getMemberList()),
				columnNames);
		tableModel.fireTableDataChanged();
	}

	/**
	 * 
	 */
	private void delMemBtnClicked() throws DataFileException, IOException {
		String id = (String) memberTable.getValueAt(
				memberTable.getSelectedRow(), 1);

		manager.removeMember(id);

		refreshTable();
	}

}
