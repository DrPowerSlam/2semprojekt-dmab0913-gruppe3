/**
 * 
 */
package testlayer;

import static org.junit.Assert.*;

import java.sql.SQLException;

import modellayer.Breeder;
import modellayer.Chart;
import modellayer.Compendium;
import modellayer.PartChart;
import modellayer.Queen;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controllayer.ChartCtr;
import dblayer.DBChart;
import dblayer.DBPartChart;

/**
 * @author 
 *
 */
public class ChartCtrTest {
	
	private static ChartCtr ctr = new ChartCtr();
	private static Chart testChart = new Chart();
	private static Queen testQueen = new Queen();
	private static PartChart testPartChart;
	private static DBChart testDBChart = new DBChart();
	private static DBPartChart testDBPartChart = new DBPartChart();
	private static Breeder testBreeder = new Breeder(null, null, null, null, null, null, false, 4);
	private static Compendium testCompendium = new Compendium(1, null, null);
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testQueen.setQueenID(1); //nødvendig for at indsætte i db
		//testChart = ctr.startChart(); //virker ikke med junit test da den kræver at man er logget ind
		testChart.setChartID(1);
		testChart.setBreeder(testBreeder);
		testChart.setCompendium(testCompendium);
		testPartChart = new PartChart(testChart, testQueen);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		testDBPartChart.deletePartChart(testPartChart);
		//testDBChart.deleteChart(testChart); //ingen chart sat ind pga login
	}

	@Test
	public void testValidateYear() {
		assertFalse("not false", ctr.validateYear(null));
		assertFalse("not false", ctr.validateYear("1"));
		assertFalse("not false", ctr.validateYear("123"));
		assertFalse("not false", ctr.validateYear("12345"));
		assertTrue("not true", ctr.validateYear("2014"));
	}

	@Test
	public void testValidateGrade() {
		assertFalse("not false", ctr.validateGrade(0));
		assertTrue("not true", ctr.validateGrade(1));
		assertTrue("not true", ctr.validateGrade(2));
		assertTrue("not true", ctr.validateGrade(3));
		assertTrue("not true", ctr.validateGrade(4));
		assertTrue("not true", ctr.validateGrade(5));
		assertFalse("not false", ctr.validateGrade(6));
	}
	
	@Test
	public void testCreatePartChart() throws SQLException {
		int size = testChart.getAllPartCharts().size();
		testChart = ctr.addInfo(testPartChart, 9999, "1", 1, 1, 1, 1, 1, 1);
		assertTrue("PartChart not added to testChart arraylist", 
				size < testChart.getAllPartCharts().size());
	}
}
