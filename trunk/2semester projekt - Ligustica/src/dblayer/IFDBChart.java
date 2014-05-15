package dblayer;

import java.sql.SQLException;
import java.util.ArrayList;

import modellayer.Chart;

/**
 * 
 * @author Gruppe 3
 *
 */
public interface IFDBChart {
	public ArrayList<Chart> getAllCharts(boolean retriveAssociation); 

	public Chart searchChartOnName(String name, boolean retriveAssociation) throws SQLException;

	public Chart selectSingleChart(int chartID, boolean retriveAssociation) throws SQLException;

	public int insertChart(Chart c) throws SQLException; 

	public int updateChart(Chart c) throws SQLException; 
	
	public int deleteChart(Chart c) throws SQLException;
  
}
