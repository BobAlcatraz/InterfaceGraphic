package ca.csf.dfc.donnees.tp.model;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import ca.csf.dfc.donnees.tp.controller.IForme;

public class EspaceTravail extends JPanel implements IEspaceTravail  {

	private ArrayList<IForme> m_ListForme;
	private Graphics2D m_Dessin;
	
	public EspaceTravail(int p_x, int p_y) {
		super();
		this.setSize(p_x,p_y);
	}
	
	@Override
	public void setTaille(int p_x, int p_y) {
		this.setSize(p_x, p_y);
		
	}

	@Override
	public void supprimer(IForme p_forme) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IForme verifierClick() {
		// TODO Auto-generated method stub
		return null;
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
		
	}


	@Override
	public void ajouterMouseMotionListener(MouseMotionListener p_MML) {
		this.addMouseMotionListener(p_MML);
	}

	@Override
	public void ajouterMouseListener(MouseListener p_ML) {
		this.addMouseListener(p_ML);
	}
}
