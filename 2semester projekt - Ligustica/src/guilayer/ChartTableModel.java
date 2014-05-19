package guilayer;

import modellayer.Chart;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ChartTableModel extends AbstractTableModel {
	
	private List<Chart> charts;

    public ChartTableModel(List<Chart> charts) {
        this.charts = new ArrayList<>(charts);
    }

    @Override
    public int getRowCount() {
        return charts.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
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
            	name = "Indberettet";
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
        Chart chart = charts.get(rowIndex);
        Object value = null;
        switch (columnIndex) {
            case 0:
                value = chart.getChartID();
                break;
            case 1:
                value = chart.getQueen().getName();
                break;
            case 2:
            	value = chart.getYear();
            	break;
            case 3:
            	value = chart.getTemper(); //TODO: Lav om så man kan se om den er indberettet
            	break;
        }
        return value;
    }            

}
