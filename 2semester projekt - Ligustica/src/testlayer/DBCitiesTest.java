/**
 * /**
 * Handles the class DBCitiesTest.
 * 
 * Author: Group 3
 * Date: 07-04-2014 Version: 1.0
 */
 
package testlayer;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import modellayer.City;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dblayer.DBCity;
import dblayer.DBConnection;

public class DBCitiesTest {

	private static dblayer.DBCity testCityDB = new DBCity();
	private static City testCityUpdate = new City();
	private static City testCityInsert = new City();
	private static City testCityDelete = new City();
	private static DBConnection testConnection = DBConnection.getInstance();
	
	@BeforeClass
	/**
	 * 
	 * @throws SQLException
	 * @throws FileNotFoundException
	 */
	public static void testSetup() throws SQLException, FileNotFoundException{
		
		testConnection.insertDatabaseData();
		dblayer.DBConnection.getInstance().insertDatabaseData();
		
		testCityInsert.setCity("Aalborg");
		testCityInsert.setZipCode(800454521);
		
		testCityUpdate.setCity("Odense");
		testCityUpdate.setZipCode(79495);
		
		testCityDelete.setCity("Esbjerg");
		testCityDelete.setZipCode(876785);
		
		testCityDB.insertCity(testCityDelete);
		testCityDB.insertCity(testCityUpdate);
		
			}

	@AfterClass
	public static void testCleanup() throws SQLException, FileNotFoundException {
		
		dblayer.DBConnection.getInstance().insertDatabaseData();
		
	}
	
	/**
	 * Tests if you can insert a city into the database
	 * @throws SQLException
	 */
	@Test
	public void testInsertCity() throws SQLException{
		int zipCode = testCityInsert.getZipCode();
		assertNotSame("The city was not inserted", -1, testCityDB.insertCity(testCityInsert));
		assertEquals(testCityInsert.equals(testCityDB.selectSingleCity(zipCode)) ,true);
	}

	/**
	 * Tests if you can update a variable on a city in the database
	 * @throws SQLException
	 */
	@Test
	public void testUpdateCity() throws SQLException {
		
		int zipcode = testCityUpdate.getZipCode();
		testCityUpdate.setCity("Aa2");
		assertNotSame("The city was not updated", -1, testCityDB.updateCity(testCityUpdate));
		assertEquals("Aa2", testCityDB.selectSingleCity(zipcode).getCity());
	}

	/**
	 * Tests if you can delete a city in the database
	 * @throws SQLException
	 */
	@Test
	public void testDeleteCity() throws SQLException {
		int zipcode = testCityDelete.getZipCode();
		
		assertNotSame("The city was not deleted", -1, testCityDB.deleteCity(testCityDelete));
		assertNull(testCityDB.deleteCity(testCityDB.selectSingleCity(zipcode)));
		
	}
	
	/**
	 * Tests if you can select a city in the database
	 * @throws SQLException
	 */
	@Test
	public void testSelectSingleCity() throws SQLException {
		int zipcode = testCityInsert.getZipCode();
		assertEquals(testCityInsert.equals(testCityDB.selectSingleCity(zipcode)), true);
	}
	
	/**
	 * Tests if you can select all cities in the database
	 * @throws SQLException
	 */
	@Test
	public void testGetAllCities() throws SQLException {
		assertNotNull(testCityDB.getAllCities().size());
	}

}