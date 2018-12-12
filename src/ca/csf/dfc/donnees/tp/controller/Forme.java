package ca.csf.dfc.donnees.tp.controller;

import java.awt.*;


//Classe abstraite définissant les méthodes d'une forme.
abstract class Forme implements IForme {
	
	//La distance 'x' de la coordonnée de la forme.
	protected int m_X; 
	
	//La distance 'y' de la coordonnée de la forme.
	protected int m_Y;
	
	//La hauteur de la forme.
	protected int m_Hauteur;
	
	//La largeur de la forme.
	protected int m_Largeur;	
	
	//La largeur du trait de la forme.
	protected int m_Trait;	
	
	//La couleur de la forme.
	protected Color m_Couleur;	
	
	//La couleur du remplissage de la forme.
	protected Color m_Remplissage;
	
	/**
	 * Permet de modifier la distance 'x' de la coordonnée de la forme.
	 * @param p_X La nouvelle distance 'x' de la coordonnée de la forme.
	 */
	protected abstract void setX(int p_X);
	
	/**
	 * Permet de modifier la distance 'y, de la coordonnée de la forme.
	 * @param p_Y La nouvelle distance 'y' de la coordonnée de la forme.
	 */
	protected abstract void setY(int p_Y);
	
	/**
	 * Permet de modifier la hauteur de la forme.
	 * @param p_Hauteur La nouvelle hauteur de la forme.
	 */
	protected abstract void setHauteur(int p_Hauteur);
	
	/**
	 * Permet de modifier la largeur de la forme.
	 * @param p_Largeur La nouvelle largeur de la forme.
	 */
	protected abstract void setLargeur(int p_Largeur);
	
	/**
	 * Permet de modifier la largeur du trait de la forme.
	 * @param p_Trait La nouvelle largeur du trait de la forme.
	 */
	protected abstract void setTrait(int p_Trait);
	
	/**
	 * Permet de modifier la couleur de la forme.
	 * @param p_Couleur La nouvelle couleur de la forme.
	 */
	protected abstract void setCouleur(Color p_Couleur);
	
	/**
	 * Permet de modifier la couleur de remplissage de la forme.
	 * @param p_Remplissage La nouvelle couleur de remplissage de la forme.
	 */
	protected abstract void setRemplissage(Color p_Remplissage);
	
	
	/**
	 * Vérifie si deux instances possèdent les même valeurs.
	 * @param p_O L'instance à comparer.
	 */
	@Override
	public boolean equals(Object p_O) {
		if(!(p_O instanceof IForme) || this.compareTo((IForme)p_O) != 0) {
			return false;
		}
		else {
			return true;
		}
	}
	

}
