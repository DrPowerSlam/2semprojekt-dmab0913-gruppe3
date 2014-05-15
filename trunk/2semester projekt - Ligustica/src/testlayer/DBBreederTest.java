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
	private static Breeder testBreederUpdate = new Breeder();
	private static Breeder testBreederInsert = new Breeder();
	private static Breeder testBreederDelete = new Breeder();
	private static DBCity testCityDB = new DBCity();
	private static DBConnection testConnection = DBConnection.getInstance();
	private static City testCity = new City();

	
	@BeforeClass
	/**
	 * 
	 * @throws SQLException
	 * @throws FileNotFoundException
	 */
	public static void testSetup() throws SQLException, FileNotFoundException{
		
		dblayer.DBConnection.getInstance().insertDatabaseData();
		
		
		testConnection.insertDatabaseData();
		
		testBreederInsert.setFname("Klaus");
		testBreederInsert.setLname("Klausen");
		testBreederInsert.setAddress("Sofiendalsvej");
		testBreederInsert.setPhone("24657621");
		testBreederInsert.setEmail("asdoasdk@goms.dk");
		testBreederInsert.setPassword("pass1234");
		testBreederInsert.setAdmin(false);
		testBreederInsert.setBreederID(1);
		testBreederInsert.setCity(testCity);
		
		testBreederUpdate.setFname("Christian");
		testBreederUpdate.setLname("Christiansen");
		testBreederUpdate.setAddress("Sofiendalsvej");
		testBreederUpdate.setPhone("24657622");
		testBreederUpdate.setEmail("afq@goms.dk");
		testBreederUpdate.setPassword("pass1234");
		testBreederUpdate.setAdmin(false);
		testBreederUpdate.setBreederID(2);
		testBreederUpdate.setCity(testCity);
		
		
		testBreederDelete.setFname("Hans");
		testBreederDelete.setLname("Hansen");
		testBreederDelete.setAddress("Sofiendalsvej");
		testBreederDelete.setPhone("24657623");
		testBreederDelete.setEmail("afg@goms.dk");
		testBreederDelete.setPassword("pass1234");
		testBreederDelete.setAdmin(false);
		testBreederDelete.setBreederID(3);
		testBreederDelete.setCity(testCity);
		
		
		testBreederDB.insertBreeder(testBreederDelete);
		testBreederDB.insertBreeder(testBreederUpdate);	
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
		assertNotSame("The Breeder was not inserted", -1, testBreederDB.insertBreeder(testBreederInsert));
		assertEquals(testBreederInsert.equals(testBreederDB.selectSingleBreeder(1, true)) ,true);
	}

	/**
	 * Tests if you can update a variable on a city in the database
	 * @throws SQLException
	 */
	@Test
	public void testUpdateCity() throws SQLException {
		
		testBreederUpdate.setFname("Klaus");
		assertNotSame("The Breeder was not updated", -1, testBreederDB.updateBreeder(testBreederUpdate));
		assertEquals(testBreederInsert.equals(testBreederDB.selectSingleBreeder(2, true)) ,true);
	}

	/**
	 * Tests if you can delete a city in the database
	 * @throws SQLException
	 */
	@Test
	public void testDeleteBreeder() throws SQLException {
		
		
		assertNotSame("The Breeder was not deleted", -1, testBreederDB.deleteBreeder(testBreederDelete));
		assertNull(testBreederDB.selectSingleBreeder(3, true));
		
	}
	
	/**
	 * Tests if you can select a city in the database
	 * @throws SQLException
	 */
	@Test
	public void testSelectSingleBreeder() throws SQLException {
		assertEquals(testBreederInsert.equals(testBreederDB.selectSingleBreeder(1, true)), true);		
	}
	
	/**
	 * Tests if you can select all cities in the database
	 * @throws SQLException
	 */
	@Test
	public void testGetAllBreeder() throws SQLException {
		assertNotNull(testBreederDB.getAllBreeders(true).size());
	}
	
	
	
	
}
