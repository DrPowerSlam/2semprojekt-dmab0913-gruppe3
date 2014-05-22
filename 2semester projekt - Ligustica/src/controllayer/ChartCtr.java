package controllayer;

import dblayer.DBChart;
import dblayer.IFDBChart;
import modellayer.Settings;

public class ChartCtr {
	private IFDBChart dbC;
	private Settings settings;
	private ValidateCtr vCtr;

	public ChartCtr() {
		dbC = new DBChart();
		settings = Settings.getInstance();
		vCtr = new ValidateCtr();
	}
	
	public boolean validateYear(String year) {
		return vCtr.stringExactLength(year, 4);
	}
	
	public boolean validateGrade(int grade) {
		return vCtr.validateInt(grade, 1, 5);
	}

}
