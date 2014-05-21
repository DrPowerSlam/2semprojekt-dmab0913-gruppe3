package controllayer;

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

}
