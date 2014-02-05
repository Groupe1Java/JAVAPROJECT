package M�tier;
import java.awt.GridLayout;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import Vue.Frame1;



public class Recherche {
	
	public static void Rechercher(String choix,String type, String mot) throws SQLException {
		// TODO Auto-generated method stub
		//On r�cup�re le choix de la rechereche : lieu ou parcours
		String table;
		String select = null;
		String dans;
		if(choix=="lieux")
			 {
			 table="SELECT id_lieu,nom_lieu,descriptif_lieu FROM lieu ";
			 dans="nom";
			 }
		 else if(choix=="parcours")
		    {
			 table ="SELECT id_parcours,nom_parcours,descriptif_parcours  FROM parcours ";
			 dans="libelle";
		    }
		 else
		 {
			 table="SELECT * FROM lieu ";dans="nom";
		 }
		 //On r�cup�re le type de recherche : nom exact, partie du nom,mots dans le descriptif et le libell�,p.o.i(parcours)

		   
			if(type=="exacte")
				 {
					select="WHERE "+ dans+"_"+choix+"='"+mot+"'";
				 }
			 else if(type=="partielle")
			    {
				 select ="WHERE "+ dans+"_"+choix+" LIKE '%"+mot+"%'";
			    }
			 else if(type=="description")
			    {
				 select ="WHERE descriptif"+"_"+choix+" LIKE '%"+mot+"%' OR libelle"+"_"+choix+" LIKE '%"+mot+"%'";
			    }
			 else if(type=="poi")
			    {
				 if(choix=="lieu")
					select =",poi WHERE libelle_poi='"+mot+"'";
				 else if(choix=="parcours")
					select =",comper,poi WHERE libelle_poi='"+mot+"'";
			    }
			  
		   String query=table+select;
		 
		try{
		PreparedStatement lire =Connect.getInstance().prepareStatement(query);
		//System.out.println(lire.toString());
		Statement state = Connect.connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet res = state.executeQuery(query); 
		//int i =1;
		//ResultSetMetaData resultMeta = res.getMetaData();
		
	
			//for( i = 1; i <=  resultMeta.getColumnCount(); i++)
			 JPanel panelPrinc = new JPanel();
		 while(res.next()){
			/*for( i = 1; i <=  resultMeta.getColumnCount(); i++)//appeler m�thode d'affichage des �l�ments 
			{*/	
				String nom=res.getString(2);
				String des=res.getString(3);
				String text=nom+" : "+des;
				GridLayout experimentLayout = new GridLayout(0,3);
				
				JPanel panelsec = new JPanel();panelsec.setLayout(experimentLayout);
				JLabel Text = new JLabel(text); 
				panelsec.add(Text);
				panelPrinc.setLayout(new BoxLayout(panelPrinc, BoxLayout.PAGE_AXIS));
				panelPrinc.add(panelsec);
		      //On regarde si on se trouve sur la derni�re ligne du r�sultat*/
		       /*if(i==resultMeta.getColumnCount())
		       { Frame1.afficher_rechercher(panelPrinc);}*/
	//	}
		}Frame1.afficher_rechercher(panelPrinc);
		
		lire.close();
		state.close();
		}
		catch(SQLException e)
		{
		JOptionPane.showMessageDialog(null, e.getMessage(),"Erreur de connexion", JOptionPane.ERROR_MESSAGE);
		}
		 
		}
	
}
