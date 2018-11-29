package ca.csf.dfc.donnees.tp.controller;

import java.awt.*;

abstract class Forme implements IForme {
	
	private int m_X; 
	private int m_Y;	
	private int m_Hauteur;	
	private int m_Largeur;	
	private int m_Trait;	
	private Color m_Couleur;	
	private Color m_Remplissage;
	
	protected abstract void setX(int p_X);
	protected abstract void setY(int p_Y);
	protected abstract void setHauteur(int p_Hauteur);
	protected abstract void setLargeur(int p_Largeur);
	protected abstract void setTrait(int p_Trait);
	protected abstract void setCouleur(Color p_Couleur);
	protected abstract void setRemplissage(Color p_Couleur);
}
