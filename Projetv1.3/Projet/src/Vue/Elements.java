package Vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import M�tier.Carte;
import M�tier.Connect;
import M�tier.Lieu;
import M�tier.POI;
import M�tier.Parcours;
import M�tier.Recherche;

public class Elements {
	//Formulaire pour ajout de carte et lieu
	public static Component formulaire(final int action)
	{
		final int choix=action;
		final JPanel formulaire=new JPanel();
		formulaire.setLayout(new BoxLayout(formulaire, BoxLayout.PAGE_AXIS));
		JPanel elements=new JPanel();
		formulaire.add(elements);
		GridLayout experimentLayout = new GridLayout(0,2);
		elements.setLayout(experimentLayout);
		JLabel nom1 = new JLabel("Nom : ");
			final JTextField nom = new JTextField(20);
		JLabel libelle1 = new JLabel("Libelle (facultatif) : ");
			final JTextField libelle = new JTextField(20);
		JLabel lien1 = new JLabel("Lien : ");
			final JTextField lien = new JTextField(20);
			
			elements.add(nom1);
			elements.add(nom);
			elements.add(libelle1);
			elements.add(libelle);
			elements.add(lien1);
			elements.add(lien);
			//Panel contenant le bouton parcourir 
			JPanel chercher = new JPanel();
			formulaire.add(chercher);
			JLabel image1 = new JLabel("Image : ");
				final JTextField image = new JTextField(30);
				JButton parcourir=new JButton("Parcourir");
				parcourir.addActionListener(new ActionListener(){
				    public void actionPerformed(ActionEvent e){
				    	JFileChooser chooser = new JFileChooser();
				        int returnVal = chooser.showOpenDialog(image);
				        if(returnVal == JFileChooser.APPROVE_OPTION) {
				           String chemin=chooser.getSelectedFile().getAbsolutePath();
				           image.setText(chemin);
				    }
				 
				    }
				} );
				chercher.add(image1);
				chercher.add(image);
				chercher.add(parcourir);
		
			//Cr�ation du panel de description
			JPanel description = new JPanel();
			formulaire.add(description);
			JLabel des1 = new JLabel("Description : ");
				final JTextArea des=new JTextArea(10,50);
				description.add(des1);
				description.add(des);
			JButton validation=new JButton("Valider");
			//Bouton de validation de l'ajout
			formulaire.add(validation);
					
			
			validation.addMouseListener(new MouseListener() {
				
			    public void mouseClicked(MouseEvent e) {
			    	//Boolean verif1=Recherche.verif_chaine_string(nom.getText());
			    	JFrame frame=new JFrame();
			    	if(choix==1)// Si il s'agit d'un ajout de carte
			    	{ 
			    		
			    		String nom_carte=nom.getText();
			    		String description_carte=des.getText();
						String image_carte=image.getText();
						String lien_carte=lien.getText();
						String libelle_carte=libelle.getText();
				try {
					
					Carte.ajouterCarte(nom_carte,description_carte,image_carte,lien_carte,libelle_carte);
					JOptionPane.showMessageDialog(frame,"Votre modification a �t� prise en compte");
					
					} catch (SQLException e1) {
					// TODO Auto-generated catch block
						
					JOptionPane.showMessageDialog(frame,"Une erreur est survenue ","Erreur",JOptionPane.ERROR_MESSAGE);
				}
				
				}
			    	
					
			    }
			   
			    public void mousePressed(MouseEvent e) {}
			    public void mouseReleased(MouseEvent e) {}
			    public void mouseEntered(MouseEvent e) {}
			    public void mouseExited(MouseEvent e) {}
			    });

			
		
			
			return formulaire;
	}
	//Notre formulaire de recherche 
	public static Component formulaire_recherche()
	{
		JPanel formulaire_recherche=new JPanel();
		
		JPanel tableau=new JPanel();
		formulaire_recherche.add(tableau);
		GridLayout experimentLayout = new GridLayout(0,2);
		tableau.setLayout(experimentLayout);
		JLabel message_choix=new JLabel("Recherche sur les : ");
			String[] choix = { "lieu", "parcours"};
			final JComboBox<String> choixl = new JComboBox<String>(choix);
		JLabel message_type=new JLabel("Type de recherche : ");
			String[] type = { "exacte", "partielle","description","poi"};
			final JComboBox<String> typel = new JComboBox<String>(type);
		JLabel message_mot=new JLabel("Mot recherch� : ");
			final JTextField motl =new JTextField(20);
			JButton recherche=new JButton("Rechercher");
			JLabel vide=new JLabel();
			tableau.add(message_choix);
			tableau.add(choixl);
			tableau.add(message_type);
			tableau.add(typel);
			tableau.add(message_mot);
			tableau.add(motl);
			tableau.add(vide);
			tableau.add(recherche);
			
		
			recherche.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent arg0) {
					// TODO Auto-generated method stub
					String choix=choixl.getSelectedItem().toString(); 
					String type=typel.getSelectedItem().toString(); 
					String mot=motl.getText();
					try {
						Recherche.Rechercher(choix,type,mot);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			});return formulaire_recherche;
	}
	//on cr�ee notre liste des cartes avec les bouttons de mod et supp
	public static JScrollPane liste_carte() throws SQLException
	{
		
		String query = Carte.LireCarte();
		 
		final JPanel panelPrinc = new JPanel();
		PreparedStatement lect = Connect.getInstance().prepareStatement(query);
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
			    dialog.setTitle("Suppression d'une carte");
			    dialog.setSize(new Dimension(460,100));
			    dialog.setVisible(true);
			   
			    JLabel label1 = new JLabel("Vous etes sur le point de supprimer la carte : "+nom);
			   
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
			Carte.SupprCarte(id);
			dialog.dispose();
			Frame1.actualiser();
			//Comment revenir � la page principal ? 
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
			FormModif_carte(id);
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
	//On appel la m�thode qui affiche la liste de nos carte
	public static Component AffRecap_carte(int action) throws SQLException
	{

	JPanel Liste=new JPanel();
	Liste.add(liste_carte());
	return Liste;
	}
	// On va charger l'affichage de le formulaire pr� remplie des modif de carte
	public  static Component FormModif_carte(int id_composant) throws SQLException 
	{
	 
	String chainereq = "SELECT * FROM carte WHERE id_carte ="+id_composant;
	Statement state = Connect.connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	final int id = id_composant;
 
	ResultSet res = state.executeQuery(chainereq);
	 
	res.next();
	final String nom = res.getString(2);
	final String description = res.getString(3);
	final String image =res.getString(4);
	final String lien = res.getString(5);
	final int libelle = Integer.parseInt(res.getString(8));
	
	
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
	final JTextField Libelle = new JTextField(libelle);
	final JTextField image2 = new JTextField(image, 20);
	JButton Modifier = new JButton("Modifier");
	JButton Annuler = new JButton("Annuler");
	JButton Parcourir=new JButton("Parcourir");
	Parcourir.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e){
	    	JFileChooser chooser = new JFileChooser();
	        int returnVal = chooser.showOpenDialog(image2);
	        if(returnVal == JFileChooser.APPROVE_OPTION) {
	           String chemin=chooser.getSelectedFile().getAbsolutePath();
	           image2.setText(chemin);
	        }
			 
    }
} );
	panel2.add(new JLabel("Nom : "));
	panel2.add(Nom);
	panel3.add(new JLabel("Description : "));
	panel3.add(Description);
	panel4.add(new JLabel("Image : "));
	panel4.add(image2);panel4.add(Parcourir);
	panel5.add(new JLabel("Lien : "));
	panel5.add(Lien);
	panel6.add(new JLabel("Libelle : "));
	panel6.add(Libelle);
	panel7.add(Modifier);
	panel7.add(Annuler);
	 
	panel1.add(panel2);
	panel1.add(panel3);
	panel1.add(panel4);
	panel1.add(panel5);
	panel1.add(panel5);
	panel1.add(panel6);
	panel1.add(panel7);
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
	    String image=image2.getText();
	    String lien = Lien.getText();
	    String libelle = Libelle.getText();
	    Date ajd = new java.sql.Date(System.currentTimeMillis());
	   
	    try {
	Carte.ModifCarte(id, nom, description,image, lien, libelle, ajd);
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
	// Cr�er l'affichage du tableau pour modifier et supprimer les lieux
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
									//Comment revenir � la page principal ? 
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
	//Afficher le formulaire de modification
	public static Component AffRecap(int action) throws SQLException
	{
		JPanel Liste=new JPanel();
		Liste.add(Recap());
		return Liste;
	}

//	Cr�er le formualaire de modification et recup les infos
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
		
	/*******************************************************************Pour les POI***************************************************/
	// Cr�er l'affichage du tableau pour modifier et supprimer les lieux
		public static Component Recap_poi() throws SQLException 
		{
			
				String query = POI.LirePoi();
				String TEST = null;
				final JPanel panelPrinc = new JPanel();
			PreparedStatement lire =Connect.getInstance().prepareStatement(query);
			Statement state = Connect.connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet res = state.executeQuery(query);
		         
			 while(res.next()){
				 
				final int id = res.getInt(1);
				final String nom = res.getString(4);
				JPanel panelsec = new JPanel();
				JLabel Text = new JLabel(nom); 
				JButton boutonMod = new JButton("Modifier");
				JButton boutonSuppr = new JButton("Supprimer"); 
				 
				boutonSuppr.addMouseListener(new MouseListener() {
			    		@Override public void mouseClicked(MouseEvent e) {
			    			
			    			final JDialog dialog = new JDialog();
			    			dialog.setLayout(new BorderLayout());
			    			dialog.setTitle("Suppression d'un poi");
			    			dialog.setSize(new Dimension(460,100));
			    			dialog.setVisible(true);
			    			
			    			JLabel label1 = new JLabel("Vous etes sur le point de supprimer le poi : "+nom);
			    			
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
										POI.SupprPoi(id);
										dialog.dispose();
										Frame1.actualiser();
										//Comment revenir � la page principal ? 
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
		    			try {FormModif_poi(id);
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
		//Afficher le formulaire de modification
		public static Component AffRecap_poi(int action) throws SQLException
		{
			JPanel Liste=new JPanel();
			Liste.add(Recap_poi());
			return Liste;
		}

//		Cr�er le formualaire de modification et recup les infos
		public  static Component FormModif_poi(int id_composant) throws SQLException 
		{
			
			String chainereq = "SELECT * FROM poi WHERE id_poi ="+id_composant;
			PreparedStatement lect = Connect.connect.prepareStatement(chainereq);
			
			//System.out.print(id_composant);
			Statement state = Connect.connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			final int id = id_composant;
				
				ResultSet res = state.executeQuery(chainereq);
				
				res.next();
				final String nom = res.getString(4);
				final String description = res.getString(5);
				final String lien = res.getString(6);
				final String libelle = res.getString(10);
				final String image = res.getString(7);
		
			
			
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
			JPanel panel8 = new JPanel();
			
			
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
			panel8.add(Modifier);
			panel8.add(Annuler);
			
			panel1.add(panel2);
			panel1.add(panel3);
			panel1.add(panel4);
			panel1.add(panel5);
			panel1.add(panel5);
			panel1.add(panel7);
			panel1.add(panel6);
			panel1.add(panel8);
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
	    			
	    			POI.modifierPOI(id, nom, description, lien, libelle, ajd, image);
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
		/******************************************Pour nos parcours************************************************/
		public static Component formulaire_choix_action_parcours() throws SQLException
		{/*	panelPrinc:contient l'ensemble:
		 		panelchoix:contient notre grid qui contient notre liste d�roulante et les boutons d'action
		 		panelaffichage: dans lequel on chargera tout nos �l�ments
		 */
			JPanel panelPrinc=new JPanel();
			 panelPrinc.setLayout(new BoxLayout( panelPrinc, BoxLayout.PAGE_AXIS));
			JPanel panelchoix=new JPanel();
			final JPanel panelaffichage=new JPanel();
			
			JLabel intitule=new JLabel("Vos Parcours");JLabel vide=new JLabel(" ");
			panelPrinc.add(intitule);panelPrinc.add(vide);
			GridLayout grid=new GridLayout(0,7,10,10);
			panelchoix.setLayout(grid);
			panelPrinc.add(panelchoix);
			//On place nos �l�ments dans notre panelchoix
			final JComboBox<String> liste_parcours =new JComboBox<String>();
			String query=Parcours.creation_liste_parcours();
			PreparedStatement lect = Connect.getInstance().prepareStatement(query);
			Statement state = Connect.connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet res = state.executeQuery(query);	
			JButton boutonVoir=new JButton("Voir");
			JButton boutonAjout = new JButton("Ajouter");
			JButton boutonMod = new JButton("Modifier");
			JButton boutonSuppr = new JButton("Supprimer"); panelPrinc.add(panelaffichage);
			
			boutonAjout.addMouseListener(new MouseListener() {
	    		@Override public void mouseClicked(MouseEvent e) {
	    			panelaffichage.removeAll();panelaffichage.add(AjoutParcours());panelaffichage.revalidate();
	    		}
	    		public void mousePressed(MouseEvent e) {}
	    		public void mouseReleased(MouseEvent e) {}
	    		public void mouseEntered(MouseEvent e) {}
	    		public void mouseExited(MouseEvent e) {}
	    		});
			
			try{
				
			while(res.next()){
			final int  id=res.getInt(1);
			liste_parcours.addItem(res.getString(2));     
			panelchoix.add(liste_parcours);
			
			boutonVoir.addMouseListener(new MouseListener() {
	    		@Override public void mouseClicked(MouseEvent e) {
	    			try {
						afficher_info_parcours(id);
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
			
			boutonSuppr.addMouseListener(new MouseListener() {
	    		@Override public void mouseClicked(MouseEvent e) {
	    			try {
						Parcours.SupprParcours(id);
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
			boutonMod.addMouseListener(new MouseListener() {
	    		@Override public void mouseClicked(MouseEvent e) {
	    			panelaffichage.removeAll();try {
	    				String nom_sel=liste_parcours.getSelectedItem().toString();
	    				
						panelaffichage.add(ModifParcours(nom_sel));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}panelaffichage.revalidate();
	    		}
	    		public void mousePressed(MouseEvent e) {}
	    		public void mouseReleased(MouseEvent e) {}
	    		public void mouseEntered(MouseEvent e) {}
	    		public void mouseExited(MouseEvent e) {}
	    		});
			
				res.close();
			}
		catch (SQLException e) {
			e.printStackTrace();
				}

			panelchoix.add(boutonVoir);
			panelchoix.add(boutonAjout);
			panelchoix.add(boutonMod);
			panelchoix.add(boutonSuppr);
				
			return panelPrinc;
		}
		//Formulaire d'ajout de parcours
		public static JPanel AjoutParcours()
		{	JPanel panelPrinc=new JPanel();
			panelPrinc.setLayout(new BoxLayout(panelPrinc, BoxLayout.PAGE_AXIS));
			JPanel panelSec=new JPanel();panelPrinc.add(panelSec);
			panelPrinc.add(panelSec);
			GridLayout grid=new GridLayout(0,3,10,10);
			panelSec.setLayout(grid);
			JLabel libelle1=new JLabel("Libelle : ");panelSec.add(libelle1);
			final JTextField libelle= new JTextField(20);panelSec.add(libelle);
			JLabel vide=new JLabel(" ");panelSec.add(vide);
			JLabel description1=new JLabel("Description : ");panelSec.add(description1);
			final JTextField description= new JTextField(20);panelSec.add(description);
			JLabel vide2=new JLabel(" ");panelSec.add(vide2);
			JLabel lien1=new JLabel("Lien : ");panelSec.add(lien1);
			final JTextField lien= new JTextField(20);panelSec.add(lien);
			JLabel vide4=new JLabel(" ");panelSec.add(vide4);
			JLabel reference1=new JLabel("Reference : ");panelSec.add(reference1);
			final JTextField reference= new JTextField(20);panelSec.add(reference);
			JLabel vide5=new JLabel(" ");panelSec.add(vide5);
			JLabel image1=new JLabel("Image : ");panelSec.add(image1);			
			final JTextField image= new JTextField(20);
			JButton parcourir=new JButton("Parcourir");panelSec.add(image);
			panelSec.add(parcourir);
			JLabel vide1=new JLabel(" ");panelPrinc.add(vide1);
			JButton ajout=new JButton("Ajouter");panelPrinc.add(ajout);
			final Date ajd = new java.sql.Date(System.currentTimeMillis());
			parcourir.addActionListener(new ActionListener(){
			    public void actionPerformed(ActionEvent e){
			    	JFileChooser chooser = new JFileChooser();
			        int returnVal = chooser.showOpenDialog(image);
			        if(returnVal == JFileChooser.APPROVE_OPTION) {
			           String chemin=chooser.getSelectedFile().getAbsolutePath();
			           image.setText(chemin);
			    }
			 
			    }
			} );
			ajout.addMouseListener(new MouseListener() {
	    		@Override public void mouseClicked(MouseEvent e) {
	    			try {
	    				String descriptif_parcours=description.getText();
	    				Date date_consult_parcours=ajd;
	    				Date date_crea_parcours=ajd;
	    				String libelle_parcours=libelle.getText();
	    				String image_parcours=image.getText();
	    				String lien_parcours=lien.getText();
	    				String ref=reference.getText();
	    				int reference_parcours = Integer.parseInt(ref); 
	    				Parcours.ajouterParcours(descriptif_parcours,libelle_parcours,lien_parcours,image_parcours,reference_parcours,date_crea_parcours,date_consult_parcours);
	    					
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
			
			return panelPrinc;
		}
		//Formulaire de modification de parcours
				public static JPanel ModifParcours(final String nom_sel) throws SQLException
				{	String query = Parcours.Selectparcours(nom_sel);
				String TEST = null;
				final JPanel panelPrinc = new JPanel();
				panelPrinc.setLayout(new BoxLayout(panelPrinc, BoxLayout.PAGE_AXIS));
			PreparedStatement lire =Connect.getInstance().prepareStatement(query);
			Statement state = Connect.connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet res = state.executeQuery(query);
			res.next();
				final int idP = res.getInt(1);
				final String desP = res.getString(2);
				final String libelleP = res.getString(3);
				final String lienP = res.getString(4);
				final String imageP = res.getString(5);
				final int refP1 = res.getInt(6);
				String refP=Integer.toString(refP1);
					JPanel panelSec=new JPanel();panelPrinc.add(panelSec);
					panelPrinc.add(panelSec);
					GridLayout grid=new GridLayout(0,3,10,10);
					panelSec.setLayout(grid);
					JLabel libelle1=new JLabel("Libelle : ");panelSec.add(libelle1);
					final JTextField libelle= new JTextField(libelleP,20);panelSec.add(libelle);
					JLabel vide=new JLabel(" ");panelSec.add(vide);
					JLabel description1=new JLabel("Description : ");panelSec.add(description1);
					final JTextField description= new JTextField(desP,20);panelSec.add(description);
					JLabel vide2=new JLabel(" ");panelSec.add(vide2);
					JLabel lien1=new JLabel("Lien : ");panelSec.add(lien1);
					final JTextField lien= new JTextField(lienP,20);panelSec.add(lien);
					JLabel vide4=new JLabel(" ");panelSec.add(vide4);
					JLabel reference1=new JLabel("Reference : ");panelSec.add(reference1);
					final JTextField reference= new JTextField(refP,20);panelSec.add(reference);
					JLabel vide5=new JLabel(" ");panelSec.add(vide5);
					JLabel image1=new JLabel("Image : ");panelSec.add(image1);			
					final JTextField image= new JTextField(imageP,20);
					JButton parcourir=new JButton("Parcourir");panelSec.add(image);
					panelSec.add(parcourir);
					JLabel vide1=new JLabel(" ");panelPrinc.add(vide1);
					JButton modif=new JButton("Modifier");panelPrinc.add(modif);
					final Date ajd = new java.sql.Date(System.currentTimeMillis());
					parcourir.addActionListener(new ActionListener(){
					    public void actionPerformed(ActionEvent e){
					    	JFileChooser chooser = new JFileChooser();
					        int returnVal = chooser.showOpenDialog(image);
					        if(returnVal == JFileChooser.APPROVE_OPTION) {
					           String chemin=chooser.getSelectedFile().getAbsolutePath();
					           image.setText(chemin);
					    }
					 
					    }
					} );
					modif.addMouseListener(new MouseListener() {
			    		@Override public void mouseClicked(MouseEvent e) {
			    			try {
			    				int id_parcours=idP;
			    				String descriptif_parcours=description.getText();
			    				Date date_consult_parcours=ajd;
			    				Date date_crea_parcours=ajd;
			    				String libelle_parcours=libelle.getText();
			    				String image_parcours=image.getText();
			    				String lien_parcours=lien.getText();
			    				String ref=reference.getText();
			    				int reference_parcours = Integer.parseInt(ref); 
			    				Parcours. ModifParcours(id_parcours,descriptif_parcours,libelle_parcours,lien_parcours,image_parcours,reference_parcours,date_crea_parcours,date_consult_parcours);
			    					
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
			 
					return panelPrinc;
				}
		//On affiche les infos sur le parcours concern�: on r�cup�re la requete et on stocke les infos pour l'affichage
		public static JPanel afficher_info_parcours(int id) throws SQLException
		{
			String query="SELECT * FROM  parcours WHERE id_parcours="+id;//Parcours.Selectparcours(id);
			PreparedStatement lect = Connect.getInstance().prepareStatement(query);
				Statement state = Connect.connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet res = state.executeQuery(query);	
			//R�cup�ration des donn�es
				res.next();
			String nom1 = res.getString(3);
			JLabel nom= new JLabel(nom1,20);
			String description1 = res.getString(2);
			JLabel description= new JLabel(description1,20);
			String lien1 = res.getString(4);
			JLabel lien= new JLabel(lien1,20);
			String reference1 = res.getString(6);
			JLabel reference= new JLabel(reference1,20);
			String image1 = res.getString(5);
			JLabel image = new JLabel(new ImageIcon(image1));
			Date date_cons1 = res.getDate(8);
			JLabel date_cons= new JLabel(String.valueOf(date_cons1),20);
			Date date_crea1 = res.getDate(7);
			JLabel date_crea= new JLabel(String.valueOf(date_crea1),20);
			
			JPanel panel_info_parcours=new JPanel();
			panel_info_parcours.setLayout(new BoxLayout(panel_info_parcours, BoxLayout.PAGE_AXIS));
			//Panel des des infos sur le parcours:texte
			JPanel panel_info_parcours1=new JPanel();
			panel_info_parcours.add(panel_info_parcours1);
			GridLayout grid=new GridLayout(0,2);
			panel_info_parcours1.setLayout(grid);
			panel_info_parcours1.add(nom);
			panel_info_parcours1.add(reference);
			panel_info_parcours1.add(lien);
			panel_info_parcours1.add(description);
			panel_info_parcours1.add(date_cons);
			panel_info_parcours1.add(date_crea);
			JPanel panel_info_parcours2=new JPanel();
			panel_info_parcours.add(panel_info_parcours2);
			panel_info_parcours2.add(image);
			return panel_info_parcours;
		}
		//on affiche les infos sur les poi du parcours et on ajoute des boutons de suppresion pour chacun
		public static void afficher_info_poi_parcours(int id) throws SQLException
		{
			
			Parcours.Select_poi_parcours(id);
		}
		/**************************************NEWS ***************************************************************/
		public static Component iniNews()
		{
		JPanel panelgen = new JPanel();
		panelgen.setLayout(new BoxLayout(panelgen, BoxLayout.PAGE_AXIS));
		JPanel barreNews = new JPanel(new BorderLayout());
		panelgen.setPreferredSize(new Dimension(750,500));
		barreNews.setPreferredSize(new Dimension(100,30));
		JPanel Type = new JPanel();
		final JComboBox type = new JComboBox();
		 
		type.setPreferredSize(new Dimension(100, 20));
		   type.addItem("carte");
		   type.addItem("lieu");
		   type.addItem("parcours");
		   type.addItem("poi");
		 
		   
		   
		JButton valider = new JButton("OK");
		JButton supprimerTout = new JButton("Supprimer Tout");
		 
		supprimerTout.addMouseListener(new MouseListener() {
		    @Override public void mouseClicked(MouseEvent e) {
		   
		    Carte.SupprDesNews();
		    Lieu.SupprDesNews();
		    Parcours.SupprDesNews();
		    POI.SupprDesNews();
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
		 
		Type.add(new JLabel("Type : "));
		Type.add(type);
		Type.add(valider);
		 
		barreNews.add(Type, BorderLayout.WEST);
		barreNews.add(supprimerTout, BorderLayout.EAST);
		 
		 
		final JScrollPane test = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		test.setPreferredSize(new Dimension(500,500));
		 
		 
		valider.addMouseListener(new MouseListener() {
		    @Override public void mouseClicked(MouseEvent e) {
		     
		    String Type = type.getSelectedItem().toString();
		    JPanel panelAff = new JPanel();
		   
		   
		    //******************************************************************************************************
		     
		    if (Type.equals("carte"))
		    {
		    try {
		String requete = Carte.LireCarte();
		PreparedStatement lire =Connect.getInstance().prepareStatement(requete);
		Statement state = Connect.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		   ResultSet res = state.executeQuery(requete);
		   while(res.next()){
		    final int id = res.getInt(1);
		    String Nom = res.getString(2);
		    String Description = res.getString(3);
		    String Lien = res.getString(5);
		    Date Date = res.getDate(6);
		   
		   
		    JPanel panelSec = new JPanel();
		   
		    JButton supp = new JButton("supprimer");
		   
		   
		    JLabel nom = new JLabel(Nom); 
		    JLabel descri = new JLabel(Description);
		    JLabel lien = new JLabel(Lien);
		    lien.setCursor(new Cursor(Cursor.HAND_CURSOR));
		   
		    lien.addMouseListener(new MouseAdapter() {
		           //Click sur le lien
		           public void mouseClicked(MouseEvent e) {
		               JLabel label=(JLabel)e.getSource();
		               String plainText = label.getText().replaceAll("\\<.*?\\>", "");
		               try {
		                   Desktop.getDesktop().browse(new URI(plainText));
		               } catch (URISyntaxException ex) {
		                   Logger.getLogger(Elements.class.getName()).log(Level.SEVERE, null, ex);
		               } catch (IOException ex) {
		                   Logger.getLogger(Elements.class.getName()).log(Level.SEVERE, null, ex);
		               }
		           }
		           public void mousePressed(MouseEvent e) {}
		    public void mouseReleased(MouseEvent e) {}
		    public void mouseEntered(MouseEvent e) {}
		    public void mouseExited(MouseEvent e) {}
		    });
		           
		       
		   
		    if(Date.toString().equals("1111-11-11"))
		    {
		   
		    }
		    else
		    {
		    panelSec.add(nom);
		    panelSec.add(descri);
		    panelSec.add(lien); 
		    panelSec.add(supp);
		   
		    panelAff.add(panelSec);
		   
		    supp.addMouseListener(new MouseListener() {
		    @Override public void mouseClicked(MouseEvent e) {
		    Carte.SupprUneNews(id);
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
		   
		   }
		    panelAff.setLayout(new BoxLayout(panelAff, BoxLayout.PAGE_AXIS));
		 
		 
		        test.setViewportView(panelAff);
		        
		 
		 
		   }
		   
		 
		} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		}
		   
		    }
		   
		   
		    //****************************************************************************************************************
		   
		    else if (Type.equals("lieu"))
		    {
		    try {
		String requete = Lieu.LireAllAllLieu();
		PreparedStatement lire =Connect.getInstance().prepareStatement(requete);
		Statement state = Connect.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		   ResultSet res = state.executeQuery(requete);
		   
		   
		   
		   
		   while(res.next()){
		    final int id = res.getInt(1);
		    String Nom = res.getString(2);
		    String Description = res.getString(3);
		    String Lien = res.getString(5);
		    Date Date = res.getDate(6);
		   
		   
		    JPanel panelSec = new JPanel();
		   
		    JButton supp = new JButton("supprimer");
		   
		   
		    JLabel nom = new JLabel(Nom); 
		    JLabel descri = new JLabel(Description);
		    JLabel lien = new JLabel(Lien);
		    lien.setCursor(new Cursor(Cursor.HAND_CURSOR));
		   
		    lien.addMouseListener(new MouseAdapter() {
		           //Click sur le lien
		           public void mouseClicked(MouseEvent e) {
		               JLabel label=(JLabel)e.getSource();
		               String plainText = label.getText().replaceAll("\\<.*?\\>", "");
		               try {
		                   Desktop.getDesktop().browse(new URI(plainText));
		               } catch (URISyntaxException ex) {
		                   Logger.getLogger(Elements.class.getName()).log(Level.SEVERE, null, ex);
		               } catch (IOException ex) {
		                   Logger.getLogger(Elements.class.getName()).log(Level.SEVERE, null, ex);
		               }
		           }
		           public void mousePressed(MouseEvent e) {}
		    public void mouseReleased(MouseEvent e) {}
		    public void mouseEntered(MouseEvent e) {}
		    public void mouseExited(MouseEvent e) {}
		    });
		           
		       
		   
		    if(Date.toString().equals("1111-11-11"))
		    {
		   
		    }
		    else
		    {
		    panelSec.add(nom);
		    panelSec.add(descri);
		    panelSec.add(lien); 
		    panelSec.add(supp);
		   
		    panelAff.add(panelSec);
		   
		    supp.addMouseListener(new MouseListener() {
		    @Override public void mouseClicked(MouseEvent e) {
		    Lieu.SupprUneNews(id);
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
		   
		   }
		    panelAff.setLayout(new BoxLayout(panelAff, BoxLayout.PAGE_AXIS));
		 
		 
		        test.setViewportView(panelAff);
		        
		 
		 
		   }
		   
		 
		} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		}
		   
		    }
		   
		   
		    //***************************************************************************************************************
		   
		    else if (Type.equals("poi"))
		    {
		    try {
		String requete = POI.LirePoi();
		PreparedStatement lire =Connect.getInstance().prepareStatement(requete);
		Statement state = Connect.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		   ResultSet res = state.executeQuery(requete);
		   while(res.next()){
		    final int id = res.getInt(1);
		    String Nom = res.getString(4);
		    String Description = res.getString(5);
		    String Lien = res.getString(6);
		    Date Date = res.getDate(9);
		   
		   
		    JPanel panelSec = new JPanel();
		   
		    JButton supp = new JButton("supprimer");
		   
		   
		    JLabel nom = new JLabel(Nom); 
		    JLabel descri = new JLabel(Description);
		    JLabel lien = new JLabel(Lien);
		    lien.setCursor(new Cursor(Cursor.HAND_CURSOR));
		   
		    lien.addMouseListener(new MouseAdapter() {
		           //Click sur le lien
		           public void mouseClicked(MouseEvent e) {
		               JLabel label=(JLabel)e.getSource();
		               String plainText = label.getText().replaceAll("\\<.*?\\>", "");
		               try {
		                   Desktop.getDesktop().browse(new URI(plainText));
		               } catch (URISyntaxException ex) {
		                   Logger.getLogger(Elements.class.getName()).log(Level.SEVERE, null, ex);
		               } catch (IOException ex) {
		                   Logger.getLogger(Elements.class.getName()).log(Level.SEVERE, null, ex);
		               }
		           }
		           public void mousePressed(MouseEvent e) {}
		    public void mouseReleased(MouseEvent e) {}
		    public void mouseEntered(MouseEvent e) {}
		    public void mouseExited(MouseEvent e) {}
		    });
		           
		       
		   
		    if(Date.toString().equals("1111-11-11"))
		    {
		   
		    }
		    else
		    {
		    panelSec.add(nom);
		    panelSec.add(descri);
		    panelSec.add(lien); 
		    panelSec.add(supp);
		   
		    panelAff.add(panelSec);
		   
		    supp.addMouseListener(new MouseListener() {
		    @Override public void mouseClicked(MouseEvent e) {
		    POI.SupprUneNews(id);
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
		   
		   }
		    panelAff.setLayout(new BoxLayout(panelAff, BoxLayout.PAGE_AXIS));
		 
		 
		        test.setViewportView(panelAff);
		        
		 
		 
		   }
		   
		 
		} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		}
		   
		    }
		   
		   
		    //************************************************************************************************************
		 
		    else if (Type.equals("parcours"))
		    {
		    try {
		String requete = Parcours.LireParcours();
		PreparedStatement lire =Connect.getInstance().prepareStatement(requete);
		Statement state = Connect.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		   ResultSet res = state.executeQuery(requete);
		   while(res.next()){
		    final int id = res.getInt(1);
		    String Nom = res.getString(3);
		    String Description = res.getString(2);
		    String Lien = res.getString(4);
		    Date Date = res.getDate(7);
		   
		   
		    JPanel panelSec = new JPanel();
		   
		    JButton supp = new JButton("supprimer");
		   
		   
		    JLabel nom = new JLabel(Nom); 
		    JLabel descri = new JLabel(Description);
		    JLabel lien = new JLabel(Lien);
		    lien.setCursor(new Cursor(Cursor.HAND_CURSOR));
		   
		    lien.addMouseListener(new MouseAdapter() {
		           //Click sur le lien
		           public void mouseClicked(MouseEvent e) {
		               JLabel label=(JLabel)e.getSource();
		               String plainText = label.getText().replaceAll("\\<.*?\\>", "");
		               try {
		                   Desktop.getDesktop().browse(new URI(plainText));
		               } catch (URISyntaxException ex) {
		                   Logger.getLogger(Elements.class.getName()).log(Level.SEVERE, null, ex);
		               } catch (IOException ex) {
		                   Logger.getLogger(Elements.class.getName()).log(Level.SEVERE, null, ex);
		               }
		           }
		           public void mousePressed(MouseEvent e) {}
		    public void mouseReleased(MouseEvent e) {}
		    public void mouseEntered(MouseEvent e) {}
		    public void mouseExited(MouseEvent e) {}
		    });
		           
		       
		   
		    if(Date.toString().equals("1111-11-11"))
		    {
		   
		    }
		    else
		    {
		    panelSec.add(nom);
		    panelSec.add(descri);
		    panelSec.add(lien); 
		    panelSec.add(supp);
		   
		    panelAff.add(panelSec);
		   
		    supp.addMouseListener(new MouseListener() {
		    @Override public void mouseClicked(MouseEvent e) {
		    Parcours.SupprUneNews(id);
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
		   
		   }
		   
		   
		   
		    panelAff.setLayout(new BoxLayout(panelAff, BoxLayout.PAGE_AXIS));
		 
		 
		        test.setViewportView(panelAff);
		        
		 
		 
		   }
		   
		 
		} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		}
		   
		    }
		 
		   
		   
		   
		           
		    }
		    public void mousePressed(MouseEvent e) {}
		    public void mouseReleased(MouseEvent e) {}
		    public void mouseEntered(MouseEvent e) {}
		    public void mouseExited(MouseEvent e) {}
		    });
		 
		panelgen.add(barreNews);
		panelgen.add(test);
		 
		 
		return panelgen;
		}
		 
		public static Component AffNews(int action)
		{
		JPanel panelEnt = new JPanel();
		panelEnt.add(iniNews());
		return panelEnt;
		}
		
	}
	
	
	


