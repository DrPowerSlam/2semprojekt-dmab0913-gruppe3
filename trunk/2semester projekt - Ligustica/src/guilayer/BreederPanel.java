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

import modellayer.Breeder;

public class BreederPanel extends JPanel {

	private JScrollPane scrollPane;
	private JPanel cardPanel, newBreederPanel;
	
	/**
	 * Create the panel.
	 */
	public BreederPanel() {
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
		ArrayList<Breeder> breeders =  new ArrayList<Breeder>();
		breeders.add(new Breeder("Hhh", "l", "address", "phone", "email", "password", true, 1));
		
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
