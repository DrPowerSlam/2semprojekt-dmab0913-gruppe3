package guilayer;

import modellayer.Queen;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class QueenPanel extends JPanel {

	private JScrollPane scrollPane;
	private JPanel cardPanel, newQueenPanel;
	
	/**
	 * Create the panel.
	 */
	public QueenPanel() {
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
		ArrayList<Queen> queens =  new ArrayList<Queen>();	
		QueenTableModel model = new QueenTableModel(queens);
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
		JButton btnNewQueen = new JButton("Ny dronning");
        btnNewQueen.setBounds(366, 422, 89, 23);
        btnNewQueen.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					newQueen();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
        add(btnNewQueen);
	}
	
	/**
	 * Updates the Table
	 */
	public void updateTable() {
		//TODO: Opdater table
	}
	
	/**
	 * Changes panel to newQueen
	 * @param forestName
	 * @throws SQLException 
	 */
	public void newQueen() throws SQLException {
		newQueenPanel = new AddQueenPanel();
		cardPanel.add(newQueenPanel);
		CardLayout cl = (CardLayout)(cardPanel.getLayout());
		cl.last(cardPanel);
	}
	
	


}
