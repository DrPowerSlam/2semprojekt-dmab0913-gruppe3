package modellayer;

public class Breeder {
	
	private int breederID;
	

	private String fname, lname, address, phone, email, password;
	private boolean isAdmin;
	private City cities;
	
	public Breeder(String fname, String lname, String address, String phone, String email, 
			String password, boolean isAdmin, int breederID, City cities) 
	{
		this.fname = fname;
		this.lname = lname;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.isAdmin = isAdmin;
		this.breederID = breederID;
		this.cities = cities;
	}
	
	public Breeder() {
		
	}
	
	/**
	 * @return the breederId
	 */
	public int getBreederID() {
		return breederID;
	}

	/**
	 * @param breederId the breederId to set
	 */
	public void setBreederID(int breederID) {
		this.breederID = breederID;
	}

	/**
	 * @return the fname
	 */
	public String getFname() {
		return fname;
	}

	/**
	 * @param fname the fname to set
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}

	/**
	 * @return the lname
	 */
	public String getLname() {
		return lname;
	}

	/**
	 * @param lname the lname to set
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the isAdmin
	 */
	public boolean isAdmin() {
		return isAdmin;
	}

	/**
	 * @param isAdmin the isAdmin to set
	 */
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	/**
	 * @return the cities
	 */
	public City getCities() {
		return cities;
	}

	/**
	 * @param cities the cities to set
	 */
	public void setCities(City cities) {
		this.cities = cities;
	}

}
