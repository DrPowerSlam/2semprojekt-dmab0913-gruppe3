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
public class DBBreederTest {
	
	private static dblayer.DBBreeder testBreederDB = new DBBreeder();
	private static Breeder testBreederUpdate = new Breeder();
	private static Breeder testBreederInsert = new Breeder();
	private static Breeder testBreederDelete = new Breeder();

	
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
//		testBreederInsert.setName("Klaus");
//		testBreederInsert.setAddress("Sofiendalsvej");
//		
//		testBreederUpdate.setName("Hans");
//		testBreederUpdate.setAddress("Klassensgade");
//		
//		testBreederDelete.setName("Esbjerg");
//		testBreederDelete.setAddress("Clemensvej");
//		
//		testBreederDB.insertBreeder(testBreederDelete);
//		testBreederDB.insertBreeder(testBreederUpdate);
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
	
	
	
}
