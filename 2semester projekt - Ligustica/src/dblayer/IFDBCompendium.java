package dblayer;

import java.sql.SQLException;
import java.util.ArrayList;

import modellayer.Compendium;

/**
 * @author Gruppe 3
 */
/*
 * Jeg har ingen anelse om hvorfor denne her kommer med fejl
 */

public class IFDBCompendium {
	
	public ArrayList<Compendium> getAllCompendiums(boolean retriveAssociation) {
		return null; //Det giver ingen mening at dette skal med før der ikke kommer fejl, eller hvad?
	} 

	public Compendium searchCompendiumOnName(String name, boolean retriveAssociation) throws SQLException;

	public Compendium selectSingleCompendium(int compendiumID, boolean retriveAssociation) throws SQLException;

	public int insertCompendium(Compendium c) throws SQLException; 

	public int updateCompendium(Compendium c) throws SQLException; 
	
	public int deleteCompendium(Compendium c) throws SQLException;

}
