package guilayer;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import modellayer.Chart;
import modellayer.Queen;
import modellayer.Settings;

public class ChartPanel extends JPanel {

	private JScrollPane scrollPane;
	private JPanel cardPanel, newSisterChartPanel, chooseQueenPanel, addQueenInfoPanel;
	private static ChartPanel instance = null;
	
	/**
	 * Create the panel.
	 */
	private ChartPanel() {
		setLayout(null);
		initTable();
		initSeconPanel();
		initButtons();
	}
	
	public static ChartPanel getInstance() {
		if(instance == null) {
			instance = new ChartPanel();
		}
		return instance;
	}
	
	/**
	 * Initialize Table
	 */
	private void initTable() {
		
		ArrayList<Chart> charts =  new ArrayList<Chart>();	
		ChartTableModel model = new ChartTableModel(charts);
        JTable table = new JTable(model);
		scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 11, 445, 400);
        add(scrollPane);
        

	}
	
	/**
	 * Initialize the secondPanel
	 */
	private void initSeconPanel() {
		cardPanel = new JPanel();
		cardPanel.setBounds(468, 11, 350, 434);
		add(cardPanel);
		cardPanel.setLayout(new CardLayout(0, 0));
	}
	
	public void initButtons() {
		JButton btnNewChart = new JButton("Ny søster skema");
        btnNewChart.setBounds(328, 422, 127, 23);
        btnNewChart.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Settings settings = Settings.getInstance();
					Chart chart = new Chart(settings.getBreeder(), true);
					newSisterChart(chart);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
        add(btnNewChart);
	}
	
	/**
	 * Updates the Table
	 */
	public void updateTable() {
		//TODO: Opdater table
	}
	
	/**
	 * Changes panel to newChart
	 * @param forestName
	 * @throws SQLException 
	 */
	public void newSisterChart(Chart chart) throws SQLException {
		newSisterChartPanel = new NewSisterChartPanel(chart);
		cardPanel.add(newSisterChartPanel);
		CardLayout cl = (CardLayout)(cardPanel.getLayout());
		cl.last(cardPanel);
	}
	
	public void addQueenInfoPanel(Chart chart, Queen queen) throws SQLException {
		addQueenInfoPanel = new AddQueenInfoPanel(chart, queen);
		cardPanel.add(addQueenInfoPanel);
		CardLayout cl = (CardLayout)(cardPanel.getLayout());
		cl.last(cardPanel);
	}
	
	public void chooseQueenPanel(Chart chart) throws SQLException {
		chooseQueenPanel = new ChooseQueenPanel(chart);
		cardPanel.add(chooseQueenPanel);
		CardLayout cl = (CardLayout)(cardPanel.getLayout());
		cl.last(cardPanel);
	}
	
	


}
