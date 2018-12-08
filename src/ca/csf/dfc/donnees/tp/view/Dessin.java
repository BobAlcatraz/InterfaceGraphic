package ca.csf.dfc.donnees.tp.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ca.csf.dfc.donnees.tp.controller.*;
import ca.csf.dfc.donnees.tp.model.*;

public class Dessin extends JFrame implements IDessin{
	private static final long serialVersionUID = 1L;
	private IEspaceTravail m_EspaceTravail;
	private IObserver m_Observer = new Observer();
	private IEnregistrement m_Enregistrement = EnregistrementXML.getInstance();
	private IExporteur m_Exporteur = ExporteurSVG.getInstance();
	private String m_FormeCreation = "Pointeur";
	private boolean m_EnDeplacement = false;
	private boolean m_EnModification = false;
	private int m_CurrentX = 0;
	private int m_CurrentY = 0;
	private JPanel m_Background = new JPanel();
	private JButton butt_Pointeur = new JButton("Pointeur");
	private JButton butt_Ligne = new JButton("Ligne");
	private JButton butt_Rectangle = new JButton("Rectangle");
	private JButton butt_Oval = new JButton("Oval");
	private Color m_CouleurTrait = Color.BLACK;
	private Color m_Remplissage = null;
	private int m_Trait = 1;
	
	public Dessin() {
		super("Dessin Vectoriel");
		this.m_EspaceTravail = new EspaceTravail(500, 500);
		this.m_Observer.SetEnregistrement(m_EspaceTravail);
		this.setSize(660, 580);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//Initialisation des aspects visuels
		this.setLayout(new BorderLayout());
		JPanel buttons = new JPanel();
		Dimension dimensionButt = new Dimension(110, 40);
		butt_Pointeur.setPreferredSize(dimensionButt);
		butt_Ligne.setPreferredSize(dimensionButt);
		butt_Rectangle.setPreferredSize(dimensionButt);
		butt_Oval.setPreferredSize(dimensionButt);
		buttons.setLayout(new GridLayout(10,1));
		buttons.add(butt_Pointeur);
		buttons.add(butt_Ligne);
		buttons.add(butt_Rectangle);
		buttons.add(butt_Oval);
		this.add(buttons, BorderLayout.WEST);
		
		this.m_Background.setLayout(new FlowLayout());
		this.m_Background.setBackground(Color.DARK_GRAY);
		this.m_Background.add((EspaceTravail)this.m_EspaceTravail);
		this.add(this.m_Background, BorderLayout.CENTER);
		JMenuBar menu = new JMenuBar();
		JMenu fichier = new JMenu("Fichier");
		JMenu edition = new JMenu("Édition");
		JMenuItem nouveau = new JMenuItem("Nouveau");
		JMenuItem sauvegarder = new JMenuItem("Sauvegarder");
		JMenuItem charger = new JMenuItem("Charger");
		JMenuItem exporter = new JMenuItem("Exporter");
		JMenuItem ajouterLigne = new JMenuItem("Ajouter une ligne");
		JMenuItem ajouterRectangle = new JMenuItem("Ajouter un rectangle");
		JMenuItem ajouterOval = new JMenuItem("Ajouter un oval");
		JMenuItem supprimer = new JMenuItem("Supprimer");
		menu.add(fichier);
		fichier.add(nouveau);
		fichier.add(sauvegarder);
		fichier.add(charger);
		fichier.add(exporter);
		menu.add(edition);
		edition.add(ajouterLigne);
		edition.add(ajouterRectangle);
		edition.add(ajouterOval);
		edition.add(supprimer);
		this.add(menu, BorderLayout.NORTH);
		//Events
		this.m_EspaceTravail.ajouterMouseMotionListener(new Crayon());
		this.m_EspaceTravail.ajouterMouseListener(new Pointeur());
		this.butt_Pointeur.addActionListener(new Outils());
		this.butt_Ligne.addActionListener(new Outils());
		this.butt_Rectangle.addActionListener(new Outils());
		this.butt_Oval.addActionListener(new Outils());
		nouveau.addActionListener(new Nouveau());
		sauvegarder.addActionListener(new Sauvegarde());
		charger.addActionListener(new Nouveau());
	}
	
	@Override
	public void CreerEspaceTravail(int p_Largeur, int p_Hauteur) {
		this.m_EspaceTravail.Vider();
		this.m_EspaceTravail.setTaille(p_Largeur, p_Hauteur);
		this.m_EspaceTravail.Deselectionner();
		this.m_Observer.SetEnregistrement(this.m_EspaceTravail);
		this.setSize(p_Largeur + 160, p_Hauteur + 80);
		this.repaint();
		this.m_Background.repaint();
	}
	
	@Override
	public boolean VerifierModification() {
		return this.m_Observer.Comparer(this.m_EspaceTravail);
	}

	@Override
	public boolean Sauvegarder() {
		boolean confirm = true;
		try {
			this.m_Enregistrement.Enregistrer(this.m_EspaceTravail);
			this.m_Observer.SetEnregistrement(this.m_EspaceTravail);
		}
		catch (Exception e){
			confirm = false;
		}
		return confirm;
	}

	@Override
	public boolean Charger() {
		boolean confirm = true;
		try {
			this.m_Enregistrement.Charger(this.m_EspaceTravail);
			this.m_EspaceTravail.Deselectionner();
			this.setSize(this.m_EspaceTravail.getLargeur() + 160, this.m_EspaceTravail.getHauteur() + 80);
			this.repaint();
			this.m_Background.repaint();
		}
		catch (Exception e) {
			confirm = false;

		}
		return confirm;
	}

	@Override
	public boolean Exporter() {
		boolean confirm = true;
		try {
			this.m_Exporteur.Exporter(this.m_EspaceTravail);
		}
		catch(Exception e){
			confirm = false;
		}
		return confirm;
	}

	@Override
	public void AjouterForme(IForme p_Forme) {
		this.m_EspaceTravail.draw(p_Forme);		
	}
	
	private class Crayon implements MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent p_e) {
			if (Dessin.this.m_EnDeplacement) {
				Dessin.this.m_EspaceTravail.Refresh(p_e.getX() - Dessin.this.m_CurrentX, p_e.getY() - Dessin.this.m_CurrentY, 0, 0);
			}
			else if(Dessin.this.m_EnModification){
				Dessin.this.m_EspaceTravail.Refresh(0, 0, p_e.getX() - Dessin.this.m_CurrentX, p_e.getY() - Dessin.this.m_CurrentY);
			}
		
			Dessin.this.m_CurrentX = p_e.getX();
			Dessin.this.m_CurrentY = p_e.getY();
		}

		@Override
		public void mouseMoved(MouseEvent p_e) {}
		
	}
	
	private class Pointeur implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent p_e) {}

		@Override
		public void mousePressed(MouseEvent p_e) {
			Dessin.this.m_CurrentX = p_e.getX();
			Dessin.this.m_CurrentY = p_e.getY();
			switch(Dessin.this.m_FormeCreation) {
			case ("Pointeur"):
				if (Dessin.this.m_EspaceTravail.hasSelection()) {
					Dessin.this.m_CurrentX = p_e.getX();
					Dessin.this.m_CurrentY = p_e.getY();
					if (Dessin.this.m_EspaceTravail.dansSelection(p_e.getX(), p_e.getY())) {
						Dessin.this.m_EnDeplacement = true;
						Dessin.this.m_EnModification = false;
					}
					else if (((EspaceTravail)Dessin.this.m_EspaceTravail).dansPointModif(p_e.getX(), p_e.getY())) {
						Dessin.this.m_EnModification = true;
						Dessin.this.m_EnDeplacement = false;
					}
					else {
						Dessin.this.m_EspaceTravail.verifierClick(p_e.getX(), p_e.getY());
						Dessin.this.m_EnModification = false;
						Dessin.this.m_EnDeplacement = false;
					}
				}
				else {
					Dessin.this.m_EspaceTravail.verifierClick(p_e.getX(), p_e.getY());
					Dessin.this.m_EnModification = false;
					Dessin.this.m_EnDeplacement = false;
				}
				break;
			case("Rectangle"):
				Dessin.this.m_EspaceTravail.Deselectionner();
				Dessin.this.AjouterForme(new Rectangle(p_e.getX(), p_e.getY(), 
						Dessin.this.m_EspaceTravail.getHauteur()/10, 
						Dessin.this.m_EspaceTravail.getLargeur()/5, 
						Dessin.this.m_Trait, Dessin.this.m_CouleurTrait, 
						Dessin.this.m_Remplissage));
				break;
			case("Oval"):
				Dessin.this.m_EspaceTravail.Deselectionner();
				Dessin.this.AjouterForme(new Oval(p_e.getX(), p_e.getY(), 
						Dessin.this.m_EspaceTravail.getHauteur()/10, 
						Dessin.this.m_EspaceTravail.getLargeur()/5,  
						Dessin.this.m_Trait, 
						Dessin.this.m_CouleurTrait, 
						Dessin.this.m_Remplissage));
				break;
			case("Ligne"):
				Dessin.this.m_EspaceTravail.Deselectionner();
				Dessin.this.AjouterForme(new Ligne(p_e.getX(), p_e.getY(), Dessin.this.m_EspaceTravail.getHauteur()/10, Dessin.this.m_EspaceTravail.getLargeur()/5, 
						Dessin.this.m_Trait, Dessin.this.m_CouleurTrait, Dessin.this.m_Remplissage));
			}
		}

		@Override
		public void mouseReleased(MouseEvent p_e) {
			if (Dessin.this.m_EnModification) {
				Dessin.this.m_EspaceTravail.AdapterForme();
			}
		}

		@Override
		public void mouseEntered(MouseEvent p_e) {}

		@Override
		public void mouseExited(MouseEvent p_e) {}
	}
	
	private class Outils implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent p_e) {
			String outil = ((JButton)p_e.getSource()).getText();
			switch (outil) {
			case ("Pointeur"):
				Dessin.this.m_FormeCreation = "Pointeur";
				break;
			case ("Ligne"):
				Dessin.this.m_FormeCreation = "Ligne";
				break;
			case("Rectangle"):
				Dessin.this.m_FormeCreation = "Rectangle";
				break;
			case("Oval"):
				Dessin.this.m_FormeCreation = "Oval";
				break;
			}
		}
	}
	
	private class Nouveau implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent p_e) {
			boolean creerNouveau = false;
			if (Dessin.this.VerifierModification()) {
				creerNouveau = true;
			}
			else {
				int resp = JOptionPane.showConfirmDialog(
					    Dessin.this,
					    "Votre espace de travail n'est pas enregisté, voulez-vous continuer?",
					    "Attention",
					    JOptionPane.YES_NO_OPTION);
					if(resp == JOptionPane.YES_OPTION) {
						creerNouveau = true;
					}
			}
			if (creerNouveau) {
				if (((JMenuItem)p_e.getSource()).getText() == "Nouveau") {
					Dessin.this.CreerEspaceTravail(200, 200);
				}
				else {
					Dessin.this.Charger();
				}
			}
		}
	}
	
	private class Sauvegarde implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent p_e) {
			Dessin.this.Sauvegarder();			
		}
	}
}
