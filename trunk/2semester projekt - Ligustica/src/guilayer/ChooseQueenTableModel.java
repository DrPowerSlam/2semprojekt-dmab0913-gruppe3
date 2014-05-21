package guilayer;

import modellayer.PartChart;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ChooseQueenTableModel extends AbstractTableModel {
	
	private List<PartChart> partCharts;

    public ChooseQueenTableModel(List<PartChart> partCharts) {
        this.partCharts = new ArrayList<>(partCharts);
    }

    @Override
    public int getRowCount() {
        return partCharts.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
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
            	name = "Honningudbytte";
            	break;
            case 3:
            	name = "Sværmetendens";
            	break;
            case 4:
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
        PartChart partChart = partCharts.get(rowIndex);
        Object value = null;
        switch (columnIndex) {
            case 0:
                value = partChart.getPartChartID();
                break;
            case 1:
                value = partChart.getQueen().getName();
                break;
            case 2:
            	value = partChart.getHoneyYield();
            	break;
            case 3:
            	value = partChart.getSwarmTendency();
            	break;
            case 4:
            	value = partChart.getTemper();
            	break;
        }
        return value;
    }            

}
