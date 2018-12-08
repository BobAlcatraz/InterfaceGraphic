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
	
	public EspaceTravail(int p_x, int p_y) {
		super();
		this.setPreferredSize(new Dimension(p_x, p_y));
	}
	
	public EspaceTravail(EspaceTravail p_ES) {
		this.setPreferredSize(new Dimension(p_ES.getWidth(), p_ES.getHeight()));
		for (IForme forme : p_ES) {
			this.m_ListForme.add(forme.GetCopie());
		}
	}
	
	@Override
	public void setTaille(int p_x, int p_y) {
		this.setPreferredSize(new Dimension(p_x, p_y));
	}

	@Override
	public void supprimer() {
		this.m_ListForme.remove(this.m_selectionne);
		this.m_selectionne = null;
		this.m_point = null;
	}
	
	@Override
	public void Deselectionner() {
		this.m_selectionne = null;
		this.m_point = null;
	}
	
	@Override
	public void Vider() {
		this.m_ListForme.clear();
	}

	@Override
	public void verifierClick(int p_x, int p_y) {
		this.m_selectionne = null;
		this.m_point = null;
		boolean trouve = false;
		for(int i = this.m_ListForme.size()-1; i >= 0 && trouve == false; i--) {
			if(this.m_ListForme.get(i).isclicked(p_x, p_y)) {
				this.m_selectionne = this.m_ListForme.get(i);
				this.m_point = new Rectangle(this.m_selectionne.GetX() + this.m_selectionne.GetLargeur(), 
						this.m_selectionne.GetY() + this.m_selectionne.GetHauteur(), 13, 13, 1, Color.white, Color.BLACK);
				trouve = true;
			}
		}
		this.repaint();
	}
	
	public boolean dansPointModif(int p_x, int p_y) {
		boolean pointClick = this.m_point.isclicked(p_x, p_y);
		return pointClick;
	}
	
	
	public void Refresh(int p_x, int p_y, int p_largeur, int p_hauteur) {
		if (this.m_selectionne != null) {
			this.m_selectionne.Modifier(this.m_selectionne.GetLargeur() + p_largeur, this.m_selectionne.GetHauteur() + p_hauteur);
			this.m_selectionne.Deplacer(this.m_selectionne.GetX() +  p_x, this.m_selectionne.GetY() +  p_y);
			this.m_point.Deplacer(this.m_point.GetX() +  (p_x == 0 ? p_largeur : p_x),this.m_point.GetY() +  (p_y == 0 ? p_hauteur : p_y));
		}
		this.repaint();
	}
	
	@Override
	public int getLargeur() {
	
		return this.getPreferredSize().width;
	}

	@Override
	public int getHauteur() {
		
		return this.getPreferredSize().height;
	}
	
	@Override
	public boolean hasSelection() {
		return this.m_selectionne != null;
	}
	
	@Override
	public boolean dansSelection(int p_x, int p_y) {
		return this.m_selectionne.isclicked(p_x,p_y);
	}
	
	@Override
	public void draw(IForme p_forme) {
		this.m_ListForme.add(p_forme);
		this.repaint();
	}
	
	@Override
	public void AdapterForme() {
		if (this.m_selectionne.GetHauteur() < 0 && this.m_selectionne.GetForme() != "ligne") {
			int y = this.m_selectionne.GetY();
			y += this.m_selectionne.GetHauteur();
			this.m_selectionne.Modifier(this.m_selectionne.GetLargeur(), this.m_selectionne.GetHauteur() * -1);
			this.m_selectionne.Deplacer(this.m_selectionne.GetX(), y);
		}
		if (this.m_selectionne.GetLargeur() < 0) {
			int x = this.m_selectionne.GetX();
			x += this.m_selectionne.GetLargeur();
			this.m_selectionne.Modifier(this.m_selectionne.GetLargeur() * -1, this.m_selectionne.GetHauteur());
			this.m_selectionne.Deplacer(x, this.m_selectionne.GetY());
		}
		this.m_point.Deplacer(this.m_selectionne.GetX() + this.m_selectionne.GetLargeur(), this.m_selectionne.GetY() + this.m_selectionne.GetHauteur());
		this.repaint();
	}
	
	@Override
	protected void paintComponent(Graphics p_graph) {
		Graphics2D graph = (Graphics2D)p_graph;
		graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graph.setColor(Color.WHITE);
		graph.fillRect(0, 0, this.getWidth(), this.getHeight());
		for (IForme forme : this.m_ListForme) {
			String sorteForme = forme.GetForme();
			
			if(forme.GetRemplissage() == null) {
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
				int pointx2 = forme.GetX() + forme.GetLargeur();
				int pointy2 = forme.GetY() + forme.GetHauteur();
				graph.setColor(forme.GetCouleur());
				graph.setStroke(new BasicStroke(forme.GetTrait()));
				graph.drawLine(forme.GetX(), forme.GetY(), pointx2, pointy2);
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
						int pointx2 = forme.GetX() + forme.GetLargeur();
						int pointy2 = forme.GetY() + forme.GetHauteur();
						graph.drawLine(this.m_selectionne.GetX(), this.m_selectionne.GetY(), pointx2, pointy2);
						graph.setColor(this.m_point.GetRemplissage());
						graph.fillOval(this.m_point.GetX(), this.m_point.GetY(), this.m_point.GetLargeur(), this.m_point.GetHauteur());
						break;
					}
				}
			}
			else {
				switch (sorteForme) {
				case "rectangle":
					graph.fillRect(forme.GetX(), forme.GetY(), forme.GetLargeur(), forme.GetHauteur());
					graph.setColor(forme.GetRemplissage());
					graph.setStroke(new BasicStroke( forme.GetTrait()));
					break;
				case "oval" :
					
					break;
				case "ligne" :
					
					break;
				}
				
			}
		}
	}


	@Override
	public void ajouterMouseMotionListener(MouseMotionListener p_MML) {
		this.addMouseMotionListener(p_MML);
	}

	@Override
	public void ajouterMouseListener(MouseListener p_ML) {
		this.addMouseListener(p_ML);
	}

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

	@Override
	public IEspaceTravail GetCopie() {
		return new EspaceTravail(this);
	}
}
