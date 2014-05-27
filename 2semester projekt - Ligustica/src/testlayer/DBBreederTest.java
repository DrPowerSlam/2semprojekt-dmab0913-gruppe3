package testlayer;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import modellayer.Breeder;
import modellayer.City;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dblayer.DBBreeder;
import dblayer.DBCity;
import dblayer.DBConnection;
public class DBBreederTest {
	
	private static dblayer.DBBreeder testBreederDB = new DBBreeder();
	private static Breeder testBreederUpdate;
	private static Breeder testBreederInsert = new Breeder();
	private static Breeder testBreederDelete = new Breeder();
	private static City testCity = new City(2500, "Valby");

	
	@BeforeClass
	/**
	 * 
	 * @throws SQLException
	 * @throws FileNotFoundException
	 */
	public static void testSetup() throws SQLException, FileNotFoundException{
		
		dblayer.DBConnection.getInstance().insertDatabaseData();
				
		testBreederUpdate = testBreederDB.selectSingleBreeder(1, true);
				
		testBreederInsert.setFname("INSERT");
		testBreederInsert.setLname("TEST");
		testBreederInsert.setAddress("Sofiendalsvej 60");
		testBreederInsert.setPhone("12345678");
		testBreederInsert.setEmail("ikke@admin.dk");
		testBreederInsert.setPassword("test123");
		testBreederInsert.setAdmin(false);
		testBreederInsert.setCity(testCity);
		
		testBreederUpdate.setFname("UPDATE");
		testBreederUpdate.setLname("SUCCESS");
		testBreederUpdate.setAddress("Sofiendalsvej");
		testBreederUpdate.setPhone("24657622");
		testBreederUpdate.setEmail("afq@goms.dk");
		testBreederUpdate.setPassword("pass1234");
		testBreederUpdate.setAdmin(false);
		testBreederUpdate.setCity(testCity);
		
		//testBreederDelete = testBreederDB.selectSingleBreeder(2, true);
		testBreederDelete.setBreederID(4);
				
		testBreederDB.insertBreeder(testBreederDelete);	
	}

	@AfterClass
	public static void testCleanup() throws SQLException, FileNotFoundException {
		
		dblayer.DBConnection.getInstance().insertDatabaseData();
		
	}
	
	/**
	 * Tests if you can insert a Breeder into the database
	 * @throws SQLException
	 */
	@Test
	public void testInsertBreeder() throws SQLException{
		assertEquals("Insert failed", 1, testBreederDB.insertBreeder(testBreederInsert));
	}

	/**
	 * Tests if you can update a variable on a breeder in the database
	 * @throws SQLException
	 */
	@Test
	public void testUpdateBreeder() throws SQLException {
		assertEquals("update failed", 1, testBreederDB.updateBreeder(testBreederUpdate));
	}

	/**
	 * Tests if you can delete a breeder in the database
	 * @throws SQLException
	 */
	@Test
	public void testDeleteBreeder() throws SQLException {		
		assertEquals("The Breeder was not deleted", 1, testBreederDB.deleteBreeder(testBreederDelete));	
	}
	
	
	/**
	 * Tests if you can select all breeders in the database
	 * @throws SQLException
	 */
	@Test
	public void testGetAllBreeder() throws SQLException {
		assertNotNull(testBreederDB.getAllBreeders(true).size());
	}
	
	
	
	
}
