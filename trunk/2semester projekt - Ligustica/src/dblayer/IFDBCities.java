package dblayer;

import java.sql.*;
import java.util.ArrayList;

import modellayer.Cities;

/**
 * @author Gruppe 3
 */
public interface IFDBCities {
	 
	public ArrayList<Cities> getAllCities(); 

	public Cities selectSingleCity(int zipCode) throws SQLException;

	public int insertCity(Cities c) throws SQLException;

	public int updateCity(Cities c) throws SQLException; 

	public int deleteCity(Cities c) throws SQLException; 
    

}
