package ca.csf.dfc.donnees.tp.controller;

import java.awt.*;

public interface IForme extends Comparable<IForme>{

	
	public Boolean isclicked(int p_X, int p_Y);	
	public void Deplacer(int p_X, int p_Y);
	public void Modifier(int p_Largeur, int p_Hauteur);
	public String GetForme();
	public IForme GetCopie();
	
	public int GetX();
	public int GetY();
	public int GetHauteur();
	public int GetLargeur();
	public int GetTrait();
	public Color GetCouleur();
	public Color GetRemplissage();
	
	
	
	
}
