package dblayer;

import java.sql.*;
import java.util.ArrayList;

import modellayer.City;
import modellayer.Queen;

public class DBQueen implements IFDBQueen {
	
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
			String wClause = "name like '%"+name+"%'";
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
	   	 String insert = "insert into Queen(year, name, honeyYield, swarmTendency, nosema, temper, honeycombfirmness, cleansingAbility, isAlive, mother, fathersMother, breederID)"
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
	   		 pstmt.setBoolean(9, q.isAlive());
	   		 pstmt.setInt(10, 1);
	   		 pstmt.setInt(11, 2);
//	   		pstmt.setInt(10, q.getMother().getQueenID());
//	   		 pstmt.setInt(11, q.getFathersMother().getQueenID());
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
	   	 		+ "mother = ?, "
	   	 		+ "fathersMother = ?, "
	   	 		+ "breederID = ? "
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
	   		 pstmt.setBoolean(9, q.isAlive());
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
	   
		     try{ // read the queens from the database
				 Statement stmt = con.createStatement();
			 	 stmt.setQueryTimeout(5);
			 	 results = stmt.executeQuery(query);
			 	
				 while( results.next() ){ // loop through all Queens and create them as objects
				     Queen qObj = new Queen();
					 qObj = buildQueen(results);	
					 if(retrieveAssociation) {
						 Queen motherObj = selectSingleQueen(results.getInt("mother"), false);
						 qObj.setMother(motherObj);
						 
						 Queen fathersMotherObj = selectSingleQueen(
								 results.getInt("fathersMother"), false);
						 qObj.setMother(fathersMotherObj);
						 
						 IFDBBreeder dbBreeder = new DBBreeder();
						 qObj.setBreeder(dbBreeder.selectSingleBreeder(
								 results.getInt("breederID"), false));
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
					
					if(retrieveAssociation) {
						Queen motherObj = selectSingleQueen(results.getInt("mother"), false);
						 qObj.setMother(motherObj);
						 
						 Queen fathersMotherObj = selectSingleQueen(results.getInt("fathersMother"), false);
						 qObj.setFathersMother(fathersMotherObj);
						 
						 IFDBBreeder dbBreeder = new DBBreeder();
						 qObj.setBreeder(dbBreeder.selectSingleBreeder(results.getInt("breederID"), false));
					 }	
					stmt.close();
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
	   		 qObj.setQueenID(result.getInt("queenID"));
	   		 qObj.setYear(result.getInt("year"));
	   		 qObj.setName(result.getString("name"));
	   		 qObj.setHoneyYield(result.getInt("honeyYield"));
	   		 qObj.setSwarmTendency(result.getInt("swarmTendency"));
	   		 qObj.setNosema(result.getInt("nosema"));
	   		 qObj.setTemper(result.getInt("temper"));
	   		 qObj.setHoneycomFirmness(result.getInt("honeycombfirmness"));
	   		 qObj.setClensingAbility(result.getInt("cleansingAbility"));
	   		 qObj.setAlive(result.getBoolean("isAlive")); 
	   		 
	   	 } catch (Exception e) {
	   		 System.out.println("error building Queen object, " +e);
	        }
	        return qObj;
	    }

}
