package M�tier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.JOptionPane;

public class POI {
	
	// Requ�te d'ajout : 
	
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
	
	//Requ�te de suppression de POI
	
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
	
	

}
