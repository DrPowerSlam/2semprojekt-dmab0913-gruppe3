/**
 * Handles the class breeder controller
 * 
 * Authors: Jimmy Møller, Mikkel Petersen, Tue Brodersen, Thomas Bonderup and Christian Schmidt 
 * Date: 26.May 2014.
 */
package controllayer;

import java.sql.SQLException;
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
	
	/**
	 * Insert breeder to DB
	 * @return 1 if succeeded, -1 if failed.
	 */
	public int insertBreeder(String fname, String lname, String address, String phone, String email, 
			String password, boolean isAdmin, int breederID, City city) 
	{
		int returnValue = 0;
		
		//create breeder object
		Breeder b = new Breeder(fname, lname, address, phone, email, password, isAdmin, breederID);
		b.setCity(city);
		
		//insert to db
		try {
			returnValue = dbB.insertBreeder(b);
		} catch (SQLException e) {
			e.printStackTrace();
			returnValue = -1;
		}
		return returnValue;
	}
	
	/**
	 * Get a breeder object from DB
	 * @param breederID
	 * @return a Breeder object if success, null if fail.
	 */
	public Breeder getBreeder(int breederID)
	{		
		Breeder b = null;
		
		try {
			b = dbB.selectSingleBreeder(breederID, true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	/**
	 * Delete a breeder from DB
	 * @param breederID
	 * @return 1 if delete succeeded, -1 if failed.
	 */
	public int deleteBreeder(int breederID)
	{
		int returnValue = 0;
		//create breeder object and set breederID. Only breederID is used to delete so not necessary to get breeder from db.
		Breeder b = new Breeder();
		b.setBreederID(breederID);
		
		//delete from db
		try {
			returnValue = dbB.deleteBreeder(b);
		} catch (SQLException e) {
			e.printStackTrace();
			returnValue = -1;
		}
		
		return returnValue;
	}
	
	/**
	 * Update breeder in DB
	 * @return 1 if update succeeded, -1 if failed.
	 */
	public int updateBreeder(String fname, String lname, String address, String phone, String email, 
			String password, boolean isAdmin, int breederID, City city)
	{
		int returnValue = 0;
		Breeder b = new Breeder(fname, lname, address, phone, email, password, isAdmin, breederID);
		b.setCity(city);
		
		try {
			returnValue = dbB.updateBreeder(b);
		} catch (SQLException e) {
			e.printStackTrace();
			returnValue = -1;
		}
				
		return returnValue;
	}

}
