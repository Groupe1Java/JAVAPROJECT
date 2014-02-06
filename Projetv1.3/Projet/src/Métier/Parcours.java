package M�tier;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

import javax.swing.JOptionPane;

public class Parcours {
	
	//Requ�te d'ajout de Parcours
	public static void ajouterParcours(String descriptif_parcours,String libelle_parcours, String lien_parcours,  String image_parcours,int reference_parcours , java.util.Date date_crea_parcours, java.util.Date date_consult_parcours ) throws SQLException {
		// TODO Auto-generated method stub
		 
		    String chainereq ="INSERT INTO `parcours`(`descriptif_parcours`, `libelle_parcours`, `lien_parcours`, `image_parcours`,`reference_parcours`, `date_crea_parcours`, `date_consult_parcours`) VALUES (?,?,?,?,?,?,?)";
		   
		   
		 
		try{
		PreparedStatement ajout =Connect.getInstance().prepareStatement(chainereq);
		ajout.setString(1, descriptif_parcours);
		ajout.setString(2, libelle_parcours);
		ajout.setString(3, lien_parcours);
		ajout.setString(4, image_parcours);
		ajout.setInt(5, reference_parcours);
		ajout.setDate(6, (java.sql.Date) date_crea_parcours);
		ajout.setDate(7, (java.sql.Date) date_consult_parcours);
		ajout.executeUpdate();
		}
		catch(SQLException e)
		{
		JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur de connexion", JOptionPane.ERROR_MESSAGE);
		}
		 
		}
	
	//Requ�te de modification : Date de consultation
	
	public static void ModifDateConsultParcours( Date date, String libelle_parcours) throws SQLException {
		// TODO Auto-generated method stub
		 
		    String chainereq ="UPDATE parcours SET  date_consult_parcours=? WHERE libelle_parcours = ?;";
		   
		   
		 
		try{
		PreparedStatement modif =Connect.getInstance().prepareStatement(chainereq);
		modif.setDate(1,(java.sql.Date) date);
		modif.setString(2, libelle_parcours);
		
		
		modif.executeUpdate();
		}
		catch(SQLException e)
		{
		JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur de connexion", JOptionPane.ERROR_MESSAGE);
		}
		 
		}
	//Modifier un parcours
	public static void ModifParcours( int id_parcours,String descriptif_parcours,String libelle_parcours,String lien_parcours,String image_parcours,int reference_parcours,java.util.Date date_crea_parcours,java.util.Date date_consult_parcours) throws SQLException {
		// TODO Auto-generated method stub
		 
		    String chainereq ="UPDATE `parcours` SET `descriptif_parcours`=?,`libelle_parcours`=?,`lien_parcours`=?,`image_parcours`=?,`reference_parcours`=?,`date_crea_parcours`=?,`date_consult_parcours`=? WHERE `id_parcours`=?;";
		   
		   
		 
		try{
		PreparedStatement modif =Connect.getInstance().prepareStatement(chainereq);
		modif.setString(1, descriptif_parcours);
		modif.setString(2, libelle_parcours);
		modif.setString(3, lien_parcours);
		modif.setString(4, image_parcours);
		modif.setInt(5, reference_parcours);
		modif.setDate(6, (java.sql.Date)date_crea_parcours);
		modif.setDate(7,(java.sql.Date)date_consult_parcours);
		modif.setInt(8, id_parcours);
		modif.executeUpdate();
		}
		catch(SQLException e)
		{
		JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur de connexion", JOptionPane.ERROR_MESSAGE);
		}
		 
		}
//Requete de suppression de parcours
	public static void SupprParcours(int id) throws SQLException {
		// TODO Auto-generated method stub
		 String chainereq1 ="DELETE FROM comper WHERE id_parcours = ?;";
			try{
			PreparedStatement suppr =Connect.getInstance().prepareStatement(chainereq1);
			suppr.setInt(1, id);
			suppr.executeUpdate();
			}
			catch(SQLException e)
			{
			JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur de connexion", JOptionPane.ERROR_MESSAGE);
			}
		    String chainereq ="DELETE FROM parcours WHERE id_parcours = ?;";
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
	//Selectionner un parcours grace a son id
	public static String Selectparcours(String nom_sel)throws SQLException
	{
		 String req= "SELECT * FROM parcours WHERE libelle_parcours='"+nom_sel+"';";
		 return req;
	}
	
	public static String creation_liste_parcours()throws SQLException
	{
		 String req= "SELECT id_parcours, libelle_parcours FROM  parcours  ORDER BY  libelle_parcours"; 
			return req;
	}
	
	/*public static String Selectparcours(int id)throws SQLException
	{
		 String req= "SELECT * FROM  parcours WHERE id_parcours='"+id+"' ORDER BY libelle_parcours;"; 
			return req;
	}*/
	public static String Select_poi_parcours(int id)throws SQLException
	{
		 String req= "SELECT * FROM `poi`,`comper`,`parcours`WHERE parcours.id_parcours="+id+";";
		 return req;
	}
	/*******************************NEWS************************/
	public static String LireParcours() throws SQLException {
		// TODO Auto-generated method stub
		 
		   String query ="SELECT * FROM parcours;";
		  return query;
		}
	public static void SupprDesNews()
	{
	String chainereq ="UPDATE parcours SET  date_crea_parcours=?";
	   
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
	 
	 
	   String chainereq ="UPDATE parcours SET  date_crea_parcours=? WHERE id_parcours = ?;";
	   
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
