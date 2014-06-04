package guilayer;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.border.TitledBorder;

import controllayer.QueenCtr;
import controllayer.ValidateCtr;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.JScrollPane;

import modellayer.Queen;

public class AddQueenPanel extends JPanel {
	private JPanel queenInfoPanel;
	
	private QueenCtr qCtr;
	private ValidateCtr vCtr;
	
	private JButton btnAddQueen;
	private JTextField txtYear;
	private JTextField txtSwarmTendency;
	private JTextField txtTemper;
	private JLabel lblYear;
	private JLabel lblSwarmTendency;
	private JLabel lblTemper;
	private JTextField txtHoneycombFirmness;
	private JLabel lblHoneycombFirmness;
	private JTextField txtHoneyYield;
	private JTextField txtNosema;
	private JTextField txtCleansingAbility;
	
	
	private Queen queen;

	private JTextField txtDronning;

	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public AddQueenPanel() throws SQLException {
		
		vCtr = new ValidateCtr();
		qCtr = new QueenCtr();
		
		initPanel();
		initComponents();
		
	}

	private void initPanel() {
		setLayout(null);
		
		queenInfoPanel = new JPanel();
		queenInfoPanel.setBounds(0, 0, 359, 429);
		queenInfoPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Opret ny Dronning", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(queenInfoPanel);
		queenInfoPanel.setLayout(null);
		
		
	}

	private void initComponents() throws SQLException {
		
		JLabel lblQueen = new JLabel("Dronning:");
		lblQueen.setBounds(10, 26, 110, 14);
		queenInfoPanel.add(lblQueen);
		
		txtYear = new JTextField();
		txtYear.setColumns(10);
		txtYear.setBounds(184, 54, 165, 20);
		queenInfoPanel.add(txtYear);
		
		txtSwarmTendency = new JTextField();
		txtSwarmTendency.setColumns(10);
		txtSwarmTendency.setBounds(184, 85, 165, 20);
		queenInfoPanel.add(txtSwarmTendency);
		
		txtTemper = new JTextField();
		txtTemper.setColumns(10);
		txtTemper.setBounds(184, 116, 165, 20);
		queenInfoPanel.add(txtTemper);
		
		lblYear = new JLabel("\u00C5r:");
		lblYear.setBounds(10, 57, 110, 14);
		queenInfoPanel.add(lblYear);
		
		lblSwarmTendency = new JLabel("Sv\u00E6rmtendens:");
		lblSwarmTendency.setBounds(10, 88, 149, 14);
		queenInfoPanel.add(lblSwarmTendency);
		
		lblTemper = new JLabel("Temperament:");
		lblTemper.setBounds(10, 119, 159, 14);
		queenInfoPanel.add(lblTemper);
		
		btnAddQueen = new JButton("Opret ny dronning");
		btnAddQueen.setBounds(184, 277, 165, 23);
		btnAddQueen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
					addQueen();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
		});
		queenInfoPanel.add(btnAddQueen);
		
		txtHoneycombFirmness = new JTextField();
		txtHoneycombFirmness.setColumns(10);
		txtHoneycombFirmness.setBounds(184, 147, 165, 20);
		queenInfoPanel.add(txtHoneycombFirmness);
		
		lblHoneycombFirmness = new JLabel("Tavlefasthed:");
		lblHoneycombFirmness.setBounds(10, 150, 159, 14);
		queenInfoPanel.add(lblHoneycombFirmness);
		
		txtHoneyYield = new JTextField();
		txtHoneyYield.setColumns(10);
		txtHoneyYield.setBounds(184, 178, 165, 20);
		queenInfoPanel.add(txtHoneyYield);
		
		txtNosema = new JTextField();
		txtNosema.setColumns(10);
		txtNosema.setBounds(184, 211, 165, 20);
		queenInfoPanel.add(txtNosema);
		
		txtCleansingAbility = new JTextField();
		txtCleansingAbility.setColumns(10);
		txtCleansingAbility.setBounds(184, 244, 165, 20);
		queenInfoPanel.add(txtCleansingAbility);
		
		JLabel lblYearHoneyYield = new JLabel("Honningudbytte:");
		lblYearHoneyYield.setBounds(10, 181, 159, 14);
		queenInfoPanel.add(lblYearHoneyYield);
		
		JLabel lblNosema = new JLabel("Nosema:");
		lblNosema.setBounds(10, 214, 159, 14);
		queenInfoPanel.add(lblNosema);
		
		JLabel lblCleansingAbility = new JLabel("Udrensningsevne:");
		lblCleansingAbility.setBounds(10, 247, 159, 14);
		queenInfoPanel.add(lblCleansingAbility);
		
		txtDronning = new JTextField("");
		txtDronning.setColumns(10);
		txtDronning.setBounds(184, 23, 165, 20);
		queenInfoPanel.add(txtDronning);
	}
	
	private void addQueen() throws SQLException {
		try {
			String name = txtDronning.getText();
			String years = txtYear.getText();
			int honeyYield = Integer.parseInt(txtHoneyYield.getText());
			int swarmTendency = Integer.parseInt(txtSwarmTendency.getText());
			int temper = Integer.parseInt(txtTemper.getText());
			int honeycombFirmness = Integer.parseInt(txtHoneycombFirmness .getText());
			int nosema = Integer.parseInt(txtNosema.getText());
			int cleansingAbility = Integer.parseInt(txtCleansingAbility.getText());
	
			//Check if the String are empty
			if(!years.isEmpty()) {
				//Check if year is 4 number long
				if(vCtr.stringExactLength(years, 4)) {
					//Validate the grades
					int year = Integer.parseInt(years);
					if(vCtr.validateInt(swarmTendency,1,5) && vCtr.validateInt(temper,1,5) && 
							vCtr.validateInt(honeycombFirmness,1,5) && 
							vCtr.validateInt(honeyYield,1,5) 
							&& vCtr.validateInt(nosema,1,5) && vCtr.validateInt(cleansingAbility,1,5)) {
						//Save the object
						qCtr.addQueen(name, year, swarmTendency, temper, honeycombFirmness, honeyYield, nosema, cleansingAbility);
					}else {
						JOptionPane.showMessageDialog(queenInfoPanel, "Årskaraktere, nosema, udrensningsevne, temperament, sværmtendens og honningudbytte"
								+ " skal være tal fra 1 til og med 5.", "Fejl",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(queenInfoPanel, "Du skal angive et år på 4"
							+ " cifre.", "Fejl", JOptionPane.ERROR_MESSAGE);
				}
			}else {
				JOptionPane.showMessageDialog(queenInfoPanel, "1 eller flere felter er ikke"
						+ " blevet udfyldt", "Fejl", JOptionPane.ERROR_MESSAGE);
			}
		}catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(queenInfoPanel, "Årskaraktere, nosema, udrensningsevne, temperament, sværmtendens og honningudbytte"
					+ " skal være tal fra 1 til og med 5.", "Fejl", JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
