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
	private static DBConnection testConnection = DBConnection.getInstance();
	private static Breeder testBreeder = new Breeder();
	

	
	@BeforeClass
	/**
	 * 
	 * @throws SQLException
	 * @throws FileNotFoundException
	 */
	public static void testSetup() throws SQLException, FileNotFoundException{
		
		dblayer.DBConnection.getInstance().insertDatabaseData();
		
		
		testConnection.insertDatabaseData();
		
		testQueenInsert.setQueenID(1);
		testQueenInsert.setYear(2012);
		testQueenInsert.setHoneyYield(4);
		testQueenInsert.setSwarmTendency(4);
		testQueenInsert.setNosema(4);
		testQueenInsert.setTemper(5);
		testQueenInsert.setHoneycomFirmness(5);
		testQueenInsert.setClensingAbility(3);
		testQueenInsert.setName("KS023");
		testQueenInsert.setMother(testQueenDelete);
		testQueenInsert.setFathersMother(testQueenUpdate);
		testQueenInsert.setBreeder(testBreeder);
		
		testQueenUpdate.setQueenID(2);
		testQueenUpdate.setYear(2012);
		testQueenUpdate.setHoneyYield(4);
		testQueenUpdate.setSwarmTendency(4);
		testQueenUpdate.setNosema(4);
		testQueenUpdate.setTemper(5);
		testQueenUpdate.setHoneycomFirmness(5);
		testQueenUpdate.setClensingAbility(3);
		testQueenUpdate.setName("KS024");
		testQueenUpdate.setMother(testQueenInsert);
		testQueenUpdate.setFathersMother(testQueenDelete);
		testQueenUpdate.setBreeder(testBreeder);
		
		testQueenDelete.setQueenID(3);
		testQueenDelete.setYear(2012);
		testQueenDelete.setHoneyYield(4);
		testQueenDelete.setSwarmTendency(4);
		testQueenDelete.setNosema(4);
		testQueenDelete.setTemper(5);
		testQueenDelete.setHoneycomFirmness(5);
		testQueenDelete.setClensingAbility(3);
		testQueenDelete.setName("KS025");
		testQueenDelete.setMother(testQueenInsert);
		testQueenDelete.setFathersMother(testQueenUpdate);
		testQueenDelete.setBreeder(testBreeder);
		
		
		
		testQueenDB.insertQueen(testQueenDelete);
		testQueenDB.insertQueen(testQueenUpdate);	
	}

	@AfterClass
	public static void testCleanup() throws SQLException, FileNotFoundException {
		
		dblayer.DBConnection.getInstance().insertDatabaseData();
		
	}
	
	/**
	 * Tests if you can insert a Queen into the database
	 * @throws SQLException
	 */
	@Test
	public void testInsertQueen() throws SQLException{
		assertNotSame("The Queen was not inserted", -1, testQueenDB.insertQueen(testQueenInsert));
		assertEquals(testQueenInsert.equals(testQueenDB.selectSingleQueen(1, true)) ,true);
	}

	/**
	 * Tests if you can update a variable on a Queen in the database
	 * @throws SQLException
	 */
	@Test
	public void testUpdateCity() throws SQLException {
		
		testQueenUpdate.setName("KS022");
		assertNotSame("The Queen was not updated", -1, testQueenDB.updateQueen(testQueenUpdate));
		assertEquals(testQueenUpdate.equals(testQueenDB.selectSingleQueen(2, true)) ,true);
	}

	/**
	 * Tests if you can delete a queen in the database
	 * @throws SQLException
	 */
	@Test
	public void testDeleteQueen() throws SQLException {
		
		
		assertNotSame("The queen was not deleted", -1, testQueenDB.deleteQueen(testQueenDelete));
		assertNull(testQueenDB.selectSingleQueen(3, true));
		
	}
	
	/**
	 * Tests if you can select a queen in the database
	 * @throws SQLException
	 */
	@Test
	public void testSelectSingleQueen() throws SQLException {
		assertEquals(testQueenInsert.equals(testQueenDB.selectSingleQueen(1, true)), true);		
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
