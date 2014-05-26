package guilayer;

import modellayer.PartChart;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class PartChartTableModel extends AbstractTableModel {
	
	private List<PartChart> partCharts;

    public PartChartTableModel(List<PartChart> partCharts) {
        this.partCharts = new ArrayList<>(partCharts);
    }

    @Override
    public int getRowCount() {
        return partCharts.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public String getColumnName(int column) {
        String name = "";
        switch (column) {
            case 0:
                name = "Dronning";
                break;
            case 1:
            	name = "Sværmetendens";
            	break;
            case 2:
            	name = "Temperament";
            	break;
            case 3:
            	name = "Tavlefasthed";
            	break;
            case 4:
            	name = "Honningudbytte";
            	break;
            case 5:
            	name = "Nosema";
            	break;
            case 6:
            	name = "Udrensningsevne";
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
                value = partChart.getQueen().getName();
                break;
            case 1:
                value = partChart.getSwarmTendency();
                break;
            case 2:
            	value = partChart.getTemper();
            	break;
            case 3:
            	value = partChart.getHoneycomFirmness();
            	break;
            case 4:
            	value = partChart.getHoneyYield();
            	break;
            case 5:
            	value = partChart.getNosema();
            	break;
            case 6:
            	value = partChart.getClensingAbility();
            	break;
        }
        return value;
    }            

}
