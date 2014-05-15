package dblayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modellayer.Chart;
import modellayer.Queen;

/**
 * DBChart class
 * @author Gruppe 3
 *
 */
public class DBChart {
	
	private Connection con;
	
	public DBChart() {
		con = DBConnection.getInstance().getDBcon();
	}
	
	/**
	 * An Arraylist with all charts
	 */
	public ArrayList<Chart> getAllCharts(boolean retriveAssociation) {
	 	return miscWhere("", retriveAssociation);
	 }
	
	public Chart searchChartOnName(String name, boolean retriveAssociation) {
		String wClause = "name like % "+name+" %";
		return singleWhere(wClause, retriveAssociation);
	}
	
	/**
     * Select a single Chart
     */
    public Chart selectSingleChart(int chartID, boolean retriveAssociation) throws SQLException {
   	 String wClause = "chartID = "+chartID;
        return singleWhere(wClause, retriveAssociation);
    }
    
    /**
     * Insert a Chart to the DB
     */
    public int insertChart(Chart c) throws SQLException 
    {
   	 PreparedStatement pstmt = null;
   	 int controlInt = -1;
   	 String insert = "insert into Chart(breederID, year, honeyYield, swarmTendency, nosema, temper, honeycombfirmness, cleansingAbility, compendiumID)"
                      + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
   	 //System.out.println(insert);
   	 try {
   		 pstmt = con.prepareStatement(insert);
   		 pstmt.setInt(1, c.getBreeder().getBreederID());
   		 pstmt.setInt(2, c.getYear());
   		 pstmt.setInt(3, c.getHoneyYield());
   		 pstmt.setInt(4, c.getSwarmTendency());
   		 pstmt.setInt(5, c.getNosema());
   		 pstmt.setInt(6, c.getTemper());
   		 pstmt.setInt(7, c.getHoneycomFirmness());
   		 pstmt.setInt(8, c.getClensingAbility());
   		 pstmt.setInt(9, c.getCompendium().getCompendiumID());
         controlInt = pstmt.executeUpdate();
        } catch (SQLException sqlE) {
            System.out.println("SQL Error, Queen not inserted");
            System.out.println(sqlE.getMessage());
        } catch (Exception e) {
            e.getMessage();
        }
   	 
   	 return controlInt;
    }
    
    /**
     * Updates the Chart db
     */
    public int updateChart(Chart c) throws SQLException {
   	 
   	 int controlInt = -1;

   	 String update = "UPDATE Chart SET "
    	 	+ "breederID = ?, "
   	 		+ "year = ?, "
   	 		+ "honeyYield = ?, "
   	 		+ "swarmTendency = ?,"
   	 		+ "nosema = ?, "
   	 		+ "temper = ?,"
   	 		+ "honeycombfirmness = ?,"
   	 		+ "cleansingAbility = ?, "
   	 	    + "compendiumID = ?, "
   	 		+ "WHERE chartID = ?";

   	 System.out.println(update);
   	 try {
   		 PreparedStatement pstmt = null;
   		 pstmt = con.prepareStatement(update);
   		 pstmt.setInt(1, c.getBreeder().getBreederID());
  		 pstmt.setInt(2, c.getYear());
  		 pstmt.setInt(3, c.getHoneyYield());
  		 pstmt.setInt(4, c.getSwarmTendency());
  		 pstmt.setInt(5, c.getNosema());
  		 pstmt.setInt(6, c.getTemper());
  		 pstmt.setInt(7, c.getHoneycomFirmness());
  		 pstmt.setInt(8, c.getClensingAbility());
  		 pstmt.setInt(9, c.getCompendium().getCompendiumID());
   		 
   		 controlInt = pstmt.executeUpdate();
   	 } catch (SQLException sqlE) {
   		 System.out.println("SQL Error, Queen not updated");
   		 System.out.println(sqlE.getMessage());
   	 } catch (Exception e) {
   		 e.getMessage();
   	 }
   	 
   	 return controlInt;
    }
    
    /**
     * Delete a Chart from the database
     */
    public int deleteChart(Chart c) throws SQLException 
    {
   	 PreparedStatement pstmt = null;
   	 int controlInt = -1;
   	 
   	 String delete = "DELETE FROM Chart WHERE chartID = ?";
   	 //System.out.println(delete);
   	 try {
   		 pstmt = con.prepareStatement(delete);
            pstmt.setInt(1, c.getChartID());
            controlInt = pstmt.executeUpdate();
        } catch (SQLException sqlE) {
            System.out.println("SQL Error, Queen not deleted");
            System.out.println(sqlE.getMessage());
        } catch (Exception e) {
            e.getMessage();
        }
   	 
   	 return controlInt;
    }
    
    /**
	 * If more than one Chart is to be selected
	 * @param wClause
	 * @return ArrayList with Chart objects
	 */
    
    private ArrayList<Chart> miscWhere(String wClause, boolean retrieveAssociation)
	 {
   	     ResultSet results;
	     ArrayList<Chart> list = new ArrayList<Chart>();	
		
	     String query = buildQuery(wClause);
   
	     try{ // read the cities from the database
			 Statement stmt = con.createStatement();
		 	 stmt.setQueryTimeout(5);
		 	 results = stmt.executeQuery(query);
		 	
			 while( results.next() ){ // loop through all Queens and create them as objects
			     Chart cObj = new Chart();
				 cObj = buildChart(results);	
				 if(retrieveAssociation) {
					  //er ikke sikker på om breederen skal med her. 
				 }				 
		         list.add(cObj);	
			 }//end while
			
	         stmt.close();

		 }//end try
	 	 catch(Exception e){
	 	 	System.out.println("Query exception - select: "+e);
		 	e.printStackTrace();
	 	 }
		 return list;
	 }
    
    /**
	 * If only one Chart is to be selected
	 * @param wClause
	 * @return Chart object
	 */
	 private Chart singleWhere(String wClause, boolean retrieveAssociation)
	 {
	 	ResultSet results;
	 	Chart cObj = new Chart();
		        
	 	String query = buildQuery(wClause);
		//System.out.println(query);
		
		try{ 	
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			if( results.next() ){
				cObj = buildChart(results);
				stmt.close();
				if(retrieveAssociation) {
					 //igen er jeg ikke sikker på at jeg ved hvad der skal med
				 }	
				
			}else{ //No chart found
				cObj = null;
			}
		}//end try	
		
		catch(Exception e){
			System.out.println("Query exception: "+e);
		}
		return cObj;
	 }
    
    /**
	 * Method to build the query
	 * @param wClause
	 * @return
	 */
	 private String buildQuery(String wClause)
	 {
	     String query="SELECT * FROM Chart";
		
	 	if (wClause.length()>0)
	 		query=query+" WHERE "+ wClause;
	 		
	 	return query;
	 }
	 
	 /**
	     * Builds a Chart object from the data in the database
	     * @param result
	     * @return Chart object
	     * @throws SQLException
	     */
	    private Chart buildChart(ResultSet result) throws SQLException {
	   	 Chart cObj = new Chart();

	   	 try {
	   	   //cObj.setBreeder(selectSingleBreeder(result.getInt("breederID"), false)); Er ikke helt sikker på hvordan vi får hentet ham avleren ned.
	   		 cObj.setYear(result.getInt("year"));
	   		 cObj.setHoneyYield(result.getInt("honeyYield"));
	   		 cObj.setSwarmTendency(result.getInt("swarmTendency"));
	   		 cObj.setNosema(result.getInt("nosema"));
	   		 cObj.setTemper(result.getInt("temper"));
	   		 cObj.setHoneycomFirmness(result.getInt("honeycombfirmness"));
	   		 cObj.setClensingAbility(result.getInt("cleansingAbility"));
	   	    //cObj.setCompendium(select); Er ikke helt sikker på hvordan vi får hentet ham
	   		 

	   	 } catch (Exception e) {
	   		 System.out.println("error building Chart object, " +e);
	        }
	        return cObj;
	    }

}
