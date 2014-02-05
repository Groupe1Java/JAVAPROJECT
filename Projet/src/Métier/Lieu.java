package M�tier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.JOptionPane;

public class Lieu {

	
	//Requ�te d'ajout de lieu : 
	
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
	
	
	//Requ�te de modifictaion de lieu : date de consultation
	
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
	
	
	//Requ�te d'affichage de lieu : 
	
	
	public static void LireLieu() throws SQLException {
		// TODO Auto-generated method stub
		 
		    String query ="SELECT * FROM lieu;";
		   
		   
		 
		try{
		PreparedStatement lire =Connect.getInstance().prepareStatement(query);
		//System.out.println(lire.toString());
		Statement state = Connect.connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet res = state.executeQuery(query);
		int i =1;
		ResultSetMetaData resultMeta = res.getMetaData();
		for( i = 1; i <=  resultMeta.getColumnCount(); i++)
			         
		 while(res.next()){
			for( i = 1; i <=  resultMeta.getColumnCount(); i++)
			System.out.println(resultMeta.getColumnName(i).toUpperCase() + " : " + res.getObject(i).toString());
		      //On regarde si on se trouve sur la derni�re ligne du r�sultat
		       if(res.isLast())
		       		       i++;
		}
		
		lire.close();
		state.close();
		}
		catch(SQLException e)
		{
		JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur de connexion", JOptionPane.ERROR_MESSAGE);
		}
		 
		}
	
	
	//Requ�te de suppression du lieu
	
	
	public static void Supprlieu(String nom_lieu) throws SQLException {
		// TODO Auto-generated method stub
		 
		    String chainereq ="DELETE FROM lieu WHERE nom_lieu = ?;";
		   
		   
		 
		try{
		PreparedStatement suppr =Connect.getInstance().prepareStatement(chainereq);
		suppr.setString(1, nom_lieu);
		
		
		suppr.executeUpdate();
		}
		catch(SQLException e)
		{
		JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur de connexion", JOptionPane.ERROR_MESSAGE);
		}
	}
}
