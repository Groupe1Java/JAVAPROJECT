package M�tier;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class Connect {

	
	 /** * URL de connection */  
	private static String url = "jdbc:mysql://127.0.0.1:3306/minisig";
	 /** * Nom du user */  
	private static String user = "root";
	 /** * Mot de passe du user */  
	private static String passwd = "";
	 /** * Objet Connection */  
	public static Connection connect;
	 /** * M�thode qui va retourner notre instance * et la cr�er si elle n'existe pas */  
	public static Connection getInstance(){    
	if(connect == null){     
	try {       
	connect = DriverManager.getConnection(url, user, passwd); 
	}
	
	catch (SQLException e) { 
	JOptionPane.showMessageDialog(null, e.getMessage(), "ERREUR DE CONNEXION ! ", JOptionPane.ERROR_MESSAGE); 
	}    
	}      
	return connect;  
	}
}
	
	

	


