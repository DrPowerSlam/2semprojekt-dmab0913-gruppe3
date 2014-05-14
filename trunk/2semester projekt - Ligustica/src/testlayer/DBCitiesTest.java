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

public class DBCitiesTest {

	private static dblayer.DBCity testCityDB = new DBCity();
	private static City testCityUpdate = new City();
	private static City testCityInsert = new City();
	private static City testCityDelete = new City();
	
//	@BeforeClass
//	/**
//	 * 
//	 * @throws SQLException
//	 * @throws FileNotFoundException
//	 */
//	public static void testSetup() throws SQLException, FileNotFoundException{
//		
//		dblayer.DBConnection.getInstance().resetDatabase();
//		
//		testCitiesInsert.setCity("Aalborg");
//		testCitiesInsert.setZipCode(800454521);
//		
//		testCitiesUpdate.setCity("Odense");
//		testCitiesUpdate.setZipCode(79495);
//		
//		testCitiesDelete.setCity("Esbjerg");
//		testCitiesDelete.setZipCode(876785);
//		
//		testCitiesDB.insertCity(testCitiesDelete);
//		testCitiesDB.insertCity(testCitiesUpdate);
//		
//		
//	}

//	@AfterClass
//	public static void testCleanup() throws SQLException, FileNotFoundException {
//		
//		dblayer.DBConnection.getInstance().resetDatabase();
//		
//	}
//	
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
		assertEquals(zipcode, testCityDB.selectSingleCity(zipcode).getZipCode());
		
	}
	
	/**
	 * Tests if you can select a city in the database
	 * @throws SQLException
	 */
	@Test
	public void testSelectSingleCity() throws SQLException {
		assertEquals(6000, testCityDB.selectSingleCity(6000).getZipCode());		
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