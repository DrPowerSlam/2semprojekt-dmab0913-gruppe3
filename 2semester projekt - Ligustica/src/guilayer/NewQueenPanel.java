package guilayer;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.border.TitledBorder;

import controllayer.QueenCtr;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import javax.swing.UIManager;

public class NewQueenPanel extends JPanel {
	private JPanel newQueenPanel;
	
	private QueenCtr cCtr;
	private JTextField txtQueen;
	private JButton btnCreateQueen;
	private JTextField txtYear;
	private JTextField txtSwarmTendency;
	private JTextField txtTemper;
	private JLabel lblYear;
	private JLabel lblSwarmTendency;
	private JLabel lblTemper;
	private JTextField txtHoneycombFirmness;
	private JLabel lblHoneycombFirmness;
	private JTextField txtYearHoneyYield;
	private JTextField txtNosema;
	private JTextField txtCleansingAbility;

	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public NewQueenPanel() throws SQLException {
		cCtr = new QueenCtr();
		initPanel();
		initComponents();
		
	}

	private void initPanel() {
		setLayout(new BorderLayout(0, 0));
		
		newQueenPanel = new JPanel();
		newQueenPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tilføj ny dronning", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(newQueenPanel, BorderLayout.CENTER);
		newQueenPanel.setLayout(null);
		
		
	}

	private void initComponents() throws SQLException {
		
		JLabel lblQueen = new JLabel("Dronning:");
		lblQueen.setBounds(10, 27, 110, 14);
		newQueenPanel.add(lblQueen);
		
		txtQueen = new JTextField();
		txtQueen.setColumns(10);
		txtQueen.setBounds(179, 24, 165, 20);
		newQueenPanel.add(txtQueen);
		
		txtYear = new JTextField();
		txtYear.setColumns(10);
		txtYear.setBounds(179, 55, 165, 20);
		newQueenPanel.add(txtYear);
		
		txtSwarmTendency = new JTextField();
		txtSwarmTendency.setColumns(10);
		txtSwarmTendency.setBounds(179, 86, 165, 20);
		newQueenPanel.add(txtSwarmTendency);
		
		txtTemper = new JTextField();
		txtTemper.setColumns(10);
		txtTemper.setBounds(179, 117, 165, 20);
		newQueenPanel.add(txtTemper);
		
		lblYear = new JLabel("\u00C5r:");
		lblYear.setBounds(10, 58, 110, 14);
		newQueenPanel.add(lblYear);
		
		lblSwarmTendency = new JLabel("Sv\u00E6rmetendens:");
		lblSwarmTendency.setBounds(10, 89, 149, 14);
		newQueenPanel.add(lblSwarmTendency);
		
		lblTemper = new JLabel("Temperament:");
		lblTemper.setBounds(10, 120, 159, 14);
		newQueenPanel.add(lblTemper);
		
		btnCreateQueen = new JButton("Indberet skema");
		btnCreateQueen.setBounds(223, 303, 121, 23);
		btnCreateQueen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
					createQueen();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
		});
		newQueenPanel.add(btnCreateQueen);
		
		txtHoneycombFirmness = new JTextField();
		txtHoneycombFirmness.setColumns(10);
		txtHoneycombFirmness.setBounds(179, 148, 165, 20);
		newQueenPanel.add(txtHoneycombFirmness);
		
		lblHoneycombFirmness = new JLabel("Tavlefasthed:");
		lblHoneycombFirmness.setBounds(10, 151, 159, 14);
		newQueenPanel.add(lblHoneycombFirmness);
		
		txtYearHoneyYield = new JTextField();
		txtYearHoneyYield.setColumns(10);
		txtYearHoneyYield.setBounds(179, 179, 165, 20);
		newQueenPanel.add(txtYearHoneyYield);
		
		txtNosema = new JTextField();
		txtNosema.setColumns(10);
		txtNosema.setBounds(179, 212, 165, 20);
		newQueenPanel.add(txtNosema);
		
		txtCleansingAbility = new JTextField();
		txtCleansingAbility.setColumns(10);
		txtCleansingAbility.setBounds(179, 245, 165, 20);
		newQueenPanel.add(txtCleansingAbility);
		
		JLabel lblYearHoneyYield = new JLabel("Honningudbytte:");
		lblYearHoneyYield.setBounds(10, 182, 159, 14);
		newQueenPanel.add(lblYearHoneyYield);
		
		JLabel lblNosema = new JLabel("Nosema:");
		lblNosema.setBounds(10, 215, 159, 14);
		newQueenPanel.add(lblNosema);
		
		JLabel lblCleansingAbility = new JLabel("Udrensningsevne:");
		lblCleansingAbility.setBounds(10, 248, 159, 14);
		newQueenPanel.add(lblCleansingAbility);
	}
	
	public void createQueen() throws SQLException {
		//QueenPanel queenPanel = QueenPanel.getInstance();
		String name = txtQueen.getText();
		String address = txtYear.getText();
		String country = txtSwarmTendency.getText();
		String phoneNo = txtTemper.getText();
		String email = txtHoneycombFirmness.getText();
		
		if(!name.isEmpty() && !address.isEmpty() && !country.isEmpty() && !phoneNo.isEmpty() && !email.isEmpty()) {
			//cCtr.createQueen(name, address, country, phoneNo, email);
			//queenPanel.updateTable();
		}else {
			JOptionPane.showMessageDialog(newQueenPanel, "1 or more fields are empty", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
	}
}
