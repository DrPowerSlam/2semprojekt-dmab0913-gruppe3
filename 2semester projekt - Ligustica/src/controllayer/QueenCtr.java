/**
 * Handles the class queen controller
 * 
 * Authors: Jimmy Møller, Mikkel Petersen, Tue Brodersen, Thomas Bonderup and Christian Schmidt 
 * Date: 26.May 2014.
 */
package controllayer;

import java.sql.SQLException;
import java.util.ArrayList;

import modellayer.Breeder;
import modellayer.Queen;
import modellayer.Settings;
import dblayer.DBBreeder;
import dblayer.DBQueen;
import dblayer.IFDBQueen;

public class QueenCtr {

	private IFDBQueen dbQ;
	private Settings settings;
	
	/**
	 * The constructor for the queen controller. Gets the instance of the settings class
	 */
	public QueenCtr() {
		dbQ = new DBQueen();
		settings = Settings.getInstance();
	}
	
	/**
	 * Gets an arraylist of all the queens in the database
	 * @return An arraylist with all the queens
	 */
	public ArrayList<Queen> getAllQueens() {
		return dbQ.getAllQueens(true);
	}
	
	/**
	 * Gets an arraylist of all the queens a breeder owns
	 * @param breeder The breeder who owns the queens
	 * @return An arraylist with all the queens the breeder owns
	 */
	public ArrayList<Queen> findQueensByBreeder(Breeder breeder) {
		ArrayList<Queen> theListToReturn = new ArrayList<Queen>();
		
		ArrayList<Queen> allQueens = getAllQueens();
		
		for(Queen q : allQueens) {
			if(q.getBreeder().getBreederID() == breeder.getBreederID()) {
				theListToReturn.add(q);
			}
		}
		
		return theListToReturn;
	}
	
	/**
	 * Gets a queen in the database with a given ID
	 * @param queenID The ID to search for
	 * @return The queen object that was searched for
	 */
	public Queen getQueenByID(int queenID) {
		Queen queen = null;
		try {
			queen = dbQ.selectSingleQueen(queenID, true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return queen;
	}

}
