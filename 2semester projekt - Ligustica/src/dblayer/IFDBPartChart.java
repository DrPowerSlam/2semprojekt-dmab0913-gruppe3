package dblayer;

import java.sql.SQLException;
import java.util.ArrayList;

import modellayer.PartChart;

/**
 * 
 * @author Gruppe 3
 *
 */
public interface IFDBPartChart {
	public ArrayList<PartChart> getAllPartCharts(boolean retriveAssociation); 

	public PartChart searchPartChartOnName(String name, boolean retriveAssociation) throws SQLException;

	public PartChart selectSinglePartChart(int partChartID, boolean retriveAssociation) throws SQLException;

	public int insertPartChart(PartChart c) throws SQLException; 

	public int updatePartChart(PartChart c) throws SQLException; 
	
	public int deletePartChart(PartChart c) throws SQLException;
	
	public int getMaxID();
  
}
