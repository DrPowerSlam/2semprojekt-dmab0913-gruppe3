package modellayer;
import java.util.ArrayList;

public class Chart {

	
private int chartID, year, honeyYield, swarmTendency, nosema, temper, honeycomFirmness, clensingAbility;
 private Breeder breeder;
private ArrayList<Queen> queens;

public Chart(int chartID, int year, int honeyYield, int swarmTendency,
		int nosema, int temper, int honeycomFirmness, int clensingAbility,
		Breeder breeder) {
	super();
	this.chartID = chartID;
	this.year = year;
	this.honeyYield = honeyYield;
	this.swarmTendency = swarmTendency;
	this.nosema = nosema;
	this.temper = temper;
	this.honeycomFirmness = honeycomFirmness;
	this.clensingAbility = clensingAbility;
	this.breeder = breeder;
	this.queens = new ArrayList<Queen>();
	
}

public Chart() {
	this.queens = new ArrayList<Queen>();
}

/**
 * @return the chartId
 */
public int getChartID() {
	return chartID;
}
/**
 * @return the year
 */
public int getYear() {
	return year;
}
/**
 * @return the honeyYield
 */
public int getHoneyYield() {
	return honeyYield;
}
/**
 * @return the swarmTendency
 */
public int getSwarmTendency() {
	return swarmTendency;
}
/**
 * @return the nosema
 */
public int getNosema() {
	return nosema;
}
/**
 * @return the temper
 */
public int getTemper() {
	return temper;
}
/**
 * @return the honeycomFirmness
 */
public int getHoneycomFirmness() {
	return honeycomFirmness;
}
/**
 * @return the clensingAbility
 */
public int getClensingAbility() {
	return clensingAbility;
}
/**
 * @return the breeder
 */
public Breeder getBreeder() {
	return breeder;
}
/**
 * @param chartId the chartId to set
 */
public void setChartID(int chartID) {
	this.chartID = chartID;
}
/**
 * @param year the year to set
 */
public void setYear(int year) {
	this.year = year;
}
/**
 * @param honeyYield the honeyYield to set
 */
public void setHoneyYield(int honeyYield) {
	this.honeyYield = honeyYield;
}
/**
 * @param swarmTendency the swarmTendency to set
 */
public void setSwarmTendency(int swarmTendency) {
	this.swarmTendency = swarmTendency;
}
/**
 * @param nosema the nosema to set
 */
public void setNosema(int nosema) {
	this.nosema = nosema;
}
/**
 * @param temper the temper to set
 */
public void setTemper(int temper) {
	this.temper = temper;
}
/**
 * @param honeycomFirmness the honeycomFirmness to set
 */
public void setHoneycomFirmness(int honeycomFirmness) {
	this.honeycomFirmness = honeycomFirmness;
}
/**
 * @param clensingAbility the clensingAbility to set
 */
public void setClensingAbility(int clensingAbility) {
	this.clensingAbility = clensingAbility;
}
/**
 * @param breeder the breeder to set
 */
public void setBreeder(Breeder breeder) {
	this.breeder = breeder;
}

}
