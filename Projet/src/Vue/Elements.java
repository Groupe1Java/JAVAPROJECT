package Vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.BoxLayout;
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

import Métier.Carte;
import Métier.Connect;
import Métier.Recherche;

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
		
			//Création du panel de description
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
					JOptionPane.showMessageDialog(frame,"Votre modification a été prise en compte");
					
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
		JLabel message_mot=new JLabel("Mot recherché : ");
			final JTextField motl =new JTextField(20);
			tableau.add(message_choix);
			tableau.add(choixl);
			tableau.add(message_type);
			tableau.add(typel);
			tableau.add(message_mot);
			tableau.add(motl);
			JButton recherche=new JButton("Rechercher");
			JPanel bouton=new JPanel();
			formulaire_recherche.add(bouton);
			bouton.add(recherche);
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
	//on créee notre lsite des cartes avec les bouttons de mod et supp
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
	//On appel la méthode qui affiche la liste de nos carte
	public static Component AffRecap_carte(int action) throws SQLException
	{

	JPanel Liste=new JPanel();
	Liste.add(liste_carte());
	return Liste;
	}
	// On va charger l'affichage de le formulaire pré remplie des modif de carte
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
	final String libelle = res.getString(8);
	
	
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
	Carte.ModifLieu(id, nom, description,image, lien, libelle, ajd);
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
		
	}
	
	
	


