package guilayer;

import modellayer.Queen;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class QueenTableModel extends AbstractTableModel {
	
	private List<Queen> queens;

    public QueenTableModel(List<Queen> queens) {
        this.queens = new ArrayList<>(queens);
    }

    @Override
    public int getRowCount() {
        return queens.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public String getColumnName(int column) {
        String name = "";
        switch (column) {
            case 0:
                name = "ID";
                break;
            case 1:
                name = "Dronning";
                break;
            case 2:
            	name = "År";
            	break;
            case 3:
            	name = "Honnigudbytte";
            	break;
            case 4:
            	name = "Sværmetendens";
            	break;
            case 5:
            	name = "Temperament";
            	break;
        }
        return name;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class type = String.class;
        switch (columnIndex) {
            case 0:
            case 1:
                type = Integer.class;
                break;
        }
        return type;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Queen queen = queens.get(rowIndex);
        Object value = null;
        switch (columnIndex) {
            case 0:
                value = queen.getQueenID();
                break;
            case 1:
                value = queen.getName();
                break;
            case 2:
            	value = queen.getYear();
            	break;
            case 3:
            	value = queen.getHoneyYield();
            	break;
            case 4:
            	value = queen.getSwarmTendency();
            	break;
            case 5:
            	value = queen.getTemper();
            	break;
        }
        return value;
    }            

}
