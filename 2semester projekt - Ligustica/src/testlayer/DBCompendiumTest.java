/**
 * /**
 * Handles the class DBCompendiumTest.
 * 
 * Author: Group 3
 * Date: 07-04-2014 Version: 1.0
 */
 
package testlayer;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import modellayer.Compendium;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dblayer.DBCompendium;
import dblayer.DBConnection;

public class DBCompendiumTest {

	private static dblayer.DBCompendium testCompendiumDB = new DBCompendium();
	private static Compendium testCompendiumUpdate;
	private static Compendium testCompendiumInsert = new Compendium();
	private static Compendium testCompendiumDelete = new Compendium();
	private static DBConnection testConnection = DBConnection.getInstance();
	
	@BeforeClass
	/**
	 * 
	 * @throws SQLException
	 * @throws FileNotFoundException
	 */
	public static void testSetup() throws SQLException, FileNotFoundException{
		
		//testConnection.insertDatabaseData();
		
		testCompendiumInsert.setCompendiumID(1);
		testCompendiumInsert.setName("Compendie 2013");
		testCompendiumInsert.setDate("30.feb. 2013");
		testCompendiumInsert.setDroneLines("sda");
		
		testCompendiumUpdate = testCompendiumDB.selectSingleCompendium(1, true);
		/*testCompendiumUpdate.setCompendiumID(2);
		testCompendiumUpdate.setName("Compendie 2013");
		testCompendiumUpdate.setDate("30.feb. 2013");
		testCompendiumUpdate.setDroneLines("sda");*/
		
		testCompendiumDelete.setCompendiumID(3);
		testCompendiumDelete.setName("Compendie 2013");
		testCompendiumDelete.setDate("30.feb. 2013");
		testCompendiumDelete.setDroneLines("sda");
	
		
		testCompendiumDB.insertCompendium(testCompendiumDelete);
		testCompendiumDB.insertCompendium(testCompendiumUpdate);
		
			}

	@AfterClass
	public static void testCleanup() throws SQLException, FileNotFoundException {
		
		//dblayer.DBConnection.getInstance().insertDatabaseData();
		
	}
	
	/**
	 * Tests if you can insert a compendium into the database
	 * @throws SQLException
	 */
	@Test
	public void testInsertCompendium() throws SQLException{
		assertEquals("Insert failed", 1, testCompendiumDB.insertCompendium(testCompendiumInsert));
	}

	/**
	 * Tests if you can update a variable on a city in the database
	 * @throws SQLException
	 */
	@Test
	public void testUpdateCompendium() throws SQLException {
		testCompendiumUpdate.setName("TEST");
		testCompendiumUpdate.setDate("TEST");
		testCompendiumUpdate.setDroneLines("TEST");
		assertEquals("Update failed", 1, testCompendiumDB.updateCompendium(testCompendiumUpdate));
		assertTrue("Updated object are not equal to object before update", testCompendiumUpdate.equals(testCompendiumDB.selectSingleCompendium(testCompendiumUpdate.getCompendiumID(), true)));
	}

	/**
	 * Tests if you can delete a city in the database
	 * @throws SQLException
	 */
	@Test
	public void testDeleteCompendium() throws SQLException {		
		assertEquals("Delete failed", 1, testCompendiumDB.deleteCompendium(testCompendiumDelete));		
	}
	
	/**
	 * Tests if you can select a city in the database
	 * @throws SQLException
	 */
	@Test
	public void testSearchCompendiumOnName() throws SQLException {	
		assertTrue("Objects are not equal", testCompendiumInsert.equals(testCompendiumDB.searchCompendiumOnName("Compendie 2013", true)));
	}
	
	/**
	 * Tests if you can select all cities in the database
	 * @throws SQLException
	 */
	@Test
	public void testGetAllCompendium() throws SQLException {
		assertNotNull(testCompendiumDB.getAllCompendiums(true).size());
	}

}