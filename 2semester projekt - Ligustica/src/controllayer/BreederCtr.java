package controllayer;

import java.util.ArrayList;

import dblayer.*;
import modellayer.*;

public class BreederCtr {

	private IFDBBreeder dbB;
	private Settings settings;
	
	public BreederCtr() {
		dbB = new DBBreeder();
		settings = Settings.getInstance();
	}
	
	/**
	 * 
	 * @param email
	 * @param password
	 * @return
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
	 * 
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

}
