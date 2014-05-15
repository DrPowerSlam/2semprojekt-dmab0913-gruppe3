package guilayer;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ChartPanel extends JPanel {

	private JScrollPane scrollPane;
	private JPanel cardPanel, newChartPanel;
	
	/**
	 * Create the panel.
	 */
	public ChartPanel() {
		setLayout(null);
		initTable();
		initSeconPanel();
		initButtons();
	}
	
	/**
	 * Initialize Table
	 */
	private void initTable() {
		
		//TODO Lav tablemodel og smid den i scrollpane
		scrollPane = new JScrollPane(null);
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
		JButton btnNewChart = new JButton("Ny skema");
        btnNewChart.setBounds(366, 422, 89, 23);
        btnNewChart.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					newChart();
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
	public void newChart() throws SQLException {
		newChartPanel = new NewChartPanel();
		cardPanel.add(newChartPanel);
		CardLayout cl = (CardLayout)(cardPanel.getLayout());
		cl.last(cardPanel);
	}
	
	


}
