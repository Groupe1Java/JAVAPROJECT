package M�tier;
/*import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;*/




public class Requete {
	
	
	//Requetes d'ajout
	
	//LIEU
	/*
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
	
	
	
	
	//POI
	
	public static void ajouterPOI(int x_poi, int y_poi, String libelle_poi, String description_poi, String lien_poi, String image_poi, int reference_poi, Date date_crea_poi, Date date_consult_poi, int id_lieu) throws SQLException {
		// TODO Auto-generated method stub
		 
		    String chainereq ="INSERT INTO `poi`(`x_poi`, `y_poi`, `libelle_poi`, `description_poi`,`lien_poi`, `image_poi`, `reference_poi`, `date_crea_poi`, `date_consult_poi`, `id_lieu` ) VALUES (?,?,?,?,?,?,?,?,?,?)";
		   
		   
		 
		try{
		PreparedStatement ajout =Connect.getInstance().prepareStatement(chainereq);
		ajout.setInt(1, x_poi);
		ajout.setInt(2, y_poi);
		ajout.setString(3, libelle_poi);
		ajout.setString(4, description_poi);
		ajout.setString(5, lien_poi);
		ajout.setString(6, image_poi);
		ajout.setInt(7, reference_poi);
		ajout.setDate(8, (java.sql.Date) date_crea_poi);
		ajout.setDate(9, (java.sql.Date) date_consult_poi);
		ajout.setInt(10, id_lieu);
		ajout.executeUpdate();
		}
		catch(SQLException e)
		{
		JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur de connexion", JOptionPane.ERROR_MESSAGE);
		}
		 
		}
	
	public static void ajouterParcours(String descriptif_parcours,String libelle_parcours, String lien_parcours,  String image_parcours,int reference_parcours , Date date_crea_parcours, Date date_consult_parcours ) throws SQLException {
		// TODO Auto-generated method stub
		 
		    String chainereq ="INSERT INTO `parcours`(`descriptif_parcours`, `libelle_parcours`, `lien_parcours`, `image_parcours`,`reference_parcours`, `date_crea_parcours`, `date_consult_lieu`) VALUES (?,?,?,?,?,?)";
		   
		   
		 
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
	
	//COMPER
	
	
	public static void ajouterComper(int id_parcours, int id_poi) throws SQLException {
		// TODO Auto-generated method stub
		 
		    String chainereq ="INSERT INTO `comper`(`id_parcours`, `id_poi`) VALUES (?,?)";
		   
		   
		 
		try{
		PreparedStatement ajout =Connect.getInstance().prepareStatement(chainereq);
		ajout.setInt(1, id_parcours);
		ajout.setInt(2, id_poi);
		ajout.executeUpdate();
		}
		catch(SQLException e)
		{
		JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur de connexion", JOptionPane.ERROR_MESSAGE);
		}
		 
		}
	
	
	//TEST
	
	public static void ajouterTest(String nom ,String description) throws SQLException {
		// TODO Auto-generated method stub
		 
		    String chainereq ="INSERT INTO `TEST`(`nom`, `description`) VALUES (?,?)";
		   
		   
		 
		try{
		PreparedStatement ajout =Connect.getInstance().prepareStatement(chainereq);
		ajout.setString(1,nom);
		ajout.setString(2,description);
		ajout.executeUpdate();
		}
		catch(SQLException e)
		{
		JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur de connexion", JOptionPane.ERROR_MESSAGE);
		}
		 
		}
	
	
	
	
	
	//Requete de Modification
	
	// DATE DE CONSUTATION DU LIEU
	
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
	
	
	// DATE DE CONSULTATION DU POI
	
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
	
	//DATE DE CONSULTATION PARCOURS
	
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
	
	
	
	
	//MODIFIER LA LIGNE TEST
	
	
	public static void ModifierTest(String nom ,String description, Date date) throws SQLException {
		// TODO Auto-generated method stub
		 
		    String chainereq ="UPDATE TEST SET nom = ?, description = ?, DATE=?;";
		   
		   
		 
		try{
		PreparedStatement modif =Connect.getInstance().prepareStatement(chainereq);
		modif.setString(1,nom);
		modif.setString(2,description);
		modif.setDate(3,(java.sql.Date) date);
		
		
		modif.executeUpdate();
		}
		catch(SQLException e)
		{
		JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur de connexion", JOptionPane.ERROR_MESSAGE);
		}
		 
		}
	
	
	
	//Requete de lecture
	
	public static void LireTest() throws SQLException {
		// TODO Auto-generated method stub
		 
		    String query ="SELECT * FROM test;";
		   
		   
		 
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
	
	public static void LirePoi() throws SQLException {
		// TODO Auto-generated method stub
		 
		    String query ="SELECT * FROM poi;";
		   
		   
		 
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
	
	//REQUETES DE SUPPRESSION
	
	
	public static void SupprPoi(int id_lieu) throws SQLException {
		// TODO Auto-generated method stub
		 
		    String chainereq ="DELETE FROM poi WHERE id_lieu = ?;";
		   
		   
		 
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
		
		
			public static void SupprTest(Date date) throws SQLException {
				// TODO Auto-generated method stub
				 
				    String chainereq ="DELETE FROM test WHERE DATE = ?;";
				   
				   
				 
				try{
				PreparedStatement suppr =Connect.getInstance().prepareStatement(chainereq);
				suppr.setDate(1, (java.sql.Date) date);
				
				
				suppr.executeUpdate();
				}
				catch(SQLException e)
				{
				JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur de connexion", JOptionPane.ERROR_MESSAGE);
				}
		 
		}*/
	
}
