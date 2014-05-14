package guilayer;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

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
		lblLogo.setBounds(88, 129, 87, 87);
		add(lblLogo);
		
		initLoginPane();

	}

	private void initLoginPane() {
		JPanel pane = new JPanel();
		pane.setBorder(new TitledBorder(null, "Login", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pane.setBounds(447, 36, 374, 426);
		add(pane);
		pane.setLayout(null);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(277, 100, 89, 23);
		pane.add(btnLogin);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(114, 69, 251, 20);
		pane.add(txtPassword);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(114, 38, 251, 20);
		pane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblEmailAdresse = new JLabel("E-mail adresse:");
		lblEmailAdresse.setBounds(10, 41, 94, 14);
		pane.add(lblEmailAdresse);
		
		JLabel lblAdgangskode = new JLabel("Adgangskode:");
		lblAdgangskode.setBounds(10, 72, 94, 14);
		pane.add(lblAdgangskode);
	}
}
