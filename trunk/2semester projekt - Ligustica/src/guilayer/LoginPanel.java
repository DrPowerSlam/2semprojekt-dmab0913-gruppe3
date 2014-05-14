package guilayer;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;

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
		lblLogo.setBounds(81, 83, 87, 87);
		add(lblLogo);
		
		initLoginPane();
		initInfoPane();

	}

	private void initLoginPane() {
		JPanel pane = new JPanel();
		pane.setBorder(new TitledBorder(null, "Login", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pane.setBounds(447, 11, 374, 462);
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
		
		JLabel lblPassword = new JLabel("Adgangskode:");
		lblPassword.setBounds(10, 72, 94, 14);
		pane.add(lblPassword);
		
		
	}

	private void initInfoPane() {
		JPanel ligusticaPanel = new JPanel();
		ligusticaPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		ligusticaPanel.setBounds(10, 362, 427, 111);
		add(ligusticaPanel);
		ligusticaPanel.setLayout(null);
		
		JLabel lblTitel = new JLabel("Ligustica Avlerforening");
		lblTitel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitel.setBounds(10, 11, 166, 29);
		ligusticaPanel.add(lblTitel);
		
		JLabel lblAdministrationSystemFor = new JLabel("Administration system for indberetning af avlerskema og dronninger");
		lblAdministrationSystemFor.setBounds(10, 52, 343, 14);
		ligusticaPanel.add(lblAdministrationSystemFor);
	}
}
