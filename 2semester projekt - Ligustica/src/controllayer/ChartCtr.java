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

	public ChartCtr() {
		dbC = new DBChart();
		dbPC = new DBPartChart();
		settings = Settings.getInstance();
		vCtr = new ValidateCtr();
	}
	
	public Chart startChart() {
		Chart chart = new Chart(settings.getBreeder(), true);
		try {
			dbC.insertChart(chart);
			chart.setChartID(dbC.getMaxID());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return chart;
	}
	
	public PartChart startPartChar(Chart chart, Queen queen) {
		return new PartChart(chart, queen);
	}
	
	public void saveChart(Chart chart, int year, String pedigree) {
		chart.setYear(year);
		chart.setPedigree(pedigree);
		try {
			dbC.updateChart(chart);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Chart addInfo(PartChart partChart, int year, String honeyYield, int swarmTendency, int temper, int honeycombFirmness, 
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
		
		//Save the PartChart object to the database
		try {
			dbPC.insertPartChart(pC);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//set id
		pC.setPartChartID(dbPC.selectSinglePartChart(dbPC.getMaxID(), false).getPartChartID());
		
		//Add the saved partChart to the chart objects arrayList
		chart.addPartChart(pC);
		
		return chart;
	}
	
	public ArrayList<Chart> getAllCharts() {
		return dbC.getAllCharts(true);
	}
	
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
	
	public boolean validateYear(String year) {
		boolean returnValue = false;
		if(year != null) {
		returnValue = vCtr.stringExactLength(year, 4);
		}
		
		return returnValue;
	}
	
	public boolean validateGrade(int grade) {
		return vCtr.validateInt(grade, 1, 5);
	}

}
