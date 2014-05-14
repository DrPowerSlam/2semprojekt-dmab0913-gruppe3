package dblayer;

import java.sql.*;
import java.util.ArrayList;

import modellayer.Queen;

/**
 * @author Gruppe 3
 */
public interface IFDBQueen {
	 
	public ArrayList<Queen> getAllQueens(boolean retriveAssociation); 

	public Queen searchQueenOnName(String name, boolean retriveAssociation) throws SQLException;

	public Queen selectSingleQueen(int queenID, boolean retriveAssociation) throws SQLException;

	public int insertQueen(Queen q) throws SQLException; 

	public int updateQueen(Queen q) throws SQLException; 
	
	public int deleteQueen(Queen q) throws SQLException;
  
}
