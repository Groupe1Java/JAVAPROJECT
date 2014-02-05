package M�tier;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JOptionPane;

public class Parcours {
	
	//Requ�te d'ajout de Parcours
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
	

	
	

}
