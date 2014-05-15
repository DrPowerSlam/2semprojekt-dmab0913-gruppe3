package guilayer;

import javax.swing.JPanel;
import controllayer.BreederCtr;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginPanel extends JPanel {
	private JTextField txtEmail;
	private JPasswordField txtPassword;

	/**
	 * Create the panel.
	 */
	public LoginPanel() {
		setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(LoginPanel.class.getResource("/resources/logo.png")));
		lblLogo.setBounds(359, 111, 87, 87);
		add(lblLogo);
		
		initLoginPane();

	}

	private void initLoginPane() {
		JPanel pane = new JPanel();
		pane.setBorder(new TitledBorder(null, "Login", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pane.setBounds(242, 276, 374, 122);
		add(pane);
		pane.setLayout(null);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(115, 53, 251, 20);
		pane.add(txtPassword);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(115, 22, 251, 20);
		pane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblEmailAdresse = new JLabel("E-mail adresse:");
		lblEmailAdresse.setBounds(10, 25, 94, 14);
		pane.add(lblEmailAdresse);
		
		JLabel lblPassword = new JLabel("Adgangskode:");
		lblPassword.setBounds(10, 56, 94, 14);
		pane.add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(275, 84, 89, 23);
		btnLogin.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String strPassword = new String(txtPassword.getPassword());
				validateLogin(txtEmail.getText(),  strPassword);
			}
			
		});
		pane.add(btnLogin);
		
		
		
	}

	private void validateLogin(String email, String password) {
		BreederCtr breederCtr = new BreederCtr();
		if(breederCtr.validateLogin(email, password)) {
			System.out.println("Yay");
			//Success!
			//TODO: Sæt breeder i settings, skift loginpane til tabbedpane
		} else {
			System.out.println("Noo");
			//Fejl!
			//TODO: Indsæt en label med fejltekst
		}
	}
}
