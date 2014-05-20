package modellayer;

public class Chart {

	
private int chartID, year;
private boolean isSisterChart;
private Breeder breeder;
private Compendium compendium;
private String pedigree;

public Chart(int chartID, String pedigree, boolean isSisterChart, int year, Breeder breeder, Compendium compendium) {
	this.chartID = chartID;
	this.year = year;
	this.isSisterChart = isSisterChart;
	this.breeder = breeder;
	this.compendium = compendium;
	this.pedigree = pedigree;
}

public Chart() {
	}

/**
 * @return the chartID
 */
public int getChartID() {
	return chartID;
}

/**
 * @param chartID the chartID to set
 */
public void setChartID(int chartID) {
	this.chartID = chartID;
}

/**
 * @return the year
 */
public int getYear() {
	return year;
}

/**
 * @param year the year to set
 */
public void setYear(int year) {
	this.year = year;
}

/**
 * @return the isSisterChart
 */
public boolean isSisterChart() {
	return isSisterChart;
}

/**
 * @param isSisterChart the isSisterChart to set
 */
public void setSisterChart(boolean isSisterChart) {
	this.isSisterChart = isSisterChart;
}

/**
 * @return the breeder
 */
public Breeder getBreeder() {
	return breeder;
}

/**
 * @param breeder the breeder to set
 */
public void setBreeder(Breeder breeder) {
	this.breeder = breeder;
}

/**
 * @return the compendium
 */
public Compendium getCompendium() {
	return compendium;
}

/**
 * @param compendium the compendium to set
 */
public void setCompendium(Compendium compendium) {
	this.compendium = compendium;
}

/**
 * @return the pedigree
 */
public String getPedigree() {
	return pedigree;
}

/**
 * @param pedigree the pedigree to set
 */
public void setPedigree(String pedigree) {
	this.pedigree = pedigree;
}

}
