package controllayer;

import dblayer.DBChart;
import dblayer.IFDBChart;
import modellayer.Settings;

public class ChartCtr {
	private IFDBChart dbC;
	private Settings settings;

	public ChartCtr() {
		dbC = new DBChart();
		settings = Settings.getInstance();
	}

}
