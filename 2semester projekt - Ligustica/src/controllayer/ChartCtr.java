/**
 * Handles the class Chart controller
 * 
 * Authors: Jimmy Møller, Mikkel Petersen, Tue Brodersen, Thomas Bonderup and Christian Schmidt 
 * Date: 26.May 2014.
 */
package controllayer;

import java.sql.SQLException;
import java.util.ArrayList;

import dblayer.DBChart;
import dblayer.DBPartChart;
import dblayer.IFDBChart;
import dblayer.IFDBPartChart;
import modellayer.Breeder;
import modellayer.Chart;
import modellayer.PartChart;
import modellayer.Queen;
import modellayer.Settings;

public class ChartCtr {
	private IFDBChart dbC;
	private IFDBPartChart dbPC;
	private Settings settings;
	private ValidateCtr vCtr;

	/**
	 * Constructor for the chart controller. Gets the instance of the settings class.
	 */
	public ChartCtr() {
		dbC = new DBChart();
		dbPC = new DBPartChart();
		settings = Settings.getInstance();
		vCtr = new ValidateCtr();
	}
	
	/**
	 * Creates a chart with the breeder that's logged in and inserts it to the database.
	 * @return Returns the chart object
	 */
	public Chart startChart() {
		Chart chart = new Chart(settings.getBreeder(), true);		
		return chart;
	}
	
	/**
	 * Creates a new part chart and returns it.
	 * @param chart The chart which the partchart is inserted into.
	 * @param queen The queen that is a part of the partchart.
	 * @return Returns the created partchart.
	 */
	public PartChart startPartChar(Chart chart, Queen queen) {
		return new PartChart(chart, queen);
	}
	
	/**
	 * Save a chart with a year and pedigree
	 * @param chart The chart to save
	 * @param year The year/season the chart is meant for
	 * @param pedigree The pedigree that's in focus
	 */
	public void saveChart(Chart chart, int year, String pedigree) {
		chart.setYear(year);
		chart.setPedigree(pedigree);
		
		try {
			dbC.insertChart(chart);
			chart.setChartID(dbC.getMaxID());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Save all the PartChart objects to the database
		for(PartChart pC : chart.getAllPartCharts()) {
			pC.setChart(chart);
			
			
			try {
				dbPC.insertPartChart(pC);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			//set id
			try {
				pC.setPartChartID(dbPC.selectSinglePartChart(dbPC.getMaxID(), false).getPartChartID());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * Adds information to the chart object and returns it. Saves the partchart in the database.
	 * @param partChart The partchart in focus
	 * @param year Year of the chart
	 * @param honeyYield The honeyyield of the queen.
	 * @param swarmTendency The swarm tendency of the queen
	 * @param temper The temper of the queen
	 * @param honeycombFirmness The honeycomb firmness of the honeycombs made by the queen
	 * @param honeyYieldYear The honeyyield per year
	 * @param nosema The nosema for the queen
	 * @param cleansingAbility The cleansingability of the queen
	 * @return Returns the chart object with the newly added info
	 * @throws SQLException
	 */
	public Chart addInfo(PartChart partChart, int year, String honeyYield, int swarmTendency, 
			int temper, int honeycombFirmness, 
			int honeyYieldYear, int nosema, int cleansingAbility) throws SQLException {
		PartChart pC = partChart;
		Chart chart = pC.getChart();
		
		//Add the info to the object
		pC.setYear(year);
		pC.setHoneyYield(honeyYield);
		pC.setSwarmTendency(swarmTendency);
		pC.setTemper(temper);
		pC.setHoneycomFirmness(honeycombFirmness);
		pC.setHoneyYieldYear(honeyYieldYear);
		pC.setNosema(nosema);
		pC.setClensingAbility(cleansingAbility);
		
		//Add the saved partChart to the chart objects arrayList
		chart.addPartChart(pC);
		
		return chart;
	}
	
	/**
	 * Get's all the charts in the database.
	 * @return
	 */
	public ArrayList<Chart> getAllCharts() {
		return dbC.getAllCharts(true);
	}
	
	/**
	 * Get all the charts a given breeder has made
	 * @param breeder The breeder to search for
	 * @return Returns an arraylist of the charts the breeder has made.
	 */
	public ArrayList<Chart> getAllBreederCharts(Breeder breeder) {
		ArrayList<Chart> theListToReturn;
		theListToReturn = new ArrayList<Chart>();
		
		ArrayList<Chart> allCharts = getAllCharts();
		
		for(Chart c : allCharts) {
			if(c.getBreeder().getBreederID() == breeder.getBreederID()) {
				theListToReturn.add(c);
			}
		}
		
		return theListToReturn;
	}
	
	/**
	 * Validates the year. It must be 4 in length (i.e. 0001 or 2014)
	 * @param year The year to validate
	 * @return Returns true if the year is valid, false if it's invalid
	 */
	public boolean validateYear(String year) {
		boolean returnValue = false;
		if(year != null) {
		returnValue = vCtr.stringExactLength(year, 4);
		}
		
		return returnValue;
	}
	
	/**
	 * Validates a grade. It can only be 1, 2, 3, 4 or 5.
	 * @param grade The grade to validate
	 * @return Returns true if the integer is valid, false if it's invalid
	 */
	public boolean validateGrade(int grade) {
		return vCtr.validateInt(grade, 1, 5);
	}

}
