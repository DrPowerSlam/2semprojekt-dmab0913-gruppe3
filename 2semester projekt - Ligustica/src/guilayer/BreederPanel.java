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

import controllayer.BreederCtr;
import modellayer.Breeder;

public class BreederPanel extends JPanel {

	private JScrollPane scrollPane;
	private JPanel cardPanel, newBreederPanel;
	private BreederCtr breederCtr;
	
	/**
	 * Create the panel.
	 */
	public BreederPanel() {
		breederCtr = new BreederCtr();
		setLayout(null);
		initTable();
		initSeconPanel();
		initButtons();
	}
	
	/**
	 * Initialize Table
	 */
	private void initTable() {
		
		ArrayList<Breeder> breeders =  breederCtr.getAllBreeders();		
		BreederTableModel model = new BreederTableModel(breeders);
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
		JButton btnNewBreeder = new JButton("Ny skema");
        btnNewBreeder.setBounds(366, 422, 89, 23);
        btnNewBreeder.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					newBreeder();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
        add(btnNewBreeder);
	}
	
	/**
	 * Updates the Table
	 */
	public void updateTable() {
		//TODO: Opdater table
	}
	
	/**
	 * Changes panel to newBreeder
	 * @param forestName
	 * @throws SQLException 
	 */
	public void newBreeder() throws SQLException {
		newBreederPanel = new NewBreederPanel();
		cardPanel.add(newBreederPanel);
		CardLayout cl = (CardLayout)(cardPanel.getLayout());
		cl.last(cardPanel);
	}
	
	


}