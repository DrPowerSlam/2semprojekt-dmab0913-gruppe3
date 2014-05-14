package dblayer;

import java.sql.*;
import java.util.ArrayList;

import modellayer.Compendium;

public class DBCompendium {
	
	private Connection con;
	
	public DBCompendium()
	{
		con = DBConnection.getInstance().getDBcon();
	}
	
	 /**
		 * A arraylist with all Compendiums
		 */
		 public ArrayList<Compendium> getAllCompendiums(boolean retriveAssociation) {
		 	return miscWhere("", retriveAssociation);
		 }
		 
		public Compendium searchCompendiumOnName(String name, boolean retriveAssociation) {
			String wClause = "name like % "+name+" %";
			return singleWhere(wClause, retriveAssociation);
			 
		}
		 
		 /**
	     * Select a single Queen
	     */
	    public Compendium selectSingleCompendium(int compendiumID, boolean retriveAssociation) throws SQLException {
	   	 String wClause = "compendiumID = "+compendiumID;
	        return singleWhere(wClause, retriveAssociation);
	    }
	    
	    /**
	     * Insert a Compendium to the DB
	     */
	    public int insertQueen(Compendium c) throws SQLException 
	    {
	   	 PreparedStatement pstmt = null;
	   	 int controlInt = -1;
	   	 String insert = "insert into Queen(name, date, droneLines)"
	                      + "values (?, ?, ?)";
	   	 //System.out.println(insert);
	   	 try {
	   		 pstmt = con.prepareStatement(insert);
	   		 
	   		 pstmt.setString(1, c.getName());
	   		 pstmt.setString(2, c.getDate());
	   		 pstmt.setString(3, c.getDroneLines());
	   		 
	         controlInt = pstmt.executeUpdate();
	        } catch (SQLException sqlE) {
	            System.out.println("SQL Error, Compendium not inserted");
	            System.out.println(sqlE.getMessage());
	        } catch (Exception e) {
	            e.getMessage();
	        }
	   	 
	   	 return controlInt;
	    }
	    
	    /**
	     * Updates the Compendium db
	     */
	    public int updateCompendium(Compendium c) throws SQLException {
	   	 
	   	 int controlInt = -1;

	   	 String update = "UPDATE Compendium SET "
	   	 		+ "name = ?, "
	   			+ "date = ?,"
	   	 		+ "droneLines = ?, "
	   	 		+ "WHERE compendiumID = ?";

	   	 System.out.println(update);
	   	 try {
	   		 PreparedStatement pstmt = null;
	   		 pstmt = con.prepareStatement(update);
	   		 
	   		 pstmt.setString(1, c.getName());
	   		 pstmt.setString(2, c.getDate());
	   		 pstmt.setString(3, c.getDroneLines());
	   	   		 
	   		 controlInt = pstmt.executeUpdate();
	   	 } catch (SQLException sqlE) {
	   		 System.out.println("SQL Error, Compendium not updated");
	   		 System.out.println(sqlE.getMessage());
	   	 } catch (Exception e) {
	   		 e.getMessage();
	   	 }
	   	 
	   	 return controlInt;
	    }
	    
	    /**
	     * Delete a Queen from the database
	     */
	    public int deleteCompendium(Compendium c) throws SQLException 
	    {
	   	 PreparedStatement pstmt = null;
	   	 int controlInt = -1;
	   	 
	   	 String delete = "DELETE FROM Queen WHERE queenID = ?";
	   	 //System.out.println(delete);
	   	 try {
	   		 pstmt = con.prepareStatement(delete);
	            pstmt.setInt(1, c.getCompendiumID());
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
		 * If more than one Queen is to be selected
		 * @param wClause
		 * @return ArrayList with Queen objects
		 */
	    private ArrayList<Compendium> miscWhere(String wClause, boolean retrieveAssociation)
		 {
	   	     ResultSet results;
		     ArrayList<Compendium> list = new ArrayList<Compendium>();	
			
		     String query = buildQuery(wClause);
	   
		     try{ // read the cities from the database
				 Statement stmt = con.createStatement();
			 	 stmt.setQueryTimeout(5);
			 	 results = stmt.executeQuery(query);
			 	
				 while( results.next() ){ // loop through all Compendiums and create them as objects
					 Compendium cObj = new Compendium();
					 cObj = buildCompendium(results);	
					 if(retrieveAssociation) {
						  //der er vel ingen? 
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
		 * If only one Compendium is to be selected
		 * @param wClause
		 * @return Compendium object
		 */
		 private Compendium singleWhere(String wClause, boolean retrieveAssociation)
		 {
		 	ResultSet results;
		 	Compendium cObj = new Compendium();
			        
		 	String query = buildQuery(wClause);
			//System.out.println(query);
			
			try{ 	
				Statement stmt = con.createStatement();
				stmt.setQueryTimeout(5);
				results = stmt.executeQuery(query);
				if( results.next() ){
					cObj = buildCompendium(results);
					stmt.close();
					if(retrieveAssociation) {
						 //igen er jeg ikke sikker på at jeg ved hvad der skal med
					 }	
					
				}else{ //No Compendium found
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
		     String query="SELECT * FROM Compendium";
			
		 	if (wClause.length()>0)
		 		query=query+" WHERE "+ wClause;
		 		
		 	return query;
		 }
	    
	    /**
	     * Builds a Compendium object from the data in the database
	     * @param result
	     * @return Compendium object
	     * @throws SQLException
	     */
	    private Compendium buildCompendium(ResultSet result) throws SQLException {
	    	Compendium cObj = new Compendium();

	   	 try {
	   		 cObj.setName(result.getString("name"));
	   		 cObj.setDate(result.getString("date"));
	   		 cObj.setDroneLines(result.getString("droneLines"));

	   	 } catch (Exception e) {
	   		 System.out.println("error building Compendium object, " +e);
	        }
	        return cObj;
	    }

}
