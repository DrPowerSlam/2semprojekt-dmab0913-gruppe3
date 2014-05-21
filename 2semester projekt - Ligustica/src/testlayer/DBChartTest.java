/**
 * /**
 * Handles the class DBChartTest.
 * 
 * Author: Group 3
 * Date: 07-04-2014 Version: 1.0
 */
 
package testlayer;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

import modellayer.Breeder;
import modellayer.Chart;
import modellayer.Compendium;
import modellayer.PartChart;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dblayer.DBBreeder;
import dblayer.DBChart;
import dblayer.DBCompendium;
import dblayer.DBConnection;

public class DBChartTest {

	private static dblayer.DBChart testChartDB = new DBChart();
	private static Chart testChartUpdate = new Chart();
	private static Chart testChartInsert = new Chart();
	private static Chart testChartDelete = new Chart();
	private static DBConnection testConnection = DBConnection.getInstance();
	private static Breeder testBreeder;
	private static dblayer.DBBreeder testBreederDB = new DBBreeder();
	private static Compendium testCompendium = new Compendium(1, "test", "23-09");
	private static dblayer.DBCompendium testCompendiumDB = new DBCompendium();
	
	@BeforeClass
	/**
	 * 
	 * @throws SQLException
	 * @throws FileNotFoundException
	 */
	public static void testSetup() throws SQLException, FileNotFoundException{
		
		testConnection.insertDatabaseData();		
		
		testBreeder = testBreederDB.selectSingleBreeder(1, true);
		testCompendiumDB.insertCompendium(testCompendium);
		
		testChartInsert.setChartID(1);
		testChartInsert.setBreeder(testBreeder);
		testChartInsert.setYear(2012);
		testChartInsert.setSisterChart(true);
		testChartInsert.setCompendium(testCompendium);
		testChartInsert.setPedigree("blah");
	
		
		testChartUpdate.setChartID(1);
		testChartUpdate.setBreeder(testBreeder);
		testChartUpdate.setYear(2012);
		testChartUpdate.setSisterChart(true);
		testChartUpdate.setCompendium(testCompendium);
		testChartUpdate.setPedigree("blah");
		
		testChartDelete.setChartID(1);
		testChartDelete.setBreeder(testBreeder);
		testChartDelete.setYear(2012);
		testChartDelete.setSisterChart(true);
		testChartDelete.setCompendium(testCompendium);
		testChartDelete.setPedigree("blah");
		
		
		
		
		testChartDB.insertChart(testChartDelete);
		testChartDB.insertChart(testChartUpdate);
		
		
			}

	@AfterClass
	public static void testCleanup() throws SQLException, FileNotFoundException {
		
		//dblayer.DBConnection.getInstance().insertDatabaseData();
		
	}
	
	/**
	 * Tests if you can insert a city into the database
	 * @throws SQLException
	 */
	@Test
	public void testInsertChart() throws SQLException{
		
		assertNotSame("The chart was not inserted", -1, testChartDB.insertChart(testChartInsert));
		assertEquals(testChartInsert.equals(testChartDB.selectSingleChart(1,true )) ,true);
	}

	/**
	 * Tests if you can update a variable on a city in the database
	 * @throws SQLException
	 */
	@Test
	public void testUpdateChart() throws SQLException {
		
		testChartUpdate.setPedigree("blah2");
		assertNotSame("The chart was not updated", -1, testChartDB.updateChart(testChartUpdate));
		assertEquals(3, testChartDB.selectSingleChart(2,true).getPedigree());
	}

	/**
	 * Tests if you can delete a city in the database
	 * @throws SQLException
	 */
	@Test
	public void testDeleteCity() throws SQLException {
		
		assertNotSame("The chart was not deleted", -1, testChartDB.deleteChart(testChartDelete));
		assertNull(testChartDB.deleteChart(testChartDB.selectSingleChart(3,true)));
		
	}
	
	/**
	 * Tests if you can select a city in the database
	 * @throws SQLException
	 */
	@Test
	public void testSelectSingleChart() throws SQLException {
		assertEquals(testChartInsert.equals(testChartDB.selectSingleChart(1, false)), true);
	}
	
	/**
	 * Tests if you can select all cities in the database
	 * @throws SQLException
	 */
	@Test
	public void testGetAllCities() throws SQLException {
		assertNotNull(testChartDB.getAllCharts(true).size());
	}

}