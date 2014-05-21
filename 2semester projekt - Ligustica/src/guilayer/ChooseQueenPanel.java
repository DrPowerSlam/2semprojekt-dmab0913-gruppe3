package guilayer;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.TableRowSorter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.border.TitledBorder;

import controllayer.QueenCtr;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.JScrollPane;

import modellayer.Chart;
import modellayer.PartChart;
import modellayer.Queen;

public class ChooseQueenPanel extends JPanel {
	private JPanel queenInfoPanel;
	
	private QueenCtr qCtr;
	private JButton btnAddQueen;
	private JTextField filterText;
	TableRowSorter<ChooseQueenTableModel> sorter;
	
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
		setOpaque(true);
		queenInfoPanel = new JPanel();
		queenInfoPanel.setBounds(0, 0, 359, 429);
		queenInfoPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Dronninge info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(queenInfoPanel);
		queenInfoPanel.setLayout(null);
		queenInfoPanel.setOpaque(true);
		
		
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
		
		initTable();
		
		filterText = new JTextField();
		filterText.setBounds(47, 269, 302, 20);
		filterText.getDocument().addDocumentListener(
                new DocumentListener() {
                    public void changedUpdate(DocumentEvent e) {
                        newFilter();
                    }
                    public void insertUpdate(DocumentEvent e) {
                        newFilter();
                    }
                    public void removeUpdate(DocumentEvent e) {
                        newFilter();
                    }
                });
		queenInfoPanel.add(filterText);
		filterText.setColumns(10);
		
		JLabel lblSg = new JLabel("S\u00F8g:");
		lblSg.setBounds(10, 272, 46, 14);
		queenInfoPanel.add(lblSg);
		
	}

	private void initTable() {
		ArrayList<Queen> queens =  qCtr.getAllQueens();
		ChooseQueenTableModel model = new ChooseQueenTableModel(queens);
		sorter = new TableRowSorter<ChooseQueenTableModel>(model);
        final JTable table = new JTable(model);
        table.setRowSorter(sorter);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 31, 339, 230);
		queenInfoPanel.add(scrollPane);
		
	}
	
	private void newFilter() {
	    RowFilter<ChooseQueenTableModel, Object> rf = null;
	    //If current expression doesn't parse, don't update.
	    try {
	        rf = RowFilter.regexFilter(filterText.getText(), 1);
	    } catch (java.util.regex.PatternSyntaxException e) {
	        System.out.println("FEJL!");
	    }
	    sorter.setRowFilter(rf);
	    System.out.println("Jaa: " + rf);
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
