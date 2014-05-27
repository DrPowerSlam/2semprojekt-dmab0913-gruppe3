package testlayer;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import modellayer.Queen;
import modellayer.Breeder;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dblayer.DBQueen;

import dblayer.DBConnection;
public class DBQueenTest {	
	private static dblayer.DBQueen testQueenDB = new DBQueen();
	private static Queen testQueenUpdate = new Queen();
	private static Queen testQueenInsert = new Queen();
	private static Queen testQueenDelete = new Queen();
	private static Queen testQueenMother;
	private static Queen testQueenFathersmother;
	private static DBConnection testConnection = DBConnection.getInstance();
	private static Breeder testBreeder = new Breeder();
	

	
	@BeforeClass
	/**
	 * 
	 * @throws SQLException
	 * @throws FileNotFoundException
	 */
	public static void testSetup() throws SQLException, FileNotFoundException{		
		
		testQueenMother = testQueenDB.selectSingleQueen(1, true);
		testQueenFathersmother = testQueenDB.selectSingleQueen(2, true);
		
		testBreeder.setBreederID(1);
		
		testConnection.insertDatabaseData();
		
		testQueenInsert.setQueenID(4);
		testQueenInsert.setYear(2014);
		testQueenInsert.setHoneyYield(4);
		testQueenInsert.setSwarmTendency(4);
		testQueenInsert.setNosema(4);
		testQueenInsert.setTemper(5);
		testQueenInsert.setHoneycomFirmness(5);
		testQueenInsert.setClensingAbility(3);
		testQueenInsert.setName("TEST");
		testQueenInsert.setMother(testQueenMother);
		testQueenInsert.setFathersMother(testQueenFathersmother);
		testQueenInsert.setBreeder(testBreeder);
		
		testQueenUpdate.setQueenID(5);
		testQueenUpdate.setYear(2014);
		testQueenUpdate.setHoneyYield(4);
		testQueenUpdate.setSwarmTendency(4);
		testQueenUpdate.setNosema(4);
		testQueenUpdate.setTemper(5);
		testQueenUpdate.setHoneycomFirmness(5);
		testQueenUpdate.setClensingAbility(3);
		testQueenUpdate.setName("TEST2");
		testQueenUpdate.setMother(testQueenMother);
		testQueenUpdate.setFathersMother(testQueenFathersmother);
		testQueenUpdate.setBreeder(testBreeder);
		
		testQueenDelete.setQueenID(6);
		testQueenDelete.setYear(2014);
		testQueenDelete.setHoneyYield(4);
		testQueenDelete.setSwarmTendency(4);
		testQueenDelete.setNosema(4);
		testQueenDelete.setTemper(5);
		testQueenDelete.setHoneycomFirmness(5);
		testQueenDelete.setClensingAbility(3);
		testQueenDelete.setName("TEST3");
		testQueenDelete.setMother(testQueenMother);
		testQueenDelete.setFathersMother(testQueenFathersmother);
		testQueenDelete.setBreeder(testBreeder);
		
		testQueenDB.insertQueen(testQueenDelete);
		testQueenDB.insertQueen(testQueenUpdate);	
	}

	@AfterClass
	public static void testCleanup() throws SQLException, FileNotFoundException {		
		testConnection.insertDatabaseData();		
	}
	
	/**
	 * Tests if you can insert a Queen into the database
	 * @throws SQLException
	 */
	@Test
	public void testInsertQueen() throws SQLException{
		assertEquals("Insert failed", 1, testQueenDB.insertQueen(testQueenInsert));
	}

	/**
	 * Tests if you can update a variable on a Queen in the database
	 * @throws SQLException
	 */
	@Test
	public void testUpdateQueen() throws SQLException {
		assertEquals("Update failed", 1, testQueenDB.updateQueen(testQueenUpdate));
		int id = testQueenDB.searchQueenOnName("TEST2", true).getQueenID();
		assertTrue("Updated object is not equal to test object", testQueenUpdate.equals(testQueenDB.selectSingleQueen(id, true)));
	}

	/**
	 * Tests if you can delete a queen in the database
	 * @throws SQLException
	 */
	@Test
	public void testDeleteQueen() throws SQLException {
		assertNotSame("The queen was not deleted", -1, testQueenDB.deleteQueen(testQueenDelete));
	}
	
	
	/**
	 * Tests if you can select all cities in the database
	 * @throws SQLException
	 */
	@Test
	public void testGetAllQueens() throws SQLException {
		assertNotNull(testQueenDB.getAllQueens(true).size());
	}
	
	
	
	
}
