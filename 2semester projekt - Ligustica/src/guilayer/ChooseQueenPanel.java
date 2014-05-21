package guilayer;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.border.TitledBorder;

import controllayer.ChartCtr;
import controllayer.QueenCtr;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.JScrollPane;

import modellayer.Chart;
import modellayer.PartChart;
import modellayer.Queen;

public class ChooseQueenPanel extends JPanel {
	private JPanel queenInfoPanel;
	
	private QueenCtr qCtr;
	private JButton btnAddQueen;
	private JTextField textField;
	
	private Chart chart;
	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public ChooseQueenPanel(Chart chart) throws SQLException {
		qCtr = new QueenCtr();
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
	
		btnAddQueen = new JButton("V�lg dronning");
		btnAddQueen.setBounds(184, 300, 165, 23);
		btnAddQueen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
				chooseQueen();
            }
		});
		queenInfoPanel.add(btnAddQueen);
		
		initTable();
		
		textField = new JTextField();
		textField.setBounds(47, 269, 302, 20);
		queenInfoPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblSg = new JLabel("S\u00F8g:");
		lblSg.setBounds(10, 272, 46, 14);
		queenInfoPanel.add(lblSg);
		
	}

	private void initTable() {
		ArrayList<Queen> queens =  qCtr.getAllQueens();
		ChooseQueenTableModel model = new ChooseQueenTableModel(queens);
        JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 31, 339, 230);
		queenInfoPanel.add(scrollPane);
	}
	
	public void chooseQueen() {
		ChartPanel chartPanel = ChartPanel.getInstance();
		try {
			//TODO: send chart og queen objektet til queenInfoPanel (check om queen er valgt f�rst)
			//TODO: under queenInfoPanel opret nyt CHartPart, s�t Queen til den og vis queens �rskarakter hvis de er der
			//TODO  N�r info gemmes valideres det, hvis det g�r igennem gemmes chartpart objektet, tilf�jes til chart og chart sendes med til newsistercharpanel
			chartPanel.addQueenInfoPanel();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
