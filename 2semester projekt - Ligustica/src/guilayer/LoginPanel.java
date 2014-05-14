package guilayer;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class LoginPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public LoginPanel() {
		setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(LoginPanel.class.getResource("/resources/logo.png")));
		lblLogo.setBounds(268, 96, 87, 87);
		add(lblLogo);

	}
}
