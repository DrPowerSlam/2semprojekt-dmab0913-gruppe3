package controllayer;

public class ValidateCtr {

	public ValidateCtr() {
		
	}
	
	public boolean validateInt(int intToBeValidated, int from, int too) {
		boolean result = false;
		
		if(intToBeValidated > from && intToBeValidated < too) {
			result = true;
		}
		
		return result;
	}
	
	public boolean stringMinLength(String stringToBeValidated, int minLength) {
		boolean result = true;
		
		if(stringToBeValidated.length() <= minLength) {
			result = false;
		}
		
		return result;
	}
	
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
