/**
 * Handles the class Chart
 * 
 * Authors: Jimmy Møller, Mikkel Petersen, Tue Brodersen, Thomas Bonderup and Christian Schmidt 
 * Date: 26.May 2014.
 */
package modellayer;

import java.util.ArrayList;

public class Chart {

	
	private int chartID, year;
	private boolean isSisterChart;
	private Breeder breeder;
	private Compendium compendium;
	private String pedigree, type;
	private ArrayList<PartChart> partCharts;
	
	/**
	 * Constructor for the class Chart
	 * @param chartID
	 * @param pedigree
	 * @param isSisterChart
	 * @param year
	 * @param breeder
	 * @param compendium
	 */
	public Chart(int chartID, String pedigree, boolean isSisterChart, int year, Breeder breeder, Compendium compendium) {
		this.chartID = chartID;
		this.year = year;
		this.isSisterChart = isSisterChart;
		this.breeder = breeder;
		this.compendium = compendium;
		this.pedigree = pedigree;
		this.partCharts = new ArrayList<PartChart>();
	}
	
	/**
	 * Empty constructor
	 */
	public Chart() {
		this.partCharts = new ArrayList<PartChart>();
	}
	
	public Chart(Breeder breeder, boolean isSisterChart) {
		this.breeder = breeder;
		this.isSisterChart = isSisterChart;
		this.partCharts = new ArrayList<PartChart>();
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
	
	public ArrayList<PartChart> getAllPartCharts() {
		return partCharts;
	}
	
	public void addPartChart(PartChart pChart) {
		partCharts.add(pChart);
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
	/**
	 * Compares two objects of Chart
	 * @param c Chart
	 * @return true //if the objects are equal
	 * @return false //if the objects are not equal
	 */
	public boolean equals(Chart c)
	{
		boolean result = false;
		
		if (c == null)
		{
			result = false;
		}
		if (!(c instanceof Chart))
		{
			result = false;
		}
		
		result = this.chartID == c.getChartID() && this.year == c.getYear() && this.isSisterChart == c.isSisterChart
				&& this.breeder.getBreederID() == c.breeder.getBreederID() && this.compendium.getCompendiumID() == c.getCompendium().getCompendiumID() && this.pedigree.equals(c.getPedigree());
				//&& this.partCharts.equals(c.getAllPartCharts());
		
		return result;
	}
	
	

}
