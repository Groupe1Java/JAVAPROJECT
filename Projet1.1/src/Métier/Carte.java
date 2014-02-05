package M�tier;

import java.sql.*;
import java.util.Date;
import javax.swing.*;



public class Carte {
	
	
	//Ajout d'une carte 
	
	public static void ajouterCarte(String nom_carte, String description_carte, String image_carte, String lien_carte, String libelle_carte) throws SQLException {
		// TODO Auto-generated method stub
		Date ajd=new java.sql.Date(System.currentTimeMillis());
		 
		    String chainereq ="INSERT INTO `carte`(`nom_carte`, `description_carte`, `image_carte`, `lien_carte`,`date_crea_carte`, `date_consult_carte`, `libelle_carte`) VALUES (?,?,?,?,?,?,?)";

		try{
		PreparedStatement ajout =Connect.getInstance().prepareStatement(chainereq);
		ajout.setString(1, nom_carte);
		ajout.setString(2, description_carte);
		ajout.setString(3, image_carte);
		ajout.setString(4, lien_carte);
		ajout.setDate(5, (java.sql.Date) ajd);
		ajout.setDate(6, (java.sql.Date) ajd);
		ajout.setString(7, libelle_carte);
		ajout.executeUpdate();
		}
		catch(SQLException e)
		{
		JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur de connexion", JOptionPane.ERROR_MESSAGE);
		}
		 
		}
	
	//Requete de modification
	public static void ModifCarte(int id, String nom, String description, String image, String lien, String libelle, Date ajd) throws SQLException {
		// TODO Auto-generated method stub
		 
		    String chainereq ="UPDATE `carte` SET `nom_carte`=?,`description_carte`=?,`image_carte`=?,`lien_carte`=?,`date_crea_carte`=?,`libelle_carte`=? WHERE `id_carte`=?";
		    try{
				PreparedStatement modif =Connect.getInstance().prepareStatement(chainereq);
				
				modif.setString(1, nom);
				modif.setString(2,description);
				modif.setString(3,image);
				modif.setString(4,lien);
				modif.setDate(5,(java.sql.Date) ajd);
				modif.setString(6,libelle);
				modif.setInt(7,id);
				modif.executeUpdate();
				}
				catch(SQLException e)
				{
				JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur de connexion", JOptionPane.ERROR_MESSAGE);
				}
	}
	 //Requete de modification carte date de consultation
	
	public static void ModifDateConsultCarte( Date date, String nom_carte) throws SQLException {
		// TODO Auto-generated method stub
		 
		    String chainereq ="UPDATE carte SET  date_consult_carte=? WHERE nom_carte = ?;";
		   
		   
		 
		try{
		PreparedStatement modif =Connect.getInstance().prepareStatement(chainereq);
		modif.setDate(1,(java.sql.Date) date);
		modif.setString(2, nom_carte);
		
		
		modif.executeUpdate();
		}
		catch(SQLException e)
		{
		JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur de connexion", JOptionPane.ERROR_MESSAGE);
		}
		 
		}
	
	//Requete de lecture carte
	
	public static String LireCarte() throws SQLException {
		// TODO Auto-generated method stub
		 	String query ="SELECT * FROM carte;";
		   return query;
	 
		}
	
	
	//Requete de suppression de carte 
	
	
	public static void SupprCarte(int id_carte) throws SQLException {
		// TODO Auto-generated method stub
		 
		    String chainereq ="DELETE FROM carte WHERE nom_carte = ?;";
		   
		   
		 
		try{
		PreparedStatement suppr =Connect.getInstance().prepareStatement(chainereq);
		suppr.setInt(1, id_carte);
		
		
		suppr.executeUpdate();
		}
		catch(SQLException e)
		{
		JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur de connexion", JOptionPane.ERROR_MESSAGE);
		}
	}


}
