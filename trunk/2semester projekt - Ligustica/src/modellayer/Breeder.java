/**
 * Handles the class Breeder
 * 
 * Authors: Jimmy Møller, Mikkel Petersen, Tue Brodersen, Thomas Bonderup and Christian Schmidt 
 * Date: 26.May 2014.
 */
package modellayer;

import dblayer.IFDBBreeder;

public class Breeder {
	
	private int breederID;
	

	private String fname, lname, address, phone, email, password;
	private boolean isAdmin;
	private City city;
	
	/**
	 * Constructor for the class Breeder
	 * @param fname
	 * @param lname
	 * @param address
	 * @param phone
	 * @param email
	 * @param password
	 * @param isAdmin
	 * @param breederID
	 */
	public Breeder(String fname, String lname, String address, String phone, String email, 
			String password, boolean isAdmin, int breederID) 
	{
		this.fname = fname;
		this.lname = lname;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.isAdmin = isAdmin;
		this.breederID = breederID;
		
	}
	
	/**
	 * Empty constructor
	 */
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
	 * @return the city
	 */
	public City getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(City city) {
		this.city = city;
	}
	
	/**
	 * Compares two objects of Breeder
	 * @param b Breeder
	 * @return true //if the objects are equal
	 * @return false //if the objects are not equal
	 */
	public boolean equals(Breeder b)
	{
		boolean result = false;
		
		if (b == null)
		{
			result = false;
		}
		if (!(b instanceof Breeder))
		{
			result = false;
		}
		
		result = this.address.equals(b.address) && this.breederID == b.getBreederID() && this.fname.equals(b.fname)
				&& this.lname.equals(b.lname)&& this.phone.equals(b.phone)&& this.email.equals(b.email)&& this.password.equals(b.password)
				&& this.city.equals(b.city)&& this.isAdmin == b.isAdmin;
		
		return result;
	}

}
