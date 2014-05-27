package controllayer;
/**
 * Handles the class validate controller
 * 
 * Authors: Jimmy Møller, Mikkel Petersen, Tue Brodersen, Thomas Bonderup and Christian Schmidt 
 * Date: 26.May 2014.
 */
public class ValidateCtr {

	/**
	 * Constructor for the validate controller
	 */
	public ValidateCtr() {
		
	}
	
	/**
	 * Validates an integer in a given range 
	 * @param intToBeValidated The integer to validate
	 * @param from The lowest integer that can be valid
	 * @param too The highest integer that can be valid
	 * @return True if the integer is valid, false if it's invalid
	 */
	public boolean validateInt(int intToBeValidated, int from, int too) {
		boolean result = false;
		
		if(intToBeValidated >= from && intToBeValidated <= too) {
			result = true;
		}
		
		return result;
	}
	
	/**
	 * Validates a string that has to be of a minimum length
	 * @param stringToBeValidated The string that needs to be validated
	 * @param minLength The minimum length of the string
	 * @return True if the string is valid, false if it's invalid
	 */
	public boolean stringMinLength(String stringToBeValidated, int minLength) {
		boolean result = true;
		
		if(stringToBeValidated.length() <= minLength) {
			result = false;
		}
		
		return result;
	}
	/**
	 * Validates a string with a maximum length
	 * @param stringToBeValidated The string to be validated
	 * @param maxLength The maximum length of the string
	 * @return True if the string is valid, false if it's invalid
	 */
	public boolean stringMaxLength(String stringToBeValidated, int maxLength) {
		boolean result = true;
		
		if(stringToBeValidated.length() >= maxLength) {
			result = false;
		}
		
		return result;
	}
	
	public boolean stringExactLength(String stringToBeValidated, int length) {
		boolean result = false;
		
		if(stringToBeValidated.length() == length) {
			result = true;
		}
		
		return result;
	}

}
