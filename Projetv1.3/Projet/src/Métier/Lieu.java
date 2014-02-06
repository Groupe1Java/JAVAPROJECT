package Métier;

import java.sql.PreparedStatement;import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

import javax.swing.JOptionPane;

public class Lieu {

	
	//Requête d'ajout de lieu : 
	
	public static void ajouterLieu(String nom_lieu, String description_lieu, String image_lieu, String lien_lieu, Date date_crea_lieu, Date date_consult_lieu, int libelle_lieu) throws SQLException {
		// TODO Auto-generated method stub
		 
		    String chainereq ="INSERT INTO `lieu`(`nom_lieu`, `description_lieu`, `image_lieu`, `lien_lieu`,`date_crea_lieu`, `date_consult_lieu`, `libelle_lieu`) VALUES (?,?,?,?,?,?,?)";
		   
		   
		 
		try{
		PreparedStatement ajout =Connect.getInstance().prepareStatement(chainereq);
		ajout.setString(1, nom_lieu);
		ajout.setString(2, description_lieu);
		ajout.setString(3, image_lieu);
		ajout.setString(4, lien_lieu);
		ajout.setDate(5, (java.sql.Date) date_crea_lieu);
		ajout.setDate(6, (java.sql.Date) date_consult_lieu);
		ajout.setInt(7, libelle_lieu);
		ajout.executeUpdate();
		}
		catch(SQLException e)
		{
		JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur de connexion", JOptionPane.ERROR_MESSAGE);
		}
		 
		}
	
	public static void ModifLieu( int id_lieu, String nom_lieu, String descriptif_lieu, String lien_lieu, String libelle_lieu, java.util.Date ajd, String image_lieu) throws SQLException {
		// TODO Auto-generated method stub
		 
		    String chainereq ="UPDATE lieu SET  nom_lieu = ?, descriptif_lieu = ?, lien_lieu = ?, libelle_lieu = ?, date_crea_lieu= ?, image_lieu = ? WHERE id_lieu = ?;";
		   
		   
		 
		try{
		PreparedStatement modif =Connect.getInstance().prepareStatement(chainereq);
		modif.setString(1, nom_lieu);
		modif.setString(2, descriptif_lieu);
		modif.setString(3, lien_lieu);
		modif.setString(4, libelle_lieu);
		
		modif.setDate(5, (java.sql.Date) ajd);
		modif.setString(6, image_lieu);
		modif.setInt(7, id_lieu);
		
		modif.executeUpdate();
		}
		catch(SQLException e)
		{
		JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur de connexion", JOptionPane.ERROR_MESSAGE);
		}
		 
		}
	//Requête de modifictaion de lieu : date de consultation
	
	public static void ModifDateConsultLieu( Date date, String nom_lieu) throws SQLException {
		// TODO Auto-generated method stub
		 
		    String chainereq ="UPDATE lieu SET  date_consult_lieu=? WHERE nom_lieu = ?;";
		   
		   
		 
		try{
		PreparedStatement modif =Connect.getInstance().prepareStatement(chainereq);
		modif.setDate(1,(java.sql.Date) date);
		modif.setString(2, nom_lieu);
		
		
		modif.executeUpdate();
		}
		catch(SQLException e)
		{
		JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur de connexion", JOptionPane.ERROR_MESSAGE);
		}
		 
		}
	
	
	//Requête d'affichage de lieu : 
	
	
	public static String LireLieu() throws SQLException {
		// TODO Auto-generated method stub
		 
		    String query ="SELECT id_lieu, nom_lieu,descriptif_lieu FROM lieu;";
			return query;
		   
	}
	
	
	//Requête de suppression du lieu
	
	
	public static void Supprlieu(int id_lieu) throws SQLException {
		// TODO Auto-generated method stub
		 
		    String chainereq ="DELETE FROM lieu WHERE id_lieu = ?;";
		   
		   
		 
		try{
		PreparedStatement suppr =Connect.getInstance().prepareStatement(chainereq);
		suppr.setInt(1, id_lieu);
		
		
		suppr.executeUpdate();
		}
		catch(SQLException e)
		{
		JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur de connexion", JOptionPane.ERROR_MESSAGE);
		}
	}
	/*******************Lire lieu news******************/
	public static String LireAllAllLieu() throws SQLException {
		// TODO Auto-generated method stub
		 
		   String query ="SELECT * FROM lieu;";
		   
		return query;
		  
		}
	
	/****************************** NEWS***********************/
	public static void SupprDesNews()
	{
	String chainereq ="UPDATE lieu SET  date_crea_lieu=?";
	   
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
	 
	 
	   String chainereq ="UPDATE lieu SET  date_crea_lieu=? WHERE id_lieu = ?;";
	   
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
