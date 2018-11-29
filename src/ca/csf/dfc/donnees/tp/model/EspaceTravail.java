package ca.csf.dfc.donnees.tp.model;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.swing.JPanel;

import ca.csf.dfc.donnees.tp.controller.IForme;

public class EspaceTravail extends JPanel implements IEspaceTravail  {

	private ArrayList<IForme> m_ListForme;
	private IForme m_selectionne;
	
	public EspaceTravail(int p_x, int p_y) {
		super();
		this.setSize(p_x,p_y);
		this.m_ListForme = new ArrayList<IForme>();
	}
	
	@Override
	public void setTaille(int p_x, int p_y) {
		this.setSize(p_x, p_y);
		
	}

	@Override
	public void supprimer(IForme p_forme) {
		if(this.m_ListForme.contains(p_forme)) {
			this.m_ListForme.remove(p_forme);
		}
	}

	@Override
	public void verifierClick(int p_x, int p_y) {
	
		boolean trouve = false;
		for(int i = this.m_ListForme.size()-1; i >= 0; i--) {
			if(this.m_ListForme.get(i).GetX() == p_x && this.m_ListForme.get(i).GetY() == p_y && trouve == false) {
				this.m_selectionne = this.m_ListForme.get(i);
				this.m_ListForme.add(this.m_selectionne);
				trouve = true;
			}
		}
	}
	
	
	@Override
	public void draw(IForme p_forme) {
		this.m_ListForme.add(p_forme);
		this.repaint();
	}
	@Override
	protected void paintComponent(Graphics p_graph) {
		Graphics2D graph = (Graphics2D)p_graph;
		for (IForme forme : this.m_ListForme) {
			String sorteForme = forme.GetForme();
			if(forme != this.m_selectionne) {
			switch(sorteForme) {
			case "rectangle" :
				graph.setColor(forme.GetCouleur());
				graph.setStroke(new BasicStroke(forme.GetTrait()));
				graph.drawRect(forme.GetX(), forme.GetY(), forme.GetLarger(), forme.GetHauteur());
				break;
			case "oval" :
				graph.setColor(forme.GetCouleur());
				graph.setStroke(new BasicStroke(forme.GetTrait()));
				graph.drawOval(forme.GetX(),forme.GetY(), forme.GetLarger(), forme.GetHauteur());
				break;
			case "ligne" :
				int pointx2 = forme.GetX() + forme.GetLarger();
				int pointy2 = forme.GetY() + forme.GetHauteur();
				graph.setColor(forme.GetCouleur());
				graph.setStroke(new BasicStroke(forme.GetTrait()));
				graph.drawLine(forme.GetX(), forme.GetY(), pointx2, pointy2);
				break;
				}
			}
			else {
				IForme laForme = this.m_selectionne;
				float[] dashPattern = { 30, 10, 10, 10 };
				graph.setStroke(new BasicStroke(8, BasicStroke.CAP_BUTT,
		                 BasicStroke.JOIN_MITER, 10,
		                 dashPattern, 0));
				graph.setColor(Color.red);
				switch(laForme.GetForme()) {
				case "rectangle" :
					graph.drawRect(laForme.GetX(), laForme.GetY(), laForme.GetLarger(), laForme.GetHauteur());
					break;
				case "oval" :
					graph.drawOval(laForme.GetX(), laForme.GetY(), laForme.GetLarger(), laForme.GetHauteur());
					break;
				case "ligne" :
					int pointx2 = forme.GetX() + forme.GetLarger();
					int pointy2 = forme.GetY() + forme.GetHauteur();
					graph.drawLine(forme.GetX(), forme.GetY(), pointx2, pointy2);
					break;
				}
				this.supprimer(this.m_selectionne);
				this.m_selectionne = null;
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
			if(forme != this.m_ListForme.get(rendu)) {
				verif++;
			}
				rendu++;
		}
		return verif;
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
	public int getLargeur() {
		
		return this.getWidth();
	}

	@Override
	public int getHauteur() {
		
		return this.getHeight();
	}
	
}
