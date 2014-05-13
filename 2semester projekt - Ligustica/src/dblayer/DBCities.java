package dblayer;

import modellayer.Cities;

import java.sql.*;
import java.util.ArrayList;


/**
 * 
 * @author Gruppe 3
 *
 */
public class DBCities implements IFDBCities {

	 private Connection con;

	 /**
	  * 
	  */
     public DBCities() 
     {
    	 con = DBConnection.getInstance().getDBcon();
     }
     
     
     /**
 	 * A arraylist with all Cities
 	 */
 	 public ArrayList<Cities> getAllCities() {
 	 	return miscWhere("");
 	 }
 	 
 	 /**
      * Select a single Cities
      */
     public Cities selectSingleCity(int zipCode) throws SQLException {
    	 String wClause = "zipCode = " +zipCode;
         return singleWhere(wClause);
     }
     
     /**
      * Insert a Cities to the DB
      */
     public int insertCity(Cities c) throws SQLException 
     {
    	 PreparedStatement pstmt = null;
    	 
    	 int controlInt = -1;
    	 String insert = "insert into Cities(zipCode, city)"
                       + "values (?,?)";
    	 //System.out.println(insert);
    	 try {
    		 pstmt = con.prepareStatement(insert);
             pstmt.setInt(1, c.getZipCode());
             pstmt.setString(2, c.getCity());
             controlInt = pstmt.executeUpdate();
             con.commit();
    		 pstmt.close();
         } catch (SQLException sqlE) {
             System.out.println("SQL Error, Cities not inserted");
             System.out.println(sqlE.getMessage());
         } catch (Exception e) {
             e.getMessage();
         }
    	 
    	 return controlInt;
     }
     
     /**
      * Updates the Cities db
      */
     @Override
     public int updateCity(Cities c) throws SQLException {
    	 Cities cObj = c;
    	 int controlInt = -1;
    	 
    	 PreparedStatement pstmt = null;

    	 String update = "UPDATE Cities SET "
    	 		+ "city = ? "
    	 		+ "WHERE zipCode = ?";
    	 
    	 try {
    		 pstmt = con.prepareStatement(update);
    		 pstmt.setString(1, cObj.getCity());
    		 pstmt.setInt(2, cObj.getZipCode());
    		 controlInt = pstmt.executeUpdate();
    		 pstmt.close();
    	 } catch (SQLException sqlE) {
    		 System.out.println("SQL Error, Cities not updated");
    		 System.out.println(sqlE.getMessage());
    	 } catch (Exception e) {
    		 e.getMessage();
    	 }
    	 
    	 return controlInt;
     }
     
     /**
      * Delete a City from the database
      */
     public int deleteCity(Cities c) throws SQLException 
     {
    	 PreparedStatement pstmt = null;
    	 int controlInt = -1;
    	 String delete = "DELETE FROM Cities "
    	 		+ "WHERE zipCode = ?";
    	 //System.out.println(delete);
    	 try {
    		 pstmt = con.prepareStatement(delete);
             pstmt.setInt(1, c.getZipCode());
             controlInt = pstmt.executeUpdate();
    		 pstmt.close();
         } catch (SQLException sqlE) {
             System.out.println("SQL Error, Cities not deleted");
             System.out.println(sqlE.getMessage());
         } catch (Exception e) {
             e.getMessage();
         }
    	 return controlInt;
     }
     
     /**
 	 * If more than one City is to be selected
 	 * @param wClause
 	 * @return ArrayList with Cities objects
 	 */
     private ArrayList<Cities> miscWhere(String wClause)
 	 {
    	 ResultSet results;
 	     ArrayList<Cities> list = new ArrayList<Cities>();	
 		
 	     String query = buildQuery(wClause);
    
 	     try{ // read the cities from the database
 			 Statement stmt = con.createStatement();
 		 	 stmt.setQueryTimeout(5);
 		 	 results = stmt.executeQuery(query);
 		 	
 			 while( results.next() ){ // loop through all cities and create them as objects
 			     Cities cObj = new Cities();
 				 cObj = buildCity(results);	
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
 	 * If only one city is to be selected
 	 * @param wClause
 	 * @return Cities object
 	 */
 	 private Cities singleWhere(String wClause)
 	 {
 	 	ResultSet results;
 	 	Cities cObj = new Cities();
 		        
 	 	String query = buildQuery(wClause);
 		//System.out.println(query);
 		
 		try{
 			Statement stmt = con.createStatement();
 			stmt.setQueryTimeout(5);
 			results = stmt.executeQuery(query);
 			if( results.next() ){
 				cObj = buildCity(results);
 				stmt.close();
 			}else{ 
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
 	     String query="SELECT * FROM Cities";
 		
 	 	if (wClause.length()>0)
 	 		query=query+" WHERE "+ wClause;
 	 		
 	 	return query;
 	 }
     
     /**
      * Builds a Cities object from the data in the database
      * @param result
      * @return Cities object
      * @throws SQLException
      */
     private Cities buildCity(ResultSet result) throws SQLException {
    	 Cities cObj = new Cities();

    	 try {
    		 cObj.setZipCode(result.getInt("zipCode"));
    		 cObj.setCity(result.getString("city"));
    	 } catch (Exception e) {
    		 System.out.println("building Cities object");
         } 
         return cObj;
     }

}