package controllayer;

import java.sql.SQLException;

import dblayer.DBChart;
import dblayer.DBPartChart;
import dblayer.IFDBChart;
import dblayer.IFDBPartChart;
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
		chart.setType("true");
		Chart c = null;
		try {
			dbC.insertChart(chart);
			c = dbC.selectSingleChart(dbC.getMaxID(), true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return c;
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
	
	public Chart createPartChart(PartChart partChart, int year, String honeyYield, int swarmTendency, int temper, int honeycombFirmness, 
			int honeyYieldYear, int nosema, int cleansingAbility) {
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
		
		//Add the saved partChart to the chart objects arrayList
		chart.addPartChart(pC);
		
		return chart;
	}
	
	
	public boolean validateYear(String year) {
		return vCtr.stringExactLength(year, 4);
	}
	
	public boolean validateGrade(int grade) {
		return vCtr.validateInt(grade, 1, 5);
	}

}
