package Vue;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class Frame1 extends JFrame{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static JFrame fenetre = new JFrame();
	private static JPanel contenant = new JPanel();
	public static void init_frame()
	{
		//La fen�tre
		fenetre = new JFrame("MiniSig");
		fenetre.setSize(800, 600);
		//fenetre.setResizable(false);
		init_ensemble();
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void rendre_visible()
	{
		fenetre.setVisible(true);
	}
	public static Component actualiser() throws SQLException
	{
	 
	contenant.setVisible(false);
	return contenant;
	}
	public static void init_ensemble()
	{
		// Cr�ation du panel comprenant tous les composants
		final JPanel ensemble=new JPanel();
		ensemble.setLayout(new BorderLayout());
		fenetre.add(ensemble);
	
		 //Cr�ation du menu
		
		JMenuBar menuBar = new JMenuBar();
		 ensemble.add(menuBar,BorderLayout.NORTH);
		    JMenu menuA = new JMenu("Consulter");
		    JMenuItem menuItemA1 = new JMenuItem("Carte");
		    JMenuItem menuItemA4 = new JMenuItem("Parcours");
		    menuItemA4.addActionListener(new ActionListener(){// Pour afficher nos parcours et les supprimer modifier et ajouter
			    @Override
	            public void actionPerformed(ActionEvent ae) {
			    	remove();contenant.removeAll();
					try {
						contenant.add(Elements.formulaire_choix_action_parcours());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					fenetre.setVisible(true);
					contenant.setVisible(true);
					fenetre.setVisible(true);
	            }
			    });
		    JMenuItem menuItemA5 = new JMenuItem("News");
		    menuItemA5.addActionListener(new ActionListener(){
		        
		           @Override
		           public void actionPerformed(ActionEvent ae) {
		               int action = 1;remove();
		               contenant.setVisible(true);
		               contenant.removeAll();
		               contenant.add(Elements.AffNews(action));
		               fenetre.setVisible(true);
		           }
		       });
		    menuA.add(menuItemA1);
		    menuA.add(menuItemA4);
		    menuA.addSeparator();
		    menuA.add(menuItemA5);
		   	    
		    JMenu menuB = new JMenu("Editer");
		    JMenu menuB1= new JMenu("Carte");
		    JMenuItem menuItemB11 = new JMenuItem("Ajouter une carte");
		    JMenuItem menuItemB12 = new JMenuItem("Modifier/Supprimer une carte");
		    menuB1.add(menuItemB11);
		    menuB1.add(menuItemB12);
		    menuB.add(menuB1);
		    
		    JMenu menuB2= new JMenu("Lieu");
		   // JMenuItem menuItemB21 = new JMenuItem("Ajouter Lieu");
		    JMenuItem menuItemB22 = new JMenuItem("Modifier/Supprimer Lieu");
		   // menuB2.add(menuItemB21);
		    menuB2.add(menuItemB22);
		    menuItemB22.addActionListener(new ActionListener(){
			    @Override
	            public void actionPerformed(ActionEvent ae) {
			    	int action = 1;
			    	
							
									try {
										remove();
										contenant.removeAll();
										contenant.add(Elements.AffRecap(action));
										fenetre.setVisible(true);
										contenant.setVisible(true);
										
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								
									
							
						
					fenetre.setVisible(true);
			    
	            }
			    });
		    menuB.add(menuB2);
		    
		    JMenu menuB3= new JMenu("POI");
		   // JMenuItem menuItemB31 = new JMenuItem("Ajouter POI");
		    JMenuItem menuItemB32 = new JMenuItem("Modifier/Supprimer POI");
		   // menuB3.add(menuItemB31);
		    menuB3.add(menuItemB32);
		    menuB.add(menuB3);
		    menuItemB32.addActionListener(new ActionListener(){
			    @Override
	            public void actionPerformed(ActionEvent ae) {
			    	int action = 1;
			    	
							
									try {
										remove();
										contenant.removeAll();
										contenant.add(Elements.AffRecap_poi(action));
										fenetre.setVisible(true);
										contenant.setVisible(true);
										
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								
									
							
						
					fenetre.setVisible(true);
			    
	            }
			    });
		    menuItemB22.addActionListener(new ActionListener(){
			    @Override
	            public void actionPerformed(ActionEvent ae) {
			    	int action = 1;
			    	
							
									try {
										remove();
										contenant.removeAll();
										contenant.add(Elements.AffRecap(action));
										fenetre.setVisible(true);
										contenant.setVisible(true);
										
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								
									
							
						
					fenetre.setVisible(true);
			    
	            }
			    });
		    JMenu menuB4= new JMenu("Parcours th�matiques");
		    JMenuItem menuItemB41 = new JMenuItem("Ajouter Parcours");
		    JMenuItem menuItemB42 = new JMenuItem("Modifier/Supprimer Parcours");
		    menuB4.add(menuItemB41);
		    menuB4.add(menuItemB42);
		    menuB.add(menuB4);
		    
		    JMenu menuC = new JMenu("Rechercher");
		    menuBar.add(menuA);
		    menuBar.add(menuB);
		    menuBar.add(menuC);
		 
		  //Cr�ation du panel contenant
		    
			 ensemble.add(contenant,BorderLayout.CENTER);
			 
			 
			 //Cr�ation du sous menu
			 /*final JPanel sousmenu = new JPanel();
			 ensemble.add(sousmenu,BorderLayout.EAST);
			 JLabel label2= new JLabel("ok2");
			 sousmenu.setBackground(Color.red);
			 sousmenu.add(label2);*/
			 
				 menuItemB11.addActionListener(new ActionListener(){
				   public void actionPerformed(ActionEvent e) {//Ajout des cartes
				      // e.getSource() permet de conna�tre la source qui a d�clench� l'action
					  
					   int action=1;
					   remove();
					   contenant.add(Elements.formulaire(action));
		            	fenetre.setVisible(true);
		            	contenant.setVisible(true);
		            	
				   }
				});
			 menuItemB12.addActionListener(new ActionListener(){//La suppression et modification des cartes
				   public void actionPerformed(ActionEvent e) {
				      // e.getSource() permet de conna�tre la source qui a d�clench� l'action
					   remove();
					   try {
						contenant.add(Elements.liste_carte());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		            	fenetre.setVisible(true);
		            	contenant.setVisible(true);
		            	
				   }
				});
			 menuC.addMenuListener(new MenuListener(){//Le bouton de recherche
				 @Override
			        public void menuSelected(MenuEvent e) {
			            
			            remove();
						 contenant.add(Elements.formulaire_recherche());
		            	fenetre.setVisible(true);
		            	contenant.setVisible(true);
			        }

				@Override
				public void menuCanceled(MenuEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void menuDeselected(MenuEvent arg0) {
					// TODO Auto-generated method stub
					
				}
					  
				   
				});
				
	}
	public static void remove()
	{
		contenant.removeAll(); contenant.revalidate();
		 contenant.repaint();
	}
	public static void afficher_rechercher(JPanel panelPrinc)
	{
		remove();
		JScrollPane test = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		test.setPreferredSize(new Dimension(500,500));
		test.setViewportView(panelPrinc);
		contenant.add(test);
		fenetre.setVisible(true);
		contenant.setVisible(true);
	/*	JScrollPane scrol=new JScrollPane();
		panel.add(scrol);String nom="res.nom_"+dans;
		String description="res.descriptif_"+dans;
		JButton supprimer=new JButton("Supprimer");
		JButton modifier=new JButton("Modifier");*/
	//	JTable table=new JTable();
		
	}
}
