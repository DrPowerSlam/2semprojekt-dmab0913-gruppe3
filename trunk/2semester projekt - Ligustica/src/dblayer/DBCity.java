package dblayer;


import modellayer.City;

import java.sql.*;
import java.util.ArrayList;


/**
 * 
 * @author Gruppe 3
 *
 */
public class DBCity implements IFDBCity {

	 private Connection con;

	 /**
	  * 
	  */
     public DBCity() 
     {
    	 con = DBConnection.getInstance().getDBcon();
     }
     
     
     /**
 	 * A arraylist with all City
 	 */
 	 public ArrayList<City> getAllCity() {
 	 	return miscWhere("");
 	 }
 	 
 	 /**
      * Select a single City
      */
     public City selectSingleCity(int zipCode) throws SQLException {
    	 String wClause = "zipCode = " +zipCode;
         return singleWhere(wClause);
     }
     
     /**
      * Insert a City to the DB
      */
     public int insertCity(City c) throws SQLException 
     {
    	 PreparedStatement pstmt = null;
    	 
    	 int controlInt = -1;
    	 String insert = "insert into City(zipCode, city)"
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
             System.out.println("SQL Error, City not inserted");
             System.out.println(sqlE.getMessage());
         } catch (Exception e) {
             e.getMessage();
         }
    	 
    	 return controlInt;
     }
     
     /**
      * Updates the City db
      */
     public int updateCity(City c) throws SQLException {
    	 City cObj = c;
    	 int controlInt = -1;
    	 
    	 PreparedStatement pstmt = null;

    	 String update = "UPDATE City SET "
    	 		+ "city = ? "
    	 		+ "WHERE zipCode = ?";
    	 
    	 try {
    		 pstmt = con.prepareStatement(update);
    		 pstmt.setString(1, cObj.getCity());
    		 pstmt.setInt(2, cObj.getZipCode());
    		 controlInt = pstmt.executeUpdate();
    		 pstmt.close();
    	 } catch (SQLException sqlE) {
    		 System.out.println("SQL Error, City not updated");
    		 System.out.println(sqlE.getMessage());
    	 } catch (Exception e) {
    		 e.getMessage();
    	 }
    	 
    	 return controlInt;
     }
     
     /**
      * Delete a City from the database
      */
     public int deleteCity(City c) throws SQLException 
     {
    	 PreparedStatement pstmt = null;
    	 int controlInt = -1;
    	 String delete = "DELETE FROM City "
    	 		+ "WHERE zipCode = ?";
    	 //System.out.println(delete);
    	 try {
    		 pstmt = con.prepareStatement(delete);
             pstmt.setInt(1, c.getZipCode());
             controlInt = pstmt.executeUpdate();
    		 pstmt.close();
         } catch (SQLException sqlE) {
             System.out.println("SQL Error, City not deleted");
             System.out.println(sqlE.getMessage());
         } catch (Exception e) {
             e.getMessage();
         }
    	 return controlInt;
     }
     
     /**
 	 * If more than one City is to be selected
 	 * @param wClause
 	 * @return ArrayList with City objects
 	 */
     private ArrayList<City> miscWhere(String wClause)
 	 {
    	 ResultSet results;
 	     ArrayList<City> list = new ArrayList<City>();	
 		
 	     String query = buildQuery(wClause);
    
 	     try{ // read the city from the database
 			 Statement stmt = con.createStatement();
 		 	 stmt.setQueryTimeout(5);
 		 	 results = stmt.executeQuery(query);
 		 	
 			 while( results.next() ){ // loop through all city and create them as objects
 			     City cObj = new City();
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
 	 * @return City object
 	 */
 	 private City singleWhere(String wClause)
 	 {
 	 	ResultSet results;
 	 	City cObj = new City();
 		        
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
 	     String query="SELECT * FROM City";
 		
 	 	if (wClause.length()>0)
 	 		query=query+" WHERE "+ wClause;
 	 		
 	 	return query;
 	 }
     
     /**
      * Builds a City object from the data in the database
      * @param result
      * @return City object
      * @throws SQLException
      */
     private City buildCity(ResultSet result) throws SQLException {
    	 City cObj = new City();

    	 try {
    		 cObj.setZipCode(result.getInt("zipCode"));
    		 cObj.setCity(result.getString("city"));
    	 } catch (Exception e) {
    		 System.out.println("building City object");
         } 
         return cObj;
     }
     
     /**
  	 * A arraylist with all Cities
  	 */
  	 public ArrayList<City> getAllCities() {
  	 	return miscWhere("");
  	 }

}