/**
 * Handles the class City
 * 
 * Authors: Jimmy Møller, Mikkel Petersen, Tue Brodersen, Thomas Bonderup and Christian Schmidt 
 * Date: 26.May 2014.
 */
package modellayer;

public class City {

	private int zipCode;
	private String city;
	
	/**
	 * Constructor for the class City
	 * @param zipCode
	 * @param city
	 */
	public City(int zipCode, String city) {
		super();
		this.zipCode = zipCode;
		this.city = city;
	}
	
	/**
	 * Empty constructor
	 */
	public City() {
		
	}

	/**
	 * @return the zipCode
	 */
	public int getZipCode() {
		return zipCode;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * Compares two objects of City
	 * @param c City
	 * @return true //if the objects are equal
	 * @return false //if the objects are not equal
	 */
	public boolean equals(City c)
	{
		boolean result = false;
		
		if (c == null)
		{
			result = false;
		}
		if (!(c instanceof City))
		{
			result = false;
		}
		
		result = this.zipCode == c.getZipCode() && this.city.equals(c.getCity());
		
		return result;
	}
	
}
