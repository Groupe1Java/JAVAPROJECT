package Vue;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

public class Frame1 {
	 static JFrame fenetre = new JFrame();
	private static JPanel contenant = new JPanel();
	public static void init_frame()
	{
		//La fen�tre
		fenetre = new JFrame("MiniSig");
		fenetre.setSize(800, 600);
		fenetre.setResizable(false);
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
		JPanel ensemble=new JPanel();
		ensemble.setLayout(new BorderLayout());
		fenetre.add(ensemble);
	
		 //Cr�ation du menu
		
		JMenuBar menuBar = new JMenuBar();
		 ensemble.add(menuBar,BorderLayout.NORTH);
		    JMenu menuA = new JMenu("Consulter");
		    JMenuItem menuItemA1 = new JMenuItem("Lieux");
		    JMenuItem menuItemA2 = new JMenuItem("POI");
		    JMenuItem menuItemA3 = new JMenuItem("Parcours");
		    JMenuItem menuItemA4 = new JMenuItem("News");
		    JMenuItem menuItemA5 = new JMenuItem("Carte");
		    menuA.add(menuItemA1);
		    menuA.add(menuItemA2);
		    menuA.add(menuItemA3);
		    menuA.add(menuItemA5);
		    menuA.addSeparator();
		    menuA.add(menuItemA4);
		    menuItemA5.addActionListener(new ActionListener(){
		    	 
	            @Override
	            public void actionPerformed(ActionEvent ae) {
	                int action = 1;
	                try {
						contenant.add(Elements.AffComb(action));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                fenetre.setVisible(true);
	            }
	        });

		    
		    JMenu menuB = new JMenu("Editer");
		    JMenu menuB1= new JMenu("Carte");
		    JMenuItem menuItemB11 = new JMenuItem("Ajouter une carte");
		    JMenuItem menuItemB12 = new JMenuItem("Modifier/Supprimer une carte");
		    
		    menuB1.add(menuItemB11);
		    menuB1.add(menuItemB12);
		    menuB.add(menuB1);
		    
		   
		    
		    
		    JMenu menuB2= new JMenu("Lieu");
		    JMenuItem menuItemB21 = new JMenuItem("Ajouter Lieu");
		    JMenuItem menuItemB22 = new JMenuItem("Modifier/Supprimer Lieu");
		    
		    menuItemB22.addActionListener(new ActionListener(){
			    @Override
	            public void actionPerformed(ActionEvent ae) {
			    	int action = 1;
			    	
							
									try {
									
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
		    menuB2.add(menuItemB21);
		    menuB2.add(menuItemB22);
		    menuB.add(menuB2);
		    
		    JMenu menuB3= new JMenu("POI");
		    JMenuItem menuItemB31 = new JMenuItem("Ajouter POI");
		    JMenuItem menuItemB32 = new JMenuItem("Modifier/Supprimer POI");
		    menuB3.add(menuItemB31);
		    menuB3.add(menuItemB32);
		    menuB.add(menuB3);
		  
		    JMenu menuB4= new JMenu("Parcours th�matiques");
		    JMenuItem menuItemB41 = new JMenuItem("Ajouter Parcours");
		    JMenuItem menuItemB42 = new JMenuItem("Modifier/Supprimer Parcours");
		    menuB4.add(menuItemB41);
		    menuB4.add(menuItemB42);
		    menuB.add(menuB4);
		   /* menuB.add(menuItemB1);
		    menuItemB1.add(menuItemB11);
		    menuItemB1.add(menuItemB12);
		    menuItemB1.add(menuItemB13);
		    menuB.add(menuItemB2);
		    menuB.addSeparator();
		    menuB.add(menuItemB3);*/
		    
		    JMenu menuC = new JMenu("Rechercher");
		    menuBar.add(menuA);
		    menuBar.add(menuB);
		    menuBar.add(menuC);
		 
		  //Cr�ation du panel contenant
		 
			 ensemble.add(contenant,BorderLayout.CENTER);
			 
			 
			 //Cr�ation du sous menu
			 JPanel sousmenu = new JPanel();
			 ensemble.add(sousmenu,BorderLayout.EAST);
			 JLabel label2= new JLabel("ok2");
			 sousmenu.setBackground(Color.red);
			 sousmenu.add(label2);
			 
			/* menuItemB11.addActionListener(new ActionListener(){
		    	 
		            @Override
		            public void actionPerformed(ActionEvent ae) {
		            	int action=1;
		            	contenant.add(new JLabel("ok"));
		            	contenant.add(Elements.formulaire(action));
		            }
		        });*/
			 
			
			 
			 menuItemB11.addActionListener(new ActionListener(){
				   public void actionPerformed(ActionEvent e) {
				      // e.getSource() permet de conna�tre la source qui a d�clench� l'action

					   int action=1;
					   contenant.removeAll();
		            	contenant.add(Elements.formulaire(action));
		            	fenetre.setVisible(true);
		            	
				   }
				});
			
			
	}
}
