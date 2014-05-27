/**
 * Handles the class breeder controller
 * 
 * Authors: Jimmy M�ller, Mikkel Petersen, Tue Brodersen, Thomas Bonderup and Christian Schmidt 
 * Date: 26.May 2014.
 */
package controllayer;

import java.util.ArrayList;

import dblayer.*;
import modellayer.*;

public class BreederCtr {

	private IFDBBreeder dbB;
	private Settings settings;
	
	/**
	 * The constructor for the breeder controller. Gets the instance of the settings class
	 */
	public BreederCtr() {
		dbB = new DBBreeder();
		settings = Settings.getInstance();
	}
	
	/**
	 *  Validates the login.
	 * @param email The login name
	 * @param password The password
	 * @return True if the login is valid, false if it's invalid
	 */
	public boolean validateLogin(String email, String password) {
		boolean result = false;
		
		ArrayList<Breeder> breederList = dbB.getAllBreeders(true);
		
		int index = 0;
		
		while(index < breederList.size() && result == false)
		{
			Breeder breeder = breederList.get(index);
			if(breeder.getEmail().equals(email) && breeder.getPassword().equals(password)){
				settings.setBreeder(breeder);
				result = true;
			}
			index++;
		}
		
		return result;
	}
	
	/**
	 * Logs the breeder out by setting the breeder in the settings class to null.
	 */
	public void logOut() {
		settings.setBreeder(null);
	}
	
	/**
	 * Creates initials of the breeders first and last  name.
	 * @param breeder
	 * @return String with breeders initials
	 */
	public String getInitials(Breeder breeder) {
		String firstLetter = breeder.getFname().substring(0,1);
		String secondLetter = breeder.getLname().substring(0,1);
		
		String initials = firstLetter + secondLetter;
		
		return initials;
	}
	
	public ArrayList<Breeder> getAllBreeders() {
		return dbB.getAllBreeders(true);
	}

}
