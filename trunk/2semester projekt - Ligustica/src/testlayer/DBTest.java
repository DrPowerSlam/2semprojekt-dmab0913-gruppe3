package testlayer;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class DBTest {

	@BeforeClass
	public static void testSetup(){
		
	}
	
	@AfterClass
	public static void testCleanup(){
		
	}
	
	@Test
	public void test() {
	dblayer.DBConnection testClass = dblayer.DBConnection.getInstance();
	
	System.out.println(testClass.getDBcon());
		
	}

}
