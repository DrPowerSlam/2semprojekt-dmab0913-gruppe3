package dblayer;

import java.sql.*;
import java.util.ArrayList;

import modellayer.City;

/**
 * @author Gruppe 3
 */
public interface IFDBCity {
	 
	public ArrayList<City> getAllCity(); 

	public City selectSingleCity(int zipCode) throws SQLException;

	public int insertCity(City c) throws SQLException;

	public int updateCity(City c) throws SQLException; 

	public int deleteCity(City c) throws SQLException; 
    

}
