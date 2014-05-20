package modellayer;
import java.util.ArrayList;

public class Compendium {
	private int compendiumID;
	private String name, date;
	private ArrayList<Chart> charts;
	
	public Compendium(int compendiumID, String name, String date) {
		super();
		this.compendiumID = compendiumID;
		this.name = name;
		this.date = date;
		this.charts = new ArrayList<Chart>();
	}
	
	public Compendium() {
		this.charts = new ArrayList<Chart>();
	}
	
	/**
	 * @return the compendiumID
	 */
	public int getCompendiumID() {
		return compendiumID;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * @return the charts
	 */
	public ArrayList<Chart> getCharts() {
		return charts;
	}
	/**
	 * @param compendiumID the compendiumID to set
	 */
	public void setCompendiumID(int compendiumID) {
		this.compendiumID = compendiumID;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * @param charts the charts to set
	 */
	public void setCharts(ArrayList<Chart> charts) {
		this.charts = charts;
	}
	

	

}
