package dblayer;

import java.sql.*;
import java.util.ArrayList;

import modellayer.Queen;

public class DBQueen {
	
	private Connection con;
	
	public DBQueen()
	{
		con = DBConnection.getInstance().getDBcon();
	}
	
	 /**
		 * A arraylist with all Queens
		 */
		 public ArrayList<Queen> getAllQueens(boolean retriveAssociation) {
		 	return miscWhere("", retriveAssociation);
		 }
		 
		public Queen searchQueenOnName(String name, boolean retriveAssociation) {
			String wClause = "name like % "+name+" %";
			return singleWhere(wClause, retriveAssociation);
			 
		}
		 
		 /**
	     * Select a single Queen
	     */
	    public Queen selectSingleQueen(int queenID, boolean retriveAssociation) throws SQLException {
	   	 String wClause = "queenID = "+queenID;
	        return singleWhere(wClause, retriveAssociation);
	    }
	    
	    /**
	     * Insert a Queen to the DB
	     */
	    public int insertQueen(Queen q) throws SQLException 
	    {
	   	 PreparedStatement pstmt = null;
	   	 int controlInt = -1;
	   	 String insert = "insert into Queen(year, name, honeyYield, swarmTendency, nosema, temper, honeycombfirmness, cleansingAbility, isAlive, lParent, rParent, breederID)"
	                      + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	   	 //System.out.println(insert);
	   	 try {
	   		 pstmt = con.prepareStatement(insert);
	   		 pstmt.setInt(1, q.getYear());
	   		 pstmt.setString(2, q.getName());
	   		 pstmt.setInt(3, q.getHoneyYield());
	   		 pstmt.setInt(4, q.getSwarmTendency());
	   		 pstmt.setInt(5, q.getNosema());
	   		 pstmt.setInt(6, q.getTemper());
	   		 pstmt.setInt(7, q.getHoneycomFirmness());
	   		 pstmt.setInt(8, q.getClensingAbility());
	   		 pstmt.setString(9, "true"); //Burde der ikke være en isAlive i Queen klassen?
	   		 pstmt.setInt(10, q.getMother().getQueenID());
	   		 pstmt.setInt(11, q.getFathersMother().getQueenID());
	   		 pstmt.setInt(12, q.getBreeder().getBreederID());
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
	     * Updates the Queen db
	     */
	    public int updateQueen(Queen q) throws SQLException {
	   	 
	   	 int controlInt = -1;

	   	 String update = "UPDATE Queen SET "
	   	 		+ "year = ?, "
	   			+ "name = ?,"
	   	 		+ "honeyYield = ?, "
	   	 		+ "swarmTendency = ?,"
	   	 		+ "nosema = ?, "
	   	 		+ "temper = ?,"
	   	 		+ "honeycombfirmness = ?,"
	   	 		+ "cleansingAbility = ?, "
	   	 		+ "isAlive = ?, "
	   	 		+ "lParent = ?, "
	   	 		+ "rParent = ?, "
	   	 		+ "breederID = ?, "
	   	 		+ "WHERE queenID = ?";

	   	 System.out.println(update);
	   	 try {
	   		 PreparedStatement pstmt = null;
	   		pstmt = con.prepareStatement(update);
	   		pstmt.setInt(1, q.getYear());
	   		 pstmt.setString(2, q.getName());
	   		 pstmt.setInt(3, q.getHoneyYield());
	   		 pstmt.setInt(4, q.getSwarmTendency());
	   		 pstmt.setInt(5, q.getNosema());
	   		 pstmt.setInt(6, q.getTemper());
	   		 pstmt.setInt(7, q.getHoneycomFirmness());
	   		 pstmt.setInt(8, q.getClensingAbility());
	   		 pstmt.setString(9, "true");
	   		 pstmt.setInt(10, q.getMother().getQueenID());
	   		 pstmt.setInt(11, q.getFathersMother().getQueenID());
	   		 pstmt.setInt(12, q.getBreeder().getBreederID());
	   		 pstmt.setInt(13, q.getQueenID());
	   		 
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
	     * Delete a Queen from the database
	     */
	    public int deleteQueen(Queen q) throws SQLException 
	    {
	   	 PreparedStatement pstmt = null;
	   	 int controlInt = -1;
	   	 
	   	 String delete = "DELETE FROM Queen WHERE queenID = ?";
	   	 //System.out.println(delete);
	   	 try {
	   		 pstmt = con.prepareStatement(delete);
	            pstmt.setInt(1, q.getQueenID());
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
	    private ArrayList<Queen> miscWhere(String wClause, boolean retrieveAssociation)
		 {
	   	     ResultSet results;
		     ArrayList<Queen> list = new ArrayList<Queen>();	
			
		     String query = buildQuery(wClause);
	   
		     try{ // read the cities from the database
				 Statement stmt = con.createStatement();
			 	 stmt.setQueryTimeout(5);
			 	 results = stmt.executeQuery(query);
			 	
				 while( results.next() ){ // loop through all Queens and create them as objects
				     Queen qObj = new Queen();
					 qObj = buildQueen(results);	
					 if(retrieveAssociation) {
						  //er ikke sikker på om breederen skal med her. 
					 }				 
			         list.add(qObj);	
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
		 * If only one Queen is to be selected
		 * @param wClause
		 * @return Queen object
		 */
		 private Queen singleWhere(String wClause, boolean retrieveAssociation)
		 {
		 	ResultSet results;
		 	Queen qObj = new Queen();
			        
		 	String query = buildQuery(wClause);
			//System.out.println(query);
			
			try{ 	
				Statement stmt = con.createStatement();
				stmt.setQueryTimeout(5);
				results = stmt.executeQuery(query);
				if( results.next() ){
					qObj = buildQueen(results);
					stmt.close();
					if(retrieveAssociation) {
						 //igen er jeg ikke sikker på at jeg ved hvad der skal med
					 }	
					
				}else{ //No queen found
					qObj = null;
				}
			}//end try	
			
			catch(Exception e){
				System.out.println("Query exception: "+e);
			}
			return qObj;
		 }
		 
		 /**
		 * Method to build the query
		 * @param wClause
		 * @return
		 */
		 private String buildQuery(String wClause)
		 {
		     String query="SELECT * FROM Queen";
			
		 	if (wClause.length()>0)
		 		query=query+" WHERE "+ wClause;
		 		
		 	return query;
		 }
	    
	    /**
	     * Builds a Queen object from the data in the database
	     * @param result
	     * @return Queen object
	     * @throws SQLException
	     */
	    private Queen buildQueen(ResultSet result) throws SQLException {
	   	 Queen qObj = new Queen();

	   	 try {
	   		 qObj.setYear(result.getInt("year"));
	   		 qObj.setName(result.getString("name"));
	   		 qObj.setHoneyYield(result.getInt("honeyYield"));
	   		 qObj.setSwarmTendency(result.getInt("swarmTendency"));
	   		 qObj.setNosema(result.getInt("nosema"));
	   		 qObj.setTemper(result.getInt("temper"));
	   		 qObj.setHoneycomFirmness(result.getInt("honeycombfirmness"));
	   		 qObj.setClensingAbility(result.getInt("cleansingAbility"));
	   		 //qObj.setIsAlive(result.getString("isAlive")); den har ikke én i modellaget
	   		 qObj.setMother(selectSingleQueen(result.getInt("lParent"), false));
	   		 qObj.setFathersMother(selectSingleQueen(result.getInt("rParent"), false));
	   		 //qObj.setBreeder(selectSingleBreeder(result.getInt("breederID"), false)); Er ikke helt sikker på hvordan vi får hentet ham avleren ned.

	   	 } catch (Exception e) {
	   		 System.out.println("error building Queen object, " +e);
	        }
	        return qObj;
	    }

}
