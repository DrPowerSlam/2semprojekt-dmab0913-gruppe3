package modellayer;

public class PartChart {

	private int partChartID, year, honeyYieldYear, swarmTendency, nosema, temper, honeycomFirmness, clensingAbility;
	private Chart chart;
	private Queen queen;
	private String honeyYield;
	
	public PartChart(Chart chart, Queen queen) {
		this.chart = chart;
		this.queen = queen;
	}
	
	public PartChart() {
		
	}

	/**
	 * @return the partChartID
	 */
	public int getPartChartID() {
		return partChartID;
	}

	/**
	 * @param partChartID the partChartID to set
	 */
	public void setPartChartID(int partChartID) {
		this.partChartID = partChartID;
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
	 * @return the honeyYield
	 */
	public String getHoneyYield() {
		return honeyYield;
	}

	/**
	 * @param honeyYield the honeyYield to set
	 */
	public void setHoneyYield(String honeyYield) {
		this.honeyYield = honeyYield;
	}

	/**
	 * @return the swarmTendency
	 */
	public int getSwarmTendency() {
		return swarmTendency;
	}

	/**
	 * @param swarmTendency the swarmTendency to set
	 */
	public void setSwarmTendency(int swarmTendency) {
		this.swarmTendency = swarmTendency;
	}

	/**
	 * @return the nosema
	 */
	public int getNosema() {
		return nosema;
	}

	/**
	 * @param nosema the nosema to set
	 */
	public void setNosema(int nosema) {
		this.nosema = nosema;
	}

	/**
	 * @return the temper
	 */
	public int getTemper() {
		return temper;
	}

	/**
	 * @param temper the temper to set
	 */
	public void setTemper(int temper) {
		this.temper = temper;
	}

	/**
	 * @return the honeycomFirmness
	 */
	public int getHoneycomFirmness() {
		return honeycomFirmness;
	}

	/**
	 * @param honeycomFirmness the honeycomFirmness to set
	 */
	public void setHoneycomFirmness(int honeycomFirmness) {
		this.honeycomFirmness = honeycomFirmness;
	}

	/**
	 * @return the clensingAbility
	 */
	public int getClensingAbility() {
		return clensingAbility;
	}

	/**
	 * @param clensingAbility the clensingAbility to set
	 */
	public void setClensingAbility(int clensingAbility) {
		this.clensingAbility = clensingAbility;
	}

	/**
	 * @return the honeyYieldYear
	 */
	public int getHoneyYieldYear() {
		return honeyYieldYear;
	}

	/**
	 * @param honeyYieldYear the honeyYieldYear to set
	 */
	public void setHoneyYieldYear(int honeyYieldYear) {
		this.honeyYieldYear = honeyYieldYear;
	}

	/**
	 * @return the chart
	 */
	public Chart getChart() {
		return chart;
	}

	/**
	 * @param chart the chart to set
	 */
	public void setChart(Chart chart) {
		this.chart = chart;
	}

	/**
	 * @return the queen
	 */
	public Queen getQueen() {
		return queen;
	}

	/**
	 * @param queen the queen to set
	 */
	public void setQueen(Queen queen) {
		this.queen = queen;
	}

	
	
}
