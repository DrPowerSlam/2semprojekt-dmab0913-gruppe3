package modellayer;
import java.util.ArrayList;

public class Queen {

	private int queenID, year, honeyYield, swarmTendency, nosema, temper, honeycomFirmness, clensingAbility;
	private String name;
	private Queen mother;
	private Queen fathersMother;
	private Breeder breeder;
	private ArrayList<Queen> children;
	private boolean isAlive;
	
	public Queen(int queenID, int year, int honeyYield, int swarmTendency,
			int nosema, int temper, int honeycomFirmness, int clensingAbility,
			String name, Queen mother, Queen fathersMother, Breeder breeder) {
		this.queenID = queenID;
		this.year = year;
		this.honeyYield = honeyYield;
		this.swarmTendency = swarmTendency;
		this.nosema = nosema;
		this.temper = temper;
		this.honeycomFirmness = honeycomFirmness;
		this.clensingAbility = clensingAbility;
		this.name = name;
		this.mother = mother;
		this.fathersMother = fathersMother;
		this.breeder = breeder;
		this.children = new ArrayList<Queen>();
	}
	
	public Queen() {
		new ArrayList<Queen>();
	}

	/**
	 * @return the isAlive
	 */
	public boolean isAlive() {
		return isAlive;
	}

	/**
	 * @param isAlive the isAlive to set
	 */
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	/**
	 * @return the queenID
	 */
	public int getQueenID() {
		return queenID;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the mother
	 */
	public Queen getMother() {
		return mother;
	}

	/**
	 * @return the fathersMother
	 */
	public Queen getFathersMother() {
		return fathersMother;
	}

	/**
	 * @return the breeder
	 */
	public Breeder getBreeder() {
		return breeder;
	}

	/**
	 * @return the children
	 */
	public ArrayList<Queen> getChildren() {
		return children;
	}

	/**
	 * @param queenID the queenID to set
	 */
	public void setQueenID(int queenID) {
		this.queenID = queenID;
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
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param mother the mother to set
	 */
	public void setMother(Queen mother) {
		this.mother = mother;
	}

	/**
	 * @param fathersMother the fathersMother to set
	 */
	public void setFathersMother(Queen fathersMother) {
		this.fathersMother = fathersMother;
	}

	/**
	 * @param breeder the breeder to set
	 */
	public void setBreeder(Breeder breeder) {
		this.breeder = breeder;
	}

	/**
	 * @param children the children to set
	 */
	public void setChildren(ArrayList<Queen> children) {
		this.children = children;
	}
	
	public boolean equals(Queen q)
	{
		boolean result = false;
		
		if (q == null)
		{
			result = false;
		}
		if (!(q instanceof Queen))
		{
			result = false;
		}
		 result = this.queenID == q.queenID && this.year == q.year && this.honeyYield == q.honeyYield && this.swarmTendency == q.swarmTendency
				 && this.nosema == q.nosema && this.temper == q.temper && this.honeycomFirmness == q.honeycomFirmness && this.clensingAbility == q.clensingAbility
				 && this.name.equals(q.name)&& this.mother.equals(q.mother)&& this.fathersMother.equals(q.fathersMother)&& this.breeder.equals(q.breeder)
				 && this.children.equals(q.children);
		
		return result;
	}

}
