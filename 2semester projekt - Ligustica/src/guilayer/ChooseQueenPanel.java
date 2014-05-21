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

import modellayer.Chart;

public class ChooseQueenPanel extends JPanel {
	private JPanel queenInfoPanel;
	
	private ChartCtr cCtr;
	private JButton btnAddQueen;
	private JTextField textField;
	
	private Chart chart;
	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public ChooseQueenPanel(Chart chart) throws SQLException {
		cCtr = new ChartCtr();
		this.chart = chart;
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
		btnAddQueen.setBounds(184, 300, 165, 23);
		btnAddQueen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
				chooseQueen();
            }
		});
		queenInfoPanel.add(btnAddQueen);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 31, 339, 230);
		queenInfoPanel.add(scrollPane);
		
		textField = new JTextField();
		textField.setBounds(47, 269, 302, 20);
		queenInfoPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblSg = new JLabel("S\u00F8g:");
		lblSg.setBounds(10, 272, 46, 14);
		queenInfoPanel.add(lblSg);
		
	}
	
	public void chooseQueen() {
		ChartPanel chartPanel = ChartPanel.getInstance();
		try {
			//TODO: send chart og queen objektet til queenInfoPanel (check om queen er valgt først)
			//TODO: under queenInfoPanel opret nyt CHartPart, sæt Queen til den og vis queens årskarakter hvis de er der
			//TODO  Når info gemmes valideres det, hvis det går igennem gemmes chartpart objektet, tilføjes til chart og chart sendes med til newsistercharpanel
			chartPanel.addQueenInfoPanel();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
