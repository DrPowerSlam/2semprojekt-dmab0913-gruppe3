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
	private TableRowSorter<ChooseQueenTableModel> sorter;
	private JTable table;
	
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
		
		try {
		initTable();
		}catch (ClassCastException e) {
			//
		}
		
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
				try {
					chooseQueen();
				} catch (SQLException e) {
					e.printStackTrace();
				}
            }
		});
		queenInfoPanel.add(btnAddQueen);
		
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
		try {
			ArrayList<Queen> queens =  qCtr.findQueensByBreeder(chart.getBreeder());
			ChooseQueenTableModel model = new ChooseQueenTableModel(queens);
			sorter = new TableRowSorter<ChooseQueenTableModel>(model);
	        table = new JTable(model);
	        table.setRowSorter(sorter);
	        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setBounds(10, 31, 339, 230);
			queenInfoPanel.add(scrollPane);
		}catch (ClassCastException e) {
			
		}
		
	}
	
	private void newFilter() {
	    RowFilter<ChooseQueenTableModel, Object> rf = null;
	    try {
	        rf = RowFilter.regexFilter(filterText.getText(), 1);
	    } catch (java.util.regex.PatternSyntaxException e) {

	    }
	    sorter.setRowFilter(rf);
	}
	
	public void chooseQueen() throws SQLException {
		ChartPanel chartPanel = ChartPanel.getInstance();
		try {
			Queen queen = qCtr.getQueenByID(Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));
			if(queen == null) {
				JOptionPane.showMessageDialog(queenInfoPanel, "En dronning skal markeres", "Fejl", JOptionPane.ERROR_MESSAGE);
			} else {
				chartPanel.addQueenInfoPanel(chart, queen);
			}
		} catch(IndexOutOfBoundsException e){
			JOptionPane.showMessageDialog(queenInfoPanel, "En dronning skal markeres", "Fejl", JOptionPane.ERROR_MESSAGE);
		}
	}
}
