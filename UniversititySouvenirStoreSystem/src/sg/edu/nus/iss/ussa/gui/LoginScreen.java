package sg.edu.nus.iss.ussa.gui;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


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
public class LoginScreen extends javax.swing.JFrame {
	private JLabel lblName;
	private JPasswordField PwdField;
	private JButton Login;
	private JLabel lblPwd;
	private JTextField txtName;
	
	private StoreApplication storeApp;
	
	public LoginScreen(StoreApplication storeApp)
	{
		super();
		this.storeApp=storeApp;
		initGUI();
	}
	
	private void initGUI() {
		try {
			this.setTitle("Login");
			GridBagLayout thisLayout = new GridBagLayout();
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			thisLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
			thisLayout.rowHeights = new int[] {7, 20, 20, 20, 20, 20, 7, 7, 7};
			thisLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
			thisLayout.columnWidths = new int[] {7, 20, 7, 7};
			getContentPane().setLayout(thisLayout);
			{
				lblName = new JLabel();
				getContentPane().add(lblName, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				lblName.setText("Name:");
			}
			{
				txtName = new JTextField();
				getContentPane().add(txtName, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 10, 0, 0), 0, 0));
				txtName.setPreferredSize(new java.awt.Dimension(100, 23));
			}
			{
				lblPwd = new JLabel();
				getContentPane().add(lblPwd, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				lblPwd.setText("Password:");
			}
			{
				Login = new JButton();
				getContentPane().add(Login, new GridBagConstraints(1, 4, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				Login.setText("Login");
				Login.setPreferredSize(new java.awt.Dimension(77, 23));
				Login.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						LoginActionPerformed(evt);
					}
				});
			}
			{
				PwdField = new JPasswordField();
				getContentPane().add(PwdField, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 10, 0, 0), 0, 0));
				PwdField.setPreferredSize(new java.awt.Dimension(100, 23));
			}
			pack();
			setSize(400, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void LoginActionPerformed(ActionEvent evt) {
		System.out.println("Login.actionPerformed, event="+evt);
		//TODO add your code for Login.actionPerformed
		String msg;
		if (storeApp.login(txtName.getText(),PwdField.getText().toString()) == false)
		{
			msg="Invalid UserName/Password";
			AlertMsgPopUp success = new AlertMsgPopUp(new JFrame(),"Login Status", msg);
			success.setVisible(true);
		}
	}

}
