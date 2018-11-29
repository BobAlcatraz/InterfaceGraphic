package ca.csf.dfc.donnees.tp.model;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import ca.csf.dfc.donnees.tp.controller.IForme;

public class EspaceTravail extends JPanel implements IEspaceTravail  {

	private ArrayList<IForme> m_ListForme;
	
	
	public EspaceTravail(int p_x, int p_y) {
		super();
		this.setSize(p_x,p_y);
		this.setBackground(Color.WHITE);
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
