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
import javax.swing.JScrollPane;

public class ChooseQueenInfoPanel extends JPanel {
	private JPanel queenInfoPanel;
	
	private ChartCtr cCtr;
	private JTextField txtQueen;
	private JButton btnAddQueen;
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
	public ChooseQueenInfoPanel() throws SQLException {
		cCtr = new ChartCtr();
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
		
		
		
		btnAddQueen = new JButton("Vælg dronning");
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
			JOptionPane.showMessageDialog(queenInfoPanel, "1 or more fields are empty", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
	}
}
