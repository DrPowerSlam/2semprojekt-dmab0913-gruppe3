package dblayer;

import java.sql.*;
import java.util.ArrayList;

import modellayer.Breeder;
import modellayer.City;

public class DBBreeder implements IFDBBreeder {

	private Connection con;

	/**
	 * 
	 */
    public DBBreeder() 
    {
   	 con = DBConnection.getInstance().getDBcon();
    }    
    
    /**
	 * A arraylist with all Breeders
	 */
	 public ArrayList<Breeder> getAllBreeders(boolean retriveAssociation) {
	 	return miscWhere("", retriveAssociation);
	 }
	 
	public Breeder searchBreederOnName(String name, boolean retriveAssociation) {
		String wClause = "fname like % "+name+" %";
		return singleWhere(wClause, retriveAssociation);
		 
	}
	 
	 /**
     * Select a single Breeder
     */
    public Breeder selectSingleBreeder(int breederID, boolean retriveAssociation) throws SQLException {
   	 String wClause = "breederID = "+breederID;
        return singleWhere(wClause, retriveAssociation);
    }
    
    /**
     * Insert a Breeder to the DB
     */
    public int insertBreeder(Breeder b) throws SQLException 
    {
   	 PreparedStatement pstmt = null;
   	 int controlInt = -1;
   	 String insert = "insert into Breeder(fname, lname, address, zipCode, phone, email, password, isAdmin)"
                      + "values (?, ?, ?, ?, ?, ?, ?, ?)";
   	 //System.out.println(insert);
   	 try {
   		 pstmt = con.prepareStatement(insert);
   		 pstmt.setString(1, b.getFname());
   		 pstmt.setString(2, b.getLname());
   		 pstmt.setString(3, b.getAddress());
   		 pstmt.setInt(4, b.getCity().getZipCode());
   		 pstmt.setString(5, b.getPhone());
   		 pstmt.setString(6, b.getEmail());
   		 pstmt.setString(7, b.getPassword());
   		 pstmt.setString(8, Boolean.toString(b.isAdmin()));
         controlInt = pstmt.executeUpdate();
        } catch (SQLException sqlE) {
            System.out.println("SQL Error, Breeder not inserted");
            System.out.println(sqlE.getMessage());
        } catch (Exception e) {
            e.getMessage();
        }
   	 
   	 return controlInt;
    }
    
    /**
     * Updates the Breeder db
     */
    public int updateBreeder(Breeder b) throws SQLException {
   	 
   	 int controlInt = -1;

   	 String update = "UPDATE Breeder SET "
   	 		+ "fname = ?, "
   			+ "lname = ?,"
   	 		+ "address = ?, "
   	 		+ "zipCode = ?,"
   	 		+ "phone = ?, "
   	 		+ "email = ?,"
   	 		+ "password = ?,"
   	 		+ "isAdmin = ? "
   	 		+ "WHERE breederID = ?";

   	 System.out.println(update);
   	 try {
   		 PreparedStatement pstmt = null;
   		pstmt = con.prepareStatement(update);
  		 pstmt.setString(1, b.getFname());
  		 pstmt.setString(2, b.getLname());
  		 pstmt.setString(3, b.getAddress());
  		 pstmt.setInt(4, b.getCity().getZipCode());
  		 pstmt.setString(5, b.getPhone());
  		 pstmt.setString(6, b.getEmail());
  		 pstmt.setString(7, b.getPassword());
  		 pstmt.setString(8, Boolean.toString(b.isAdmin()));
  		 pstmt.setInt(9, b.getBreederID());
   		 controlInt = pstmt.executeUpdate();
   	 } catch (SQLException sqlE) {
   		 System.out.println("SQL Error, Breeder not updated");
   		 System.out.println(sqlE.getMessage());
   	 } catch (Exception e) {
   		 e.getMessage();
   	 }
   	 
   	 return controlInt;
    }
    
    /**
     * Delete a Breeder from the database
     */
    public int deleteBreeder(Breeder b) throws SQLException 
    {
   	 PreparedStatement pstmt = null;
   	 int controlInt = -1;
   	 
   	 String delete = "DELETE FROM Breeder WHERE breederID = ?";
   	 //System.out.println(delete);
   	 try {
   		 pstmt = con.prepareStatement(delete);
            pstmt.setInt(1, b.getBreederID());
            controlInt = pstmt.executeUpdate();
        } catch (SQLException sqlE) {
            System.out.println("SQL Error, Breeder not deleted");
            System.out.println(sqlE.getMessage());
        } catch (Exception e) {
            e.getMessage();
        }
   	 
   	 return controlInt;
    }
    
    /**
	 * If more than one Breeder is to be selected
	 * @param wClause
	 * @return ArrayList with Breeder objects
	 */
    private ArrayList<Breeder> miscWhere(String wClause, boolean retrieveAssociation)
	 {
   	     ResultSet results;
	     ArrayList<Breeder> list = new ArrayList<Breeder>();	
		
	     String query = buildQuery(wClause);
   
	     try{ // read the cities from the database
			 Statement stmt = con.createStatement();
		 	 stmt.setQueryTimeout(5);
		 	 results = stmt.executeQuery(query);
		 	
			 while( results.next() ){ // loop through all Breeders and create them as objects
			     Breeder bObj = new Breeder();
				 bObj = buildBreeder(results);	
				 if(retrieveAssociation) {
					 int zipCode = results.getInt("zipCode");
					 IFDBCity dbCity = new DBCity();
					 City cityObj = dbCity.selectSingleCity(zipCode);
					 bObj.setCity(cityObj); //set the city object in the breeder object
				 }				 
		         list.add(bObj);	
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
	 * @return Breeder object
	 */
	 private Breeder singleWhere(String wClause, boolean retrieveAssociation)
	 {
	 	ResultSet results;
	 	Breeder bObj = new Breeder();
		        
	 	String query = buildQuery(wClause);
		//System.out.println(query);
		
		try{ 	
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			if( results.next() ){
				bObj = buildBreeder(results);
				//stmt.close(); NEDENSTÅENDE KAN IKKE KØRES HVIS DEN LUKKES HER
				if(retrieveAssociation) {
					 int zipCode = results.getInt("zipCode");
					 IFDBCity dbCity = new DBCity();
					 City cityObj = dbCity.selectSingleCity(zipCode);
					 bObj.setCity(cityObj); //set the city object in the breeder object
				 }	
				
			}else{ //No breeder found
				bObj = null;
			}
			stmt.close(); //KAN LUKKES HER
		}//end try	
		
		catch(Exception e){
			System.out.println("Query exception: "+e);
		}
		return bObj;
	 }
	 
	 /**
	 * Method to build the query
	 * @param wClause
	 * @return
	 */
	 private String buildQuery(String wClause)
	 {
	     String query="SELECT * FROM Breeder";
		
	 	if (wClause.length()>0)
	 		query=query+" WHERE "+ wClause;
	 		
	 	return query;
	 }
    
    /**
     * Builds a Breeder object from the data in the database
     * @param result
     * @return Breeder object
     * @throws SQLException
     */
    private Breeder buildBreeder(ResultSet result) throws SQLException {
   	 Breeder bObj = new Breeder();

   	 try {
   		 bObj.setBreederID(result.getInt("breederID"));
   		 bObj.setFname(result.getString("fname"));
   		 bObj.setLname(result.getString("lname"));
   		 bObj.setAddress(result.getString("address"));
   		 bObj.setPhone(result.getString("phone"));
   		 bObj.setEmail(result.getString("email"));
   		 bObj.setPassword(result.getString("password"));
   		 if(result.getString("isAdmin").equals("true")) {
   			 bObj.setAdmin(true);
   		 } else {
   			 bObj.setAdmin(false);
   		 }

   	 } catch (Exception e) {
   		 System.out.println("error building Breeder object, " +e);
        }
        return bObj;
    }

}
