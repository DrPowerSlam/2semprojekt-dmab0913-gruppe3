package dblayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modellayer.PartChart;
import modellayer.Queen;

/**
 * DBPartChart class
 * @author Gruppe 3
 *
 */
public class DBPartChart implements IFDBPartChart {
	
	private Connection con;
	
	public DBPartChart() {
		con = DBConnection.getInstance().getDBcon();
	}
	
	/**
	 * An Arraylist with all partPartCharts
	 */
	public ArrayList<PartChart> getAllPartCharts(boolean retriveAssociation) {
	 	return miscWhere("", retriveAssociation);
	 }
	
	public PartChart searchPartChartOnName(String name, boolean retriveAssociation) {
		String wClause = "name like % "+name+" %";
		return singleWhere(wClause, retriveAssociation);
	}
	
	/**
     * Select a single PartChart
     */
    public PartChart selectSinglePartChart(int partPartChartID, boolean retriveAssociation) throws SQLException {
   	 String wClause = "partPartChartID = "+partPartChartID;
        return singleWhere(wClause, retriveAssociation);
    }
    
    /**
     * Insert a PartChart to the DB
     */
    public int insertPartChart(PartChart c) throws SQLException 
    {
   	 PreparedStatement pstmt = null;
   	 int controlInt = -1;
   	 String insert = "insert into PartChart(honeyYield, honeyYieldYear, swarmTendency, nosema, temper,"
   	 				+ "honeycomfirmness, cleansingAbility)"
                      + "values (?, ?, ?, ?, ?)";
   	 //System.out.println(insert);
   	 try {
   		 pstmt = con.prepareStatement(insert);
   		 pstmt.setInt(1, c.getHoneyYield());
   		 pstmt.setInt(2, c.getHoneyYieldYear());
   		 pstmt.setInt(3, c.getSwarmTendency());
   		 pstmt.setInt(4, c.getNosema());
   		 pstmt.setInt(5, c.getTemper());
   		 pstmt.setInt(6, c.getHoneycomFirmness());
   		 pstmt.setInt(7, c.getClensingAbility());
         controlInt = pstmt.executeUpdate();
        } catch (SQLException sqlE) {
            System.out.println("SQL Error, PartChart not inserted");
            System.out.println(sqlE.getMessage());
        } catch (Exception e) {
            e.getMessage();
        }
   	 
   	 return controlInt;
    }
    
    /**
     * Updates the PartChart db
     */
    public int updatePartChart(PartChart c) throws SQLException {
   	 
   	 int controlInt = -1;

   	 String update = "UPDATE PartChart SET "
    	 	+ "honeyYield = ?, "
   	 		+ "honeyYieldYear = ?, "
   	 		+ "swarmTendency = ?, "
   	 		+ "nosema = ?,"
   	 		+ "temper = ?, "
   	 		+ "honeycombfirmness = ?,"
   	 	    + "cleansingAbility = ?,"
   	 	    + "chartID = ?,"
   	 	    + "queenID = ? "
   	 		+ "WHERE partPartChartID = ?";

   	 System.out.println(update);
   	 try {
   		 PreparedStatement pstmt = null;
   		 pstmt = con.prepareStatement(update);
   		 pstmt.setInt(1, c.getHoneyYield());
  		 pstmt.setInt(2, c.getHoneyYieldYear());
  		 pstmt.setInt(3, c.getSwarmTendency());
  		 pstmt.setInt(4, c.getNosema());
  		 pstmt.setInt(5, c.getTemper());
  		 pstmt.setInt(6, c.getHoneycomFirmness());
  		 pstmt.setInt(7, c.getClensingAbility());
  		 pstmt.setInt(8, c.getChart().getChartID()); 
  		 pstmt.setInt(9, c.getQueen().getQueenID()); 
  		 pstmt.setInt(10, c.getPartChartID());
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
     * Delete a PartChart from the database
     */
    public int deletePartChart(PartChart c) throws SQLException 
    {
   	 PreparedStatement pstmt = null;
   	 int controlInt = -1;
   	 
   	 String delete = "DELETE FROM PartChart WHERE partPartChartID = ?";
   	 //System.out.println(delete);
   	 try {
   		 pstmt = con.prepareStatement(delete);
            pstmt.setInt(1, c.getPartChartID());
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
	 * If more than one PartChart is to be selected
	 * @param wClause
	 * @return ArrayList with PartChart objects
	 */
    
    private ArrayList<PartChart> miscWhere(String wClause, boolean retrieveAssociation)
	 {
   	     ResultSet results;
	     ArrayList<PartChart> list = new ArrayList<PartChart>();	
		
	     String query = buildQuery(wClause);
   
	     try{ 
			 Statement stmt = con.createStatement();
		 	 stmt.setQueryTimeout(5);
		 	 results = stmt.executeQuery(query);
		 	
			 while( results.next() ){ 
			     PartChart cObj = new PartChart();
				 cObj = buildPartChart(results);	
				 if(retrieveAssociation) {
					 IFDBChart dbChart = new DBChart();
					 cObj.setChart(dbChart.selectSingleChart(results.getInt("chartID"), false));
					 
					 IFDBQueen dbQueen = new DBQueen();
					 cObj.setQueen(dbQueen.selectSingleQueen(results.getInt("queenID"), false));

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
	 * If only one PartChart is to be selected
	 * @param wClause
	 * @return PartChart object
	 */
	 private PartChart singleWhere(String wClause, boolean retrieveAssociation)
	 {
	 	ResultSet results;
	 	PartChart cObj = new PartChart();
		        
	 	String query = buildQuery(wClause);
		//System.out.println(query);
		
		try{ 	
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			if( results.next() ){
				cObj = buildPartChart(results);
				stmt.close();
				if(retrieveAssociation) {
					IFDBChart dbChart = new DBChart();
					 cObj.setChart(dbChart.selectSingleChart(results.getInt("chartID"), false));
					 
					 IFDBQueen dbQueen = new DBQueen();
					 cObj.setQueen(dbQueen.selectSingleQueen(results.getInt("queenID"), false));
				 }	
				
			}else{ //No partPartChart found
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
	     String query="SELECT * FROM PartChart";
		
	 	if (wClause.length()>0)
	 		query=query+" WHERE "+ wClause;
	 		
	 	return query;
	 }
	 
	 	/**
	     * Builds a PartChart object from the data in the database
	     * @param result
	     * @return PartChart object
	     * @throws SQLException
	     */
	    private PartChart buildPartChart(ResultSet result) throws SQLException {
	   	 PartChart cObj = new PartChart();

	   	 try {
	   		 cObj.setPartChartID(result.getInt("partPartChartID"));
	   		 cObj.setHoneyYield(result.getInt("honeyYield"));
	   		 cObj.setHoneyYieldYear(result.getInt("honeyYieldYear"));
	   	   	 cObj.setSwarmTendency(result.getInt("swarmTendency"));	 
	   	     cObj.setNosema(result.getInt("nosema"));	 
	   	     cObj.setTemper(result.getInt("temper"));	 
	   	     cObj.setHoneycomFirmness(result.getInt("honeycombfirmness"));	 
	   	     cObj.setClensingAbility(result.getInt("cleansingAbility"));	 

	   	 } catch (Exception e) {
	   		 System.out.println("error building PartChart object, " +e);
	        }
	        return cObj;
	    }

}
