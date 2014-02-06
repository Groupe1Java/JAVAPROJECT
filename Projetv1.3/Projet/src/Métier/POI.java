package M�tier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

import javax.swing.JOptionPane;

public class POI {
	
	// Requ�te d'ajout : 
	
	public static void ajouterPOI(int x_poi, int y_poi, String libelle_poi, String description_poi, String lien_poi, String image_poi, Date date_crea_poi, Date date_consult_poi, int id_lieu) throws SQLException {
		// TODO Auto-generated method stub
		 
		    String chainereq ="INSERT INTO `poi`(`x_poi`, `y_poi`, `libelle_poi`, `description_poi`,`lien_poi`, `image_poi`, `date_crea_poi`, `date_consult_poi`, `id_lieu` ) VALUES (?,?,?,?,?,?,?,?,?)";
		   
		   
		 
		try{
		PreparedStatement ajout =Connect.getInstance().prepareStatement(chainereq);
		ajout.setInt(1, x_poi);
		ajout.setInt(2, y_poi);
		ajout.setString(3, libelle_poi);
		ajout.setString(4, description_poi);
		ajout.setString(5, lien_poi);
		ajout.setString(6, image_poi);
		ajout.setDate(7, (java.sql.Date) date_crea_poi);
		ajout.setDate(8, (java.sql.Date) date_consult_poi);
		ajout.setInt(9, id_lieu);
		ajout.executeUpdate();
		}
		catch(SQLException e)
		{
		JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur de connexion", JOptionPane.ERROR_MESSAGE);
		}
		 
		}
	
	//Requ�te de modification date de consultation
	
	public static void ModifDateConsultPoi( Date date, String libelle_poi) throws SQLException {
		// TODO Auto-generated method stub
		 
		    String chainereq ="UPDATE poi SET  date_consult_poi=? WHERE libelle_poi = ?;";
		   
		   
		 
		try{
		PreparedStatement modif =Connect.getInstance().prepareStatement(chainereq);
		modif.setDate(1,(java.sql.Date) date);
		modif.setString(2, libelle_poi);
		
		
		modif.executeUpdate();
		}
		catch(SQLException e)
		{
		JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur de connexion", JOptionPane.ERROR_MESSAGE);
		}
		 
		}
	
	//Requ�te de selection POI
	
	public static String LirePoi() throws SQLException {
		// TODO Auto-generated method stub
		 
		    String query ="SELECT * FROM poi;";
		    return query;
		}
	
	//Requ�te de suppression de POI
	
	public static void SupprPoi(int id) throws SQLException {
		// TODO Auto-generated method stub
		 
		    String chainereq ="DELETE FROM poi WHERE id_poi = ?;";
		   
		   
		 
		try{
		PreparedStatement suppr =Connect.getInstance().prepareStatement(chainereq);
		suppr.setInt(1, id);
		
		
		suppr.executeUpdate();
		}
		catch(SQLException e)
		{
		JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur de connexion", JOptionPane.ERROR_MESSAGE);
		}
	}
// Requ�te de modification : 
	public static void modifierPOI(int id,String nom,
			String description, String lien, String libelle, java.util.Date ajd,
			String image) {
		// TODO Auto-generated method stub
		String chainereq ="UPDATE `poi` SET `libelle_poi`=?,`description_poi`=?,`lien_poi`=?,`image_poi`=?,`date_crea_poi`=? WHERE `id_poi`=?;";
		try{ 
		
			PreparedStatement modif =Connect.getInstance().prepareStatement(chainereq);
			modif.setString(1, nom);
			modif.setString(2, description);
			modif.setString(3, lien);
			modif.setString(4, image);	
			modif.setDate(5, (java.sql.Date) ajd);	
			modif.setInt(6, id);
			
			modif.executeUpdate();
			}
			catch(SQLException e)
			{
			JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur de connexion", JOptionPane.ERROR_MESSAGE);
			}	
	
	}
	
	/****************************NEWS**************************/
	public static void SupprDesNews()
	{
	String chainereq ="UPDATE poi SET  date_crea_poi=?";
	   
	   String NewDate = "1111-11-11";
	   Date date = Date.valueOf(NewDate);
	 
	  
	 
	try{
	PreparedStatement modif =Connect.getInstance().prepareStatement(chainereq);
	modif.setDate(1,(java.sql.Date) date);
	 
	 
	 
	modif.executeUpdate();
	}
	catch(SQLException e)
	{
	JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur de connexion", JOptionPane.ERROR_MESSAGE);
	}
	}
	public static void SupprUneNews(int idNews)
	{
	 
	 
	   String chainereq ="UPDATE poi SET  date_crea_poi=? WHERE id_poi = ?;";
	   
	   String NewDate = "1111-11-11";
	   Date date = Date.valueOf(NewDate);
	 
	  
	 
	try{
	PreparedStatement modif =Connect.getInstance().prepareStatement(chainereq);
	modif.setDate(1,(java.sql.Date) date);
	modif.setInt(2, idNews);
	 
	 
	modif.executeUpdate();
	}
	catch(SQLException e)
	{
	JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur de connexion", JOptionPane.ERROR_MESSAGE);
	}
	 
	}
}
