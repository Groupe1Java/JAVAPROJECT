package Vue;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.MenuComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;







import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Métier.Carte;
import Métier.Connect;
import Métier.Lieu;

public class Elements {
	
	public static Component formulaire(int action)
	{
		JPanel formulaire=new JPanel();
		JPanel elements=new JPanel();
		formulaire.add(elements);
		GridLayout experimentLayout = new GridLayout(0,2);
		elements.setLayout(experimentLayout);
		JLabel nom1 = new JLabel("Nom : ");
			JTextField nom = new JTextField();
		JLabel libelle1 = new JLabel("Libelle (facultatif) : ");
			JTextField libelle = new JTextField();
		JLabel des1 = new JLabel("Description : ");
			JTextArea des=new JTextArea();
		JLabel lien1 = new JLabel("Lien : ");
			JTextField lien = new JTextField();
			
			elements.add(nom1);
			elements.add(nom);
			elements.add(libelle1);
			elements.add(libelle);
			elements.add(des1);
			elements.add(des);
			elements.add(lien1);
			elements.add(lien);
			
			JPanel parcour=new JPanel();
			parcour.add(elements);
			JLabel image1 = new JLabel("Image : ");
				JTextField image = new JTextField();
				JButton parcourir=new JButton("Parcourir");
				parcour.add(image1);
				parcour.add(image);
				parcour.add(parcourir);
			return formulaire;
	}
	

	public static Component Recap() throws SQLException 
	{
		
			String query = Lieu.LireLieu();
			
			 String TEST = null;
			 final JPanel panelPrinc = new JPanel();
			
			 
			
		
		
		PreparedStatement lire =Connect.getInstance().prepareStatement(query);
	
		Statement state = Connect.connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet res = state.executeQuery(query);
		
		
		
		
			         
		 while(res.next()){
			 
			final int id = res.getInt(1);
			final String nom = res.getString(2);
			 
			
			
			JPanel panelsec = new JPanel();
			JLabel Text = new JLabel(nom); 
			
	
			JButton boutonMod = new JButton("Modifier");
			JButton boutonSuppr = new JButton("Supprimer"); 
			 
			boutonSuppr.addMouseListener(new MouseListener() {
		    		@Override public void mouseClicked(MouseEvent e) {
		    			
		    			final JDialog dialog = new JDialog();
		    			dialog.setLayout(new BorderLayout());
		    			dialog.setTitle("Suppression d'un lieu");
		    			dialog.setSize(new Dimension(460,100));
		    			dialog.setVisible(true);
		    			
		    			JLabel label1 = new JLabel("Vous etes sur le point de supprimer le lieu : "+nom);
		    			
		    			JButton boutonOui = new JButton("Oui");
		    			JButton boutonAnnul = new JButton("Annuler"); 
		    			JPanel panelter = new JPanel(); 
		    			panelter.add(boutonOui);
		    			panelter.add(boutonAnnul);
		    			JPanel panelGen = new JPanel();
		    			panelGen.add(label1);
		    			panelGen.add(panelter);
		    			panelGen.setLayout(new BoxLayout(panelGen, BoxLayout.PAGE_AXIS));
		    			dialog.add(panelGen, BorderLayout.CENTER);
		    			
		    			
		    			boutonAnnul.addMouseListener(new MouseListener() {
				    		@Override public void mouseClicked(MouseEvent e) {
				    			dialog.dispose();
				    		}
				    		public void mousePressed(MouseEvent e) {}
				    		public void mouseReleased(MouseEvent e) {}
				    		public void mouseEntered(MouseEvent e) {}
				    		public void mouseExited(MouseEvent e) {}
				    		});
		    			
		    			
		    			boutonOui.addMouseListener(new MouseListener() {
				    		@Override public void mouseClicked(MouseEvent e) {
				    			try {
									Lieu.Supprlieu(id);
									dialog.dispose();
									Frame1.actualiser();
									//Comment revenir à la page principal ? 
									//Frame1.init_ensemble();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
				    		}
				    		public void mousePressed(MouseEvent e) {}
				    		public void mouseReleased(MouseEvent e) {}
				    		public void mouseEntered(MouseEvent e) {}
				    		public void mouseExited(MouseEvent e) {}
				    		});
		    			

		    		
		    		}
		    		public void mousePressed(MouseEvent e) {}
		    		public void mouseReleased(MouseEvent e) {}
		    		public void mouseEntered(MouseEvent e) {}
		    		public void mouseExited(MouseEvent e) {}
		    		});
			
			boutonMod.addMouseListener(new MouseListener() {
	    		@Override public void mouseClicked(MouseEvent e) {
	    			try {
						FormModif(id);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	    		}
	    		public void mousePressed(MouseEvent e) {}
	    		public void mouseReleased(MouseEvent e) {}
	    		public void mouseEntered(MouseEvent e) {}
	    		public void mouseExited(MouseEvent e) {}
	    		});
			
			
			panelsec.add(Text);
			panelsec.add(boutonMod);
			panelsec.add(boutonSuppr);
			
			
			panelPrinc.add(panelsec);		
			
			
			 
		}
			
			
		      
		 
		 panelPrinc.setLayout(new BoxLayout(panelPrinc, BoxLayout.PAGE_AXIS));
		 
		 JScrollPane test = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		 test.setPreferredSize(new Dimension(500,500));
         test.setViewportView(panelPrinc);
		 
			return test;
		
		 }
		
		
	
	
	
	
	
	
	public static Component AffRecap(int action) throws SQLException
	{
		
		
		JPanel Liste=new JPanel();
		
		
	
		
		Liste.add(Recap());
		
		
		return Liste;

		}

		
	
	public  static Component FormModif(int id_composant) throws SQLException 
	{
		
		String chainereq = "SELECT * FROM lieu WHERE id_lieu ="+id_composant;
		PreparedStatement lect = Connect.connect.prepareStatement(chainereq);
		
		//System.out.print(id_composant);
		Statement state = Connect.connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		final int id = id_composant;
		
		
		
		
		
		
		
			
		
			
			
			ResultSet res = state.executeQuery(chainereq);
			
			res.next();
			final String nom = res.getString(2);
			final String description = res.getString(3);
			final String lien = res.getString(5);
			final String libelle = res.getString(8);
			final String image = res.getString(4);
			
		
		
		
		
		final JDialog FormModif = new JDialog();
		FormModif.setTitle("Formulaire de Modification");
		FormModif.setSize(new Dimension(460,400));
		
		
		
		JPanel panel1 = new JPanel(); 
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();
		JPanel panel6 = new JPanel();
		JPanel panel7 = new JPanel();
		
		
		
		final JTextField Nom = new JTextField(nom,10);
		final JTextArea Description = new JTextArea(description);
		Description.setRows(8);
		Description.setColumns(20);
		final JTextField Lien = new JTextField(lien, 10);
		final JTextField Libelle = new JTextField(libelle, 10);
		final JTextField Image = new JTextField(image, 20);
	final JButton Parcourir = new JButton("Parcourir");
		
		Parcourir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			    JFileChooser chooser = new JFileChooser();
			       int returnVal = chooser.showOpenDialog(Image);
			       if(returnVal == JFileChooser.APPROVE_OPTION) {
			          String chemin=chooser.getSelectedFile().getAbsolutePath();
			          Image.setText(chemin);
			       }
			 
			    }
		} );
		
		JButton Modifier = new JButton("Modifier");
		JButton Annuler = new JButton("Annuler");
		
		panel2.add(new JLabel("Nom : "));
		panel2.add(Nom);
		panel3.add(new JLabel("Description : "));
		panel3.add(Description);
		panel4.add(new JLabel("Lien : "));
		panel4.add(Lien);
		panel5.add(new JLabel("Libelle : "));
		panel5.add(Libelle);
		panel7.add(new JLabel("Image"));
		panel7.add(Image);
		panel7.add(Parcourir);
		panel6.add(Modifier);
		panel6.add(Annuler);
		
		panel1.add(panel2);
		panel1.add(panel3);
		panel1.add(panel4);
		panel1.add(panel5);
		panel1.add(panel5);
		panel1.add(panel7);
		panel1.add(panel6);
		
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));
		
		FormModif.add(panel1);
		FormModif.setVisible(true);
		
		Annuler.addMouseListener(new MouseListener() {
    		@Override public void mouseClicked(MouseEvent e) {
    			FormModif.dispose();
    		}
    		public void mousePressed(MouseEvent e) {}
    		public void mouseReleased(MouseEvent e) {}
    		public void mouseEntered(MouseEvent e) {}
    		public void mouseExited(MouseEvent e) {}
    		});
		Modifier.addMouseListener(new MouseListener() {
    		@Override public void mouseClicked(MouseEvent e) {
    			String nom = Nom.getText();
    			String description = Description.getText(); 
    			String lien = Lien.getText();
    			String libelle = Libelle.getText();
    			String image = Image.getText();
    			Date ajd = new java.sql.Date(System.currentTimeMillis());
    			
    			try {
					Lieu.ModifLieu(id, nom, description, lien, libelle, ajd, image);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    			
    			
    			
    			FormModif.dispose();
    			try {
					Frame1.actualiser();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    		
    		}
    		public void mousePressed(MouseEvent e) {}
    		public void mouseReleased(MouseEvent e) {}
    		public void mouseEntered(MouseEvent e) {}
    		public void mouseExited(MouseEvent e) {}
    		});
		
		
		
		
		
		return FormModif;
	}
	public static Component remplir_Jcomb() throws SQLException {
         
	String requete = Carte.LireCarte();
	PreparedStatement lire =Connect.getInstance().prepareStatement(requete);
	Statement state = Connect.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
       ResultSet res = state.executeQuery(requete);
                   
       
        JComboBox <String> carte = new JComboBox <String>();

                   
      
                   
              while(res.next()){
                           
                           final int id = res.getInt(1);
                          final String nom = res.getString(2);
                          carte.addItem(nom.toString());
                  
                    }
                        
                        
                        
                           return carte;
                          
                    }
	
	public static Component AffComb(int action) throws SQLException
	{
		 JPanel Pchoix = new JPanel();
		 Pchoix.add(remplir_Jcomb());
		 return Pchoix;
	}	

	}
	
	
	

	 

