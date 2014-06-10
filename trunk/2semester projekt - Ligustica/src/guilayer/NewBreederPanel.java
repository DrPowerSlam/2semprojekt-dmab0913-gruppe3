package guilayer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import modellayer.City;
import controllayer.BreederCtr;

public class NewBreederPanel extends JPanel {
	private JPanel newBreederPanel;
	
	private BreederCtr bCtr;
	private JTextField txtFirstName, txtLastName, txtAddress, txtTelephone, txtEmail, txtPassword, txtCity;
	private JButton btnCreateBreeder;
	private JLabel lblFirstName, lblLastName, lblAddress, lblTelephone, lblEmail, lblPassword, lblIsAdmin, lblCity;
	private JComboBox comboBoxType;
	private City city = new City();
	private BreederPanel breederPanel = BreederPanel.getInstance();

	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public NewBreederPanel() throws SQLException {
		bCtr = new BreederCtr();
		initPanel();
		initComponents();
		
	}

	private void initPanel() {
		setLayout(new BorderLayout(0, 0));
		
		newBreederPanel = new JPanel();
		newBreederPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tilføj avler", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(newBreederPanel, BorderLayout.CENTER);
		newBreederPanel.setLayout(null);
		
		
	}

	private void initComponents() throws SQLException {
		
		txtFirstName = new JTextField();
		txtFirstName.setColumns(10);
		txtFirstName.setBounds(179, 24, 165, 20);
		newBreederPanel.add(txtFirstName);
		
		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		txtLastName.setBounds(179, 55, 165, 20);
		newBreederPanel.add(txtLastName);
		
		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(179, 86, 165, 20);
		newBreederPanel.add(txtAddress);
		
		txtTelephone = new JTextField();
		txtTelephone.setColumns(10);
		txtTelephone.setBounds(179, 117, 165, 20);
		newBreederPanel.add(txtTelephone);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(179, 148, 165, 20);
		newBreederPanel.add(txtEmail);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(179, 179, 165, 20);
		newBreederPanel.add(txtPassword);
		
		String[] comboType = { "IkkeAdmin", "ErAdmin" };
		comboBoxType = new JComboBox<String>(comboType);
		comboBoxType.setBounds(179, 210, 165, 20);
		newBreederPanel.add(comboBoxType);
		
		txtCity = new JTextField();
		txtCity.setColumns(10);
		txtCity.setBounds(179, 241, 165, 20);
		newBreederPanel.add(txtCity);
		
		lblFirstName = new JLabel("Fornavn:");
		lblFirstName.setBounds(10, 27, 110, 14);
		newBreederPanel.add(lblFirstName);
		
		lblLastName = new JLabel("Efternavn:");
		lblLastName.setBounds(10, 58, 110, 14);
		newBreederPanel.add(lblLastName);
		
		lblAddress = new JLabel("Adresse:");
		lblAddress.setBounds(10, 89, 149, 14);
		newBreederPanel.add(lblAddress);
		
		lblTelephone = new JLabel("TelefonNr:");
		lblTelephone.setBounds(10, 120, 149, 14);
		newBreederPanel.add(lblTelephone);
		
		lblEmail = new JLabel("Email:");
		lblEmail.setBounds(10, 151, 149, 14);
		newBreederPanel.add(lblEmail);
		
		lblPassword = new JLabel("Kodeord:");
		lblPassword.setBounds(10, 182, 149, 14);
		newBreederPanel.add(lblPassword);
		
		lblIsAdmin = new JLabel("Brugertype:");
		lblIsAdmin.setBounds(10, 213, 149, 14);
		newBreederPanel.add(lblIsAdmin);
		
		lblCity = new JLabel("PostNummer:");
		lblCity.setBounds(10, 244, 149, 14);
		newBreederPanel.add(lblCity);
		
		btnCreateBreeder = new JButton("Opret Avler");
		btnCreateBreeder.setBounds(223, 303, 121, 23);
		btnCreateBreeder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
					createBreeder();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
		});
		newBreederPanel.add(btnCreateBreeder);
	}
	
	public void createBreeder() throws SQLException {
		String firstName = txtFirstName.getText();
		String lastName = txtLastName.getText();
		String address = txtAddress.getText();
		String phoneNo = txtTelephone.getText();
		String email = txtEmail.getText();
		String password = txtPassword.getText();
		if(!txtCity.getText().isEmpty()) {
		city.setZipCode(Integer.parseInt(txtCity.getText())); 
		}
		int control = 0;
		
		if(!firstName.isEmpty() && !lastName.isEmpty() && !address.isEmpty() && !phoneNo.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
			
			control = bCtr.insertBreeder(firstName, lastName, address, phoneNo, email, password, isAdmin(), 0, city);
			if(control == 1) {
				JOptionPane.showMessageDialog(this, "Avler er oprettet!", 
						"Succes", JOptionPane.INFORMATION_MESSAGE);
				breederPanel.updateTable();
			}
			else {
				JOptionPane.showMessageDialog(this, "Fejl! Avler ikke oprettet.",
						"Fejl", JOptionPane.ERROR_MESSAGE);
			}
			
		}else {
			JOptionPane.showMessageDialog(newBreederPanel, "Alle felter skal være udfyldt!", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private boolean isAdmin() {
		boolean comboType = false;
        String comboTypeString = comboBoxType.getSelectedItem().toString();
        if(comboTypeString == "ErAdmin") {
        	comboType = true;
        }
        else {
        	comboType = false;
        }
        return comboType;
	}
	
}
