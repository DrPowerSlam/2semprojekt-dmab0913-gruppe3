package guilayer;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.border.TitledBorder;

import controllayer.ChartCtr;
import controllayer.ValidateCtr;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.JScrollPane;

import modellayer.Chart;
import modellayer.PartChart;
import modellayer.Queen;

public class AddQueenInfoPanel extends JPanel {
	private JPanel queenInfoPanel;
	
	private ChartCtr cCtr;
	
	private JButton btnAddQueen;
	private JTextField txtYear, txtQueen;
	private JTextField txtSwarmTendency;
	private JTextField txtTemper;
	private JLabel lblYear;
	private JLabel lblSwarmTendency;
	private JLabel lblTemper;
	private JTextField txtHoneycombFirmness;
	private JLabel lblHoneycombFirmness;
	private JTextField txtYearHoneyYield;
	private JTextField txtHoneyYield;
	private JTextField txtNosema;
	private JTextField txtCleansingAbility;
	
	private Chart chart;
	private Queen queen;
	private PartChart partChart;

	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public AddQueenInfoPanel(Chart chart, Queen queen) throws SQLException {
		cCtr = new ChartCtr();
		this.chart = chart;
		this.queen = queen;
		partChart = new PartChart(chart, queen);
		initPanel();
		initComponents();
		
	}

	private void initPanel() {
		setLayout(null);
		
		queenInfoPanel = new JPanel();
		queenInfoPanel.setBounds(0, 0, 359, 429);
		queenInfoPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Dronninge info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(queenInfoPanel);
		queenInfoPanel.setLayout(null);
		
		
	}

	private void initComponents() throws SQLException {
		
		JLabel lblQueen = new JLabel("Dronning:");
		lblQueen.setBounds(10, 26, 110, 14);
		queenInfoPanel.add(lblQueen);
		
		txtQueen = new JTextField(queen.getName());
		txtQueen.setColumns(10);
		txtQueen.setBounds(184, 23, 165, 20);
		txtQueen.setEditable(false);
		queenInfoPanel.add(txtQueen);
		
		txtYear = new JTextField(Integer.toString(queen.getYear()));
		txtYear.setColumns(10);
		txtYear.setBounds(184, 54, 165, 20);
		queenInfoPanel.add(txtYear);
		
		txtSwarmTendency = new JTextField(Integer.toString(queen.getSwarmTendency()));
		txtSwarmTendency.setColumns(10);
		txtSwarmTendency.setBounds(184, 85, 165, 20);
		queenInfoPanel.add(txtSwarmTendency);
		
		txtTemper = new JTextField(Integer.toString(queen.getTemper()));
		txtTemper.setColumns(10);
		txtTemper.setBounds(184, 116, 165, 20);
		queenInfoPanel.add(txtTemper);
		
		lblYear = new JLabel("\u00C5r:");
		lblYear.setBounds(10, 57, 110, 14);
		queenInfoPanel.add(lblYear);
		
		lblSwarmTendency = new JLabel("\u00C5rskarakter sv\u00E6rmetendens:");
		lblSwarmTendency.setBounds(10, 88, 149, 14);
		queenInfoPanel.add(lblSwarmTendency);
		
		lblTemper = new JLabel("\u00C5rskarakter temperament:");
		lblTemper.setBounds(10, 119, 159, 14);
		queenInfoPanel.add(lblTemper);
		
		btnAddQueen = new JButton("Tilf\u00F8j dronning til skema");
		btnAddQueen.setBounds(184, 307, 165, 23);
		btnAddQueen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
					createChart();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
		});
		queenInfoPanel.add(btnAddQueen);
		
		txtHoneycombFirmness = new JTextField(Integer.toString(queen.getHoneycomFirmness()));
		txtHoneycombFirmness.setColumns(10);
		txtHoneycombFirmness.setBounds(184, 147, 165, 20);
		queenInfoPanel.add(txtHoneycombFirmness);
		
		lblHoneycombFirmness = new JLabel("\u00C5rskarakter tavlefasthed:");
		lblHoneycombFirmness.setBounds(10, 150, 159, 14);
		queenInfoPanel.add(lblHoneycombFirmness);
		
		txtYearHoneyYield = new JTextField(Integer.toString(queen.getHoneyYield()));
		txtYearHoneyYield.setColumns(10);
		txtYearHoneyYield.setBounds(184, 178, 165, 20);
		queenInfoPanel.add(txtYearHoneyYield);
		
		txtHoneyYield = new JTextField(Integer.toString(queen.getHoneyYield()));
		txtHoneyYield.setColumns(10);
		txtHoneyYield.setBounds(184, 211, 165, 20);
		queenInfoPanel.add(txtHoneyYield);
		
		txtNosema = new JTextField(Integer.toString(queen.getNosema()));
		txtNosema.setColumns(10);
		txtNosema.setBounds(184, 245, 165, 20);
		queenInfoPanel.add(txtNosema);
		
		txtCleansingAbility = new JTextField(Integer.toString(queen.getClensingAbility()));
		txtCleansingAbility.setColumns(10);
		txtCleansingAbility.setBounds(184, 276, 165, 20);
		queenInfoPanel.add(txtCleansingAbility);
		
		JLabel lblYearHoneyYield = new JLabel("\u00C5rskarakter honningudbytte:");
		lblYearHoneyYield.setBounds(10, 181, 159, 14);
		queenInfoPanel.add(lblYearHoneyYield);
		
		JLabel lblHoneyYield = new JLabel("Honningudbytte:");
		lblHoneyYield.setBounds(10, 214, 159, 14);
		queenInfoPanel.add(lblHoneyYield);
		
		JLabel lblNosema = new JLabel("Nosema:");
		lblNosema.setBounds(10, 248, 159, 14);
		queenInfoPanel.add(lblNosema);
		
		JLabel lblCleansingAbility = new JLabel("Udrensningsevne:");
		lblCleansingAbility.setBounds(10, 279, 159, 14);
		queenInfoPanel.add(lblCleansingAbility);
	}
	
	private void createChart() throws SQLException {
		//ChartPanel chartPanel = ChartPanel.getInstance();
		try {
			String year = txtYear.getText();
			String honeyYield = txtHoneyYield.getText();
			int swarmTendency = Integer.parseInt(txtSwarmTendency.getText());
			int temper = Integer.parseInt(txtTemper.getText());
			int honeycombFirmness = Integer.parseInt(txtHoneycombFirmness .getText());
			int honeyYieldYear = Integer.parseInt(txtYearHoneyYield.getText());
			int nosema = Integer.parseInt(txtNosema.getText());
			int cleansingAbility = Integer.parseInt(txtCleansingAbility.getText());
	
			//Check if the String are empty
			if(!year.isEmpty() && !honeyYield.isEmpty()) {
				//Check if year is 4 number long
				if(cCtr.validateYear(year)) {
					//Validate the grades
					if(cCtr.validateGrade(swarmTendency) && cCtr.validateGrade(temper) && cCtr.validateGrade(honeycombFirmness) && cCtr.validateGrade(honeyYieldYear) 
							&& cCtr.validateGrade(nosema) && cCtr.validateGrade(cleansingAbility)) {
						//Ctr.createPartChart(name, address, country, phoneNo, email, chart); //TODO: add partchart to chart arraylist
						//chartPanel.updateTable();
					}else {
						JOptionPane.showMessageDialog(queenInfoPanel, "Års karaktere, nosema og udrensningsevne skal være tal fra 0 til og med 5.", "Fejl", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(queenInfoPanel, "Du skal angive et år på 4 cifre.", "Fejl", JOptionPane.ERROR_MESSAGE);
				}
			}else {
				JOptionPane.showMessageDialog(queenInfoPanel, "1 eller flere felter er ikke blevet udfyldt", "Fejl", JOptionPane.ERROR_MESSAGE);
			}
		}catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(queenInfoPanel, "Årskaraktere, nosema og udrensningsevne skal være tal fra 1 til og med 5.", "Fejl", JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
