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
	
	public QueenCtr() {
		dbQ = new DBQueen();
		settings = Settings.getInstance();
	}
	
	public ArrayList<Queen> getAllQueens() {
		return dbQ.getAllQueens(true);
	}
	
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
