package modellayer;

public class City {

	private int zipCode;
	private String city;
	
	public City(int zipCode, String city) {
		super();
		this.zipCode = zipCode;
		this.city = city;
	}
	
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