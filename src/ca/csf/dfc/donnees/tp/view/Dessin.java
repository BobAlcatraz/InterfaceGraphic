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
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.AbstractDocument;

import ca.csf.dfc.donnees.tp.controller.*;
import ca.csf.dfc.donnees.tp.model.*;

/**
 * 
 * @author administrateur
 *
 */
public class Dessin extends JFrame implements IDessin{
	private static final long serialVersionUID = 1L;
	private IEspaceTravail m_EspaceTravail;
	private IObserver m_Observer = new Observer();
	private IEnregistrement m_Enregistrement = EnregistrementXML.getInstance();
	private IExporteur m_Exporteur = ExporteurSVG.getInstance();
	private String m_FormeCreation = "";
	private boolean m_EnDeplacement = false;
	private boolean m_EnModification = false;
	private int m_CurrentX = 0;
	private int m_CurrentY = 0;
	private JPanel m_Background = new JPanel();
	private JButton butt_Pointeur = new JButton("Pointeur");
	private JButton butt_Ligne = new JButton("Ligne");
	private JButton butt_Rectangle = new JButton("Rectangle");
	private JButton butt_Oval = new JButton("Oval");
	private Border m_BorderBlue = BorderFactory.createLineBorder(Color.BLUE, 2);
	private Border m_BorderNormal = butt_Pointeur.getBorder();
	private Color m_CouleurTrait = Color.BLACK;
	private Color m_Remplissage = null;
	private int m_Trait = 1;
	JTextField txt_RR = new JTextField("0", 3);
	JTextField txt_RG = new JTextField("0", 3);
	JTextField txt_RB = new JTextField("0", 3);
	JTextField txt_CR = new JTextField("0", 3);
	JTextField txt_CG = new JTextField("0", 3);
	JTextField txt_CB = new JTextField("0", 3);
	JCheckBox cb_RT = new JCheckBox("T", true);
	JSpinner spn_TailleTrait = new JSpinner(new SpinnerListModel(Arrays.asList(
			1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30)));
	
	/**
	 * Construit la fenêtre principale du programme
	 */
	public Dessin() {
		////////////////////////
		//Configurations de base
		////////////////////////
		super("Dessin Vectoriel");
		this.m_EspaceTravail = new EspaceTravail(500, 500);
		this.m_Observer.SetEnregistrement(m_EspaceTravail);
		this.setSize(710, 600);
		this.setMinimumSize(new Dimension(710, 300));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		////////////////////////////////////
		//Initialisation des aspects visuels
		////////////////////////////////////
		this.setLayout(new BorderLayout());
		//Section Bouttons
		JPanel buttons = new JPanel();
		Dimension dimensionButt = new Dimension(110, 40);
		butt_Pointeur.setPreferredSize(dimensionButt);
		butt_Pointeur.setName("Pointeur");
		butt_Ligne.setPreferredSize(dimensionButt);
		butt_Ligne.setName("Ligne");
		butt_Rectangle.setPreferredSize(dimensionButt);
		butt_Rectangle.setName("Rectangle");
		butt_Oval.setPreferredSize(dimensionButt);
		butt_Oval.setName("Oval");
		buttons.setLayout(new GridLayout(10,1));
		buttons.add(butt_Pointeur);
		buttons.add(butt_Ligne);
		buttons.add(butt_Rectangle);
		buttons.add(butt_Oval);
		this.add(buttons, BorderLayout.WEST);
		//Section EspaceTravail
		this.m_Background.setLayout(new FlowLayout());
		this.m_Background.setBackground(Color.DARK_GRAY);
		this.m_Background.add((EspaceTravail)this.m_EspaceTravail);
		this.add(this.m_Background, BorderLayout.CENTER);
		//Section Barre de Menus
		JMenuBar menu = new JMenuBar();
		JMenu fichier = new JMenu("Fichier");
		JMenu edition = new JMenu("Édition");
		JMenuItem nouveau = new JMenuItem("Nouveau");
		JMenuItem sauvegarder = new JMenuItem("Sauvegarder");
		JMenuItem charger = new JMenuItem("Charger");
		JMenuItem exporter = new JMenuItem("Exporter");
		JMenuItem ajouterLigne = new JMenuItem("Ajouter une ligne");
		ajouterLigne.setName("Ligne");
		JMenuItem ajouterRectangle = new JMenuItem("Ajouter un rectangle");
		ajouterRectangle.setName("Rectangle");
		JMenuItem ajouterOval = new JMenuItem("Ajouter un oval");
		ajouterOval.setName("Oval");
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
		JPanel top = new JPanel();
		top.setLayout(new GridLayout(2, 1));
		top.add(menu);
		//Section options style
		JPanel options = new JPanel();
		options.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel remplissage = new JLabel("Remplissage : ");
		JLabel lbl_RR = new JLabel("R");
		JLabel lbl_RG = new JLabel("G");
		JLabel lbl_RB = new JLabel("B");
		options.add(remplissage);
		options.add(lbl_RR);
		options.add(txt_RR);
		options.add(lbl_RG);
		options.add(txt_RG);
		options.add(lbl_RB);
		options.add(txt_RB);
		options.add(cb_RT);
		JLabel couleur = new JLabel("      Couleur : ");
		JLabel lbl_CR = new JLabel("R");
		JLabel lbl_CG = new JLabel("G");
		JLabel lbl_CB = new JLabel("B");
		options.add(couleur);
		options.add(lbl_CR);
		options.add(txt_CR);
		options.add(lbl_CG);
		options.add(txt_CG);
		options.add(lbl_CB);
		options.add(txt_CB);
		JLabel trait = new JLabel("      Trait : ");
		spn_TailleTrait.setPreferredSize(new Dimension(40, 20));
		options.add(trait);
		options.add(spn_TailleTrait);
		top.add(options);
		this.add(top, BorderLayout.NORTH);
		/////////////////
		//Section Events
		/////////////////
		this.m_EspaceTravail.ajouterMouseMotionListener(new Crayon());
		this.m_EspaceTravail.ajouterMouseListener(new Pointeur());
		this.butt_Pointeur.addActionListener(new Outils());
		this.butt_Ligne.addActionListener(new Outils());
		this.butt_Rectangle.addActionListener(new Outils());
		this.butt_Oval.addActionListener(new Outils());
		nouveau.addActionListener(new Nouveau());
		sauvegarder.addActionListener(new Sauvegarde());
		charger.addActionListener(new Nouveau());
		exporter.addActionListener(new Exportation());
		supprimer.addActionListener(new Suppression());
		ajouterLigne.addActionListener(new Outils());
		ajouterRectangle.addActionListener(new Outils());
		ajouterOval.addActionListener(new Outils());
		((AbstractDocument)txt_RR.getDocument()).setDocumentFilter(new TextFilter());
		((AbstractDocument)txt_RG.getDocument()).setDocumentFilter(new TextFilter());
		((AbstractDocument)txt_RB.getDocument()).setDocumentFilter(new TextFilter());
		((AbstractDocument)txt_CR.getDocument()).setDocumentFilter(new TextFilter());
		((AbstractDocument)txt_CG.getDocument()).setDocumentFilter(new TextFilter());
		((AbstractDocument)txt_CB.getDocument()).setDocumentFilter(new TextFilter());
		this.spn_TailleTrait.addChangeListener(new SpinnerGetter());
		this.cb_RT.addActionListener(new Transparence());
		//Fin
		butt_Pointeur.doClick();
	}
	
	/**
	 * Voir la documentation de la classe parent
	 */
	@Override
	public void CreerEspaceTravail(int p_Largeur, int p_Hauteur) {
		this.m_EspaceTravail.Vider();
		this.m_EspaceTravail.setTaille(p_Largeur, p_Hauteur);
		this.m_EspaceTravail.Deselectionner();
		this.m_Observer.SetEnregistrement(this.m_EspaceTravail);
		this.setSize(p_Largeur + 210, p_Hauteur + 100);
		this.repaint();
		this.m_Background.repaint();
	}
	
	/**
	 * Voir la documentation de la classe parent
	 */
	@Override
	public boolean VerifierModification() {
		return this.m_Observer.Comparer(this.m_EspaceTravail);
	}
	
	/**
	 * Voir la documentation de la classe parent
	 */
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

	/**
	 * Voir la documentation de la classe parent
	 */
	@Override
	public boolean Charger() {
		boolean confirm = true;
		try {
			this.m_Enregistrement.Charger(this.m_EspaceTravail);
			this.m_EspaceTravail.Deselectionner();
			this.setSize(this.m_EspaceTravail.getLargeur() + 210, this.m_EspaceTravail.getHauteur() + 100);
			this.repaint();
			this.m_Background.repaint();
		}
		catch (Exception e) {
			confirm = false;

		}
		return confirm;
	}

	/**
	 * Voir la documentation de la classe parent
	 */
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
	
	/**
	 * Voir la documentation de la classe parent
	 */
	@Override
	public void Supprimer() {
		Dessin.this.m_EspaceTravail.supprimer();
	}

	/**
	 * Voir la documentation de la classe parent
	 */
	@Override
	public void AjouterForme(IForme p_Forme) {
		this.m_EspaceTravail.draw(p_Forme);		
	}
	
	/**
	 * Prend les valeurs des TextField pour les insérer dans les paramètres de
	 * couleurs du dessin
	 */
	public void UpdaterCouleurs() {
		this.m_CouleurTrait = new Color(Integer.parseInt(this.txt_CR.getText()), Integer.parseInt(this.txt_CG.getText()), Integer.parseInt(this.txt_CB.getText()));
		if (this.cb_RT.isSelected()) {
			this.m_Remplissage = null;
		}
		else {
			this.m_Remplissage = new Color(Integer.parseInt(this.txt_RR.getText()), Integer.parseInt(this.txt_RG.getText()), Integer.parseInt(this.txt_RB.getText()));
			if (this.m_Remplissage == null) {
			}
		}
		if (this.m_EspaceTravail.hasSelection()) {
			this.m_EspaceTravail.ChangerRemplissage(this.m_Remplissage);
			this.m_EspaceTravail.ChangerCouleur(this.m_CouleurTrait);
		}
		this.m_EspaceTravail.Refresh(0,0,0,0);
	}
	
	
	///////////////////////////////////////////////////////////////////////
	//SECTION CLASSES PRIVÉES UTILITAIRES
	///////////////////////////////////////////////////////////////////////
	/**
	 * Classe observant les mouvements de la souris dans l'espace de travail
	 */
	private class Crayon implements MouseMotionListener{
		/**
		 * Passe les informations de mouvement de la souris pendant
		 * que le bouton de clique est enfoncé à l'espace de travail
		 */
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
	
	/**
	 * Classe observant les cliques de la souris dans l'espace de
	 * travail
	 */
	private class Pointeur implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent p_e) {}

		/**
		 * Informe l'espace de travail de l'action à prendre d'après
		 * un clique de la souris selon l'outil sélectionné dans le dessin
		 */
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
				Dessin.this.m_EnModification = false;
				Dessin.this.m_EnDeplacement = false;
				Dessin.this.AjouterForme(new Rectangle(p_e.getX(), p_e.getY(), 
						Dessin.this.m_EspaceTravail.getHauteur()/10, 
						Dessin.this.m_EspaceTravail.getLargeur()/5, 
						Dessin.this.m_Trait, 
						Dessin.this.m_CouleurTrait, 
						Dessin.this.m_Remplissage));
				break;
			case("Oval"):
				Dessin.this.m_EspaceTravail.Deselectionner();
				Dessin.this.m_EnModification = false;
				Dessin.this.m_EnDeplacement = false;
				Dessin.this.AjouterForme(new Oval(p_e.getX(), p_e.getY(), 
						Dessin.this.m_EspaceTravail.getHauteur()/10, 
						Dessin.this.m_EspaceTravail.getLargeur()/5,  
						Dessin.this.m_Trait, 
						Dessin.this.m_CouleurTrait, 
						Dessin.this.m_Remplissage));
				break;
			case("Ligne"):
				Dessin.this.m_EspaceTravail.Deselectionner();
				Dessin.this.m_EnModification = false;
				Dessin.this.m_EnDeplacement = false;
				Dessin.this.AjouterForme(new Ligne(p_e.getX(), p_e.getY(), Dessin.this.m_EspaceTravail.getHauteur()/10, Dessin.this.m_EspaceTravail.getLargeur()/5, 
						Dessin.this.m_Trait, Dessin.this.m_CouleurTrait, Dessin.this.m_Remplissage));
			}
		}

		/**
		 * Passe à l'espace de travail la commande d'adapter les formes
		 * pour respecter les normes de Graphics2D
		 */
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
	
	/**
	 * Classe de sélection d'outil pour le dessin
	 */
	private class Outils implements ActionListener{
		/**
		 * Permet de sélectionné le bon outil d'après le bouton activé
		 */
		@Override
		public void actionPerformed(ActionEvent p_e) {
			String outil = ((JComponent)p_e.getSource()).getName();
			switch (outil) {
			case ("Pointeur"):
				Dessin.this.m_FormeCreation = "Pointeur";
				Dessin.this.butt_Pointeur.setBorder(Dessin.this.m_BorderBlue);
				Dessin.this.butt_Ligne.setBorder(Dessin.this.m_BorderNormal);
				Dessin.this.butt_Rectangle.setBorder(Dessin.this.m_BorderNormal);
				Dessin.this.butt_Oval.setBorder(Dessin.this.m_BorderNormal);
				break;
			case ("Ligne"):
				Dessin.this.m_FormeCreation = "Ligne";
				Dessin.this.butt_Ligne.setBorder(Dessin.this.m_BorderBlue);
				Dessin.this.butt_Pointeur.setBorder(Dessin.this.m_BorderNormal);
				Dessin.this.butt_Rectangle.setBorder(Dessin.this.m_BorderNormal);
				Dessin.this.butt_Oval.setBorder(Dessin.this.m_BorderNormal);
				break;
			case("Rectangle"):
				Dessin.this.m_FormeCreation = "Rectangle";
				Dessin.this.butt_Rectangle.setBorder(Dessin.this.m_BorderBlue);
				Dessin.this.butt_Ligne.setBorder(Dessin.this.m_BorderNormal);
				Dessin.this.butt_Pointeur.setBorder(Dessin.this.m_BorderNormal);
				Dessin.this.butt_Oval.setBorder(Dessin.this.m_BorderNormal);
				break;
			case("Oval"):
				Dessin.this.m_FormeCreation = "Oval";
				Dessin.this.butt_Oval.setBorder(Dessin.this.m_BorderBlue);
				Dessin.this.butt_Ligne.setBorder(Dessin.this.m_BorderNormal);
				Dessin.this.butt_Rectangle.setBorder(Dessin.this.m_BorderNormal);
				Dessin.this.butt_Pointeur.setBorder(Dessin.this.m_BorderNormal);
				break;
			}
		}
	}
	
	/**
	 * Classe gérant les création et chargement d'espace de travail
	 */
	private class Nouveau implements ActionListener{
		/**
		 * Verifie si l'on veux créer un nouvel espace de travail ou
		 * en charger un
		 */
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
					FenetreDimensionEspace dialog = new FenetreDimensionEspace(Dessin.this);
					dialog.setVisible(true);
					if (dialog.getResultatDialogue()) {
						Dessin.this.CreerEspaceTravail(dialog.getLargeurChoisie(), dialog.getHauteurChoisie());
					}	
				}
				else {
					if(!Dessin.this.Charger()) {
						JOptionPane.showMessageDialog(Dessin.this, "Le chargement a échoué", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
	}
	
	/**
	 * Classe gérant les appels de sauvegarde
	 */
	private class Sauvegarde implements ActionListener{
		/**
		 * Permet d'appeler la fonction de sauvegarde et informe
		 * l'utilisateur si cette sauvegarde échoue
		 */
		@Override
		public void actionPerformed(ActionEvent p_e) {
			if(!Dessin.this.Sauvegarder()) {
				JOptionPane.showMessageDialog(Dessin.this, "La sauvegarde a échouée", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/**
	 * Classe gérant les appels d'exportation
	 */
	private class Exportation implements ActionListener{
		/**
		 * Permet d'appeler la fonction d'exportation et informe
		 * l'utilisateur si cette exportation échoue
		 */
		@Override
		public void actionPerformed(ActionEvent p_e) {
			if(!Dessin.this.Exporter()) {
				JOptionPane.showMessageDialog(Dessin.this, "L'exportation a échouée", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/**
	 * Classe gérant les appels à la suppression
	 */
	private class Suppression implements ActionListener{
		/**
		 * Appelle la méthode de suppression
		 */
		@Override
		public void actionPerformed(ActionEvent p_e) {
			Dessin.this.Supprimer();
		}
	}
	
	/**
	 * Classe gérant les entrées dans les champs de couleurs
	 * du dessin
	 */
	private class TextFilter extends DocumentFilter{
		private static final int MAX_LENGTH = 3;
		
		/**
		 * Verifie que l'insertion ne dépassent pas 3 charactères
		 */
	    public void insertString(FilterBypass fb, int offs, String str, AttributeSet a) throws BadLocationException {
	    	if ((fb.getDocument().getLength() + str.length()) <= MAX_LENGTH) {	
	    		try {
		        	Integer.parseInt(str);
					super.insertString(fb, offs, str, a);
					if (Integer.parseInt(fb.getDocument().getText(0, fb.getDocument().getLength())) > 255) {
						super.replace(fb, 0, 3, "255", a);
					}
					Dessin.this.UpdaterCouleurs();
		        }
		        catch (Exception e) {}
	    	}
	    }

	    /**
		 * Verifie que les remplacements ne dépassent pas 3 charactères
		 */
	    public void replace(FilterBypass fb, int offs, int length, String str, AttributeSet a) throws BadLocationException {
	        if ((fb.getDocument().getLength() + str.length() - length) <= MAX_LENGTH) {
	        	try {
		        	Integer.parseInt(str);
					super.insertString(fb, offs, str, a);
					if (Integer.parseInt(fb.getDocument().getText(0, fb.getDocument().getLength())) > 255) {
						super.replace(fb, 0, 3, "255", a);
					}
					Dessin.this.UpdaterCouleurs();
		        }
		        catch (Exception e) {}
	        }
	    }
	}
	
	/**
	 * Classe gérant les modification de la largeur des traits
	 */
	private class SpinnerGetter implements ChangeListener{
		/**
		 * Tient à jour les valeurs de largeur de trait et force
		 * l'espace de travail à se redessiner
		 */
		@Override
		public void stateChanged(ChangeEvent p_e) {
			Dessin.this.m_Trait = (int)Dessin.this.spn_TailleTrait.getValue();
			Dessin.this.m_EspaceTravail.ChangerTrait(Dessin.this.m_Trait);
			Dessin.this.m_EspaceTravail.Refresh(0,0,0,0);
		}
	}
	
	/**
	 * Classe gérant la transparence des formes du desssin
	 */
	private class Transparence implements ActionListener{
		/**
		 * Permet de garder la transparence à jour dans le dessin
		 */
		@Override
		public void actionPerformed(ActionEvent p_e) {
			Dessin.this.UpdaterCouleurs();
		}
	}
}
