package ca.csf.dfc.donnees.tp.model;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.swing.JPanel;

import ca.csf.dfc.donnees.tp.controller.*;

public class EspaceTravail extends JPanel implements IEspaceTravail  {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<IForme> m_ListForme = new ArrayList<IForme>();
	private IForme m_selectionne;
	private IForme m_point;
	/**
	 * Constructeur 
	 * @param p_x   la largeur
	 * @param p_y la hauteur
	 */
	public EspaceTravail(int p_x, int p_y) {
		super(); 
		this.setPreferredSize(new Dimension(p_x, p_y));
	}
	/**
	 * Constructeur copy un autre Espace
	 * @param p_ES   un EspaceDeTravail
	 */
	public EspaceTravail(EspaceTravail p_ES) {
		this.setPreferredSize(new Dimension(p_ES.getWidth(), p_ES.getHeight()));
		for (IForme forme : p_ES) {
			this.m_ListForme.add(forme.GetCopie());
		}
	}
	/**
	 * Définit la taille de L'EspaceDeTravail
	  *@param p_x   la largeur
	 * @param p_y la hauteur
	 */
	@Override
	public void setTaille(int p_x, int p_y) {
		this.setPreferredSize(new Dimension(p_x, p_y));
	}
	/**
	 * Supprime une IForme de la liste
	 */
	@Override
	public void supprimer() {
		boolean enRecherche = true;
		for (int x = 0; enRecherche && x < this.m_ListForme.size(); ++x) {
			if (this.m_ListForme.get(x) == this.m_selectionne) {
				this.m_ListForme.remove(x);
				enRecherche = false;
			}
		}
		this.m_selectionne = null;
		this.m_point = null;
		repaint();
	}
	/**
	 * Rend la IForme sélectionné a null et le point
	 */
	@Override
	public void Deselectionner() {
		this.m_selectionne = null;
		this.m_point = null;
	}
	/**
	 * vide la liste de IForme
	 */
	@Override
	public void Vider() {
		this.m_ListForme.clear();
	}
	/**
	 * vérifie si une forme est sélectionné et laffecte a m_selectionne
	 * @param p_x coordonnée x
	 * @param p_y coordonnée y
	 */
	@Override
	public void verifierClick(int p_x, int p_y) {
		this.m_selectionne = null;
		this.m_point = null;
		boolean trouve = false;
		for(int i = this.m_ListForme.size()-1; i >= 0 && trouve == false; i--) {
			if(this.m_ListForme.get(i).isclicked(p_x, p_y)) {
				this.m_selectionne = this.m_ListForme.get(i);
				this.m_point = new Rectangle(this.m_selectionne.GetX() + this.m_selectionne.GetLargeur(), 
						this.m_selectionne.GetY() + this.m_selectionne.GetHauteur(), 10, 10, 1, Color.white, Color.BLACK);
				trouve = true;
			}
		}
		this.repaint();
	}
	/**
	 * vérifie si le point est sélectionné
	 * @param p_x coordonnée x
	 * @param p_y coordonnée y
	 */
	public boolean dansPointModif(int p_x, int p_y) {
		boolean pointClick = this.m_point.isclicked(p_x, p_y);
		return pointClick;
	}
	
	/**
	 * modifie/deplace la forme sélectionné
	 * @param p_x coordonnée x
	 * @param p_y coordonnée y
	 * @param p_largeur mesure de largeur 
	 * @param p_hauteur mesure de hauteur
	 */
	public void Refresh(int p_x, int p_y, int p_largeur, int p_hauteur) {
		if (this.m_selectionne != null) {
			this.m_selectionne.Modifier(this.m_selectionne.GetLargeur() + p_largeur, this.m_selectionne.GetHauteur() + p_hauteur);
			this.m_selectionne.Deplacer(this.m_selectionne.GetX() +  p_x, this.m_selectionne.GetY() +  p_y);
			this.m_point.Deplacer(this.m_point.GetX() +  (p_x == 0 ? p_largeur : p_x), this.m_point.GetY() +  (p_y == 0 ? p_hauteur : p_y));
		}
		this.repaint();
	}
	/**
	 * renvoie la hauteur de l'EspaceTravail
	 * @return la largeur
	 */
	@Override
	public int getLargeur() {
	
		return this.getPreferredSize().width;
	}
	/**
	 * renvoie la largeur de l'EspaceTravail
	 * @return la hauteur
	 */
	@Override
	public int getHauteur() {
		
		return this.getPreferredSize().height;
	}
	/**
	 * vérifie si la forme n'est pas null
	 * @return vrai si la forme n'est pas null
	 */
	@Override
	public boolean hasSelection() {
		return this.m_selectionne != null;
	}
	/**
	 * vérifie si la forme n'est pas null
	 * @return vrai si la forme n'est pas null
	 */
	@Override
	public boolean dansSelection(int p_x, int p_y) {
		return this.m_selectionne.isclicked(p_x,p_y);
	}
	/**
	 * Ajoute une forme a la liste et appele la methode repaint
	 */
	@Override
	public void draw(IForme p_forme) {
		this.m_ListForme.add(p_forme);
		this.repaint();
	}
	/**
	 * adapte la forme sélectionné pour 2dGraphics
	 */
	@Override
	public void AdapterForme() {
		if (this.m_selectionne.GetHauteur() < 0 && this.m_selectionne.GetForme() != "ligne") {
			int y = this.m_selectionne.GetY();
			y += this.m_selectionne.GetHauteur();
			this.m_selectionne.Modifier(this.m_selectionne.GetLargeur(), this.m_selectionne.GetHauteur() * -1);
			this.m_selectionne.Deplacer(this.m_selectionne.GetX(), y);
		}
		if (this.m_selectionne.GetLargeur() < 0 && this.m_selectionne.GetForme() != "ligne") {
			int x = this.m_selectionne.GetX();
			x += this.m_selectionne.GetLargeur();
			this.m_selectionne.Modifier(this.m_selectionne.GetLargeur() * -1, this.m_selectionne.GetHauteur());
			this.m_selectionne.Deplacer(x, this.m_selectionne.GetY());
		}
		if (this.m_selectionne.GetLargeur() < 0) {
			this.m_selectionne.Deplacer(this.m_selectionne.GetX() + this.m_selectionne.GetLargeur(), this.m_selectionne.GetY() + this.m_selectionne.GetHauteur());
			this.m_selectionne.Modifier(this.m_selectionne.GetLargeur() * -1, this.m_selectionne.GetHauteur() * -1);
		}
		this.m_point.Deplacer(this.m_selectionne.GetX() + this.m_selectionne.GetLargeur(), this.m_selectionne.GetY() + this.m_selectionne.GetHauteur());
		this.repaint();
	}
	/**
	 * dessine les formes
	 * qui sont dans la liste de forme
	 * @param p_graph un Graphics
	 */
	@Override
	protected void paintComponent(Graphics p_graph) {
		Graphics2D graph = (Graphics2D)p_graph;
		graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graph.setColor(Color.WHITE);
		graph.fillRect(0, 0, this.getWidth(), this.getHeight());
		for (IForme forme : this.m_ListForme) {
			String sorteForme = forme.GetForme();
			if(forme.GetRemplissage() != null) {
				switch (sorteForme) {
				case "rectangle":
					graph.setColor(forme.GetRemplissage());
					graph.fillRect(forme.GetX(), forme.GetY(), forme.GetLargeur(), forme.GetHauteur());
					graph.setStroke(new BasicStroke( forme.GetTrait()));
					break;
				case "oval" :
					graph.fillOval(forme.GetX(), forme.GetY(), forme.GetLargeur(), forme.GetHauteur());
					graph.setColor(forme.GetRemplissage());
					graph.setStroke(new BasicStroke(forme.GetTrait()));
					break;
				}
					
			}
			switch(sorteForme) {
			case "rectangle" :
				graph.setColor(forme.GetCouleur());
				graph.setStroke(new BasicStroke(forme.GetTrait()));
				graph.drawRect(forme.GetX(), forme.GetY(), forme.GetLargeur(), forme.GetHauteur());
				break;
			case "oval" :
				graph.setColor(forme.GetCouleur());
				graph.setStroke(new BasicStroke(forme.GetTrait()));
				graph.drawOval(forme.GetX(),forme.GetY(), forme.GetLargeur(), forme.GetHauteur());
				break;
			case "ligne" :
				graph.setColor(forme.GetCouleur());
				graph.setStroke(new BasicStroke(forme.GetTrait()));
				graph.drawLine(forme.GetX(), forme.GetY(), forme.GetX() + forme.GetLargeur(), forme.GetY() + forme.GetHauteur());
				break;
				}
			if(this.m_selectionne != null) {
				IForme laForme = this.m_selectionne;
							float[] dashPattern = { 20, 10, 10, 10 };
							graph.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT,
					                 BasicStroke.JOIN_MITER, 10,
					                 dashPattern, 0));
							graph.setColor(Color.red);
							switch(laForme.GetForme()) {
								case "rectangle" :
									graph.drawRect(laForme.GetX()-2, laForme.GetY()-2, laForme.GetLargeur()+5, laForme.GetHauteur()+5);
									graph.setColor(this.m_point.GetRemplissage());
									graph.fillOval(this.m_point.GetX(), this.m_point.GetY(), this.m_point.GetLargeur(), this.m_point.GetHauteur());
									break;
								case "oval" :
									graph.drawRect(laForme.GetX()-2, laForme.GetY()-2, laForme.GetLargeur()+5, laForme.GetHauteur()+5);
									graph.setColor(this.m_point.GetRemplissage());
									graph.fillOval(this.m_point.GetX()+2, this.m_point.GetY()+2, this.m_point.GetLargeur(), this.m_point.GetHauteur());
									break;
								case "ligne" :
									int pointx2 = laForme.GetX() + laForme.GetLargeur();
									int pointy2 = laForme.GetY() + laForme.GetHauteur();
									graph.drawLine(laForme.GetX(), laForme.GetY(), pointx2, pointy2);
									graph.setColor(this.m_point.GetRemplissage());
									graph.fillOval(this.m_point.GetX(), this.m_point.GetY(), this.m_point.GetLargeur(), this.m_point.GetHauteur());
									break;
							}
					}
				
		}
	}
	
	/**
	 * change la couleur du remplissage de la forme selectionné
	 */
	public void ChangerRemplissage(Color p_couleur) {
		this.m_selectionne.ModifierRemplissage(p_couleur);
	}
	/**
	 * change la couleur de la forme selectionné
	 */
	public void ChangerCouleur(Color p_couleur) {
		this.m_selectionne.ModifierCouleur(p_couleur);
	}
	/**
	 * change le trait de la forme selectionné
	 */
	public void ChangerTrait(int p_grosseur) {
		this.m_selectionne.ModifierTrait(p_grosseur);
	}

	/**
	 * ajoute un mouse motion listener
	 */
	@Override
	public void ajouterMouseMotionListener(MouseMotionListener p_MML) {
		this.addMouseMotionListener(p_MML);
	}
	/**
	 * ajoute un mouse listener
	 */
	@Override
	public void ajouterMouseListener(MouseListener p_ML) {
		this.addMouseListener(p_ML);
	}
	/**
	 * pour comparer les EspaceDeTravail les une avec les autres
	 */
	@Override
	public int compareTo(IEspaceTravail o) {
		int verif= 0;
		int rendu = 0;
		for(IForme forme : o) {
			if(rendu >= this.m_ListForme.size() || !forme.equals(this.m_ListForme.get(rendu))) {
				verif++;
			}
				rendu++;
		}
		return verif;
	}
	/**
	 * vérifie si les EspaceDeTravail son identique 
	 */
	@Override
	public boolean equals(Object p_ET) {
		if (!(p_ET instanceof EspaceTravail) || this.compareTo((EspaceTravail)p_ET) != 0) {
			return false;
		}
		else {
			return true;
		}
	}
	
	@Override
	public Iterator<IForme> iterator() {
		
		return new EspaceIterator();
	}
	
	private class EspaceIterator implements Iterator<IForme>{

		private int m_cible;
		public EspaceIterator() {
			this.m_cible = 0;
		}
		
		@Override
		public boolean hasNext() {
			return this.m_cible < EspaceTravail.this.m_ListForme.size();
		}

		@Override
		public IForme next() {
			IForme forme = null;
			if(this.hasNext()) {
				forme = EspaceTravail.this.m_ListForme.get(this.m_cible);
				this.m_cible++;
			}
			else {
				throw new NoSuchElementException();
			}
			return forme;
		}
		
	}
	/**
	 * copie l'EspaceDeTravail
	 *@return une copie de l'EspaceDeTravail
	 */ 
	@Override
	public IEspaceTravail GetCopie() {
		return new EspaceTravail(this);
	}
}
