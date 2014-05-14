package dblayer;

import java.sql.*;
import java.util.ArrayList;
import modellayer.Breeder;

/**
 * @author Gruppe 3
 */
public interface IFDBBreeder {
	 
	public ArrayList<Breeder> getAllBreeders(boolean retriveAssociation); 

	public Breeder selectSingleBreeder(int breederID, boolean retriveAssociation) throws SQLException;

	public int insertBreeder(Breeder b) throws SQLException;

	public int updateBreeder(Breeder b) throws SQLException; 

	public int deleteBreeder(Breeder b) throws SQLException; 
    

}
