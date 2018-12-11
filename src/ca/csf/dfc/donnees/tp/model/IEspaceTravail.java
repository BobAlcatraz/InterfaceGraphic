package ca.csf.dfc.donnees.tp.model;

import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import ca.csf.dfc.donnees.tp.controller.IForme;

public interface IEspaceTravail extends Comparable<IEspaceTravail>, Iterable<IForme>  {

	public void setTaille(int p_x, int p_y);
	
	public void supprimer();
	
	public void verifierClick(int p_x, int p_y);
	
	public void draw(IForme p_forme);
	
	public void ajouterMouseMotionListener(MouseMotionListener p_MML);
	
	public void ajouterMouseListener(MouseListener p_ML);
	
	public int getLargeur();
	
	public int getHauteur();
	
	public void Refresh(int p_x, int p_y, int p_hauteur, int p_largeur);
	
	public boolean hasSelection();
	
	public boolean dansSelection(int p_x, int p_y);
	
	public void Deselectionner();
	
	public void Vider();
	
	public void AdapterForme();
	
	public IEspaceTravail GetCopie();
	
	public void ChangerRemplissage(Color p_couleur);
	
	public void ChangerCouleur(Color p_couleur);
	
	public void ChangerTrait(int p_grosseur);
	
}
