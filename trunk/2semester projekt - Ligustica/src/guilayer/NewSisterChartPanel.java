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

import modellayer.Chart;
import modellayer.Settings;
import javax.swing.JScrollPane;

public class NewSisterChartPanel extends JPanel {
	private JPanel newChartPanel;
	
	private ChartCtr cCtr;
	private JButton btnCreateChart;
	private JTextField txtYear;
	private JTextField txtPedigree;
	private JLabel lblYear;
	private JLabel lblPedigree;
	
	private Chart chart;

	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public NewSisterChartPanel() throws SQLException {
		Settings settings = Settings.getInstance();
		cCtr = new ChartCtr();
		chart = new Chart(settings.getBreeder(), true);
		initPanel();
		initComponents();
		
	}

	private void initPanel() {
		setLayout(new BorderLayout(0, 0));
		
		newChartPanel = new JPanel();
		newChartPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Indberetning af søster skema", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(newChartPanel, BorderLayout.CENTER);
		newChartPanel.setLayout(null);
		
		initTable();
		
	}

	private void initComponents() throws SQLException {
		
		txtYear = new JTextField();
		txtYear.setColumns(10);
		txtYear.setBounds(179, 24, 165, 20);
		newChartPanel.add(txtYear);
		
		txtPedigree = new JTextField();
		txtPedigree.setColumns(10);
		txtPedigree.setBounds(179, 55, 165, 20);
		newChartPanel.add(txtPedigree);
		
		lblYear = new JLabel("\u00C5r:");
		lblYear.setBounds(10, 27, 110, 14);
		newChartPanel.add(lblYear);
		
		lblPedigree = new JLabel("Stamtavle:");
		lblPedigree.setBounds(10, 58, 149, 14);
		newChartPanel.add(lblPedigree);
		
		btnCreateChart = new JButton("Indberet skema");
		btnCreateChart.setBounds(223, 404, 121, 23);
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
		
		JButton btnTilfjDronning = new JButton("Tilf\u00F8j dronning");
		btnTilfjDronning.setBounds(223, 354, 121, 23);
		newChartPanel.add(btnTilfjDronning);
		
		JLabel lblTilfjedeDronninger = new JLabel("Tilf\u00F8jede dronninger:");
		lblTilfjedeDronninger.setBounds(10, 99, 110, 14);
		newChartPanel.add(lblTilfjedeDronninger);
		
		
	}

	private void initTable() {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 119, 334, 225);
		newChartPanel.add(scrollPane);
	}
	
	public void createChart() throws SQLException {
		//ChartPanel chartPanel = ChartPanel.getInstance();
//		String name = txtQueen.getText();
//		String address = txtYear.getText();
//		String country = txtPedigree.getText();
//		String phoneNo = txtTemper.getText();
//		String email = txtHoneycombFirmness.getText();
//		
//		if(!name.isEmpty() && !address.isEmpty() && !country.isEmpty() && !phoneNo.isEmpty() && !email.isEmpty()) {
//			//cCtr.createChart(name, address, country, phoneNo, email);
//			//chartPanel.updateTable();
//		}else {
//			JOptionPane.showMessageDialog(newChartPanel, "1 or more fields are empty", "Error Message", JOptionPane.ERROR_MESSAGE);
//		}
	}
}
