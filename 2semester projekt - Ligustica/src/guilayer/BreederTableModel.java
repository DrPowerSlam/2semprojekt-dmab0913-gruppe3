package guilayer;

import modellayer.Breeder;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class BreederTableModel extends AbstractTableModel {
	
	private List<Breeder> breeders;

    public BreederTableModel(List<Breeder> breeders) {
        this.breeders = new ArrayList<>(breeders);
    }

    @Override
    public int getRowCount() {
        return breeders.size();
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
                name = "Navn";
                break;
            case 2:
            	name = "Adresse";
            	break;
            case 3:
            	name = "Telefon nr.";
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
        Breeder breeder = breeders.get(rowIndex);
        Object value = null;
        String name = breeder.getFname()+ " " + breeder.getLname();
        switch (columnIndex) {
            case 0:
                value = breeder.getBreederID();
                break;
            case 1:
                value = name;
                break;
            case 2:
            	value = breeder.getAddress();
            	break;
            case 3:
            	value = breeder.getPhone();
            	break;
        }
        return value;
    }            

}
