package guilayer;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.border.TitledBorder;

import controllayer.ChartCtr;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import javax.swing.UIManager;

public class ChooseQueenPanel extends JPanel {
	private JPanel newChartPanel;
	
	private ChartCtr cCtr;
	private JTextField txtQueen;
	private JButton btnCreateChart;
	private JTextField txtYear;
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

	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public ChooseQueenPanel() throws SQLException {
		cCtr = new ChartCtr();
		initPanel();
		initComponents();
		
	}

	private void initPanel() {
		setLayout(new BorderLayout(0, 0));
		
		newChartPanel = new JPanel();
		newChartPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Indberetning af skema", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(newChartPanel, BorderLayout.CENTER);
		newChartPanel.setLayout(null);
		
		
	}

	private void initComponents() throws SQLException {
		
		JLabel lblQueen = new JLabel("Dronning:");
		lblQueen.setBounds(10, 27, 110, 14);
		newChartPanel.add(lblQueen);
		
		txtQueen = new JTextField();
		txtQueen.setColumns(10);
		txtQueen.setBounds(179, 24, 165, 20);
		newChartPanel.add(txtQueen);
		
		txtYear = new JTextField();
		txtYear.setColumns(10);
		txtYear.setBounds(179, 55, 165, 20);
		newChartPanel.add(txtYear);
		
		txtSwarmTendency = new JTextField();
		txtSwarmTendency.setColumns(10);
		txtSwarmTendency.setBounds(179, 86, 165, 20);
		newChartPanel.add(txtSwarmTendency);
		
		txtTemper = new JTextField();
		txtTemper.setColumns(10);
		txtTemper.setBounds(179, 117, 165, 20);
		newChartPanel.add(txtTemper);
		
		lblYear = new JLabel("\u00C5r:");
		lblYear.setBounds(10, 58, 110, 14);
		newChartPanel.add(lblYear);
		
		lblSwarmTendency = new JLabel("\u00C5rskarakter sv\u00E6rmetendens:");
		lblSwarmTendency.setBounds(10, 89, 149, 14);
		newChartPanel.add(lblSwarmTendency);
		
		lblTemper = new JLabel("\u00C5rskarakter temperament:");
		lblTemper.setBounds(10, 120, 159, 14);
		newChartPanel.add(lblTemper);
		
		btnCreateChart = new JButton("Indberet skema");
		btnCreateChart.setBounds(223, 303, 121, 23);
		btnCreateChart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
					createChart();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
		});
		newChartPanel.add(btnCreateChart);
		
		txtHoneycombFirmness = new JTextField();
		txtHoneycombFirmness.setColumns(10);
		txtHoneycombFirmness.setBounds(179, 148, 165, 20);
		newChartPanel.add(txtHoneycombFirmness);
		
		lblHoneycombFirmness = new JLabel("\u00C5rskarakter tavlefasthed:");
		lblHoneycombFirmness.setBounds(10, 151, 159, 14);
		newChartPanel.add(lblHoneycombFirmness);
		
		txtYearHoneyYield = new JTextField();
		txtYearHoneyYield.setColumns(10);
		txtYearHoneyYield.setBounds(179, 179, 165, 20);
		newChartPanel.add(txtYearHoneyYield);
		
		txtHoneyYield = new JTextField();
		txtHoneyYield.setColumns(10);
		txtHoneyYield.setBounds(179, 210, 165, 20);
		newChartPanel.add(txtHoneyYield);
		
		txtNosema = new JTextField();
		txtNosema.setColumns(10);
		txtNosema.setBounds(179, 241, 165, 20);
		newChartPanel.add(txtNosema);
		
		txtCleansingAbility = new JTextField();
		txtCleansingAbility.setColumns(10);
		txtCleansingAbility.setBounds(179, 272, 165, 20);
		newChartPanel.add(txtCleansingAbility);
		
		JLabel lblYearHoneyYield = new JLabel("\u00C5rskarakter honningudbytte:");
		lblYearHoneyYield.setBounds(10, 182, 159, 14);
		newChartPanel.add(lblYearHoneyYield);
		
		JLabel lblHoneyYield = new JLabel("Honningudbytte:");
		lblHoneyYield.setBounds(10, 213, 159, 14);
		newChartPanel.add(lblHoneyYield);
		
		JLabel lblNosema = new JLabel("Nosema:");
		lblNosema.setBounds(10, 244, 159, 14);
		newChartPanel.add(lblNosema);
		
		JLabel lblCleansingAbility = new JLabel("Udrensningsevne:");
		lblCleansingAbility.setBounds(10, 275, 159, 14);
		newChartPanel.add(lblCleansingAbility);
	}
	
	public void createChart() throws SQLException {
		//ChartPanel chartPanel = ChartPanel.getInstance();
		String name = txtQueen.getText();
		String address = txtYear.getText();
		String country = txtSwarmTendency.getText();
		String phoneNo = txtTemper.getText();
		String email = txtHoneycombFirmness.getText();
		
		if(!name.isEmpty() && !address.isEmpty() && !country.isEmpty() && !phoneNo.isEmpty() && !email.isEmpty()) {
			//cCtr.createChart(name, address, country, phoneNo, email);
			//chartPanel.updateTable();
		}else {
			JOptionPane.showMessageDialog(newChartPanel, "1 or more fields are empty", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
	}
}
