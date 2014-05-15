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
	
	public void logOut() {
		settings.setBreeder(null);
	}

}
